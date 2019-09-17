#!/bin/bash

projectArray=(address user)
projectVersion=v1

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

    docker build -f ./build/Dockerfile -t $project:$projectVersion .
    cd -
  done
fi

if [[ $@ == *"start-k8"* ]]; then
  eval $(minikube docker-env)
  note "Starting building yaml files..."
  note "Starting Mongo Server..."
  kubectl apply -f k8/mongo-volume.yaml
  kubectl apply -f k8/mongo.yaml
  sleep 2

  for project in ${projectArray[*]}; do
    note "Setting $project config"
    kubectl apply -f k8/$project-config.yaml
    note "Starting $project..."
    kubectl apply -f k8/$project.yaml
    sleep 3
  done
  note "Starting gateway Server..."
  kubectl apply -f k8/gateway.yaml
fi

if [[ $@ == *"stop-k8"* ]]; then
  note "Stoping kubernetes..."
  kubectl delete -f k8/gateway.yaml

  kubectl delete service ${projectArray[*]}
  kubectl delete deployment ${projectArray[*]}

  for project in ${projectArray[*]}; do
    note "Stoping $project config"
    kubectl delete -f k8/$project-config.yaml
  done

  kubectl delete -f k8/mongo.yaml
fi
#
