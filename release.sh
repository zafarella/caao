#!/bin/sh
#
# The purpose: to have a one click script which will do the release of entire project.
#
#echo "-------------------------------------------------------------------------------\n"
#echo ""
#echo "-------------------------------------------------------------------------------\n"
#mvn versions:display-plugin-updates

 export MAVEN_OPTS="-Xmx2048m -XX:MaxPermSize=512m -Dmaven.artifact.threads=8"

    mvn  release:prepare release:perform -T4C;
    git gc

# to sync the tags in remote and local repos:

#git tag -l | xargs git tag -d

#git fetch
