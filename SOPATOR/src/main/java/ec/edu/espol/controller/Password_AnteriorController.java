/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.person.Persona;
import ec.edu.espol.model.util.Extras;
import ec.edu.espol.proyecto.App;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextFormatter;
/**
 * FXML Controller class
 *
 * @author danny
 */
public class Password_AnteriorController implements Initializable {

    
    @FXML
    private PasswordField Passwordanterior;
    @FXML
    private PasswordField Password1anterior;
    private Persona persona; //Persona
    private String claveAnterior, tipoCuentaAnterior, rolNuevo;
    private boolean cambioPassword;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formatosTextfield();
    }
    private void formatosTextfield() {
        Passwordanterior.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        Password1anterior.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
    }
    @FXML
    private void Cancelar() {
        try {
            FXMLLoader fxml = App.loadFXMLLoad("Registrarse");
            App.setRoot(fxml);
            RegistrarseController rc = fxml.getController();
            rc.setBasics(persona);
        } catch (IOException e) {
            Alert a = new Alert(AlertType.ERROR, e.toString()); a.show();
        }    
    }
    private void replaceOrRemove(String rolAnterior, String rolNuevo, Persona persona) {
        if (rolAnterior.equals(rolNuevo)) {
            HashMap<String, Object> mapa = Extras.deserializar(rolAnterior);
            mapa.replace(persona.getEmail(), persona);
            Extras.serializar(mapa, rolAnterior);//
            changeToLogin();
        } else {
            HashMap<String, Object> mapa = Extras.deserializar(rolAnterior);
            mapa.remove(persona.getEmail());
            Extras.serializar(mapa, rolAnterior);//
            mapa = Extras.deserializar(rolNuevo);                    
            mapa.put(persona.getEmail(), persona);
            Extras.serializar(mapa, rolAnterior);
            changeToLogin();
        }
    }
    @FXML //Si se dejan los espacios en blanco (de las contraseñas) entonces no se realiza el cambio de contraseña
    private void Continuar() {
        if (cambioPassword && Passwordanterior.getText().equals(Password1anterior.getText())) {
            persona.setClave(claveAnterior); // Cambio de clave
            replaceOrRemove(tipoCuentaAnterior, rolNuevo, persona);
        } else if (!cambioPassword && Passwordanterior.getText().equals(Password1anterior.getText()) && claveAnterior.equals(Extras.claveHex(Passwordanterior.getText()))) {
            replaceOrRemove(tipoCuentaAnterior, rolNuevo, persona);
        } else {
            Alert a = new Alert(AlertType.ERROR, "Las contraseñas no coinciden, asegurese de rellenar los recuadros");
            a.show();
        }
    }
    private void changeToLogin() {
        try {
            FXMLLoader fxml = App.loadFXMLLoad("Login");
            App.setRoot(fxml);
            LoginController lc = fxml.getController();
            lc.setBasics(persona.getEmail());
        } catch (IOException e) {
            Alert a = new Alert(AlertType.ERROR, e.toString()); a.show();
        }
    }
    public void setBasics(Persona p, String clave, String tipo, String rol, boolean cambioPassword) {
        this.persona = p; this.claveAnterior = clave; this.cambioPassword = cambioPassword;
        if (tipo.equals("Comprador && Vendedor")) {
            tipoCuentaAnterior = "Persona";
        } else {
            this.tipoCuentaAnterior = tipo;
        }
        if (rol.equals("Comprador && Vendedor")) {
            rolNuevo = "Persona";
        } else {
            this.rolNuevo = rol;
        }
    }
}
