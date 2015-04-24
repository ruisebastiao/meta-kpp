
DESCRIPTION = "UVC Capture" 
SECTION = "examples" 
LICENSE = "CLOSED" 
SRC_URI[md5sum] = "9ed1bc9cabff0f85f1c8486a69343773"
SRC_URI[sha256sum] = "cd089fbfd1cafe0fa0293ae8071342b594ceefdb69d2322b2810cc4f6475fbd0"
PR = "r1" 


#inherit autotools-brokensep

#CFLAGS_prepend = "-I ${includedir} CFLAGS = -std=gnu99" 

#S = "${WORKDIR}/${PN}-${PV}"
SRC_URI = "http://staticwave.ca/source/uvccapture/uvccapture-${PV}.tar.bz2"  

#BBCLASSEXTEND = "native"
