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


read -p "Please make sure all is good before starting release. \n Press [Y] when ready, or any other key to exit the process.";
if [[ ! $REPLY =~ ^[Yy]$ ]]
then
    mvn -Dmaven.artifact.threads=20 release:prepare release:perform -T4C ;
fi


echo "Would you like to make git cleanup on your local repo? It will keep your git speedy.(Y/n)\n";
if [[ ! $REPLY =~ ^[Yy]$ ]]
then
    git gc
fi


# to sync the tags in remote and local repos:

#git tag -l | xargs git tag -d

#git fetch
