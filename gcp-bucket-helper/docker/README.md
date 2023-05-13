Docker Build Base Images
```
docker build -f docker/Dockerfile.base -t ubuntu-java-mvn-ttyd:0.0.1 .
```
Docker Build App
```
mvn clean package
docker build -f docker/Dockerfile -t gcr.io/applied-radar-376321/api-helper-compiler-svc:0.0.1 .
```
Docker Run

```
```
docker run -ti --name java-app -p 8086:8086 gcr.io/applied-radar-376321/api-helper-compiler-svc:0.0.1 .
```