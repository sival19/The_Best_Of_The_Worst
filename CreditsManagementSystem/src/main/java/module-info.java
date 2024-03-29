module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires json;
    exports presentation;
    requires com.fasterxml.jackson.databind;
    requires java.sql;
    requires postgresql;
    requires mybatis;
    exports Intefaces;
    exports domain.logIn;
    exports domain.credits;
    opens presentation to javafx.fxml;
    exports persistancy.database;
    exports domain;
    exports persistancy;
    exports presentation.controllers;
    opens presentation.controllers to javafx.fxml;
    exports persistancy.database.objectMapper;
}