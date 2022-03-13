#!/bin/sh

echo "Hi $USER. \033[0;31m This is 99Minutes test Remember write goods commits\033[0m"
./gradlew executeValidations --stacktrace

status=$?

if [ "$status" = 0 ] ; then
    echo "Static analysis found no problems. Have  a good day :)"
    exit 0
else
    echo 1>&2 "Static analysis found violations."
    exit 1
fi