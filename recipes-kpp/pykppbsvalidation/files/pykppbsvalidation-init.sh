#! /bin/sh
# /etc/init.d/pykppbsvalidation-init.sh
#
set -e

# Carry out specific functions when asked to by the system
case "$1" in
  start)
    if [ "$( pidof python /home/root/pykppbsvalidation.py)" ]
    then
      echo "KPP Python BS Validation is running"
    else
      echo "Starting KPP Python BS Validation "  
      python /home/root/pykppbsvalidation.py&
    fi



    ;;
  stop)
    echo "Stopping KPP Python BS Validation "
    if [ "$( pidof python /home/root/pykppbsvalidation.py)" ]
    then
      pid=$( pidof python /home/root/pykppbsvalidation.py)
      kill -s TERM $pid
    else
     :
    fi
    
    ;;
  *)
    echo "Usage: /etc/init.d/pykppbsvalidation-init.sh {start|stop}"
    exit 1
    ;;
esac

exit 0

