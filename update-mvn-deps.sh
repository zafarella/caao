#!/bin/bash

read -p "This will update library dependencies to latest release versions, continue?  [yn]" answer
if [[ $answer = y ]] ; then
	mvn versions:use-latest-releases -T 2C
	mvn clean compile -T 4C
fi

