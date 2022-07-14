package com.lms.library_manager.dbo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

public class Auth {

    String username;
    String encryptedPassword;

    public Auth(String username, String password) {
        this.username = username;
        this.encryptedPassword = encryptPassword(password);
    }

    /**
     * Use To Authenticate the user
     * Return User Type in a String. ADMIN, MEMBER
     * Return Empty String if Authentication Failed
     */
    public String authenticate() {
        try{
            Connection con = new DBConnector().getConnection();

            PreparedStatement stmt = con.prepareStatement("""
                    SELECT * FROM users WHERE username="%s"
                    """.formatted(this.username));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                String encryptedPassword = rs.getString("password");
                String userType = rs.getString("user_type");

                con.close();
                return Objects.equals(encryptedPassword, this.encryptedPassword) ? userType : "";
            } else {

                con.close();
                return "";
            }

        } catch(Exception e){
            e.printStackTrace();
            return "";
        }
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

}
