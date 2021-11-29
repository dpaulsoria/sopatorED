package ec.edu.espol.sopator;

import generator.Sopator;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;

public class PrimaryController implements Initializable {

    @FXML
    private TextField filas;
    @FXML
    private TextField columnas;
    @FXML
    private ComboBox cbox;
    
    private void formatosTextfield() {
        filas.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches("[1-9][1-9]"))? condicion: null ));
        columnas.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches("[1-9][1-9]"))? condicion: null ));
    }
    
    private void setComboBoxRol() {
        ArrayList<String> lista = new ArrayList<>();
        lista.add("ANIMALES"); lista.add("COLORES");
        cbox.setItems(FXCollections.observableArrayList(lista));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formatosTextfield();
        setComboBoxRol();
    }
    
    
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
