#!/bin/sh

curl -H "Content-Type: application/json" -X POST -d '{"BAD","attributes": "{ \"ghg\": \"val\", \"p2\", \"val2\" }" }' \
http://localhost:8080/task
