module com.example.freshstart {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.freshstart to javafx.fxml;
    exports com.example.freshstart;
    exports com.example.freshstart.controllers;
    opens com.example.freshstart.controllers to javafx.fxml;

}