SUMMARY = "Userspace framebuffer boot logo based on usplash"
DESCRIPTION = "PSplash is a userspace graphical boot splash screen for mainly embedded Linux devices supporting a 16bpp or 32bpp framebuffer. It has few dependencies (just libc), supports basic images and text and handles rotation. Its visual look is configurable by basic source changes. Also included is a 'client' command utility for sending information to psplash such as boot progress information."

FILESEXTRAPATHS_append := "${THISDIR}/files/"
PR = "r17"

SRC_URI = "git://git.yoctoproject.org/${BPN} \
           file://psplash-init \
           ${SPLASH_IMAGES}"

SPLASH_IMAGES = "file://key_plastics_logo-img.h;outsuffix=default"
