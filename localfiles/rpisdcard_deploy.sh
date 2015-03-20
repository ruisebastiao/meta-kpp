#! /bin/bash

imgloc=$1
devloc=$2

du_imgsize="$(du -sLmc $1 | grep total)"

imgsize="$(cut -d' ' -f1 <<< $du_imgsize)M"

echo "Deploing in $devloc file:$imgloc (Size:$imgsize)"

#"$(dd if=$imgloc | pv -s $imgsize | dd of=$devloc bs=1M)"

pv -s $imgsize  $imgloc | dd iflag=fullblock of=$devloc bs=1M

sync

echo "Done"
