SECTION = "kernel"
DESCRIPTION = "beagleboard.org Linux kernel for beaglebone"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

FILESEXTRAPATHS_prepend := "${THISDIR}/linux-ti-staging-3.%:"

# Default is to package all dtb files for ti33x devices unless building
# for the specific beaglebone machine.
KERNEL_DEVICETREE_beaglebone += " am335x-boneblack-bb-view-43.dtb"

MACHINE_EXTRA_RRECOMMENDS += " kernel-modules"

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|omap3|beaglebone"


#S = "${WORKDIR}/git"

BRANCH = "3.14"

#SRCREV = "7828f3b4cb26fa9c978aa96cc46cca8521957f24"
SRCREV = "${AUTOREV}"

#PV = "3.14.26"

RV = "04"

#Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
#MACHINE_KERNEL_PR = "r${RV}-"



KERNEL_GIT_URI = "git://${HOME}/beagleboard-linux/"
KERNEL_GIT_PROTOCOL = "file"

#KERNEL_GIT_URI = "git://RobertCNelson/ti-linux-kernel.git"
#KERNEL_GIT_PROTOCOL = "git"
     
SRC_URI = "${KERNEL_GIT_URI};protocol=${KERNEL_GIT_PROTOCOL};branch=${BRANCH} \
            file://defconfig \
           "
           
#SRC_URI += "
#	  file://0007-add_bbview_devicetree.patch 
#           "
