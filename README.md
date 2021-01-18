# TodoList
The application is built Using Spring Framework and for database Postgres SQL is used.
### Assumtion
 * The todolist api can be used to feed Resource to any
frontend service and as such Token based authenctication is used
so that each user has specific control over the todolist
* Each Task as a whole is under a list. A user can have many list and each list can contain one to many task.
* Used PostGresSql for storing the Refresh Tokens.
* For Email Notfication used SMTP service from Mailtrap.io.
* Three type of Priosity can be set to task URGENT(1) , NORMAL(0), INDEFINATE(-1);

## IDEA
* User will first sign Up or Sigin from a device
* If user is new Send a verfication mail
        * Else Check in database for User and send JWT
 * For Creating Editing Todo each Request must have the bearer Token.
 * After Certain Time A new Token is sent on expiration of Current Token.
 

## Open Endpoints

Open endpoints require no Authentication.

* [SignUp] : `POST /api/v1/auth/signup`
* [Verfication]: `GET /api/v1/auth/accountVerification/{token}`
* [LogIn]: `POST /api/v1/auth/login`
* [LogOut]: `POST /api/v1/auth/logout`
## Endpoints that require Authentication

Closed endpoints require a valid Token to be included in the header of the
request. A Token can be acquired from the Login view above.
###ToDo
EndPoints for Viewing and manipulatiing the Todos that the Authencticated User has permission to access.

* [TodoList By ID] : `GET /api/v1/todoList/{id}`
* [TodoList GET ALL] : `GET /api/v1/todoList/`
* [TodoList edit]: `PUT /api/v1/todoList/edit/`
* [TodoList  Delete By ID] : `DELETE /api/v1/todoList/{id}`
* [Todo By ID] : `GET /api/v1/todos/{id}`
* [TodoList GET ALL] : `GET /api/v1/todoList/`

* [Todo By TASKNAME] : `GET /api/v1/todos/taskName/{taskName}`
* [Todo By TargetDate] : `GET /api/v1/todos/targetDate/{targetDate}`
* [Todo By status] : `GET /api/v1/todos/isDone/{isDone}`
* [Todo By ID] : `GET /api/v1/todos/{id}`
* [Todo set Priority] : `PUT /api/v1/todos/priority/{id}`
* [Todo edit]: `PUT /api/v1/todos/edit`
* [Todo  Delete By ID] : `DELETE /api/v1/todos/delete/{id}`
* [Todo Create]: `POST /api/v1/todos/`


