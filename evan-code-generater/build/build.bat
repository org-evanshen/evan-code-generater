@echo off
echo mvn install ...

cd ..
call mvn clean install -Dmaven.test.skip=true

echo ant package
cd build
call ant

pause
