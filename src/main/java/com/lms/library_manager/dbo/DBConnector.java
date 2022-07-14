package com.lms.library_manager.dbo;

import java.sql.*;

public class DBConnector {

    public Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/library2","admin","admin123");

        } catch(Exception e){ e.printStackTrace();}

        return null;
    }
}
