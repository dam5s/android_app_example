# Android Sample App

Spike on setting up an Android project using:

IntelliJ IDEA
Maven
Android
Roboguice
Robolectric
Mockito
...

Some low level Api code comes from the Pivotal Android IntellJ Starter project.
Namely: Http and EasySSLSocketFactory.

The rest of the app was entirely test driven.



The Maven setup is almost ready to use.
You will need fetch the Android jar file manually (using the Android SDK),
then install it following the instructions on the Manven Android Plugin website:

https://code.google.com/p/maven-android-plugin/wiki/GettingStarted


## Building Robolectric

###Make sure you have maps

```
$ mvn install:install-file \
    -Dfile=$ANDROID_HOME/add-ons/addon-google_apis-google-16/libs/maps.jar \
    -DgroupId=com.google.android.maps \
    -DartifactId=maps \
    -Dversion=16_r2 \
    -Dpackaging=jar
```

###Build package from fork

From the robolectric submodule directory

```
$ mvn clean package
```

or

```
$ rake robolectric:package
```

###Install package from fork

From the robolectric submodule directory

```
$ mvn install:install-file \
    -Dfile=target/robolectric-2.0-alpha-3-SNAPSHOT.jar \
    -DgroupId=org.robolectric \
    -DartifactId=robolectric \
    -Dversion=2.0-alpha-3-FORKED \
    -Dpackaging=jar

$ cp pom.xml \
~/.m2/repository/org/robolectric/robolectric/2.0-alpha-3-FORKED/robolectric-2.0-alpha-3-FORKED.pom
```

or

```
$ rake robolectric:install
```
