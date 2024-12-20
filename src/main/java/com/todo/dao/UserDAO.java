package com.todo.dao;

import com.todo.model.User;

import java.sql.*;

public class UserDAO {
    private  DatabaseConnection con= new DatabaseConnection();

    //user account queries
    private String insertUser = "insert into users(userName,email,user_password) values(?,?,?);";
    private String updateName = "update users set userName = ? where id = ?;";
    private String updateMail = "update users set email = ? where id = ?;";
    private String updatePassword = "update users set user_password = ? where id = ?;";
    private String deleteUser = "delete from users where id = ?;";
    private String selectUser = "select id from users where email = ?;";
    private String checkUser = "select id from users where email = ?;";



    //creating user
    public boolean insertUser(User user) {
        PasswordEncryption hashText = new PasswordEncryption();
        String hashedPassword = hashText.encryptPassword(user.getPassword());
        try(Connection connection = con.getConnection();
            PreparedStatement statement = connection.prepareStatement(insertUser);){
            statement.setString(1,user.getUserName());
            statement.setString(2,user.getEmail());
            statement.setString(3,hashedPassword);
            int rowsEffected = statement.executeUpdate();
            if(rowsEffected>0){
                return true;
            }
        }catch (SQLException e) {
            if (e.getSQLState().startsWith("23")) {
                throw new SQLServerException("User already exists with email: ");
            }
            throw new SQLServerException("Error while creating user: " + (user.getUserName() != null ? user.getUserName() : "user")
            );
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    //update user mail
    public boolean updateUserName(String newUserName, int id, User user) {
        try(Connection connection = con.getConnection();
            PreparedStatement statement = connection.prepareStatement(updateName);){
            statement.setString(1,newUserName);
            statement.setInt(2,id);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        }catch (SQLException | ClassNotFoundException e) {
            throw new SQLServerException("Error while updating mail of: " + (user.getUserName() != null ? user.getUserName() : "user")
            );
        }

    }
    //update user mail
    public boolean updateMail(String userMail,int userID,User user) {
        try {
            if (!checkMail(userMail)) {
                return false;
            }
        } catch (SQLServerException e) {
            throw new SQLServerException("Error while updating mail of: " + (user.getUserName() != null ? user.getUserName() : "user")
            );
        }
        try(Connection connection = con.getConnection();
            PreparedStatement statement = connection.prepareStatement(updateMail);){
            statement.setString(1,userMail);
            statement.setInt(2,userID);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        }catch (SQLException | ClassNotFoundException e) {
            throw new SQLServerException("Error while updating mail of: " + (user.getUserName() != null ? user.getUserName() : "user")
            );
        }
    }
    //update password of an account
    public boolean updatePassword(String userPassword,int userID,User user) {
        PasswordEncryption hashText = new PasswordEncryption();
        String hashedPassword = hashText.encryptPassword(userPassword);
        try(Connection connection = con.getConnection();
            PreparedStatement statement = connection.prepareStatement(updatePassword);){
            statement.setString(1,hashedPassword);
            statement.setInt(2,userID);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        }catch (SQLException | ClassNotFoundException e) {
            throw new SQLServerException("Error occured while updating password of: " + (user.getUserName() != null ? user.getUserName() : "user")
            );
        }
    }
    //deleting an user
    public boolean deleteUser(int userID,User user) {
        try(Connection connection = con.getConnection();
            PreparedStatement statement = connection.prepareStatement(deleteUser);){
            statement.setInt(1,userID);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        }catch (SQLException | ClassNotFoundException e){
            throw new SQLServerException("Error deleting user: " + (user.getUserName() != null ? user.getUserName() : "user")
            );
        }
    }
    //selecting an user
    public int selectUser(String email) {
        try(Connection connection = con.getConnection();
            PreparedStatement statement = connection.prepareStatement(selectUser);){
            statement.setString(1,email);
            ResultSet result = statement.executeQuery();
            if (result.next()){
                int id = result.getInt("id");
                return id;
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new SQLServerException("Error retrieving id with " + email);

        }
        return -1;
    }
    //selecting an user
    public boolean checkMail(String email) {
        try(Connection connection = con.getConnection();
            PreparedStatement statement = connection.prepareStatement(checkUser);){
            statement.setString(1,email);
            ResultSet result = statement.executeQuery();
            if (result.next()){
                return true;
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new SQLServerException("Error retrieving id with " + email);

        }
        return false;
    }
}
