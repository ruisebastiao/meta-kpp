#FILESEXTRAPATHS_prepend := "${THISDIR}/linux-ti-staging-3.14:"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

BRANCH = "3.14"

# Corresponds to tag 3.14.22-ti-32
SRCREV = "4cf127efe37e7839863c05f4c5586e1b3609f720"
PV = "3.14.22"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
#MACHINE_KERNEL_PR_append = "d+gitr${SRCPV}"
PR = "${MACHINE_KERNEL_PR}"


SRC_URI = "git:///home/automacao/yocto/beagleboard-linux;protocol=file;nobranch=1 \
           file://defconfig \
          "


#SRC_URI += "file://defconfig 
#            file://0020-host-index-for-enumerating-mmxblkN.patch \
#           "

DEFAULT_PREFERENCE = "1"
COMPATIBLE_MACHINE = "beaglebone"
