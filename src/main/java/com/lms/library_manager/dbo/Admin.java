package com.lms.library_manager.dbo;

import java.sql.Connection;
import java.sql.Statement;

public class Admin extends User{

    public final String USER_TYPE = "ADMIN";
    public final int MAX_BOOK_LIMIT = 5;

    public Admin(String username, String password, String fName, String lName, String mobile) {
        super(username, password, fName, lName, mobile);
    }

    @Override
    public boolean save() {
        try{
            Connection con = new DBConnector().getConnection();
            Statement stmt=con.createStatement();
            try {
                stmt.executeUpdate("""
                    INSERT INTO
                    users(username, user_type, password, first_name, last_name, mobile)
                    VALUES ("%s", "%s", "%s","%s","%s","%s")
                    """.formatted(this.username, this.USER_TYPE, this.encryptedPassword, this.fName, this.lName, this.mobile));
            } catch(java.sql.SQLIntegrityConstraintViolationException e){
                stmt.executeUpdate("""
                    UPDATE users
                    SET password="%s", user_type="%s", first_name="%s", last_name="%s", mobile="%s"
                    WHERE username="%s";
                    """.formatted(this.encryptedPassword, this.USER_TYPE, this.fName, this.lName, this.mobile, this.username));
            } catch(Exception e) {
                e.printStackTrace();
                return false;
            }
            con.close();
            return true;
        }

        catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }

    //    public boolean updateInfo(Admin admin) {
//        return true;
//    }
//    public Admin createAdmin(){
//        return new Admin();
//    }
//    public Member createUser(){
//        return new Member();
//    }
//    public Book addBook(){
//        return new Book("", "", "");
//    }
}
