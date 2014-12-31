DESCRIPTION = "A kernel module to add stmpe device"
HOMEPAGE = "https://github.com/notro/fbtft_tools.git"
SECTION = "kernel/modules"
PRIORITY = "optional"
LICENSE = "CLOSED"
RDEPENDS_stmpe_device = "kernel (${KERNEL_VERSION})"
DEPENDS = "virtual/kernel"
PR = "r2"

inherit module

SRCREV = "d58ea725ab864bf72d2b3b4f0764d2dd5c92e192"

COMPATIBLE_MACHINE = "raspberrypi"

SRC_URI = "git://github.com/ruisebastiao/fbtft_tools.git;protocol=http \
	   "
S = "${WORKDIR}/git/stmpe_device"

inherit module

do_compile() {
  unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS CC LD CPP
  oe_runmake 'MODPATH="${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers" ' \
             'KDIR="${STAGING_KERNEL_DIR}"' \
             'KERNEL_VERSION="${KERNEL_VERSION}"' \
             'CC="${KERNEL_CC}"' \
             'LD="${KERNEL_LD}"'
}


do_install() {
   install -d ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers
   install -m 0644 ${S}/stmpe_device${KERNEL_OBJECT_SUFFIX} ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers
}


SRC_URI[md5sum] = "bd56ccd8c75466ae11224695e4c072ea"
SRC_URI[sha256sum] = "ff5e22a2c4c0e54917e1c8f610bfbf04fe8cb82609a1b1bdc11897879d64b682"

