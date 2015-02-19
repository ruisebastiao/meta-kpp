SECTION = "kernel"
DESCRIPTION = "beagleboard.org Linux kernel for beaglebone"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit kernel siteinfo
require recipes-kernel/linux/linux-yocto.inc

DEPENDS += "lzop-native"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
#INHIBIT_PACKAGE_STRIP = "1"


FILESEXTRAPATHS_prepend := "${THISDIR}/linux-3.14:"

# Pull in the devicetree files into the rootfs
RDEPENDS_kernel-base += "kernel-devicetree"

KERNEL_IMAGETYPE = "zImage"


# Add a run-time dependency for the PM firmware to be installed
# on the target file system.
#RDEPENDS_kernel-base += "am33x-cm3"

LINUX_KERNEL_TYPE = "KPP"
LINUX_VERSION_EXTENSION = "-${LINUX_KERNEL_TYPE}"

# for the specific cubieboard machine.
KERNEL_DEVICETREE_cubieboard = "sun4i-a10-cubieboard.dtb"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

COMPATIBLE_MACHINE = "cubieboard"


S = "${WORKDIR}/git"

BRANCH = "sunxi-next"

SRCREV = "77f18db7e075a879236d88dbc22090b76d9fe275"
PV = "3.19-rc6"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "d+gitr${SRCPV}"
PR = "${MACHINE_KERNEL_PR}"



KERNEL_GIT_URI = "git://${HOME}/linux-sunxi/"



SRC_URI = "${KERNEL_GIT_URI};protocol=file;branch=${BRANCH} \	   	    	   
	    file://defconfig \	    
          "
