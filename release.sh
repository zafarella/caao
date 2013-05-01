#!/bin/sh

git status

mvn versions:display-plugin-updates

mvn release:prepare release:perform -T4C

git gc 

# to sync the tags in remote and local repos:

#git tag -l | xargs git tag -d

#git fetch
