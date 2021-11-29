/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.person.Comprador;
import ec.edu.espol.model.person.Persona;
import ec.edu.espol.model.person.Vendedor;
import ec.edu.espol.model.util.Extras;
import ec.edu.espol.proyecto.App;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

/**
 * FXML Controller class
 *
 * @author danny
 */
public class LoginController implements Initializable {
    
    @FXML
    private TextField Correo;
    @FXML
    private PasswordField Password;
    @FXML
    private ComboBox cbx;
    private String tipo;
    private Persona p; private Comprador c; private Vendedor v;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formatosTextfield();
        setComboBoxRol();
    }
    private void formatosTextfield() {
        Correo.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,40}"))? condicion: null ));
        Password.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
    }
    
    private void setComboBoxRol() {
        ArrayList<String> lista = new ArrayList<>();
        lista.add("Comprador && Vendedor"); lista.add("Comprador"); lista.add("Vendedor");
        cbx.setItems(FXCollections.observableArrayList(lista));
    }
    @FXML
    private void Login() {
        tipo = (String)cbx.getValue();
        if (tipo == null || (Correo.getText().equals("") || Password.getText().equals(""))) {
            Alert a = new Alert(AlertType.WARNING, "Complete los recuadros"); a.show();
        } else {
            if (tipo.equals("Comprador && Vendedor")) tipo = "Persona";
            if (Extras.deserializar(tipo) != null) {
                HashMap<String,Object> mapa = Extras.deserializar(tipo);System.out.println(mapa.toString());       
                validación(Correo.getText(), Password.getText(), tipo, mapa, p);
            } else {
                Alert a = new Alert(AlertType.WARNING, "Registrese en Sign Up"); a.show();
            }
        }
    }
    
    private void validación(String correo, String Pass, String Tipo, HashMap<String, Object> Mapa, Persona persona) {
        if (Mapa.containsKey(correo)) {
            persona = (Persona)Mapa.get(correo);
            if (persona.getClave().equals(Extras.claveHex(Pass))) {
                changeScene(Tipo, persona);
            } else {
                persona=null;
                Alert a = new Alert(AlertType.WARNING, "Contraseña incorrectas");
                a.show();
            }
        } else {
            Alert a = new Alert(AlertType.WARNING, "El usuario no existe, por favor registrarse en Sign Up"); a.show();
        }
    }
    @FXML
    private void changeToRegistrarse() {
        try {
            FXMLLoader fxml = App.loadFXMLLoad("Registrarse");
            App.setRoot(fxml);
            if (p != null) {
                RegistrarseController rc = fxml.getController();
                rc.setBasics(p);
            }                
        } catch (IOException e) {
            Alert a = new Alert(AlertType.ERROR, e.toString()); a.show();
        }
    }
    public void setBasics(String cor, String tipo) {
        Correo.setText(cor);
        cbx.setValue(tipo);
    }
    public void setBasics(String cor) {
        Correo.setText(cor);
    }
    private void changeScene(String tipo, Persona persona) {
        switch(tipo) {
            case "Persona":
                changeToMain(persona);
                break;
            case "Comprador":
                changeToComprador(persona);
                break;
            case "Vendedor":
                changeToVendedor(persona);
                break;
        }
    }
    private void changeToMain(Persona persona) {
        try {
            FXMLLoader fxml = App.loadFXMLLoad("Main");
            App.setRoot(fxml);
            MainController mc = fxml.getController();
            mc.setBasics(persona);
        } catch (IOException ex) {
            Alert a = new Alert(AlertType.ERROR, ex.toString()); a.show();
        }
    }
    private void changeToComprador(Persona persona) {
        try {
            FXMLLoader fxml = App.loadFXMLLoad("Comprador");
            App.setRoot(fxml);
            CompradorController cc = fxml.getController();
            cc.setBasics(persona);
        } catch (IOException ex) {
            Alert a = new Alert(AlertType.ERROR, ex.toString()); a.show();
        }
    }
    private void changeToVendedor(Persona persona) {
        try {
            FXMLLoader fxml = App.loadFXMLLoad("Vendedor");
            App.setRoot(fxml);
            VendedorController vc = fxml.getController();
            vc.setBasics(persona);
        } catch (IOException ex) {
            Alert a = new Alert(AlertType.ERROR, ex.toString()); a.show();
        }
    }
}
