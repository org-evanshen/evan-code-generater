#####################
#!/bin/sh
#####################

echo "begin build pdf-tool ..."

cd ..
mvn clean package -Dmaven.test.skip=true

cd build
ant


