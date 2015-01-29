PACKAGECONFIG_X11 = ""
PACKAGECONFIG_GL = ""
PACKAGECONFIG_remove="x11"

PACKAGECONFIG_append =  " dbus evdev widgets tools libs linuxfb directfb gles2 \
                        teststools accessibility tslib"                       

DISTRO_FEATURES_remove = "x11 wayland"