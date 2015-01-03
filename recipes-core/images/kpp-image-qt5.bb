SUMMARY = "A Qt5 image"

LICENSE = "CLOSED"

IMAGE_FEATURES += "ssh-server-dropbear splash"

# Base this image on core-image-minimal
include recipes-core/images/core-image-minimal.bb

IMAGE_INSTALL_append = " packagegroup-kpp-base packagegroup-qt5-full "


# Include modules in rootfs
IMAGE_INSTALL += " \
	kernel-modules \
	"
