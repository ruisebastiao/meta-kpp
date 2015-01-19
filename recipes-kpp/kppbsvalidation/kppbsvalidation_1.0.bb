SUMMARY = "KPP BS Validation"
DESCRIPTION = ""

LICENSE = "CLOSED"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PR = "r07"

DEPENDS = "qtbase qtserialport qtwebsockets"

SRCREV = "${AUTOREV}"

BRANCH="master"

#KERNEL_GIT_URI = "git:///home/rui/KPPBSValidation"
#KERNEL_GIT_PROTOCOL="file"

KERNEL_GIT_URI = "git://github.com/ruisebastiao/KPPBSValidation.git"
KERNEL_GIT_PROTOCOL="git"

SRC_URI = "${KERNEL_GIT_URI};protocol=${KERNEL_GIT_PROTOCOL};branch=${BRANCH} \
	file://kppbsvalidation-init.sh \	
	"

S = "${WORKDIR}/git"

inherit qmake5

FBDEV="/dev/fb0"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

datadir="/home/root"

#FILES_${PN}-dbg += "${datadir}/${P}/.debug"
FILES_${PN} += "${datadir}"

do_install_append(){
        FBDEVPARSED=$(echo ${FBDEV} | sed 's./.\\/.g')        
        sed 's/fb_dev=\/dev\/fb./fb_dev='$FBDEVPARSED' /' ${WORKDIR}/kppbsvalidation-init.sh > ${WORKDIR}/kppbsvalidation-init2.sh	
	cat ${WORKDIR}/kppbsvalidation-init2.sh
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/kppbsvalidation-init2.sh  ${D}${sysconfdir}/init.d/kppbsvalidation-init.sh
	
}

inherit update-rc.d
INITSCRIPT_NAME = "kppbsvalidation-init.sh"
INITSCRIPT_PARAMS = "start 99 4 5 . stop 99 0 1 6 ."

