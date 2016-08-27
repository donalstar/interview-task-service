#!/bin/sh

curl -H "Content-Type: application/json" -X POST -d '{"name":"new task","attributes": "{ \"ghg\": \"val\", \"p2\", \"val2\" }" }' \
http://localhost:8080/task
