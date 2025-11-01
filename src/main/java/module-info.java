module oop.creditcard_oop_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens oop.creditcard_oop_project to javafx.fxml;
    exports oop.creditcard_oop_project;
}