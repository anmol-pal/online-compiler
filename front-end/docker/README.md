```
cd front-end
VERSION="0.0.5"
NODE_APP="gcr.io/applied-radar-376321/node-app"
```
```
npm run build
docker build -f docker/Dockerfile -t ${NODE_APP}:${VERSION} .
```
```
docker run -ti --name front-end -p 80:80  ${NODE_APP}:${VERSION}
```
```
docker push gcr.io/applied-radar-376321/node-app:0.0.2
```
