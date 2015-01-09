SUMMARY = "A Qt5 image"

LICENSE = "CLOSED"

IMAGE_FEATURES += "ssh-server-openssh splash"

# Base this image on core-image-minimal
include recipes-core/images/core-image-minimal.bb

IMAGE_INSTALL += "\
		packagegroup-kpp-base \
		packagegroup-qt5-full \
		kernel-modules \
		"
SPLASH = "psplash-kpp"

