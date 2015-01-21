#!/bin/sh

echo "To understand what it does, please read http://mojo.codehaus.org/versions-maven-plugin/ \n\n";

read -p "This will update library dependencies to latest release versions, continue?  [y/n]" answer

if [[ ${answer} = y ]] ; then

	mvn versions:use-latest-releases -T 2C
	
	mvn clean compile -T 4C

fi