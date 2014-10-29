#! /bin/sh

function ask_override(){
while true; do
    read -p "Destination exists, override (y/n)?" yn
    case $yn in
        [Yy]* ) break;;
        [Nn]* ) break;;
        * ) echo "Please answer yes or no.";;
    esac
done
}

function do_copyfiles()
{

if [ ! -d "$DIRDEST" ]; then
  mkdir $DIRDEST
fi

echo "Copying $FILENAME to $DIRDEST$FILENAME..."

if [ -f $FILEPATH ];
then

   if [ "$FILEDEST" == "" ];then
	tempdest=$DIRDEST$FILENAME
   else
	tempdest=$DIRDEST$FILEDEST
   fi



  if [ -f $tempdest ];
  then
    ask_override
    if [ "$yn" == "yes" ]; then	 
	cp $FILEPATH $tempdest
    fi 
   
  else

	cp $FILEPATH $tempdest
   
  fi

else
   echo "File $FILEPATH does not exists"
   exit 0
fi
}

function do_extractfiles()
{
if [ -f $FILEPATH ];
then
echo "Extracting $FILEPATH ..."
tar -xf $FILEPATH  -C $DIRDEST ; sync
else
   echo "$FILEPATH does not exists"
   exit 0
fi


}

if [ $# -ne 3 ]; then
  echo "Usage: $0 <yocto deploy location> <target boot> <target rootfs>"
  exit 1;
fi

IMGLOC=$1
BOOT=$2
ROOTFS=$3

FILENAME="MLO"
FILEPATH="$IMGLOC/$FILENAME"
DIRDEST="$BOOT/"
FILEDEST=""
do_copyfiles

FILENAME="u-boot.img"
FILEPATH="$IMGLOC/$FILENAME"
DIRDEST="$BOOT/"
FILEDEST=""
do_copyfiles


FILENAME="core-image-sato-beaglebone.tar.gz"
FILEPATH="$IMGLOC/$FILENAME"
DIRDEST="$ROOTFS/"
FILEDEST=""

do_extractfiles
bash -c 'echo "musb_am335x" > $DIRDEST/etc/modules'


FILENAME="zImage"
FILEPATH="$IMGLOC/$FILENAME"
DIRDEST="$BOOT/"
FILEDEST=""
do_copyfiles

FILENAME="zImage-am335x-boneblack.dtb"
FILEPATH="$IMGLOC/$FILENAME"
DIRDEST="$BOOT/dtbs/"
FILEDEST="am335x-boneblack.dtb"
do_copyfiles


FILENAME="uEnv.txt"
FILEPATH="$FILENAME"
DIRDEST="$BOOT/"

echo "Copy $FILENAME ..."

cp $FILEPATH $DIRDEST

sync

echo "Done..."

