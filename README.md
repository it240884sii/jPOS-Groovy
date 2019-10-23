## jPOS Groovy

This project is [jPOS](http://jpos.org) based application.

We recommend that you install [Gradle](http://gradle.org/) in order to build your jPOS projects, but if you don't have it installed, you can use the Gradle wrapper scripts `gradlew` and `gradlew.bat`. In the following instructions, when we say `gradle` we really mean either your installed Gradle or one of the wrapper scripts (depending if you are on Unix or DOS based platforms).

### Build an eclipse project
````
gradle eclipse
````

### Build an IDEA project
````
gradle idea
````

### Build your own jar
````
gradle jar
````

### Check the jPOS version
````
gradle version
````

### Create a distribution of your application
````
gradle dist
````
This creates a tar gzipped file in the `build/distributions` directory.

### Install application in 'build/install' directory
````
gradle installApp
````
Installs application in `build/install` with everything you need to run jPOS. Once the directory is created, you can `cd build/install/jPOS-Groovy` and call `java -jar jPOS-Groovy-2.1.4-SNAPSHOT.jar` or the `bin/q2` (or `q2.bat`) script available in the `bin` directory.

### Generate an install a Maven artifact
````
gradle install
````

### List available Gradle tasks
````
gradle tasks
````
### Connect to jPOS-Groovy server
````
telnet localhost 9000
Trying 127.0.0.1...
Connected to localhost.
Escape character is '^]'.

````
### Send  XML-formatted 0800 or 1800 message:
````
<isomsg>
 <field id="0" value="0800" />
 <field id="11" value="000001" />
 <field id="41" value="00000001" />
 <field id="70" value="301" />
</isomsg>
````
if everything ok you will get a response 
````
<isomsg>
  <!-- org.jpos.iso.packager.XMLPackager -->
  <field id="0" value="0810"/>
  <field id="11" value="000001"/>
  <field id="39" value="00000"/>
  <field id="41" value="00000001"/>
  <field id="70" value="301"/>
</isomsg>

````
