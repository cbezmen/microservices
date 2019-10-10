# Spring Kubernetes Example

## Pre required
1. For local development Minikube is required 
    ```shell script
    $ minikube start
    $ minikube dashboard
    ```
1. For Netflix ribbon to see kubernetes you have to give role 
    ```shell script
    $ kubectl create clusterrolebinding admin --clusterrole=cluster-admin --serviceaccount=default:default
    ```
    For custom namespace such as dev-kube
    ```shell script
    $ kubectl create clusterrolebinding admin --clusterrole=cluster-admin --serviceaccount=dev-kube:default
    ```
1. Add Nginx Ingress for accessing your gateway from port 80
    1. Run Script
        ```shell script
        $ minikube addons enable ingress 
        ```
    1. to check your ingress 
        ```shell script
        $ kubectl get pods -n kube-system
        ```
    1. Add ingress dns to /etc/hosts
        ```shell script
        $ 192.168.99.101  localkubeip
        ```    
## Compile Projects

You can compile projects via: 
### KUBECTL:    
1. Compile project and build docker images
    ```shell script
    $ bash build.sh compile
    ```
1. Start Kubernetes deployments, services and ingress
    ```shell script
    $ bash build.sh start-k8
    ```
1. Stop Kubernetes deployments, services and ingress
    ```shell script
    $ bash build.sh stop-k8
    ```
### HELM:    
1. Start Kubernetes deployments, services and ingress 
    ```shell script
    $ bash build.sh start-helm
    ```
1. Stop Kubernetes deployments, services and ingress
    ```shell script
    $ bash build.sh stop-helm
    ```