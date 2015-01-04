#! /bin/sh
# /etc/init.d/blah
#

# Some things that run always
touch /var/lock/blah

# Carry out specific functions when asked to by the system
case "$1" in
  start)
    echo "Starting KPP BS Validation "
    export TSLIB_TSDEVICE=/dev/input/touchscreen0
    export TSLIB_TSEVENTTYPE=INPUT
    export TSLIB_CONFFILE=/etc/ts.conf
    export TSLIB_CALIBFILE=/etc/pointercal
    export TSLIB_FBDEVICE=/dev/fb1

    /home/root/KPPBSValidation/KPPBSValidation -platform linuxfb:fb=/dev/fb1 -plugin tslib &

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


