SUMMARY = "KPP BASE IMAGE"

LICENSE = "GPL-3.0"

IMAGE_FEATURES += " ssh-server-openssh splash package-management "


# Base this image on core-image-minimal
inherit core-image

IMAGE_INSTALL += "\
		acpid rsync pm-utils\
		kernel-modules \
		"


#SPLASH = "psplash-kpp"
