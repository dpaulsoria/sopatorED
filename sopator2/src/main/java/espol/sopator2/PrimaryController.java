package espol.sopator2;

//import collection.CircularLinkedList;
import collection.CircularLinkedList;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;

public class PrimaryController implements Initializable {

    @FXML
    private TextField filas;
    @FXML
    private TextField columnas;
    @FXML
    private RadioButton animales;
    @FXML
    private RadioButton ciudades;
    @FXML
    private RadioButton colores;
    @FXML
    private RadioButton random;
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
        if (!someTopicIsSelected()) {
            Alert a = new Alert(AlertType.WARNING, "Eliga un tema:\n\"ANIMALES\", \"CIUDADES\", \"COLORES\", \"RANDOM\"");
            a.show();
        }
    }
    
    private boolean someTopicIsSelected() {
        return (random.isSelected() || animales.isSelected() || ciudades.isSelected() || colores.isSelected());
    }
    
    public void temaAnimales() {
        ciudades.setSelected(false);
        colores.setSelected(false);
        random.setSelected(false);
        t = "ANIMALES";
    }
    
    public void temaCiudades() {
        animales.setSelected(false);
        colores.setSelected(false);
        random.setSelected(false);
        t = "CIUDADES";
    }
        
    public void temaColores() {
        ciudades.setSelected(false);
        animales.setSelected(false);
        random.setSelected(false);
        t = "COLORES";
    }
    public void temaRandom() {
        ciudades.setSelected(false);
        animales.setSelected(false);
        colores.setSelected(false);
        t = "RANDOM";
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formatosTextfield();     
    }
    
    private boolean getData() {
        f = Integer.valueOf(filas.getText());
        c = Integer.valueOf(columnas.getText());
        return !(f < 6 || c < 6) && !(f>=20  || c>=20);
    }
     
    @FXML 
    private void switchToSecondary() throws IOException {
        getData();
        setTema();
        f = Integer.valueOf(filas.getText());
        c = Integer.valueOf(columnas.getText());
        if ( !(t == null) && getData() ){
            try {
                Sopator sp = new Sopator(f,c,t);
                FXMLLoader fxml = App.loadFXMLLoad("secondary");
                App.setRoot(fxml);
                SecondaryController sc = fxml.getController();
                sc.setSopator(sp);
            } catch (IOException e) {
                Alert a = new Alert(AlertType.ERROR, e.toString());
                a.show();
            }
            App.setRoot("secondary");
        } else{
            if(!getData()){
                Alert a = new Alert(AlertType.WARNING, "Para mejorar la experiencia use filas y columnas mayores a 6 o menores a 20"); 
                a.show();
            }else{
                Alert a = new Alert(AlertType.WARNING, "Eliga un tema:\n\"ANIMALES\", \"CIUDADES\", \"COLORES\""); 
                a.show();
            }
        }
        
    }
}
