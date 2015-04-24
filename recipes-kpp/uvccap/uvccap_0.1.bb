
DESCRIPTION = "UVC Capture" 
SECTION = "examples" 
LICENSE = "CLOSED" 

PR = "r0" 

#CFLAGS_prepend = "-std=gnu99" 

SRC_URI = "file://capture.c"  

S = "${WORKDIR}"



do_compile() {
	${CC} -std=gnu99 -ljpeg capture.c -o capture
}

do_install() {
	install -d ${D}${bindir}
     	install -m 0755 capture ${D}${bindir}
}
