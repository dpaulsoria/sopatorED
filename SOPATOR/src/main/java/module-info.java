module espol.sopator {
    requires javafx.controls;
    requires javafx.fxml;

    opens espol.sopator to javafx.fxml;
    exports espol.sopator;
}
