Simple Java Agent for logging all instantiated classes
======================================================

It's easy way to see classes you instantiate and how often you do it for the same class

Build logger:
mvn package

Test with application:
java -javaagent:target/ClassLogger-1.0.0-jar-with-dependencies.jar -classpath target\classes com.test.DemoApp

