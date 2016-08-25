## Task Service

### Initial Sample Tasks

| Task        | ID           | Attributes  |
| :------------- |:-------------| :-----|
| Approve Received Invoice     | 1 | Inv. ID |
| Validate W-9 Received By Supplier      | 2 | W-9 ID |
| Add Info to My Co. Profile | 3 | Company Details |



### Sample Set of Service Requests

* Create a task.
* Assign a task to a user.
* Set a task status
* Fetch all tasks assigned to a user

## Create Some Initial Seed Data

```sh

# Get into the project directory and get a Postgres prompt
$ cd interview-task-service

$ psql

# execute the seartup.sql script
tasks=> \i bin/setup.sql

# Quit postgres
tasks=> \q
```

## To start the service

```sh

# Get into the project directory
$ cd interview-task-service

# Assuming you have PostgreSQL setup locally
# Get a postgres command prompt to create a database user
$ psql

# Create a database user and set the password
postgres=# CREATE USER taskuser WITH PASSWORD 'taskpassword';

# Create the database tasks with the owner taskuser
postgres=# CREATE DATABASE tasks OWNER taskuser;

# Quit postgres
postgres=# \q

# Run tests
$ mvn test

# Start the service
$ mvn jetty:run

# Confirm a response from the service
$ curl http://localhost:8080/task
The Task Service!

# Save a message to confirm writing to the database works
$ curl -X POST http://localhost:8080/task/post

# Get the message back
$ curl http://localhost:8080/task/get
It works!
```


