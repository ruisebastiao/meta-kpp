DESCRIPTION = "Qt5 Packagegroup"
LICENSE = "CLOSED"

inherit packagegroup

 
PACKAGES += "\
        packagegroup-qt5-full \
	packagegroup-qt5-base \
	packagegroup-qt5-3d \
 	packagegroup-qt5-declarative \
	packagegroup-qt5-multimedia \
	packagegroup-qt5-extras \
	packagegroup-qt5-bsvalidation \
    "

RDEPENDS_packagegroup-qt5-full = " packagegroup-qt5-base \
    	packagegroup-qt5-3d \
	packagegroup-qt5-declarative \
	packagegroup-qt5-multimedia \
	packagegroup-qt5-extras \
    "

RDEPENDS_packagegroup-qt5-base = "\
	qtbase \
    	qtbase-tools \
        qtbase-plugins \
   	qtbase-fonts \
    qtbase-fonts-pfa \
    qtbase-fonts-pfb \
    qtbase-fonts-qpf \
    qtbase-fonts-ttf-dejavu \
    qtbase-fonts-ttf-vera \ 
    "

RDEPENDS_packagegroup-qt5-3d = "\
   qt3d \
    qt3d-qmlplugins \
    qt3d-tools \    
"

RDEPENDS_packagegroup-qt5-declarative = " qtdeclarative \
    qtdeclarative-plugins \
    qtdeclarative-qmlplugins \
    "


RDEPENDS_packagegroup-qt5-multimedia = " qtmultimedia \
    qtmultimedia-plugins \
    qtmultimedia-qmlplugins \
    qtsvg \
    qtsvg-plugins \
    "

RDEPENDS_packagegroup-qt5-extras = " qttools \
    	qttools-plugins \
    	qttools-tools \
    	qtconnectivity \
    	qtconnectivity-qmlplugins \
    	qtenginio \
    	qtenginio-qmlplugins \
    	qtlocation \
    	qtlocation-plugins \
    	qtlocation-qmlplugins \
    	qtscript \
    	qtsensors \
    	qtsensors-plugins \
    	qtsensors-qmlplugins \
    	qtserialport \
    	qtsystems \
    	qtsystems-qmlplugins \
    	qtsystems-tools \
    	qtwebsockets \
    	qtwebsockets-qmlplugins \
    	qtxmlpatterns \
    	qtxmlpatterns-tools \    
    "

RDEPENDS_packagegroup-qt5-bsvalidation = " packagegroup-qt5-base \
	packagegroup-qt5-declarative \
	packagegroup-qt5-multimedia \
    	qtserialport \
    	qtwebsockets \
    	qtwebsockets-qmlplugins \
    "

DISTRO_FEATURES_remove = "x11 wayland"
