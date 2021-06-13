[![actions](https://github.com/cbezmen/microservices/actions/workflows/maven.yml/badge.svg)](https://github.com/cbezmen/microservices/actions)
[![codecov](https://codecov.io/gh/cbezmen/microservices/branch/master/graph/badge.svg?token=FD1C9DADQA)](https://codecov.io/gh/cbezmen/microservices)

# Spring Kubernetes Example

Example Spring cloud kubernetes project. Run in local&kubernetes environment.

## Compile Projects

You can compile projects via:

### Local Development:

> You need docker up and running in local environment.

1. Before Running apps from you favorite IDE apply configs in local kube env
   ```shell
   $ bash build.sh apply-config-k8
   ```
   You can remove config via
   ```shell
   $ bash build.sh remove-config-k8
   ```
1. Check applications on local environment
    1. Address Endpoint
       ```shell
       $ curl http://localhost:8081/address/1
       ```
    1. User Endpoint which calls address domain
       ```shell
       $ curl http://localhost:8080/users/1
       ```
    1. Get environment configs
       ```shell
       $ curl http://localhost:8080/config
       ```

### Kubernetes:

> Please check ingress for kubernetes environment. [Check Adding Ingress](#add-ingress)

1. Compile project and build docker images
    ```shell
    $ bash build.sh compile
    ```
1. Start Kubernetes deployments, services and ingress
    ```shell
    $ bash build.sh start-k8
    ```
1. Scale Kubernetes deployments
    ```shell
    $ bash build.sh start-k8 scale=2
    ```
1. Stop Kubernetes deployments, services and ingress
    ```shell
    $ bash build.sh stop-k8
    ```
1. Check applications on kubernetes
    1. Address Endpoint
       ```shell
       $ curl http://localhost:31001/address/1
       ```
    1. User Endpoint which calls address domain
       ```shell
       $ curl http://localhost:31000/users/1
       ```
    1. Get environment configs
       ```shell
       $ curl http://localhost:31000/config
       ```
1. Access via Ingress
    1. Address Endpoint
       ```shell
       $ curl http://localhost/address-api/address/1
       ```
    1. User Endpoint which calls address domain
       ```shell
       $ curl http://localhost/user-api/users/1
       ```
    1. Get environment configs
       ```shell
       $ curl http://localhost/user-api/config
       ```
1. Update Configuration
    1. Update configuration file in user-docker config and refresh actuator via endpoint.
    ```shell
    $ curl -X POST http://localhost/user-api/actuator/refresh
    ```

### <a name="add-ingress"></a>Add Ingress

> Please read documentation for adding ingress if you are not using mac
https://kubernetes.github.io/ingress-nginx/deploy/#docker-for-mac

This command add ingress to your docker desktop kubernetes.

```shell
$ kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v0.46.0/deploy/static/provider/cloud/deploy.yaml 
```

### Cli Versions

|            Cli            | Version |
| :-----------------------: | :-----: |
|          docker           | 20.10.5 |
| docker desktop kubernetes | v1.19.7 |
|            mvn            |  3.8.1  |

![Deadline gif](https://i.imgur.com/7ntFRIT.gif)
