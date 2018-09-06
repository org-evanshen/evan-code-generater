#####################
#!/bin/sh
#####################
echo "execute office to pdf"

echo "source file is [$1]"
echo "target file is [$2]"

java  -Djava.ext.dirs=../lib -classpath ../conf org.evanframework.toolbox.pdf.main.Office2Pdf $1 $2
