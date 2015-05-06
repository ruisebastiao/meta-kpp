SUMMARY = "QZXing"
DESCRIPTION = ""

LICENSE = "CLOSED"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

inherit qmake5 pkgconfig

PR = "r01"

DEPENDS = " qtbase "


SRCREV = "${AUTOREV}"

BRANCH="master"

KERNEL_GIT_URI = "git://github.com/ruisebastiao/QZXing.git"
KERNEL_GIT_PROTOCOL="git"

SRC_URI = "${KERNEL_GIT_URI};protocol=${KERNEL_GIT_PROTOCOL};branch=${BRANCH} \
	"

#PACKAGES += "${PN}-dev ${PN} ${PN}-dbg"	

S = "${WORKDIR}/git/source"

do_install_append() {
	install -d ${D}${includedir}/
	install -m 644 ${S}/QZXing.h ${D}${includedir}/
	install -m 644 ${S}/QZXing_global.h ${D}${includedir}/
}


FILES_${PN} += "${includedir} ${libdir}/*.so"

#FILES_${PN}-dev += " ${S}/QZXing.h ${libdir}/*.so"

#FILES_${PN}-dbg += "${libdir}/.debug"
