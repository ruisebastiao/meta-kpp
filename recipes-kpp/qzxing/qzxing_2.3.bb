SUMMARY = "QZXing"
DESCRIPTION = ""

LICENSE = "CLOSED"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

inherit qmake5 pkgconfig

PR = "r17"

DEPENDS = "qtbase qtdeclarative"


SRCREV = "${AUTOREV}"

BRANCH="master"

KERNEL_GIT_URI = "git://github.com/ruisebastiao/QZXing.git"
KERNEL_GIT_PROTOCOL="git"

SRC_URI = "${KERNEL_GIT_URI};protocol=${KERNEL_GIT_PROTOCOL};branch=${BRANCH} \
	"

PACKAGES = "${PN}-dev ${PN} ${PN}-dbg"	

S = "${WORKDIR}/git/source"


FILES_${PN} += "${includedir} ${libdir}/*.so"

FILES_${PN}-dev += "${libdir}/*.so"

FILES_${PN}-dbg += "${libdir}/.debug"
