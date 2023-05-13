FROM ubuntu-java-mvn-ttyd:0.0.1
COPY target/spring-boot-data-jpa-gcp-sql-mysql-crud-0.0.1-SNAPSHOT.jar execute/app.jar
RUN mkdir execute/src/main/resources/ -p
COPY src/main/resources/maps.json execute/src/main/resources/maps.json
WORKDIR execute/
ENTRYPOINT java -jar app.jar
# ENTRYPOINT /bin/bash
