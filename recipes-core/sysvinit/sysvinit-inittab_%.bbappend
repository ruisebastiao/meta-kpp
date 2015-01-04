SUMMARY = "Inittab configuration for SysVinit with autologin"


do_install_append () {
 sed -i '/1:2345:respawn/c\1:2345:respawn:/bin/login -f root tty1 </dev/tty1 >/dev/tty1 2>&1' ${D}${sysconfdir}/inittab
 cat ${D}${sysconfdir}/inittab
}
