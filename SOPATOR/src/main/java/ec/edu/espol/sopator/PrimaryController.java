package ec.edu.espol.sopator;

import generator.Sopator;
import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        Sopator sp = new Sopator(10, 10, "ANIMALES");
        System.out.println(sp.toString());
        App.setRoot("secondary");
    }
}
