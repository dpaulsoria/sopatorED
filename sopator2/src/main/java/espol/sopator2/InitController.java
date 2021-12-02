/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.sopator2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import generator.Sopator;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

/**
 * FXML Controller class
 *
 * @author danny
 */
public class InitController implements Initializable {

    private String tema;
    private Sopator sopa;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        formatosTextfield();
    }    
    
    @FXML
    private void generarSopator(ActionEvent event) {
        Sopator sopator = new Sopator(10,10, getTema());
        this.sopa = sopator;
        System.out.println(sopator.toString());
        switchToSopator();
        
    }
    private void formatosTextfield() {
        filas.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches("[0-9]{0,2}")) ? condicion:null));
        columnas.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches("[0-9]{0,2}")) ? condicion:null));
    }
    
    private void switchToSopator() {
        try {
            FXMLLoader fxml = App.loadFXMLLoad("Sopator");
            App.setRoot(fxml);
            SopatorController fxmlController = fxml.getController();
            fxmlController.setBasics(this.sopa);
        } catch (IOException e) {
            Alert a = new Alert(AlertType.ERROR, e.toString()); a.show();
        }
    }
    private String getTema() {
        if (!someTopicIsSelected()) {
            Alert a = new Alert(AlertType.WARNING, "Eliga un tema:\n\"ANIMALES\", \"CIUDADES\", \"COLORES\""); a.show();
            return null;
        } else {
            return tema;
        }
    }
    
    private boolean someTopicIsSelected() {
        return (animales.isSelected() || ciudades.isSelected() || colores.isSelected());
    }
    
    @FXML
    private void temaAnimales(ActionEvent event) {
        ciudades.setSelected(false);
        colores.setSelected(false);
        tema = "ANIMALES";
    }

    @FXML
    private void temaCiudades(ActionEvent event) {
        animales.setSelected(false);
        colores.setSelected(false);
        tema = "CIUDADES";
    }

    @FXML
    private void temaColores(ActionEvent event) {
        ciudades.setSelected(false);
        animales.setSelected(false);
        tema = "COLORES";
    }
    
}
