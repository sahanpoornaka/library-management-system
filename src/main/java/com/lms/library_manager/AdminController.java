package com.lms.library_manager;

import javafx.fxml.FXML;

import java.io.IOException;

public class AdminController {

    @FXML
    public void onAddUserBtnClick() throws IOException {
        new MainApplication().changeScene("add-user-view.fxml");
    }

    @FXML
    public void onViewUserBtnClick() throws IOException {
        new MainApplication().changeScene("view-user-view.fxml");
    }

    @FXML
    public void onAddBookBtnClick() throws IOException {
        new MainApplication().changeScene("add-book-view.fxml");
    }

    @FXML
    public void onViewBookBtnClick() throws IOException {
        new MainApplication().changeScene("view-book-view.fxml");
    }

    @FXML
    public void onIssueBookBtnClick() throws IOException {
        new MainApplication().changeScene("issue-book-view.fxml");
    }

    @FXML
    public void onReturnBookBtnClick() throws IOException {
        new MainApplication().changeScene("return-book-view.fxml");
    }
}
