package espol.sopator2;

//import collection.CircularLinkedList;
import collection.CircularLinkedList;
import collection.CircularNode;
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
            Alert a = new Alert(AlertType.WARNING, "Eliga un tema:\n\"ANIMALES\", \"CIUDADES\", \"COLORES\""); a.show();
        }
    }
    
    private boolean someTopicIsSelected() {
        return (animales.isSelected() || ciudades.isSelected() || colores.isSelected());
    }
    
    public void temaAnimales() {
        ciudades.setSelected(false);
        colores.setSelected(false);
        t = "ANIMALES";
    }
    
    public void temaCiudades() {
        animales.setSelected(false);
        colores.setSelected(false);
        t = "CIUDADES";
    }
        
    public void temaColores() {
        ciudades.setSelected(false);
        animales.setSelected(false);
        t = "COLORES";
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formatosTextfield();
        CircularLinkedList<Integer> c = new CircularLinkedList<>();
        for (int i = 0; i<10; i++) {
            c.addLast(i);
        }
        c.addFirst(3);
        c.addLast(3);
        c.set(3, 4);
        System.out.println(c.toString());
        c.desplazarDer();
        System.out.println(c.toString());
               
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
        if (t == null) {
            Alert a = new Alert(AlertType.WARNING, "Eliga un tema:\n\"ANIMALES\", \"CIUDADES\", \"COLORES\""); a.show();
        }
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
