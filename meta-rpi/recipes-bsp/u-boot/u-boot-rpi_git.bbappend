DESCRIPTION = "U-Boot port for RaspberryPi"

SRCREV = "8366615c5301c0da4c9a645d1e885cbe7614fc9e"
SRC_URI = "git://github.com/swarren/u-boot.git;branch=rpi_dev"

LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

UBOOT_MACHINE = "rpi_defconfig"

PR="r04"