SECTION = "kernel"
DESCRIPTION = "Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

DEPENDS += "lzop-native"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

LINUX_KERNEL_TYPE = "beagleboard.org"
LINUX_VERSION_EXTENSION = "-${LINUX_KERNEL_TYPE}"

FILESEXTRAPATHS_prepend := "${THISDIR}/beagleboard-linux-4.0:"

# Pull in the devicetree files into the rootfs
RDEPENDS_kernel-base += "kernel-devicetree"

# Add a run-time dependency for the PM firmware to be installed
# on the target file system.
RDEPENDS_kernel-base += "am33x-cm3"

# Default is to package all dtb files for ti33x devices unless building
# for the specific beaglebone machine.
KERNEL_DEVICETREE_beaglebone = "am335x-boneblack.dtb am335x-boneblack-4dcape-43t.dtb"

#am335x-boneblack-4dcape-43t.dtb

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|omap3|beaglebone"


S = "${WORKDIR}/git"

BRANCH = "v4.0-rc7"
	

SRCREV = "f22e6e847115abc3a0e2ad7bb18d243d42275af1"
PV = "4.0"


# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "d+gitr${SRCPV}"
PR = "${MACHINE_KERNEL_PR}"


do_kernel_configme_prepend(){
  cp  ${WORKDIR}/firmware/am335x-pm-firmware.elf ${S}/firmware/
  cp  ${WORKDIR}/firmware/am335x-pm-firmware.bin ${S}/firmware/
  cp  ${WORKDIR}/firmware/am335x-bone-scale-data.bin ${S}/firmware/
  cp  ${WORKDIR}/firmware/am335x-evm-scale-data.bin ${S}/firmware/
  cp  ${WORKDIR}/firmware/am43x-evm-scale-data.bin ${S}/firmware/
	
 
}

KERNEL_GIT_URI = "git://${HOME}/linux/"




SRC_URI = "${KERNEL_GIT_URI};protocol=file;branch=${BRANCH} \	   	    
	file://defconfig \	    
	file://firmware/am335x-pm-firmware.elf \
	file://firmware/am335x-pm-firmware.bin \
	file://firmware/am335x-bone-scale-data.bin \
	file://firmware/am335x-evm-scale-data.bin \
	file://firmware/am43x-evm-scale-data.bin \
	file://0001-dma-cppi41-add-missing-bitfields.patch \
	file://0002-arm-Export-cache-flush-management-symbols-when-MULTI_CACHE.patch \
	file://0003-fix-sgx-drm.patch \
	file://0004-add-dts-files.patch \
	file://0005-add-omap-sgx-header.patch \
	file://0006-add_sgx_support_in_dts.patch \
	file://0007-ARM-OMAP2-Use-pdata-quirks-for-sgx-deassert_hardrese.patch \
          "


