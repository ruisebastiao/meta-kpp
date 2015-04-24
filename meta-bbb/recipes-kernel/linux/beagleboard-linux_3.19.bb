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

FILESEXTRAPATHS_prepend := "${THISDIR}/beagleboard-linux-3.19:"

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

BRANCH = "v3.19"
# Corresponds to tag  v3.19				

SRCREV = "bfa76d49576599a4b9f9b7a71f23d73d6dcff735"
PV = "3.19"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "d+gitr${SRCPV}"
PR = "${MACHINE_KERNEL_PR}"


do_install_prepend(){
  cp  ${WORKDIR}/am335x-bone-ti-tscadc-4-wire.dtsi ${S}/arch/arm/boot/dts/
  cp  ${WORKDIR}/am335x-boneblack-4dcape-43t.dts ${S}/arch/arm/boot/dts/
  cp  ${WORKDIR}/am335x-bone-panel-480x272.dtsi ${S}/arch/arm/boot/dts/
  cp  ${WORKDIR}/am335x-bone-keymap1.dtsi ${S}/arch/arm/boot/dts/
  cp  ${WORKDIR}/am335x-bone-led-gpio1-28.dtsi ${S}/arch/arm/boot/dts/
  cp  ${WORKDIR}/am335x-bone-common-pinmux.dtsi ${S}/arch/arm/boot/dts/
  cp  ${WORKDIR}/am335x-bone-bl-gpio1-18.dtsi ${S}/arch/arm/boot/dts/
  cp  ${WORKDIR}/am335x-boneblack-emmc.dtsi ${S}/arch/arm/boot/dts/
  cp  ${WORKDIR}/am335x-bone-4dcape-43t.dtsi ${S}/arch/arm/boot/dts/
  cp  ${WORKDIR}/am33xx.dtsi ${S}/arch/arm/boot/dts/
  cp  ${WORKDIR}/sgx-omap.h ${S}/include/linux/platform_data/	
 
}

do_kernel_configme_append(){
  cp  ${WORKDIR}/am335x-pm-firmware.elf ${S}/firmware/
  cp  ${WORKDIR}/am335x-pm-firmware.bin ${S}/firmware/
  cp  ${WORKDIR}/am335x-bone-scale-data.bin ${S}/firmware/
  cp  ${WORKDIR}/am335x-evm-scale-data.bin ${S}/firmware/
  cp  ${WORKDIR}/am43x-evm-scale-data.bin ${S}/firmware/
	
 
}

KERNEL_GIT_URI = "git://${HOME}/bb-kernel/ignore/linux-src/"




SRC_URI = "${KERNEL_GIT_URI};protocol=file;branch=${BRANCH} \	   	    
	file://defconfig \	    
	file://am335x-bone-ti-tscadc-4-wire.dtsi \
	file://am335x-boneblack-4dcape-43t.dts \
	file://am335x-bone-panel-480x272.dtsi \
	file://am335x-bone-led-gpio1-28.dtsi \
	file://am335x-bone-keymap1.dtsi \
	file://am335x-bone-common-pinmux.dtsi \
	file://am335x-bone-bl-gpio1-18.dtsi \
	file://am335x-boneblack-emmc.dtsi \
	file://am335x-bone-4dcape-43t.dtsi \
	file://am335x-pm-firmware.elf \
	file://am335x-pm-firmware.bin \
	file://am335x-bone-scale-data.bin \
	file://am335x-evm-scale-data.bin \
	file://am43x-evm-scale-data.bin \
	file://am33xx.dtsi \
	file://sgx-omap.h \
          "

