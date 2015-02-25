SUMMARY = "Raspberry pi Python GPIO Lib"
DESCRIPTION = ""

LICENSE = "CLOSED"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PR = "r05"

S = "${WORKDIR}/RPi.GPIO-0.5.11"

SRC_URI = "https://pypi.python.org/packages/source/R/RPi.GPIO/RPi.GPIO-0.5.11.tar.gz;protocol=http \	
	"
inherit distutils

RDEPENDS_${PN} += "python-core"
RDEPENDS_${PN}_class-native = ""

BBCLASSEXTEND += "native"


SRC_URI[md5sum] = "9dc3dab6ce2b7ccb833a866efb392821"
SRC_URI[sha256sum] = "8d6f02da7f90e24512ad80ee4ccf34ef33687c88c47326f100cf7ac4d7ae4bf3"

