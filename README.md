Dynamic To-Do Management System

A dynamic web-based to-do management application built using JSP and Servlets. 
This application provides real-time task management with features such as adding, updating, and deleting tasks seamlessly. 
The application ensures user authentication and intuitive usability with a responsive interface.

Technologies Used

Java: Core language for back-end logic and servlet-based processing.

JSP (Java Server Pages): For dynamic content rendering.

Servlets: To handle HTTP requests and manage application logic.

MySQL: For database operations and data persistence.

HTML, CSS, JavaScript, Bootstrap: For creating the responsive and user-friendly interface.

AJAX: To enable real-time updates and enhance user experience.

Apache Tomcat 11: As the application server.

Features

User Authentication: Secure login system to protect task data.

Real-Time Updates: AJAX implementation for task addition, updates, and deletion without page reloads.

Dynamic Interface: Responsive and user-friendly design using Bootstrap.

Error Handling: Ensures smooth operation with detailed error pages.

How to Set Up and Run the Application

Prerequisites

Install Java JDK (Version 8 or later).

Install Apache Tomcat Server (Version 11).

Install MySQL and set up a database.

Use an IDE like IntelliJ IDEA, Eclipse, or NetBeans.

Install a browser for testing (e.g., Chrome, Firefox).

Steps to Execute

Clone the Repository:

git clone https://github.com/NithinBairoju/TODO_webapp.git

Navigate to the project directory.

Set Up MySQL Database:

Create a database named todo_app.

Import the provided schema.sql file (if available) or create the required tables:

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL
);

CREATE TABLE tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    task_description TEXT NOT NULL,
    status BOOLEAN DEFAULT 0,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

Update the database credentials in DatabaseConnection.java file located in the src/main/java/com.todo/dao/DatabaseConnection package.

Configure Tomcat Server:

Add Tomcat 11 server to your IDE and set it as the runtime environment.

Deploy the project by placing the WAR file in the webapps folder of Tomcat or by running the project from the IDE.

Run the Application:

Start the Tomcat server.

Access the application by navigating to:

http://localhost:8080/todo-app

Default Credentials:

If the database is pre-seeded, use the following credentials:

Username: admin

Password: password

Alternatively, create a new account using the Sign Up page.

Error Handling

If the application encounters any errors, such as 404 (Page Not Found) or 500 (Internal Server Error), 
it will redirect to the respective custom error pages for a better user experience.

Contributing

If you'd like to contribute to this project:

Fork the repository.

Create a new branch (git checkout -b feature-name).

Commit your changes (git commit -m 'Add some feature').

Push to the branch (git push origin feature-name).

Open a pull request.

License

This project is licensed under the MIT License. See the LICENSE file for details.

Acknowledgments

Special thanks to the open-source community for providing amazing tools and documentation.

Resources used for learning and inspiration include Stack Overflow, Oracle Documentation, and MDN Web Docs.

