SUMMARY = "KPP BS Validation"
DESCRIPTION = ""

LICENSE = "CLOSED"

DEPENDS = "qtbase qtserialport qtwebsockets"
f
SRC_URI = "git://github.com/ruisebastiao/KPPBSValidation.git"

S = "${WORKDIR}/git"

inherit qmake5
