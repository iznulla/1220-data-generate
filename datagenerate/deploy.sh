#!/bin/bash


if [ -d "./ptoject/" ]; then
    scp -r ./ptoject/datagenerate-0.0.1-SNAPSHOT.jar/ root@212.237.218.57:/home/
else
    echo "Error: Local directory './datagenerate-0.0.1-SNAPSHOT.jar/' does not exist."
    exit 1
fi

#if [ -e "./docker-compose.yaml" ]; then
#    scp ./docker-compose.yaml codela@192.168.128.3:/home/codela
#else
#    echo "Error: Local file './docker-compose.yaml' does not exist."
#    exit 1
#fi

#if [ -e "./Dockerfile.run" ]; then
#    scp ./Dockerfile.run codela@192.168.128.3:/home/codela
#else
#    echo "Error: Local file './Dockerfile.run' does not exist."
#    exit 1
#fi

