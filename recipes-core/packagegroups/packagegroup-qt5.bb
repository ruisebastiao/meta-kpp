DESCRIPTION = "Qt5 Packagegroup"
LICENSE = "CLOSED"

inherit packagegroup

 
PACKAGES = "\
         packagegroup-qt5-full \
    "

RDEPENDS_packagegroup-qt5-full = "\
    qtbase \
    qtbase-tools \
    qtbase-plugins \
    qtbase-fonts \
    qtbase-fonts-pfa \
    qtbase-fonts-pfb \
    qtbase-fonts-qpf \
    qtbase-fonts-ttf-dejavu \
    qtbase-fonts-ttf-vera \
    qt3d \
    qt3d-qmlplugins \
    qt3d-tools \
    qtdeclarative \
    qtdeclarative-plugins \
    qtdeclarative-qmlplugins \
    qtdeclarative-tools \
    qtgraphicaleffects-qmlplugins \
    qtimageformats-plugins \
    qtmultimedia \
    qtmultimedia-plugins \
    qtmultimedia-qmlplugins \
    qtsvg \
    qtsvg-plugins \
    qttools \
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

DISTRO_FEATURES_remove = "x11 wayland"