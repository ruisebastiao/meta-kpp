PACKAGES += "\
            packagegroup-qt5-machine-related \
            "

RDEPENDS_packagegroup-qt5-machine-related = "\
            ${@base_contains('MACHINE', 'beaglebone', 'libgles-omap3 omap3-sgx-modules', '', d)} \
            "
