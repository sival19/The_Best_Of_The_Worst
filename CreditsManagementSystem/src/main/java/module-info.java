module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires json;
    exports presentation;
    requires com.fasterxml.jackson.databind;
    exports domain.logIn;
    exports domain.credits;
}