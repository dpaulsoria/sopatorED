module espol.sopaletras {
    requires javafx.controls;
    requires javafx.fxml;

    opens espol.sopaletras to javafx.fxml;
    exports espol.controller;
}
