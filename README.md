
#The CAAO
====
Stands for Context-aware organizer.

The main idea of the project is creation of organizer which is context-aware [Mark Weiser](http://en.wikipedia.org/wiki/Mark_Weiser).

Server side was build using OSGi framework to provide modularity and easy extendable functionality targeting integration of the server with other
prospective frameworks.



# Requirements
====
## Back-end
* JDK 1.6 or greater
* Eclipse
  * [Knopflerfish eclipse plug-in](http://www.knopflerfish.org/eclipse_plugin.html)
  * Apache Ant - optional
  * JUnit 3
  * maven
====
## Front-end
  * [Android SDK](http://developer.android.com/sdk/installing/index.html?pkg=tools)
  * [Robotium] (https://code.google.com/p/robotium)
====
### Building
  ## Maven
  * see the release.sh or just run ```mvn install```.

## IDE
#### IDEA
* Just import maven project, the rest should work. Idea has a bug on Linux - it does not reads the env variables -- workaround: 
```bash
bash -l /opt/idea/bin/idea.sh
```
#### Eclipse
You will need:
* Maven plugin
* git client (if want to commit from eclipse)

#Contact
===
Feel free to contact me in case u have any suggestion or any ideas - zafarella at gmail dot com.
