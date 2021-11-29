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
import ec.edu.espol.model.util.Oferta;
import ec.edu.espol.model.vehiculo.Vehículo;
import ec.edu.espol.model.vehiculo.VehículoTableView;
import ec.edu.espol.proyecto.App;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author danny
 */
public class CompradorController implements Initializable {

    @FXML
    private TextField Nombrecomprador;
    @FXML
    private TextField Apellidocomprador;
    @FXML
    private TextField Correocomprador;
    @FXML
    private TextField Orgcomprador;
    @FXML
    private TextField Nickcomprador;
    @FXML
    private TextField nomcomprador;
    @FXML
    private TextField apecomprador;
    @FXML
    private TextField orgcomprador;
    @FXML
    private TextField usercomprador;
    @FXML
    private TextField rolcomprador;
    @FXML
    private TextField corcomprador;
    @FXML
    private PasswordField Passwordcomprador;
    @FXML
    private PasswordField Password1comprador;
    @FXML
    private TextField recinicomprador;
    @FXML
    private TextField recfincomprador;
    @FXML
    private TextField añoinicomprador;
    @FXML
    private TextField añofincomprador;
    @FXML
    private TextField preinicomprador;
    @FXML
    private TextField  prefincomprador;
    @FXML
    private TextField placaofertacomprador;
    @FXML
    private TextField precioofertacomprador;
    @FXML
    private Text confcomprador;    
    @FXML
    private ComboBox cbxVehiculocomprador;
    @FXML
    private TableView<VehículoTableView> tvcomprador;
    @FXML
    private TableColumn<VehículoTableView, String> tvplacacomprador;
    @FXML
    private TableColumn<VehículoTableView, String>  tvmodelocomprador;
    @FXML
    private TableColumn<VehículoTableView, String>  tvmarcacomprador;
    @FXML
    private TableColumn<VehículoTableView, String>  tvmotorcomprador;
    @FXML
    private TableColumn<VehículoTableView, String>  tvpreciocomprador;
    @FXML
    private TableColumn<VehículoTableView, String>  tvañocomprador;
    @FXML
    private ImageView imageOfertaVcomprador;
    @FXML
    private CheckBox filtroRECcomprador;
    @FXML
    private CheckBox filtroAÑOcomprador;
    @FXML
    private CheckBox filtroPREcomprador;
    private Comprador c;
    private ArrayList<ArrayList> v;
    private String tipo;
    private int posiciónTablacomprador;
    private Oferta ofcomprador;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setComboBoxTipoV(); formatoStrings(); formatoNumeros();
        posiciónTablacomprador = -1;
    }
    
    private void formatoStrings() {
        Nombrecomprador.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        Apellidocomprador.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        Correocomprador.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,40}"))? condicion: null ));
        Orgcomprador.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        Nickcomprador.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        Passwordcomprador.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        Password1comprador.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        placaofertacomprador.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
            
    }
    
    private void formatoNumeros() {
        precioofertacomprador.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches("[0-9]{0,9}"))? condicion: null ));
        recinicomprador.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches("[0-9]{0,9}"))? condicion: null ));
        recfincomprador.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches("[0-9]{0,9}"))? condicion: null ));
        añoinicomprador.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches("[0-9]{0,9}"))? condicion: null ));
        añofincomprador.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches("[0-9]{0,9}"))? condicion: null ));
        preinicomprador.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches("[0-9]{0,9}"))? condicion: null ));
        prefincomprador.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches("[0-9]{0,9}"))? condicion: null ));
 
    }
    private void setComboBoxTipoV() {
        ArrayList<String> lista = new ArrayList<>();
        lista.add("Auto");
        lista.add("Camioneta"); lista.add("Camión"); lista.add("Motocicleta");
        cbxVehiculocomprador.setItems(FXCollections.observableArrayList(lista));
    }
    @FXML
    private void setConf(KeyEvent event) {
        if (Passwordcomprador.getText().equals(Password1comprador.getText())) {
            confcomprador.setVisible(true);
        } else {
            confcomprador.setVisible(false);
        }        
    }
    @FXML
    private void Actualizar() {
        try {
            FXMLLoader fxml = App.loadFXMLLoad("Registrarse");            
            App.setRoot(fxml);
            RegistrarseController rc = fxml.getController();
            rc.setModoActualizar((Persona)c, "Comprador");
        } catch (IOException e) {
            Alert a = new Alert(AlertType.ERROR, e.toString()); a.show();
        }
    }
    @FXML
    private void Logout() {
        try {
            FXMLLoader fxml = App.loadFXMLLoad("Login");
            App.setRoot(fxml);
            LoginController lc = fxml.getController();
            lc.setBasics(c.getEmail());
        } catch (IOException e) {
            Alert a = new Alert(AlertType.ERROR, e.toString()); a.show();
        }
    }
    private void out() {
        try {
            FXMLLoader fxml = App.loadFXMLLoad("Login");
            App.setRoot(fxml);
        } catch (IOException e) {
            Alert a = new Alert(AlertType.ERROR, e.toString()); a.show();
        }
    }
    @FXML
    private void setEditableFalse() {
        recinicomprador.setEditable(filtroRECcomprador.isSelected());
        recfincomprador.setEditable(filtroRECcomprador.isSelected());
        añoinicomprador.setEditable(filtroAÑOcomprador.isSelected());
        añofincomprador.setEditable(filtroAÑOcomprador.isSelected());
        preinicomprador.setEditable(filtroPREcomprador.isSelected());
        prefincomprador.setEditable(filtroPREcomprador.isSelected());
    }
    
    private String[] setParametros() {
        String[] parametros = new String[6];
        parametros[0] = recinicomprador.getText();
        parametros[1] = recfincomprador.getText();
        parametros[2] = añoinicomprador.getText();
        parametros[3] = añofincomprador.getText();
        parametros[4] = preinicomprador.getText();
        parametros[5] = preinicomprador.getText();
        return parametros;
    }
    
    private void ingresoPersonaComprador(HashMap<String, Object> mapaComprador, Persona comprador) {
        mapaComprador = Extras.deserializar("Comprador");
        if (mapaComprador.containsKey(comprador.getEmail())) {
            Alert a = new Alert(AlertType.ERROR, "Usuario ya existente"); a.show();
        } else {
            mapaComprador.put(comprador.getEmail(), comprador);
            Extras.serializar(mapaComprador, "Comprador");
            Alert a = new Alert(AlertType.CONFIRMATION, "Usuario ingresado con éxito"); a.show();
        }
    }
    private void ingresarComprador(HashMap<String, Object> mapaC, Persona comprador) {
        mapaC = Extras.deserializar("Comprador");
        ingresoPersonaComprador(mapaC, comprador);
        if (mapaC.containsKey(comprador.getEmail())) {
            Alert a = new Alert(AlertType.ERROR, "Usuario ya existente"); a.show();
        } else {
            mapaC.put(comprador.getEmail(), comprador);
            Extras.serializar(mapaC, "Comprador");
            Alert a = new Alert(AlertType.CONFIRMATION, "Usuario ingresado con éxito"); a.show();
        }
    }
    @FXML
    private void Registro() {
        if (Passwordcomprador.getText().equals(Password1comprador.getText()) && !validacionesDatosComprador()) {
            String[] datospersona = {Nombrecomprador.getText(), Apellidocomprador.getText(), Correocomprador.getText(), Orgcomprador.getText(), Nickcomprador.getText(), Passwordcomprador.getText()};
            Persona comprador = new Comprador(datospersona, Comprador.nextCode());
            HashMap<String, Object> mapaC = new HashMap<String, Object>();
            if (Extras.deserializar("Comprador") != null) {
                // PRIMERO VALIDAR SI YA EXISTE
                ingresarComprador(mapaC, comprador);
            } else {
                mapaC.put(comprador.getEmail(), comprador);
                Extras.serializar(mapaC, "Comprador");
                Alert a = new Alert(AlertType.CONFIRMATION, "Usuario ingresado con éxito"); a.show();

            }
        } else if (!Passwordcomprador.getText().equals(Password1comprador.getText())) {
            Alert a = new Alert(AlertType.ERROR, "Contraseñas no coincidentes"); a.show();
        }
    }   
    private boolean validacionesDatosComprador() {
        return Nombrecomprador.getText().equals("") &&   
               Apellidocomprador.getText().equals("") &&        
               Correocomprador.getText().equals("") &&       
               Orgcomprador.getText().equals("") &&         
               Nickcomprador.getText().equals("") &&          
               Passwordcomprador.getText().equals("") &&         
               Password1comprador.getText().equals("");
    }
    public void setBasics(Persona com) {
        this.c = (Comprador)com;
        setInfoUsuario();
    }
    private void setInfoUsuario() {
        nomcomprador.setText(c.getNombre());
        apecomprador.setText(c.getApellido());
        corcomprador.setText(c.getEmail());
        orgcomprador.setText(c.getOrganización());
        usercomprador.setText(c.getUsuario());
        rolcomprador.setText("Comprador");
    }
    
    private VehículoTableView castVehículoToTableView(Vehículo v) {
        VehículoTableView vtv = new VehículoTableView();
        vtv.setPlaca(new SimpleStringProperty(v.getPlaca()));
        vtv.setMarca(new SimpleStringProperty(v.getMarca()));
        vtv.setModelo(new SimpleStringProperty(v.getModelo()));
        vtv.setMotor(new SimpleStringProperty(v.getMotor()));
        vtv.setPrecio(new SimpleDoubleProperty(v.getPrecio()));
        vtv.setAño(new SimpleIntegerProperty(v.getAño()));
        return vtv;
    }
    private void makeOffert(Vehículo v, HashMap<String, Object> mapaOfertas) {
        v.setIDOferta(ofcomprador.getIDOferta());
        ofcomprador.setV(v);
        mapaOfertas.put(String.valueOf(ofcomprador.getIDOferta()), ofcomprador);
        Extras.serializar(mapaOfertas, "Oferta");
        Alert a = new Alert(AlertType.CONFIRMATION, "Oferta realizada con éxito"); a.show();
    }
    private boolean validacionesDatosOferta() {
        return placaofertacomprador.getText().length() == 0 && //String
               precioofertacomprador.getText().length() == 0; // Int
    }
    @FXML
    private void Ofertar() {
        if (!validacionesDatosOferta()) {
            Vehículo vehiculo = obtenerVehículoPorPlaca(placaofertacomprador.getText(), cbxVehiculocomprador.getValue().toString());
            ofcomprador = new Oferta(Oferta.nextCode(), c, Double.valueOf(precioofertacomprador.getText()), vehiculo.getVendedor(), vehiculo);
            if (Extras.deserializar("Oferta") != null) {
                HashMap<String, Object> mapaOfertas = Extras.deserializar("Oferta");
                makeOffert(vehiculo, mapaOfertas);
            } else {
                HashMap<String, Object> mapaOfertas = new HashMap<String, Object>();
                makeOffert(vehiculo, mapaOfertas);
            }
        } else {
            Alert a = new Alert(AlertType.WARNING, "Ingrese una placa y precio para ofertar"); a.show();
        }
    }
    private Vendedor obtenerVendedor(String emailVendedor) {
        HashMap<String, Object> mapaVendedores = Extras.deserializar("Vendedor");
        return (Vendedor)mapaVendedores.get(emailVendedor);
    }
    private Vehículo obtenerVehículoPorPlaca(String placa, String tipoV) {
        HashMap<String, Object> mapaV = Extras.deserializar(tipoV);
        return (Vehículo) mapaV.get(placa);
    }
    @FXML
    private void searchVehículos() {
        if ((String)cbxVehiculocomprador.getValue() != null) {
            // LISTA DE VEHÍCULOS QUE CUMPLEN LOS REQUERIMIENTOS DE BÚSQUEDA
            ArrayList<Vehículo> listaVehículos = Vehículo.buscarVehículo(setParametros(), (String)cbxVehiculocomprador.getValue());
            if (listaVehículos == null) {
                System.out.println(listaVehículos.toString());
                Alert a = new Alert(AlertType.WARNING, "No hay vehículos en el sistema, por favor registrar"); a.show();
            } else {
                setVehículosInTableView(listaVehículos);
            }
        } else {
            Alert a = new Alert(AlertType.ERROR, "Seleccione un tipo de Vehículo"); a.show();
        }
            
    }
    private void setVehículosInTableView(ArrayList<Vehículo> lista) {
        ArrayList<VehículoTableView> listaVehículos = new ArrayList<>();
        for(Vehículo v: lista) {
            listaVehículos.add(castVehículoToTableView(v));
        }
        tvcomprador.setItems(FXCollections.observableArrayList(listaVehículos));
        tvplacacomprador.setCellValueFactory(new PropertyValueFactory<VehículoTableView, String>("Placa"));
        tvmarcacomprador.setCellValueFactory(new PropertyValueFactory<VehículoTableView, String>("Marca"));
        tvmodelocomprador.setCellValueFactory(new PropertyValueFactory<VehículoTableView, String>("Modelo"));
        tvmotorcomprador.setCellValueFactory(new PropertyValueFactory<VehículoTableView, String>("motor"));
        tvpreciocomprador.setCellValueFactory(new PropertyValueFactory<VehículoTableView, String>("Precio"));
        tvañocomprador.setCellValueFactory(new PropertyValueFactory<VehículoTableView, String>("Año"));

    }
}
