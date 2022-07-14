package com.lms.library_manager.dbo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class Issue {
    private String isbn;
    private String username;
    private LocalDate issueDate;
    private LocalDate dueDate;

    public Issue(String isbn, String username, LocalDate issueDate, LocalDate dueDate) {
        this.isbn = isbn;
        this.username = username;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
    }

    public boolean save() {
        try{
            Connection con = new DBConnector().getConnection();
            Statement stmt=con.createStatement();
            try {
                stmt.executeUpdate("""
                    INSERT INTO issues(isbn, username, issue_date, due_date) VALUES ("%s","%s","%s", "%s")
                    """.formatted(this.isbn, this.username, this.issueDate.toString(), this.dueDate.toString()));
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

}
