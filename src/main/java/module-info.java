module by.bsuir.program {
    requires javafx.controls;
    requires javafx.fxml;


    opens by.bsuir.program to javafx.fxml;
    exports by.bsuir.program;
    exports by.bsuir.program.controllers;
    opens by.bsuir.program.controllers to javafx.fxml;
}