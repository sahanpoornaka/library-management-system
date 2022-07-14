package com.lms.library_manager;

import com.lms.library_manager.dbo.BookModel;
import com.lms.library_manager.dbo.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.ResourceBundle;

public class SearchController implements Initializable{
    @FXML
    private TextField searchText;
    @FXML
    private TableView<BookModel> bookViewTable;
    @FXML
    public TableColumn<BookModel, Long> isbn;

    @FXML
    public TableColumn<BookModel, String> title;

    @FXML
    public TableColumn<BookModel, String> author;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //make sure the property value factory should be exactly same as the e.g getStudentId from your model class
        isbn.setCellValueFactory(new PropertyValueFactory<>("Isbn"));
        title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        author.setCellValueFactory(new PropertyValueFactory<>("Author"));

        //add your data to the table here.
        ObservableList<BookModel> bookModel = loadData("");
        bookViewTable.setItems(bookModel);
    }

    @FXML
    public void onSearchBtnClick() {
        ObservableList<BookModel> bookModel = loadData(searchText.getText());
        bookViewTable.setItems(bookModel);
    }

    public void goToAdminMenu() throws IOException {
        new MainApplication().changeScene("admin-view.fxml");
    }

    public void onBackBtnClick() throws IOException {
        goToAdminMenu();
    }

    public ObservableList<BookModel> loadData(String searchStr){

        ObservableList<BookModel> bookList = FXCollections.observableArrayList();

        try{
            try (Connection con = new DBConnector().getConnection()) {
                PreparedStatement stmt = con.prepareStatement("SELECT * FROM books");
                if(!searchStr.isEmpty()){
                    searchStr = searchStr.toLowerCase();
                    String qrStr = "SELECT * FROM books WHERE LOWER(title) LIKE \"%" + searchStr + "%\" OR LOWER(author) LIKE \"%" + searchStr + "%\"";
                    stmt = con.prepareStatement(qrStr);
                }
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    long isbn = rs.getLong("isbn");
                    String title = rs.getString("title");
                    String author = rs.getString("author");
                    bookList.add(new BookModel(isbn, title, author));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return bookList;
    }


}
