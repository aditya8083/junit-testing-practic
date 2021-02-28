
Spring Boot JWT Authentication and authorization added

Steps to setup the project:

1. Download the project.
2. Download the postgres and start the server: https://www.postgresql.org/download/
3. Run the application. Its will run on port 8083.
4. Run the Below queries on postgres DB:

INSERT INTO roles(name) VALUES('ROLE_USER');

INSERT INTO roles(name) VALUES('ROLE_ADMIN');

We have 2 roles in admin or user role.

Except login and register APIs, all other APIs will need the Bearer token - either ADMIN or USER role.




=============================================

Swagger URI:
http://localhost:8083/swagger-ui.html#/
