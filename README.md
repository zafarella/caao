
#The CAAO

Stands for Context-aware organizer. The main idea of the project is creation of organizer which is context-aware [Mark Weiser](http://en.wikipedia.org/wiki/Mark_Weiser).
I have tried to utilize OSGi as a server side and android as mobile client. Web front-end is planned to be impleneted using Vaadin as another plugable bundle for OSGi (in progress).


## Requirements
### Back-end
  * JDK 1.6 or greater
  * Eclipse / Intellij Idea
  * [Knopflerfish eclipse plug-in](http://www.knopflerfish.org/eclipse_plugin.html) or
  * [Felix] (http://felix.apache.org)
  * Apache Ant - optional
  * JUnit 3
  * maven

### Front-end
  * Android SDK

### IDE
  * Just import maven project, the rest should work. Idea has a bug on Linux - it does not reads the env variables -- workaround:
```bash -l /opt/idea/bin/idea.sh```

## Eclipse
  * Maven plugin
  * git client

## Build Status
  * Master [![Build Status](https://travis-ci.org/zafarella/caao.svg?branch=master)](https://travis-ci.org/zafarella/caao)
  * Dev [![Build Status](https://travis-ci.org/zafarella/caao.svg?branch=dev)](https://travis-ci.org/zafarella/caao)

## Code convention
  * According to [Google Java Style](https://google-styleguide.googlecode.com/svn/trunk/javaguide.html)

## Branching model
 * According to [](http://nvie.com/posts/a-successful-git-branching-model)

## Code analysis and review
  * CodePro

#Contact
Feel free to contact me in case u have any suggestion or any ideas - zafarella at gmail dot com.
