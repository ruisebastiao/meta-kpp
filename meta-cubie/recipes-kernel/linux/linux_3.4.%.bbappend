FILESEXTRAPATHS_append := "${THISDIR}/linux-3.4:"
COMPATIBLE_MACHINE = "cubieboard"


PV = "3.4"
PR = "r1"
SRCREV_pn-${PN} = "9a1cd034181af628d4145202289e1993c1687db6"


S = "${WORKDIR}/git"

BRANCH = "sunxi-3.4"

KERNEL_GIT_URI = "git://${HOME}/linux-sunxi/"


SRC_URI = "${KERNEL_GIT_URI};protocol=file;branch=${BRANCH} \	
        file://defconfig \
        "        