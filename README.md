## Task Service

| Tables        | Are           | Cool  |
| ------------- |:-------------:| -----:|
| col 3 is      | right-aligned | $1600 |
| col 2 is      | centered      |   $12 |
| zebra stripes | are neat      |    $1 |

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
$ curl http://localhost:8080/hello
Shift happens!

# Save a message to confirm writing to the database works
$ curl -X POST http://localhost:8080/hello/post

# Get the message back
$ curl http://localhost:8080/hello/get
It works!
```

