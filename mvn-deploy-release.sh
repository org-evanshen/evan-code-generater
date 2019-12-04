####################
#!/bin/sh
####################

echo "Begin deploy release ..."

mvn versions:set -pl ./ -DremoveSnapshot=true
mvn clean deploy -Dmaven.test.skip=true -Prelease
mvn versions:revert

echo "end"
