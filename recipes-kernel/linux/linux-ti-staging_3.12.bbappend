#FILESEXTRAPATHS_prepend := "${THISDIR}/linux-ti-staging-3.14:"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

SRCREV = "68f449afccf6d1fb6b38bddfc3a40d9e97b53bdf"

KERNEL_GIT_PROTOCOL = "file"
KERNEL_GIT_URI = "git:///home/automacao/yocto/ti-linux-kernel"


DEFAULT_PREFERENCE = "1"
COMPATIBLE_MACHINE = "beaglebone"
