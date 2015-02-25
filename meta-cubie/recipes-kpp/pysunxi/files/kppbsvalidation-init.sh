#! /bin/sh
# /etc/init.d/kppbsvalidation-init.sh
#
set -e

# Carry out specific functions when asked to by the system
case "$1" in
  start)
    if [ "$(pidof KPPBSValidation)" ]
    then
      echo "KPP BS Validation is running"
    else
      echo "Starting KPP BS Validation "
      
      fb_dev=/dev/fbx
      
      export TSLIB_TSDEVICE=/dev/input/event0
      export TSLIB_CONFFILE=/etc/ts.conf
      CALIBFILE=/etc/pointercal
      export TSLIB_CALIBFILE=$CALIBFILE
      export TSLIB_FBDEVICE=$fb_dev

      

      COUNTER=0
      while [  $COUNTER -lt 15 ]; do

      if [ -e $fb_dev ];
      then
        break;
      fi
      sleep 1
      let COUNTER=COUNTER+1
     done


      if [ -f $CALIBFILE ];
      then
         :
      else
        echo "Calibration file not found ($CALIBFILE)... Executing calibration"
        ts_calibrate
     fi

      /home/root/KPPBSValidation/KPPBSValidation -platform linuxfb:fb=$fb_dev -plugin tslib &
    fi



    ;;
  stop)
    echo "Stopping KPP BS Validation "

    ;;
  *)
    echo "Usage: /etc/init.d/kppbsvalidation-init.sh {start|stop}"
    exit 1
    ;;
esac

exit 0

