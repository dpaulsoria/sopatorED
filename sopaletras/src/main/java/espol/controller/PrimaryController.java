package espol.controller;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void start() throws IOException {
        App.setRoot("secondary");
    }
}
