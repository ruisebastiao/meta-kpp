SUMMARY = "KPP Python BS Validation"
DESCRIPTION = ""

LICENSE = "CLOSED"

FILESEXTRAPATHS_append := "${THISDIR}/files:"

PR = "r02"

DEPENDS = "python python-pyserial python-dev python-netclient python-pyudev"

SRC_URI = "file://pykppbsvalidation-init.sh \	
	  file://pykppbsvalidation.py \
	  file://App.cfg \
	"

S = "${WORKDIR}"


do_install_prepend(){
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/pykppbsvalidation-init.sh  ${D}${sysconfdir}/init.d/pykppbsvalidation-init.sh		
	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/pykppbsvalidation.py  ${D}${bindir}/pykppbsvalidation.py
	install -d ${D}/home/root/
	install -m 0755 ${WORKDIR}/App.cfg  ${D}/home/root/App.cfg	
}


FILES_${PN} ="${bindir}/pykppbsvalidation.py \
	      ${sysconfdir}/init.d/pykppbsvalidation-init.sh \
	      "

inherit update-rc.d
INITSCRIPT_NAME = "pykppbsvalidation-init.sh"
INITSCRIPT_PARAMS = "start 99 4 5 . stop 99 0 1 6 ."

