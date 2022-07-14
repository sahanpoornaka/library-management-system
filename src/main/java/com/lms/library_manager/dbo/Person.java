package com.lms.library_manager.dbo;

import java.sql.Connection;
import java.sql.Statement;

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String city;

    public Person(int id, String firstName, String lastName, String city) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void save() {
        try{
            Connection con = new DBConnector().getConnection();
            Statement stmt=con.createStatement();
            try {
                stmt.executeUpdate("""
                    INSERT INTO Person(id, firstName, lastName, city) VALUES (%s,"%s","%s","%s")
                    """.formatted(this.id, this.firstName, this.lastName, this.city));
            } catch(java.sql.SQLIntegrityConstraintViolationException e){
                stmt.executeUpdate("""
                    UPDATE Person
                    SET firstName="%s", lastName="%s", city="%s"
                    WHERE id=%s;
                    """.formatted(this.firstName, this.lastName, this.city, this.id));
            } catch(Exception e){ e.printStackTrace();}
            con.close();
        }

        catch(Exception e){ e.printStackTrace();}

    }
}
