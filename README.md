## Task Service

### Overview

This is a simple REST-based web service that provides task-related functionality for clients.

### Example Use Cases

* As a Tradeshift API consumer, I want to create a task.
* As a Tradeshift API consumer, I want to assign a task to a user.
* As a Tradeshift API consumer, I want to complete a task.
* As a Tradeshift API consumer, I want to fetch all tasks assigned to me.


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


## Scripts to Run Some Simple Tests
```
$ cd bin

# Create a new task
./create_task.sh

# Assign a task to a user
./assign_task_to_user.sh

# Set task status
./set_status.sh

# Get all tasks assigned to a user
./get_user_tasks.sh

```

## To start the service

Start/unit-test the web service per the instructions in https://github.com/Tradeshift/interview-task-service


