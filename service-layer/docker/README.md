Docker Build Base Images
```
docker build -f docker/Dockerfile.base -t ubuntu-java-mvn-ttyd:0.0.1 .
```
Docker Build App
```
mvn clean package
docker build -f docker/Dockerfile.java -t gcr.io/applied-radar-376321/java-compiler-svc:0.0.1 .
```
Docker Run

```
```
docker run -ti --name java-app -p 8080:8080 -p 8081:8081 gcr.io/applied-radar-376321/java-compiler-svc:0.0.1 .
```
