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

##### Create Persitant Volume
```
$ cd manifests
$ kubectl create -f mongo-pv.yml
$ kubectl get pv
```

##### Create Persitant Volume Claim
```
$ cd manifests
$ kubectl create -f mongo-pvc.yml
$ kubectl get pvc
```

##### Create MongoDB Controller
```
$ cd manifests
$ kubectl create -f mongo-controller.yml
```

##### Create MongoDB Service
```
$ cd manifests
$ kubectl create -f mongo-service.yml
```

##### Create API Service Deployment
```
$ cd manifests
$ kubectl create -f api-deploy.yml
```

##### Create API Service
```
$ cd manifests
$ kubectl create -f api-service.yml
```

##### Check Services
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
##### Config MongoDB
Check config MongoDB in api-service/src/main/resources/application.properties

##### Create API Service Replication Controller
https://access.redhat.com/documentation/en-us/red_hat_enterprise_linux_atomic_host/7/html/getting_started_with_kubernetes/get_started_orchestrating_containers_with_kubernetes#exploring_kubernetes_pods

We can create API Service Replication Controller instead of a API Service Deployment.
Please checkout Git branch: replication-api-service

```
$ cd manifests
$ kubectl create -f api-controller.yml
```

### Usage
##### Create a student
POST http://mysite.com/student
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

##### Get all student
http://mysite.com/students

### Reference
[Running a MEAN stack on Google Cloud Platform with Kubernetes](https://medium.com/google-cloud/running-a-mean-stack-on-google-cloud-platform-with-kubernetes-149ca81c2b5d)

[Node.js and MongoDB on Kubernetes](https://github.com/kubernetes/examples/tree/master/staging/nodesjs-mongodb)

[GET STARTED PROVISIONING STORAGE IN KUBERNETES](https://access.redhat.com/documentation/en-us/red_hat_enterprise_linux_atomic_host/7/html/getting_started_with_kubernetes/get_started_provisioning_storage_in_kubernetes)