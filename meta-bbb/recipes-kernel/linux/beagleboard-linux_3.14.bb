SECTION = "kernel"
DESCRIPTION = "Linux kernel for cubieboard"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

DEPENDS += "lzop-native"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

LINUX_KERNEL_TYPE = "beagleboard.org"
LINUX_VERSION_EXTENSION = "-${LINUX_KERNEL_TYPE}"

FILESEXTRAPATHS_prepend := "${THISDIR}/beagleboard-linux-3.14:"

# Pull in the devicetree files into the rootfs
RDEPENDS_kernel-base += "kernel-devicetree"

# Add a run-time dependency for the PM firmware to be installed
# on the target file system.
RDEPENDS_kernel-base += "am33x-cm3"

# Default is to package all dtb files for ti33x devices unless building
# for the specific beaglebone machine.
KERNEL_DEVICETREE_beaglebone = "am335x-bone.dtb am335x-boneblack.dtb am335x-boneblack-bb-view-43.dtb am335x-boneblack-4dcape-43t.dtb"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

#COMPATIBLE_MACHINE = "beaglebone"
COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|omap3|beaglebone"

#FILES_${PN}-dbg += "/lib/firmware/.debug"

S = "${WORKDIR}/git"

BRANCH = "3.14"

# Corresponds to tag  3.14.29-ti-r55				
SRCREV = "ee2a2099346924329953321c9c80e88841b0ddd3"
PV = "3.14.55"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "d+gitr${SRCPV}"
PR = "${MACHINE_KERNEL_PR}"

do_patch_append(){
  cp  ${WORKDIR}/am335x-bone-ti-tscadc-4-wire.dtsi ${S}/arch/arm/boot/dts/
#cp  ${WORKDIR}/am335x-bone-panel-480x272-bgrx_16bpp.dtsi ${S}/arch/arm/boot/dts/
 
}


KERNEL_GIT_URI = "git://${HOME}/beagleboard-linux/"



SRC_URI = "${KERNEL_GIT_URI};protocol=file;branch=${BRANCH} \	   	    
	file://defconfig \	    
	file://am335x-bone-ti-tscadc-4-wire.dtsi \
	file://0000-Handle-ISOCH-differently-and-not-use-the-hrtimer.patch \
          "
#    
#	    file://am335x-bone-panel-480x272-bgrx_16bpp.dtsi
#	    file://0002-fix_bbview43.patch 
          
#file://0003-add_pmic_interrupt.patch 
