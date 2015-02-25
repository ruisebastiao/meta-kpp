SUMMARY = "Cubieboard Python GPIO Lib"
DESCRIPTION = ""

LICENSE = "CLOSED"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PR = "r02"

#DEPENDS = "pythonnative"

SRCREV = "${AUTOREV}"

BRANCH="master"

#KERNEL_GIT_URI = "git:///home/rui/KPPBSValidation"
#KERNEL_GIT_PROTOCOL="file"

KERNEL_GIT_URI = "git://github.com/ruisebastiao/pySUNXI.git"
KERNEL_GIT_PROTOCOL="git"

SRC_URI = "${KERNEL_GIT_URI};protocol=${KERNEL_GIT_PROTOCOL};branch=${BRANCH} \	
	"

S = "${WORKDIR}/git"

#do_install(){
#	 python setup.py install
	
#}

#INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

#inherit pkgconfig pythonnative

#FILES_${PN} = "${libdir}/python*"
#FILES_${PN}-lib = "${libdir}/lib*.so.*"
#FILES_${PN}-dev += "${bindir} ${datadir}"

inherit distutils

RDEPENDS_${PN} += "python-stringold python-codecs python-math"
RDEPENDS_${PN}_class-native = ""

BBCLASSEXTEND += "native"



