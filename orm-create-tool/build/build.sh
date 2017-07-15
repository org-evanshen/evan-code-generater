##################
#!/bin/sh
##################

cd ..
mvn clean deploy -Dmaven.test.skip=true

cd build
ant
