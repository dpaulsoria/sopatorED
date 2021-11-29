package espol.sopator2;

import generator.Sopator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
    private ComboBox<String> cbox;
    @FXML
    private Button primaryButton;
    private int f, c;
    
    private void formatosTextfield() {
        filas.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches("[0-9]{0,2}")) ? condicion:null));
        columnas.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches("[0-9]{0,2}")) ? condicion:null));
    }
    
    private void setComboBoxRol() {
        cbox = new ComboBox<>();
        cbox.getItems().add("ANIMALES");
        cbox.getItems().add("COLORES");
        cbox.getItems().add("CIUDADES");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formatosTextfield();
        setComboBoxRol();
    }
    
    private boolean getData() {
        f = Integer.valueOf(filas.getText());
        c = Integer.valueOf(columnas.getText());
        return !(f < 1 || c < 1);
    }
    
    @FXML
    private void switchToSecondary() throws IOException {
        System.out.println(cbox.getValue());
        if (getData()) {
            Sopator sp = new Sopator(f,c,cbox.getValue());
            System.out.println(sp.toString());
        } else {
            
        }
        App.setRoot("secondary");
    }
}
