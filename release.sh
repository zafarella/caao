#!/bin/sh
#
# The purpose: to have a one click script which will do the release of entire project.
#
#

git status;

#echo "-------------------------------------------------------------------------------\n"
#echo ""
#echo "-------------------------------------------------------------------------------\n"
#mvn versions:display-plugin-updates


    mvn -Dmaven.artifact.threads=20 release:prepare release:perform -T4C -B ;

    git gc

# to sync the tags in remote and local repos:

#git tag -l | xargs git tag -d

#git fetch
