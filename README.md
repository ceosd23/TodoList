# TodoList
The application is built Using Spring Framework and \
for database Postgres SQL is used.\
###Assumtion
 * The todolist api can be used to feed Resource to any
frontend service and as such Token based authenctication is used
so that each user has specific control over the todolist
* Each Task as a whole is under a list. A user can have many list and each list can contain one to many task.
* Used PostGresSql for storing the Refresh Tokens.
* For Email Notfication used SMTP service from Mailtrap.io.

## Open Endpoints

Open endpoints require no Authentication.

* [Login](login.md) : `POST /api/v1/auth`

## Endpoints that require Authentication

Closed endpoints require a valid Token to be included in the header of the
request. A Token can be acquired from the Login view above.
###ToDo
EndPoints for Viewing and manipulatiing the Todos that the Authencticated User has permission to access.

* [Todo info] : `GET /api/v1//`
* [Update info](user/put.md) : `PUT /api/user/`

### Account related

Endpoints for viewing and manipulating the Accounts that the Authenticated User
has permissions to access.

