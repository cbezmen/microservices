#!/bin/bash

projectArray=(address user)
projectVersion=v1
podNumber=1
namespace=dev-kube

function note() {
  local GREEN NC
  GREEN='\033[0;32m'
  NC='\033[0m' # No Color
  printf "\n${GREEN}$@  ${NC}\n" >&2
}

set -e

if [[ $@ == *"compile"* ]]; then
  eval $(minikube docker-env)
  for project in ${projectArray[*]}; do
    note "Compiling $project..."
    cd $project
    gradle clean build -x test
    cp ../Dockerfile ./build

    docker build -f ./build/Dockerfile -t ${project}:$projectVersion .
    cd -
  done
fi

if [[ $@ == *"start-k8"* ]]; then
  eval $(minikube docker-env)
  note "Starting building yaml files..."
  note "Starting Infrastructures"
  kubectl apply -f k8/infrastructure/
  sleep 2

  note "Setting configs"
  kubectl apply -f k8/config/
  note "Starting workloads"
  awk 'FNR==1{print "---"}1' k8/workload/* | IMAGE_TAG=${projectVersion} POD=${podNumber} envsubst | kubectl apply -f -
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

function deploy() {
  note "Deploying $1..."
  DEPLOYED=$(helm ls | grep -E "$1" | grep "DEPLOYED" | wc -l)
  if [ ${DEPLOYED} == 0 ] ; then
    note "Installing"
    helm install --name=$1 --namespace=$2 helm/$1
  else
    note "Upgrading"
    helm upgrade $1 helm/$1
  fi
}

function delete() {
  note "Deleting ${1}..."
  DEPLOYED=$(helm ls --all | grep -E "${1}" | wc -l)
  if [ ${DEPLOYED} -gt 0 ] ; then
    helm delete --purge ${1} 
  fi

}

if [[ $@ == *"start-helm"* ]]; then
  deploy infrastructure ${namespace}
  for project in ${projectArray[*]}; do
    deploy ${project} ${namespace}
  done
    note "Deployment finished..."
fi

if [[ $@ == *"stop-helm"* ]]; then
  for project in ${projectArray[*]}; do
    delete ${project} ${namespace}
  done
  delete infrastructure ${namespace}
  
  note "Deleting finished..."

fi