FROM ubuntu-java-mvn-ttyd:0.0.1
COPY target/compiler-*.jar execute/app.jar
WORKDIR execute/
ENTRYPOINT java -jar app.jar

#ENTRYPOINT /bin/bash