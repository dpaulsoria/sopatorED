package espol.sopator2;

import collection.ArrayList;
import generator.Sopator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class PrimaryController implements Initializable {

    @FXML
    private TextField filas;
    @FXML
    private TextField columnas;
    @FXML
    private ComboBox cbox;
    @FXML
    private Button primaryButton;
    
    private void formatosTextfield() {
        filas.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches("[1-9]+"))? condicion: null ));
        columnas.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches("[1-9]+"))? condicion: null ));
    }
    
    private void setComboBoxRol() {
        ArrayList<String> lista = new ArrayList<>();
        lista.addLast("ANIMALES"); lista.addLast("COLORES");
        cbox.setItems(FXCollections.observableArrayList(lista));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formatosTextfield();
        setComboBoxRol();
    }
    
    @FXML
    private void switchToSecondary() throws IOException {
        Sopator sp = new Sopator(10,10,"ANIMALES");
        System.out.println(sp.toString());
        App.setRoot("secondary");
    }
}
