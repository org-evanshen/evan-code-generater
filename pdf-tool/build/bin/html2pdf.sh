#####################
#!/bin/sh
#####################
echo "execute html to pwf"

echo "source file is [$1]"
echo "target file is [$2]"

java  -Djava.ext.dirs=../lib -classpath ../conf com.ancun.pdftools.main.Html2Pdf $1 $2
