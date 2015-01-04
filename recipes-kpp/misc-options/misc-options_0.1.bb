SUMMARY = "Set final options"
LICENSE = "CLOSED"

#FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
#SRC_URI = "file://issue"



do_configure() {
    :
}

do_compile() {
    :
}

do_install() {
#  install -d ${D}${sysconfdir}
#  install -m 0644 ${WORKDIR}/issue ${D}${sysconfdir}/issue
#  echo -ne "\033[9;0]" >> ${D}${sysconfdir}/issue
:
}

