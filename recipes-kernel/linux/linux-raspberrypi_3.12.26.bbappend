FILESEXTRAPATHS_prepend := "${THISDIR}/linux-raspberrypi:"


inherit kernel
require recipes-kernel/linux/setup-defconfig.inc

#require recipes-kernel/linux/linux-dtb.inc


MACHINE_EXTRA_RRECOMMENDS = "kernel-modules"

#KERNEL_DEVICETREE_raspberrypi="bcm2708-rpi-b.dtb "

BRANCH = "rpi-3.14.y"

SRCREV = "efa58ca2948bcfeb0db62e3ba863490b37ae25fd"

KERNEL_GIT_URI = "git://~/linux-rpi"

do_configure_prepend() {
}


SRC_URI = "${KERNEL_GIT_URI};protocol=file;branch=${BRANCH} \
	   file://defconfig \
	   file://addfbtft.patch \
          "


