package com.lms.library_manager;

import javafx.fxml.FXML;
import java.io.IOException;

public class MemberController {

    @FXML
    public void onSearchBookBtnClick() throws IOException {
        new MainApplication().changeScene("search-view.fxml");
    }

    @FXML
    public void onUpdateUserBtnClick() throws IOException {
        new MainApplication().changeScene("update-user-view.fxml");
    }

    @FXML
    public void onViewHistoryBtnClick() throws IOException {
        new MainApplication().changeScene("view-history-view.fxml");
    }

    @FXML
    public void onChangePasswordBtnClick() throws IOException {
        new MainApplication().changeScene("change-password-view.fxml");
    }
}
