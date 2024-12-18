package com.todo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    //jdbc connection details
    private String url ="jdbc:mysql://localhost:3306/webTodo";
    private String userName = "root";
    private String password = "@nithin44sql";


    public DatabaseConnection() {

    }

    //getting connection of jdbc
    protected Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, userName, password);

        return connection;
    }
}
