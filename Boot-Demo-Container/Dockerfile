FROM java:8

VOLUME /tmp

ADD target/Boot-Demo-Container-1.0-SNAPSHOT.jar app.jar
EXPOSE 9096

ENTRYPOINT ["java","-jar","/app.jar"]

MAINTAINER minsky