package ec.edu.espol.sopator;

import generator.Sopator;
import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        try {
            Sopator sp = new Sopator(10, 10, "ANIMALES");
            System.out.println(sp.toString() + "\n");
            sp.añadirFila();
            sp.desplazarFila(2, 2);
            //sp.añadirColumna();
            System.out.println(sp.toString());
        } catch(Exception e) {
            System.out.println(e);
        }
        App.setRoot("secondary");
    }
}
