SUMMARY = "KPP Python BS Validation"
DESCRIPTION = ""

LICENSE = "CLOSED"

FILESEXTRAPATHS_append := "${THISDIR}/files:"

PR = "r15"

DEPENDS = "python python-pyserial python-dev python-netclient python-pyudev"

SRC_URI = "file://pykppbsvalidation-init.sh \	
	  file://pykppbsvalidation.py \
	"

S = "${WORKDIR}/git"

#INHIBIT_PACKAGE_DEBUG_SPLIT = "1"


FILES_${PN} += "/home/root/"
PACKAGE_${PN} += "/home/root/"

do_install_append(){
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/pykppbsvalidation-init.sh  ${D}${sysconfdir}/init.d/pykppbsvalidation-init.sh		
	install -d ${D}/home/root/
	install -m 0755 ${WORKDIR}/pykppbsvalidation.py  ${D}/home/root/pykppbsvalidation.py
	
}

inherit update-rc.d
INITSCRIPT_NAME = "pykppbsvalidation-init.sh"
INITSCRIPT_PARAMS = "start 1 2 4 5 . stop 1 0 1 6 ."

