SUMMARY = "KPP Python BS Validation"
DESCRIPTION = "using cubieboard GPIO"

LICENSE = "CLOSED"

COMPATIBLE_MACHINE = "cubieboard"

FILESEXTRAPATHS_append := "${THISDIR}/files:"

SRC_URI = "file://KPPGPIO.py \	
	"


do_install_prepend(){
	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/KPPGPIO.py  ${D}${bindir}/KPPGPIO.py
}


FILES_${PN} ="${bindir}/KPPGPIO.py \	      
	      "

