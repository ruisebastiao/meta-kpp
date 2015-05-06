SUMMARY = "KPP BARCODE READER"
DESCRIPTION = ""

LICENSE = "CLOSED"

PR = "r14"

S = "${WORKDIR}/git"

DEPENDS = "qtbase qtwebsockets qtmultimedia qzxing kppqtcommonlib opencv libv4l"


inherit qmake5 pkgconfig

SRCREV = "${AUTOREV}"

BRANCH="master"

KERNEL_GIT_URI = "git://github.com/ruisebastiao/KPPBarcodeReader.git"
KERNEL_GIT_PROTOCOL="git"

SRC_URI = "${KERNEL_GIT_URI};protocol=${KERNEL_GIT_PROTOCOL};branch=${BRANCH} \
"

do_install_append() {
	install -d ${D}${includedir}/
	install -m 644 ${S}/*.h ${D}${includedir}/
}


#FILES_${PN} += "${includedir} ${libdir}/*.so*"

