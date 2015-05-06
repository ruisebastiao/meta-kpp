SUMMARY = "Image for Bs Validation Systems"

LICENSE = "GPL-3.0"

include kpp-image-base.bb

include kpp-image-qt5.bb


MACHINE_EXTRA_RRECOMMENDS = "kernel-modules eee-acpi-scripts"

IMAGE_FEATURES += " splash "

SPLASH = "psplash-kpp"

PR = "r1"


EXTRA_IMAGE_FEATURES += " tools-sdk "

#packagegroup-qt5-machine-related

IMAGE_INSTALL += "\
		packagegroup-qt5-base \
	        linux-firmware-ralink \
		linux-firmware-rtl8192cu \
		git \
		gdb \
		gdbserver \
		nano \
		gstreamer \
		gst-meta-video \
		gst-plugins-base-app \
    		gst-plugins-base \
		gst-plugins-good \
		gst-plugins-good-rtsp \
		gst-plugins-good-udp \
		gst-plugins-good-rtpmanager \
		gst-plugins-good-rtp \
		gst-plugins-good-video4linux2 \
		openssh-sftp-server \
		freetype dbus \
		i2c-tools dtc coreutils \	       
		connman wireless-tools wpa-supplicant \
		tslib-conf tslib-tests tslib-calibrate tslib strace \
		jpeg libdrm \
		qtserialport \    	
	    	qtwebsockets \
		kppbsvalidation \
		connman-client rfkill usbutils \
		"


