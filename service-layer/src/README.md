
# cs552-javacompilermicorservice

Prereq:

Install maven 3.8.8 version 

Install Java 17 

Change path in Compiler Service

Build Vanila Command 

```
mvn clean package 
```
Run Command 
```
java -jar target/compiler-0.0.1-SNAPSHOT.jar
```

Build With Docker 
```
```
Run container 
```
```


Installing Maven 3.8.8

```
sudo apt-get install openjdk-17-jdk
wget https://downloads.apache.org/maven/maven-3/3.8.8/binaries/apache-maven-3.8.8-bin.tar.gz
wget https://downloads.apache.org/maven/maven-3/3.8.8/binaries/apache-maven-3.8.8-bin.tar.gz.sha512
sudo tar -zxvf apache-maven-3.8.8-bin.tar.gz -C /opt/
sudo mv /opt/apache-maven-3.8.8 /opt/maven
export M2_HOME=/opt/maven
export PATH=$PATH:$M2_HOME/bin
sudo vi ~/.bashrc //add the following lines in this file
export M2_HOME=/opt/maven
export PATH=$PATH:$M2_HOME/bin
```