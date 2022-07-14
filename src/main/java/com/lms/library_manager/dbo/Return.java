package com.lms.library_manager.dbo;

import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;

public class Return {

    private int issue_id;
    private LocalDate returned_date;
    private double fine;

    public Return(int issue_id, LocalDate returned_date, double fine) {
        this.issue_id = issue_id;
        this.returned_date = returned_date;
        this.fine = fine;
    }

    public boolean save() {
        try{
            Connection con = new DBConnector().getConnection();
            Statement stmt=con.createStatement();
            try {
                stmt.executeUpdate("""
                    INSERT INTO returns(issue_id, return_date, fine) VALUES (%s,"%s", %s)
                    """.formatted(this.issue_id, this.returned_date.toString(), this.fine));
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
