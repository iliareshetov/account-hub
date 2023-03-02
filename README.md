### The following are the functional requirements for the system:

1. The system should present a homepage to the user.
2. The homepage should provide the user with the option to either log in using existing credentials or register a new account.
3. User account information should include the following details: first name, last name, email, password, and date of birth.
4. After the user logs in, they should be presented with their account information and should be able to edit their details, including the password.
5. The system should allow the logged-in user to log out. 


### Demo Project Deployed on Amazon Lightsail
**NOTE: Project runs off http please make sure your browser doesn't append https automatically**
You can access the demo project by following this link: http://16.170.25.181/  

### Running the Project Locally

* To run the project locally, create a .env file in the project root directory.
```
MYSQLDB_ROOT_PASSWORD=<secure-password>
MYSQLDB_DATABASE=<db-name>
MYSQL_ROOT_HOST=<host-ip>

SPRING_DATASOURCE_URL=jdbc:mysql://<host-ip>:3306/<db-name>
SPRING_DATASOURCE_USERNAME=<db-user-name>
SPRING_DATASOURCE_PASSWORD=<secure-password>
```
* To build and start the services in detach mode.
```
docker-compose up -d
```