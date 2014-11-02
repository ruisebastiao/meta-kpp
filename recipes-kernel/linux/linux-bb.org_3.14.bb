SECTION = "kernel"
DESCRIPTION = "beagleboard.org Linux kernel for beaglebone"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

DEPENDS += "lzop-native"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

LINUX_KERNEL_TYPE = "beagleboard.org"
LINUX_VERSION_EXTENSION = "-${LINUX_KERNEL_TYPE}"

FILESEXTRAPATHS_prepend := "${THISDIR}/linux-bb.org-3.14:"

# Pull in the devicetree files into the rootfs
RDEPENDS_kernel-base += "kernel-devicetree"

# Add a run-time dependency for the PM firmware to be installed
# on the target file system.
RDEPENDS_kernel-base += "am33x-cm3"

# Default is to package all dtb files for ti33x devices unless building
# for the specific beaglebone machine.
KERNEL_DEVICETREE_beaglebone = "am335x-bone.dtb am335x-boneblack.dtb"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

COMPATIBLE_MACHINE = "beaglebone"

#FILES_${PN}-dbg += "/lib/firmware/.debug"

S = "${WORKDIR}/git"

BRANCH = "3.14.22-ti-r33"

# Corresponds to tag 3.14.22-ti-33
SRCREV = "1c3097fb9db07c49d4a497729c61ed4947ecad00"
PV = "3.14.22"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "d+gitr${SRCPV}"
PR = "${MACHINE_KERNEL_PR}"


KERNEL_GIT_URI = "git:///home/rui/poky/beagleboard-linux"

SRC_URI = "${KERNEL_GIT_URI};protocol=file;branch=${BRANCH} \
           file://defconfig \
          "

