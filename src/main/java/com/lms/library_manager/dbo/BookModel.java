package com.lms.library_manager.dbo;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class BookModel {

    private SimpleLongProperty isbn;
    private SimpleStringProperty title;
    private SimpleStringProperty author;

    public BookModel(long isbn, String title, String author) {
        this.isbn = new SimpleLongProperty(isbn);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);;
    }

    public long getIsbn() {
        return isbn.get();
    }

    public SimpleLongProperty isbnProperty() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn.set(isbn);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getAuthor() {
        return author.get();
    }

    public SimpleStringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }
}
