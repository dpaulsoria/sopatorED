module espol.sopator2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens espol.sopator2 to javafx.fxml;
    exports espol.sopator2;
}
