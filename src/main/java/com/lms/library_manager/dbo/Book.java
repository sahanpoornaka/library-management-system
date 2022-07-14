package com.lms.library_manager.dbo;

import java.sql.Connection;
import java.sql.Statement;

public class Book {

    private String isbn;
    private String title;
    private String author;

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean save() {
        try{
            Connection con = new DBConnector().getConnection();
            Statement stmt=con.createStatement();
            try {
                stmt.executeUpdate("""
                    INSERT INTO books(isbn, title, author) VALUES ("%s","%s","%s")
                    """.formatted(this.isbn, this.title, this.author));
            } catch(java.sql.SQLIntegrityConstraintViolationException e){
                stmt.executeUpdate("""
                    UPDATE books
                    SET title="%s", author="%s"
                    WHERE isbn="%s";
                    """.formatted(this.title, this.author, this.isbn));
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
