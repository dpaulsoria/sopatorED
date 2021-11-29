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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author danny
 */

public class RegistrarseController implements Initializable {


    @FXML
    private TextField Nombreregistrarse;
    @FXML
    private TextField Apellidoregistrarse;
    @FXML
    private TextField Correoregistrarse;
    @FXML
    private TextField Organizaciónregistrarse;
    @FXML
    private TextField Usuarioregistrarse;
    @FXML
    private PasswordField Contraseñaregistrarse;
    @FXML
    private PasswordField ConfContraseñaregistrarse;
    @FXML
    private Button registroregistrarse;
    @FXML
    private Button volverregistrarse;
    @FXML
    private Button Titleregistrarse;
    @FXML
    private ComboBox cbxregistrarse;
    @FXML
    private CheckBox checkPasswordChange;
    private Persona p;
    private Text confregistrarse;
    private boolean modoactualizar = false;
    private String vistadeRetorno;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        setComboBoxRol();
        formatosTextfield();
        
    }
    
    private void formatosTextfield() {
        Nombreregistrarse.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        Apellidoregistrarse.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        Correoregistrarse.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,40}"))? condicion: null ));
        Organizaciónregistrarse.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        Usuarioregistrarse.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        Contraseñaregistrarse.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        ConfContraseñaregistrarse.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        
    }
    @FXML
    private void setConf(KeyEvent event) {
        if (Contraseñaregistrarse.getText().equals(ConfContraseñaregistrarse.getText())) {
            confregistrarse.setVisible(true);
        } else {
            confregistrarse.setVisible(false);
        }        
    }
    private void setComboBoxRol() {
        ArrayList<String> lista = new ArrayList<>();
        lista.add("Comprador && Vendedor"); lista.add("Comprador"); lista.add("Vendedor");
        cbxregistrarse.setItems(FXCollections.observableArrayList(lista));
    }
    
    private void changeToLogin(String correo) {
        try {
            FXMLLoader fxml = App.loadFXMLLoad("Login");
            App.setRoot(fxml);
            LoginController lc = fxml.getController();
            lc.setBasics(correo);
        } catch (IOException e) {
            Alert a = new Alert(AlertType.ERROR, e.toString()); a.show();
        }
    }
    private void changeToVendedor(Persona per) {
        try {
            FXMLLoader fxml = App.loadFXMLLoad("Vendedor");
            App.setRoot(fxml);
            VendedorController vc = fxml.getController();
            vc.setBasics((Vendedor)per);
        } catch (IOException e) {
            System.out.println(e.toString()); System.out.println(e.getMessage());
        }
    }
    private void changeToComprador(Persona per) {
        try {
            FXMLLoader fxml = App.loadFXMLLoad("Comprador");
            App.setRoot(fxml);
            CompradorController cc = fxml.getController();
            cc.setBasics((Comprador)per);
        } catch (IOException e) {
            System.out.println(e.toString()); System.out.println(e.getMessage());
        }  
    }
    private void changeToMain(Persona per) {
        try {
            FXMLLoader fxml = App.loadFXMLLoad("Main");
            App.setRoot(fxml);
            MainController mc = fxml.getController();
            mc.setBasics(per);
        } catch (IOException e) {
            Alert a = new Alert(AlertType.ERROR, e.toString()); a.show();
        }  
    }
    @FXML
    private void setEditablePassword() {
        Contraseñaregistrarse.setEditable(checkPasswordChange.isSelected());
        ConfContraseñaregistrarse.setEditable(checkPasswordChange.isSelected());
    }
    private void changeToPasswordAnterior() {
        try {
            FXMLLoader fxml = App.loadFXMLLoad("Password_Anterior");
            App.setRoot(fxml);
            Password_AnteriorController cac = fxml.getController();
            cac.setBasics(p, Contraseñaregistrarse.getText(), vistadeRetorno, (String)cbxregistrarse.getValue(), checkPasswordChange.isSelected());
        } catch (IOException e) {
            Alert a = new Alert(AlertType.ERROR, e.toString()); a.show();
        }
    }
    @FXML
    private void Registro() {
        if (!validarDatos() && (String)cbxregistrarse.getValue() != null) {
            if (modoactualizar && Contraseñaregistrarse.getText().equals(ConfContraseñaregistrarse.getText())) {
                changeToPasswordAnterior();
            } else if (!Contraseñaregistrarse.getText().equals(ConfContraseñaregistrarse.getText())) {
                Alert a = new Alert(AlertType.ERROR, "Contraseñas no coincidentes"); a.show();
            } else if (!modoactualizar && Contraseñaregistrarse.getText().equals(ConfContraseñaregistrarse.getText())){
                makeRegistro();
            }
        } else {
            Alert a = new Alert(AlertType.ERROR, "Seleccione un rol"); a.show();
        }
    }
    private void ingresoPersonaMain(String[] datosPersonales, String correo) {
        if (Extras.deserializar("Persona") != null) {
            if (Extras.deserializar("Persona").containsKey(correo)) {
                Alert a = new Alert(AlertType.WARNING, "Correo en uso actualmente"); a.show();
            } else {
                HashMap<String, Object> mapaPersona = Extras.deserializar("Vendedor");
                Persona persona = new Persona(datosPersonales);
                mapaPersona.put(correo, persona);
                Extras.serializar(mapaPersona, "Persona");
                Alert a = new Alert(AlertType.CONFIRMATION, "Usuario creado con éxito"); a.show();
            }
        } else  {
            HashMap<String, Object> mapaPersona = new HashMap<String, Object>();
            Persona persona = new Persona(datosPersonales);
            mapaPersona.put(correo, persona);
            Extras.serializar(mapaPersona, "Persona");
            Alert a = new Alert(AlertType.CONFIRMATION, "Usuario creado con éxito"); a.show();
        }
    }
    private void ingresoPersonaVendedor(String[] datosPersonales, String correo) {
        Persona persona = new Vendedor(datosPersonales, Vendedor.nextCode());
        if (Extras.deserializar("Vendedor") != null) {
            if (Extras.deserializar("Vendedor").containsKey(correo)) {
                Alert a = new Alert(AlertType.WARNING, "Correo en uso actualmente"); a.show();
            } else {
                HashMap<String, Object> mapaVendedor = Extras.deserializar("Vendedor");
                mapaVendedor.put(correo, persona);
                Extras.serializar(mapaVendedor, "Vendedor");
                Alert a = new Alert(AlertType.CONFIRMATION, "Usuario creado con éxito"); a.show();
            }
        } else {
            HashMap<String, Object> mapaVendedor = new HashMap<String, Object>();
            mapaVendedor.put(correo, persona);
            Extras.serializar(mapaVendedor, "Vendedor");
            Alert a = new Alert(AlertType.CONFIRMATION, "Usuario creado con éxito"); a.show();
        }
    }
    private void ingresoPersonaComprador(String[] datosPersonales, String correo) {
        Persona persona = new Comprador(datosPersonales, Comprador.nextCode());
        if (Extras.deserializar("Comprador") != null) {
            if (Extras.deserializar("Comprador").containsKey(correo)) {
                Alert a = new Alert(AlertType.WARNING, "Correo en uso actualmente"); a.show();
            } else {
                HashMap<String, Object> mapaComprador = Extras.deserializar("Comprador");
                mapaComprador.put(correo, persona);
                Extras.serializar(mapaComprador, "Comprador");
                Alert a = new Alert(AlertType.CONFIRMATION, "Usuario creado con éxito"); a.show();
            }
        } else  {
            HashMap<String, Object> mapaComprador = new HashMap<String, Object>();
            mapaComprador.put(correo, persona);
            Extras.serializar(mapaComprador, "Comprador");
            Alert a = new Alert(AlertType.CONFIRMATION, "Usuario creado con éxito"); a.show();
        }
    }
    private void ingresoPorRoles(String rol, String[] datosRoles, String correo) {
        switch (rol) {
            case "Comprador && Vendedor":
                ingresoPersonaMain(datosRoles, correo); break;
            case "Comprador":
                ingresoPersonaComprador(datosRoles, correo); break;
            case "Vendedor":
                ingresoPersonaVendedor(datosRoles, correo); break;
            default:
                Alert a = new Alert(Alert.AlertType.ERROR, "Elija un tipo"); a.show();
                break;
        }
    }
    private void makeRegistro() {
        String[] datosP = {Nombreregistrarse.getText(), Apellidoregistrarse.getText(), Correoregistrarse.getText(), Organizaciónregistrarse.getText(), Usuarioregistrarse.getText(), Contraseñaregistrarse.getText()};   
        if ((String)cbxregistrarse.getValue() != null) {
            if (Contraseñaregistrarse.getText().equals(ConfContraseñaregistrarse.getText())) {
                ingresoPorRoles((String)cbxregistrarse.getValue(), datosP, Correoregistrarse.getText());
                changeToLogin(Correoregistrarse.getText());
            } else {
                Alert a = new Alert(Alert.AlertType.WARNING, "Las contraseñas no son iguales..."); a.show();
            }    
        } else {
            Alert a = new Alert(AlertType.WARNING, "Seleccione un Rol"); a.show();
        }
    }
    private boolean validarDatos() {
        return Nombreregistrarse.getText().length() == 0 && Apellidoregistrarse.getText().length() == 0 && Correoregistrarse.getText().length() == 0 &&
                Organizaciónregistrarse.getText().length() == 0 && Usuarioregistrarse.getText().length() == 0 && Contraseñaregistrarse.getText().length() == 0;
    }
    private void vistaDeRetorno(String vista, Persona persona) {
        switch(vista) {
            case"Comprador && Vendedor":
                changeToMain(persona);
                break;
            case"Comprador":
                changeToComprador(persona);
                break;
            case"Vendedor":
                changeToVendedor(persona);
                break;
        }
    }
    @FXML
    private void Logout() {
        if (modoactualizar) {
            vistaDeRetorno(vistadeRetorno, p);
        } else {            
            try {
                changeToLogin(p.getEmail());
            } catch(NullPointerException e) {
                changeToLogin("");
            }
        }
    }
    
    public void setModoActualizar(Persona persona, String tipo) {
        modoactualizar = true;
        Titleregistrarse.setText("Actualización de Datos");
        setEditablesToFalse();
        this.p = persona;
        Nombreregistrarse.setText(p.getNombre());
        Apellidoregistrarse.setText(p.getApellido());
        Correoregistrarse.setText(p.getEmail());
        Organizaciónregistrarse.setText(p.getOrganización());
        Usuarioregistrarse.setText(p.getUsuario());
        vistadeRetorno = tipo;
        cbxregistrarse.setValue((String)tipo);
        setModoActualizarLabels();
    }
    private void setModoActualizarLabels() {
        Contraseñaregistrarse.setPromptText("Nueva Contraseña");
        ConfContraseñaregistrarse.setPromptText("Confirmar nueva Contraseña");
        volverregistrarse.setText("Cancelar");
        registroregistrarse.setText("Actualizar");
    }
    private void setEditablesToFalse() {
        Nombreregistrarse.setEditable(false);
        Apellidoregistrarse.setEditable(false);
        Correoregistrarse.setEditable(false);
        Organizaciónregistrarse.setEditable(false);
        Usuarioregistrarse.setEditable(false);
        checkPasswordChange.setVisible(true);
    }
    public void setBasics(Persona per) {
        this.p = per;
    }
}