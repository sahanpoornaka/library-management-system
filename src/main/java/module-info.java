module com.lms.library_manager {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    requires java.sql;
    requires mysql.connector.java;

    opens com.lms.library_manager to javafx.fxml;
    exports com.lms.library_manager;
    exports com.lms.library_manager.dbo;
}