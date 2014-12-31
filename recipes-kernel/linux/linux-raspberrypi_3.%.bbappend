FILESEXTRAPATHS_prepend := "${THISDIR}/linux-raspberrypi:"
COMPATIBLE_MACHINE = "raspberrypi"
COMPATIBLE_MACHINE_raspberrypi = "raspberrypi"

inherit kernel

require recipes-kernel/linux/setup-defconfig.inc

#require recipes-kernel/linux/linux-dtb.inc

#KERNEL_DEFCONFIG="defconfig"

MACHINE_EXTRA_RRECOMMENDS = "kernel-modules"

# CMDLINE for raspberrypi
CMDLINE_append_raspberrypi = " fbtft_device.name=adafruitts fbtft_device.rotate=90 fbtft_device.debug=0 fbtft_device.verbose=0 fbcon=map:10 fbcon=font:VGA8x8 "


#KERNEL_DEVICETREE_raspberrypi="bcm2708-rpi-b.dtb "

BRANCH = "rpi-3.14.y"

SRCREV = "efa58ca2948bcfeb0db62e3ba863490b37ae25fd"

KERNEL_GIT_URI = "git://~/linux-rpi"


SRC_URI = "${KERNEL_GIT_URI};protocol=file;branch=${BRANCH} \
	   file://defconfig-rpi \
	   file://addfbtft.patch \
          "

