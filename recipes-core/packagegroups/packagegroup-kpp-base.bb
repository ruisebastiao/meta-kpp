DESCRIPTION = "Image base Packagegroup"
LICENSE = "CLOSED"

inherit packagegroup


PACKAGES = "\
         packagegroup-kpp-base \
    "

RDEPENDS_packagegroup-kpp-base = "\
 gcc g++ binutils libgcc libgcc-dev libstdc++ \
libstdc++-dev libstdc++-staticdev libexif \
autoconf automake ccache chkconfig glib-networking \
packagegroup-core-buildessential pkgconfig  \
boost cmake zlib glib-2.0 git \
    gdb \
    gdbserver \
    nano \
    gstreamer \
    gst-meta-video \
    gst-plugins-base-app \
    gst-plugins-base \
    gst-plugins-good \
    gst-plugins-good-rtsp \
    gst-plugins-good-udp \
    gst-plugins-good-rtpmanager \
    gst-plugins-good-rtp \
    gst-plugins-good-video4linux2 \
    openssh-sftp-server \
    cairo pango fontconfig freetype dbus \
    i2c-tools dtc coreutils \
    connman wireless-tools wpa-supplicant bluez4 opkg \
    tslib-conf tslib-tests tslib-calibrate tslib \
    "

