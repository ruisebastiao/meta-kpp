PACKAGES += "\
            packagegroup-qt5-machine-related \
            "

RDEPENDS_packagegroup-qt5-machine-related = "\
	    ${@base_contains('MACHINE', 'cubieboard', 'sunxi-mali', '', d)} \
            "
