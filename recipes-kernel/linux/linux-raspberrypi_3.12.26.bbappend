FILESEXTRAPATHS_prepend := "${THISDIR}/linux-raspberrypi:"

BRANCH = "rpi-3.18.y"

SRCREV = "ba25740fc3418f25099abc0c3049fb3101a27367"

KERNEL_GIT_URI = "git:///home/automacao/yocto/linux-rpi"


SRC_URI = "${KERNEL_GIT_URI};protocol=file;branch=${BRANCH} \
	    file://defconfig \
          "


