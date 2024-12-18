package com.todo.dao;

import org.mindrot.jbcrypt.BCrypt;
public class PasswordEncryption {
    public String encryptPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }
    public boolean verifypassword(String password,String hashPassword){
        return BCrypt.checkpw(password,hashPassword);
    }
}
