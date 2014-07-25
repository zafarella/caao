
#The CAAO
====
Stands for Context-aware organizer. The main idea of the project is creation of organizer which is context-aware [Mark Weiser](http://en.wikipedia.org/wiki/Mark_Weiser).
I have tried to utilize OSGi as a server side and android as mobile client. Web front-end is planned to be impleneted using Vaadin as another plugable bundle for OSGi (in progress).


# Requirements
## Back-end
* JDK 1.6 or greater
* [Knopflerfish eclipse plug-in](http://www.knopflerfish.org/eclipse_plugin.html) or
* [Felix] (http://felix.apache.org)
* Apache Ant - optional
* JUnit 3
* maven

====
## Front-end
  * Android SDK

====
## Using maven
* see the release.sh

===
### IDEA
* Just import maven project, the rest should work. Idea has a bug on Linux - it does not reads the env variables -- workaround: 
```bash -l /opt/idea/bin/idea.sh```

## Eclipse
You will need:
* Maven plugin
* git client (if wanna commit for eclipse)

## CI
  * Master branch [![Build Status](https://travis-ci.org/zafarella/caao.svg?branch=dev)](https://travis-ci.org/zafarella/caao)
  * Dev branch 

## Code convention
* According to [Google Java Style](https://google-styleguide.googlecode.com/svn/trunk/javaguide.html)


## Code analysis and review
 Later will add [Sonar](http://www.sonarqube.org/)

#Contact
===
Feel free to contact me in case u have any suggestion or any ideas - zafarella at gmail dot com.
