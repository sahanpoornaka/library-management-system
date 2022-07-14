package com.lms.library_manager;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;

import com.lms.library_manager.dbo.Auth;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameText;

    @FXML
    private TextField passwordText;

    @FXML
    protected void onLoginBtnClick() throws IOException {
        String username = usernameText.getText();
        String password = passwordText.getText();

        // Handle Empty Input Fields
        if(username.isEmpty() || password.isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Input Fields are Empty. Please Fill the Username and Password").showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> clearFields());
            return;
        }

        String userType = new Auth(username, password).authenticate();

        switch (userType) {
            case "ADMIN" -> new MainApplication().changeScene("admin-view.fxml");
            case "MEMBER" -> new MainApplication().changeScene("member-view.fxml");
            default ->
                    new Alert(Alert.AlertType.ERROR, "Authentication Failed. Please Check Username and Password Again").showAndWait()
                            .filter(response -> response == ButtonType.OK)
                            .ifPresent(response -> clearFields());
        }
    }

    private void clearFields() {
        usernameText.setText("");
        passwordText.setText("");
    }
}