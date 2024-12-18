package com.todo.dao;

import com.todo.model.User;

import java.sql.*;

public class UserLogin {
    private DatabaseConnection con = new DatabaseConnection();

    private String userLogin = "select * from users where email = ?;";

    public User checkUserExist(String email,String password) {
        PasswordEncryption hashText = new PasswordEncryption();
        UserDAO userMethods = new UserDAO();
        try(Connection connection = con.getConnection();
            PreparedStatement stament = connection.prepareStatement(userLogin);) {
            stament.setString(1,email);
            ResultSet result = stament.executeQuery();

            if (result.next()){
                String hashedPassword = result.getString("user_password");

                //if plain password matches hash password it returns boolean value
                if(hashText.verifypassword(password,hashedPassword)) {
                    String name = result.getString("userName");
                    int userId = result.getInt("id");
                    User user = new User();
                    user.setUserName(name);
                    user.setEmail(email);
                    user.setUser_id(userMethods.selectUser(user.getEmail()));
                    return user;
                }
            }else{
                throw new UserNotFound("this account does not exist");
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new SQLServerException("error occurred retrieving data");
        }
        return null;
    }
}
