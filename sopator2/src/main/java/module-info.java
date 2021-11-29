module espol.sopator2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens espol.sopator2 to javafx.fxml;
    exports espol.sopator2;
}
