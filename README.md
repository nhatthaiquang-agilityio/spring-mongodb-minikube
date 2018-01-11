# Spring, MongoDB and Kubernetes
    The following document describes the deployment of a basic Spring API Service and MongoDB web stack on Kubernetes. Currently this example does not use replica sets for MongoDB.

    Using Replication Controllers for building MongoDB

# Requirements
    Java: JDK 1.8
    Maven Build
    Docker and Minikube on local

### Kubernetes
Using Minikube: build on local

#### Minikube
```
minikube start
```

```
minikube stop
```

### APIs Service
##### Build API Service
```
$ cd api-service
$ mvn package -DskipTests=True
```

##### Create docker images for APIs Service and push Docker Hub
```
$ cd api-service
$ docker build -t nhatthai/api-service .
$ docker push nhatthai/api-service
```

##### Create MongoDB ReplicationController
```
$ cd manifests
$ kubectl create -f mongo-controller.yml
```

##### Create MongoDB service
```
$ cd manifests
$ kubectl create -f mongo-service.yml
```

##### Create API Service Deployment
```
$ cd manifests
$ kubectl create -f api-deploy.yml
```

##### Create API Service service
```
$ cd manifests
$ kubectl create -f api-service.yml
```

##### Check services
```
$ kubectl describe services
```

```
$ kubectl describe replicationcontrollers/mongo-controller
```

```
$ kubectl exec -it mongo-controller-vbqf4 -c mongo bash
```

##### Enable and Create Ingress
```
$ minikube addons enable ingress
```

```
$ cd manifests
$ kubectl create -f ingress.yml
```

##### Add mysite.com into /etc/hosts
```
$ minikube ip
192.168.99.100
```

```
192.168.99.100 mysite.com
```

### Notes
Check config MongoDB in api-service/src/main/resources/application.properties


### Usage

Create a student: POST http://mysite.com/student
```
{
	"id": "1",
	"name": "NhatThai",
	"description": "Programmer",
	"courses": [{
		"id": "1",
		"name": "Minikube on local",
		"description": "Kubenetes",
		"steps": [1]
	}]
}
```

Get all student: http://mysite.com/students


### Reference
[Running a MEAN stack on Google Cloud Platform with Kubernetes](https://medium.com/google-cloud/running-a-mean-stack-on-google-cloud-platform-with-kubernetes-149ca81c2b5d)

[Node.js and MongoDB on Kubernetes](https://github.com/kubernetes/examples/tree/master/staging/nodesjs-mongodb)