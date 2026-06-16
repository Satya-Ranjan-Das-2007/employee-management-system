Spring Boot Employee Management System - Debugging Assignment
Please DO NOT COPY FROM EXTERNAL SOURCES FOR YOUR OWN BETTER IF YOU WANT TO BE BETTER IN INTERVIEWS, I HAVE GIVEN THE HINTS AND INSTRUCTIONS, ANY DOUBTS OR HASSLES PLS ,YOU ARE WELCOME TO REACH ME ON MY WHATSAPP, WILL BE HAPPY TO GUIDE YOU BETTER
Objective
Fix the broken Spring Boot project and make all APIs work correctly.

You are NOT allowed to rewrite everything. You must debug and fix existing code.

SECTION 1: BUG FIX TASKS
1. Employee.java Issues
Problems in code:
Entity is not properly mapped
Missing annotations
Getters and setters are incorrect
Missing default constructor
Your Task:
Write answers for:

Which annotation is missing to make this a database table? ANS The missing annotation is '@Entity', for @Entity tells JPA/Hibernate that the class should be mapped to a database table.

Why do we need a default constructor in JPA? ANS JPA uses reflection to create objects when reading data from the database.Without a default constructor, Hibernate cannot create an object of the entity class and may throw an exception such as: "No default constructor for entity" Therefore, every JPA entity should have a no-argument constructor.

Fix all getter and setter methods ANS All the getter methods will use a return type required of specific parameters with no needed parameter inside the parenthesis, whereas in setter methods the return type will be void whereas the parenthesis will have paramters inside of it for example as such: public Long getId() { return id; } public void setId(Long id) { this.id = id; }

What happens if @Id is missing? ANS JPA cannot identify the primary key of the entity. At application startup, Hibernate will throw an error similar to: Entity 'Employee' has no identifier OR No identifier specified for entity Because every JPA entity must have exactly one primary key field marked with @Id.

2. Repository Issues
Problems:
Repository is not extending JpaRepository
Your Task:
Why do we extend JpaRepository? ANS We extend JpaRepository because it provides ready-made CRUD operations without writing SQL queries or implementation code. By extending JpaRepository, we automatically get methods like: save,findAll,findById,etc.

What happens if we don't extend it? ANS If we don't extend it, then there will be no CRUD methods available to use, and we cannot call repository implementation such as, repo.save, repo.findAll, repo.findById, etc. because spring data JPA will not create a repository implementation automatically. Compilation errors such as: "The method save(Employee) is undefined" may show up.

Fix repository so CRUD works ANS We extend EmployeeRepository to JpaRepository<Employee, Long>, where: -Employee → Entity class -Long → Data type of primary key (id) And even import, org.springframework.data.jpa.repository.JpaRepository & com.example.demo.entity.Employee.

3. Controller Issues
Problems:
Missing annotations (@RestController, @RequestMapping)
Missing @RequestBody and @PathVariable
Methods returning null
Repository not injected properly
Your Task:
What does @RestController do? ANS @RestController tells Spring Boot that the class will handle HTTP requests and return data (usually JSON). Without @RestController, Spring may try to return a JSP/HTML page instead of JSON data.

Difference between @RequestBody and @PathVariable? ANS @RequestBody - It is used to receive data from the request body, where Spring converts JSON into an object. @PathVariable - It is used to receive values from the URL, which is displayed on web browser page of local host of the used port.

Fix all APIs (POST, GET, PUT, DELETE) ANS All API's we use we add them on top of their methods we use the '@GetMapping' annotations with for post we use 'save' and for get we use 'all' and '{id}', for put we use 'update/{id}' and for delete we use 'delete/{id}'.

Explain flow: Postman → Controller → Repository → DB ANS The flow is as, 1: Postman sends request 2: Controller receives request -> Spring converts JSON into an 'Employee' object. 3: Controller calls Repository 4: Repository talks to Database -> Spring Data JPA generates SQL automatically. 5: Database stores data -> Record is saved in H2 6: Response returned

4. application.properties Issues
Problems:
Incorrect datasource configuration
Missing H2 settings
App not starting
Your Task:
Why do we need application.properties? ANS We need it because, for it is the central and most important configuration file in Spring Boot, where it configures, server ports, database connections, JPA/Hibernate settings, logging, security settings, custom application properties. Without proper configuration, Spring Boot may fail to connect to the database or start the application correctly.

What is H2 database used for? ANS H2 is a lightweight, in-memory database commonly used for, learning spring boot, testing applications, rapid development, and running applications without downloading MySQL.

Fix configuration so application runs ANS server.port=8080 spring.datasource.url=jdbc:h2:mem:testdb spring.datasource.driverClassName=org.h2.Driver spring.datasource.username=sa spring.datasource.password= spring.jpa.hibernate.ddl-auto=update spring.jpa.show-sql=true spring.h2.console.enabled=true

SECTION 2: POSTMAN TESTING
Write answers after testing:

What is response of POST /employee/save? ANS The employee data is saved in the database and the saved employee object is returned. { "id":1, "name":"RAMU", "designation":"Web Developer", "salary":1,50,000 }

What happens if GET /employee/1 is called with invalid ID? ANS If the employee ID does not exist, the repository returns an empty Optional 'NULL'.

What is returned in DELETE API? ANS The API returns a success message: Employee Deleted Successfully It's due to having the return being, return "Employee Deleted Successfully";

Which method is used for update? ANS The PUT method is used for updating an employee.

SECTION 3: THEORY QUESTIONS
Answer briefly:

What is REST API? ANS REST API is a web service that allows applications to communicate using HTTP methods such as GET, POST, PUT, and DELETE.

What is CRUD? ANS CRUD, stands for create, read, update and delete, which are the basic database operations.

Difference between POST and PUT? ANS POST - It is used to create a new resource PUT - It is used to update an existing resource

What is dependency injection? ANS Dependency Injection is a technique where Spring automatically provides required objects (dependencies) to a class instead of the class creating them.

Why do we use Spring Boot? ANS Spring Boot is used to build Java applications quickly with minimal configuration. It provides embedded servers, auto-configuration, and simplifies development of REST APIs and web applications.

SUBMISSION RULES
Fix all bugs in code
Run project successfully
Test all APIs in Postman
Push corrected code to GitHub
Fill this THEORY_QUESTIONS.md file
