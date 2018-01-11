# Spring, MongoDB and Kubernetes
    MongoDb
    API Service

# Requirements
    Install mongodb on local or using docker
    Java: JDK 1.8
    Maven Build


### Install mongodb

```
$ kubectl describe replicationcontrollers/mongo-controller
```

```
$ kubectl exec -it mongo-controller-vbqf4 -c mongo bash
```

### APIs Service
Build API Service
```
$ cd api-service
$ mvn package -DskipTests=True
```

Create docker images for APIs Service and push Docker Hub
```
$ cd api-service
$ docker build -t nhatthai/api-service .
$ docker push nhatthai/api-service
```

### Reference
[Running a MEAN stack on Google Cloud Platform with Kubernetes](https://medium.com/google-cloud/running-a-mean-stack-on-google-cloud-platform-with-kubernetes-149ca81c2b5d)

[Node.js and MongoDB on Kubernetes](https://github.com/kubernetes/examples/tree/master/staging/nodesjs-mongodb)