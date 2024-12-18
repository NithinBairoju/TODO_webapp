package com.todo.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.todo.model.Todo;

public class TodoDAO {
    private DatabaseConnection con = new DatabaseConnection();
    private String insertTodo = "insert into todos(user_id,task) values(?,?);";
    private String updateTodo = "update todos set task = ? where id = ?;";
    private String deleteTodo = "delete from todos where id = ?;";
    private String setStatus = "UPDATE todos SET task_status = ? WHERE id = ?;";
    private String getTodo =  "Select * from todos where user_id=?;";

    public int insertTask(int user_id, String description) {
        try(Connection connection = con.getConnection();
            PreparedStatement statement = connection.prepareStatement(insertTodo,  Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1,user_id);
            statement.setString(2,description);
            int rowsUpdated = statement.executeUpdate();
            if(rowsUpdated == 0){
                throw new SQLServerException("Inserting task failed, no ID obtained.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Task ID
                } else {
                    throw new SQLServerException("Inserting task failed, no ID obtained.");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new SQLServerException("Error while creating task");
        }
    }
    public void updateTask(int task_id,String description) {
        try(Connection connection = con.getConnection();
            PreparedStatement statement = connection.prepareStatement(updateTodo);){
            statement.setString(1,description);
            statement.setInt(2,task_id);

            statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new SQLServerException("Error while updating task");
        }
    }
    public boolean deleteTask(int task_id) {
        try(Connection connection = con.getConnection();
            PreparedStatement statement = connection.prepareStatement(deleteTodo);){
            statement.setInt(1,task_id);

            int rowsUpdated = statement.executeUpdate();
            if(rowsUpdated>0){
                return true;
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new SQLServerException("Error while deleting task");
        }
        return false;

    }
    public boolean updateTaskStatus(int task_id, boolean status) {
        try(Connection connection = con.getConnection();
            PreparedStatement statement = connection.prepareStatement(setStatus);){
            statement.setBoolean(1,status);
            statement.setInt(2,task_id);
            int rowsUpdated = statement.executeUpdate();
//            System.out.println("Updating task with ID: " + task_id + " to status: " + status);
            if(rowsUpdated>0){
                return true;
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error while updating task status: " + e.getMessage());
            e.printStackTrace();
            throw new SQLServerException("Error while updating task status");
        }
        return false;
    }
    public ArrayList<Todo> getTodoList(int user_id)  {
        ArrayList<Todo> todoList = new ArrayList<>();
        try(Connection connection = con.getConnection();
            PreparedStatement statement = connection.prepareStatement(getTodo);){
            statement.setInt(1,user_id);

            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                int task_id = rs.getInt("id");
                int userId = rs.getInt("user_id");
                String task = rs.getString("task");
                boolean task_status = rs.getBoolean("task_status");
                String createdTime = rs.getString("created_at");

                Todo todo = new Todo(task_id,userId,task,task_status,createdTime);
                todoList.add(todo);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new SQLServerException("Error while retrieving data");
        }
        return todoList;
    }
}
