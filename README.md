## Task Service

### Initial Sample Tasks

| Task        | ID           | Attributes  |
| :------------- |:-------------| :-----|
| Approve Received Invoice     | 1 | Inv. ID |
| Validate W-9 Received By Supplier      | 2 | W-9 ID |
| Add Info to My Co. Profile | 3 | Company Details |


### Sample Service Requests - Documentation

####  Create a task.

``POST /task``

_Request Example:_

```

$curl -H "Content-Type: application/json" -X POST \
-d '{"name":"my task","attributes": "{ \"ghg\": \"val\", \"p2\", \"val2\" }" }' http://[hostname]/task


```

_Response Example:_

```
HTTP/1.1 201 Created
Content-Type:application:json
{
“code”: 0,
“Message”: “Task created successfully”,
“Task_id”: 1
} 
```

####  Assign a task to a user.

``POST /task/{task-id}/assign/{user-id}``

_Request Example:_

```
$curl [hostname]/task/{task-id}/assign/{user-id}
```

_Response Example:_

```
HTTP/1.1 200 OK
Content-Type:application:json
{
“code”: 0,
“Message”: “Task assigned successfully”,
} 
```

####  Set a task status

``POST /task/{task-id}/setstatus/{status}``

_Request Example:_

```
$curl [hostname]/task/{task-id}/setstatus/complete
```

_Response Example:_

```
HTTP/1.1 200 OK
Content-Type:application:json
{
“code”: 0,
“Message”: “Task status updated successfully”,
} 
```

####  Fetch all tasks assigned to a user

``GET /task?user_id={user-id}``

_Request Example:_

```
$curl [hostname]/task?user_id={user-id}
```

_Response Example:_

```
HTTP/1.1 200 OK
Content-Type:application:json
{
“code”: 0,
“message”: “Success”,
“tasks”:  [
{ “id”: 1,
“Name”: “My Task”,
“Status” “Active”,
“Assigned”: 2,
“Attributes”: {
“param1” : “val1”,
etc.
},
{
etc.
}
]
} 
```

## Create Some Initial Seed Data

```sh

# Get into the project directory and get a Postgres prompt
$ cd interview-task-service

$ psql

# execute the startup.sql script
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


