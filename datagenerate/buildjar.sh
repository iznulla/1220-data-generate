#!/bin/bash
rm -rf target
./mvnw clean package -Dmaven.test.skip=true -DoutputDirectory=ptoject
