SUMMARY = "KPP BS Validation"
DESCRIPTION = ""

LICENSE = "CLOSED"

DEPENDS = "qtbase qtserialport qtwebsockets"

SRCREV = "${AUTOREV}"

BRANCH="master"

KERNEL_GIT_URI = "git:///home/rui/KPPBSValidation"
KERNEL_GIT_PROTOCOL="file"

#KERNEL_GIT_URI = "git://github.com/ruisebastiao/KPPBSValidation.git"
#KERNEL_GIT_PROTOCOL="git"

SRC_URI = "${KERNEL_GIT_URI};protocol=${KERNEL_GIT_PROTOCOL};branch=${BRANCH} \
	"

S = "${WORKDIR}/git"

inherit qmake5


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

datadir="/home/root"

#FILES_${PN}-dbg += "${datadir}/${P}/.debug"
FILES_${PN} += "${datadir}"

inherit update-rc.d

INITSCRIPT_NAME = "bsvalidation_boot"
INITSCRIPT_PARAMS = "defaults 10"

