#!/bin/bash

projectArray=(address user)
projectVersion=v1
namespace=dev-kube

function note() {
  local GREEN NC
  GREEN='\033[0;32m'
  NC='\033[0m' # No Color
  printf "\n${GREEN}$@  ${NC}\n" >&2
}

set -e

function getEnvironmentVariables() {
    # set key value pair from arguments
    if [[ "$#" -eq "0" ]]; then
        echo "Error! Usage: Remind me how this works again ..."
        exit 1
    fi

    while [[ "$#" > "0" ]]
    do
        case $1 in
        (*=*) eval $1;;
        esac
        shift
    done
}

getEnvironmentVariables $@
podNumber=${scale-1}

function runConfig() {
  note "Starting core"
  kubectl apply -f k8/core/

  note "Setting configs"
  kubectl apply -f k8/config/
}

function removeConfig() {
  note "Deleting configs"
  kubectl delete -f k8/config/
}

function startInfrastructure() {
  note "Starting Infrastructures"
  kubectl apply -f k8/infrastructure/
  sleep 2
}

function startWorkloads() {
  note "Starting workloads"
  awk 'FNR==1{print "---"}1' k8/workload/* | IMAGE_TAG=${projectVersion} POD=${podNumber} envsubst | kubectl apply -f -
}

if [[ $@ == *"compile"* ]]; then
  note "Compiling projects..."
  mvn clean install

  for project in ${projectArray[*]}; do
    note "Building docker image for $project..."
    cd $project
    cp ../Dockerfile ./target
    docker build -f ./target/Dockerfile -t ${project}:$projectVersion .
    cd -
  done
fi

if [[ $@ == *"apply-config-k8"* ]]; then
  runConfig
fi

if [[ $@ == *"remove-config-k8"* ]]; then
  removeConfig
fi

if [[ $@ == *"start-k8"* ]]; then
  note "Starting building yaml files..."
  runConfig
  startInfrastructure
  startWorkloads
fi

if [[ $@ == *"stop-k8"* ]]; then
  eval $(minikube docker-env)
  note "Stopping kubernetes..."
  note "Stopping workloads"
  kubectl delete -f k8/workload
  note "Stopping configs"
  kubectl delete -f k8/config/
  note "Stopping infrastructures"
  kubectl delete -f k8/infrastructure/

fi
