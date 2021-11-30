package espol.sopator2;

//import collection.CircularLinkedList;
import generator.Sopator;
import java.io.IOException;
import java.net.URL;
//import java.util.LinkedList;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    private TextField tema;
    @FXML
    private Button primaryButton;
    private int f;
    private int c;
    private String t;
    
    private void formatosTextfield() {
        filas.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches("[0-9]{0,2}")) ? condicion:null));
        columnas.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches("[0-9]{0,2}")) ? condicion:null));
    }
    
    private void setTema() {
        if (tema.getText() == null) {
            Alert a = new Alert(AlertType.WARNING, "Eliga un tema: \"ANIMALES\", \"CIUDADES\", \"COLORES\""); a.show();
        } else {
            t = tema.getText();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formatosTextfield();
               
    }
    
    private boolean getData() {
        f = Integer.valueOf(filas.getText());
        c = Integer.valueOf(columnas.getText());
        return !(f < 1 || c < 1);
    }
     
    @FXML 
    private void switchToSecondary() throws IOException {
        getData();
        setTema();
        Sopator sp = new Sopator(f,c,t);
        try {
            FXMLLoader fxml = App.loadFXMLLoad("secondary");
            App.setRoot(fxml);
            SecondaryController sc = fxml.getController();
            sc.setSopator(sp);
        } catch (IOException e) {
            Alert a = new Alert(AlertType.ERROR, e.toString());
            a.show();
        }
        App.setRoot("secondary");
    }
}
