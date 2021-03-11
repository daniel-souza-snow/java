FROM openjdk:11
VOLUME /tmp
ADD ./target/java-0.0.1-SNAPSHOT.jar java.jar
ENTRYPOINT ["java","-jar","/java.jar"]