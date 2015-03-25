SUMMARY = "Image for Bs Validation Systems"

LICENSE = "GPL-3.0"

MACHINE_EXTRA_RRECOMMENDS = "kernel-modules eee-acpi-scripts"

include kpp-image-qt5.bb
include kpp-image-base.bb

IMAGE_INSTALL += "\
		  kppbsvalidation \
		  opencv \
		"

