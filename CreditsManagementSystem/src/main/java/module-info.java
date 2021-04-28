module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires json;
    exports presentation;
    requires com.fasterxml.jackson.databind;
    requires java.sql;
    requires postgresql;
    exports domain.logIn;
    exports domain.credits;
    opens presentation to javafx.fxml;
}