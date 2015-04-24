#!/bin/sh
set -e

FILE=EXCLUDE_FILE

NANDDEV=$1

echo "Setting up excludes"

if [ -f $FILE ];
then
   :
else
   /bin/cat <<EOM >$FILE
/dev/*
/proc/*
/sys/*
/media/*
/mnt/*
/run/*
/tmp/*
/var/volatile/*

EOM
fi



echo "Mounting NAND partition"
mount=nand_p2

if grep -qs "$mount" /proc/mounts; then
  umount $nand_p2
else
  :
fi

rm -rf nand_p2
mkdir nand_p2

mount $NANDDEV nand_p2/

echo "executing rsinc..."

rsync -avc --exclude-from=EXCLUDE_FILE / /mnt/nand_p2

