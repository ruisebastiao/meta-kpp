SUMMARY = "KPP BS Validation"
DESCRIPTION = ""

LICENSE = "CLOSED"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PR = "r05"

S = "${WORKDIR}/git"

DEPENDS += "qtbase qtmultimedia qzxing opencv"

inherit qmake5 pkgconfig

SRCREV = "${AUTOREV}"

BRANCH="master"

KERNEL_GIT_URI = "git://github.com/ruisebastiao/KPPBarcodeReader.git"
KERNEL_GIT_PROTOCOL="git"

SRC_URI = "${KERNEL_GIT_URI};protocol=${KERNEL_GIT_PROTOCOL};branch=${BRANCH} \
"

