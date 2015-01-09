FILESEXTRAPATHS_prepend := "${THISDIR}/linux-raspberrypi:"

inherit kernel

require recipes-kernel/linux/setup-defconfig.inc

# CMDLINE for raspberrypi
CMDLINE_append_raspberrypi = " fbcon=map:10 fbcon=font:VGA8x8 "


BRANCH = "rpi-3.14.y"

SRCREV = "efa58ca2948bcfeb0db62e3ba863490b37ae25fd"

KERNEL_GIT_URI = "git://~/linux-kernels/linux-rpi"


SRC_URI = "${KERNEL_GIT_URI};protocol=file;branch=${BRANCH} \
	   file://defconfig-rpi \
	   file://addfbtft.patch \	  
          "
PR = "r03"

