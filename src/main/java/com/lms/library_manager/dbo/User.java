package com.lms.library_manager.dbo;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Statement;

public abstract class User {

    protected String username;
    protected String encryptedPassword;
    protected String fName;
    protected String lName;
    protected String mobile;

    public User(String username, String password, String fName, String lName, String mobile) {
        this.username = username;
        this.encryptedPassword = encryptPassword(password);
        this.fName = fName;
        this.lName = lName;
        this.mobile = mobile;
    }

    private String encryptPassword(String password){
        String encryptedPassword = "";
        try
        {
            /* MessageDigest instance for MD5. */
            MessageDigest m = MessageDigest.getInstance("MD5");

            /* Add plain-text password bytes to digest using MD5 update() method. */
            m.update(password.getBytes());

            /* Convert the hash value into bytes */
            byte[] bytes = m.digest();

            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */
            StringBuilder s = new StringBuilder();
            for (byte aByte : bytes) {
                s.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }

            /* Complete hashed password in hexadecimal format */
            encryptedPassword =  s.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        return encryptedPassword;
    }

    public abstract boolean save();
}