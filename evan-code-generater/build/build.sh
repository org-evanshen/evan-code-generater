##################
#!/bin/sh
##################

cd ..
mvn clean install -Dmaven.test.skip=true

cd build
ant
