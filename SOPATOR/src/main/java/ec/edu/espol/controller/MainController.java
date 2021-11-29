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
import ec.edu.espol.model.vehiculo.Auto;
import ec.edu.espol.model.vehiculo.Camioneta;
import ec.edu.espol.model.vehiculo.Camión;
import ec.edu.espol.model.vehiculo.Motocicleta;
import ec.edu.espol.model.vehiculo.Vehículo;
import ec.edu.espol.model.vehiculo.VehículoTableView;
import ec.edu.espol.proyecto.App;
import java.awt.Component;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 * FXML Controller class
 *
 * @author User
 */
public class MainController implements Initializable {

    @FXML
    private TextField Nombre;
    @FXML
    private TextField Apellido;
    @FXML
    private TextField Correo;
    @FXML
    private TextField Organización;
    @FXML
    private TextField Usuario;
    @FXML
    private PasswordField Contraseña;
    @FXML
    private PasswordField ConfContraseña;
    @FXML
    private ComboBox cbx1;
    @FXML
    private ComboBox cbx;
    @FXML
    private ComboBox cbxVehículo;
    @FXML
    private Text conf;
    @FXML
    private TextField placa;
    @FXML
    private TextField marca;
    @FXML
    private TextField modelo;
    @FXML
    private TextField tipoMotor;
    @FXML
    private TextField año;
    @FXML
    private TextField recorrido;
    @FXML
    private TextField color;
    @FXML
    private TextField tipoCombustible;
    @FXML
    private TextField vidrios;
    @FXML
    private TextField transmisión;
    @FXML
    private TextField precio;
    @FXML
    private TextField recfin;
    @FXML
    private TextField recini;
    @FXML
    private TextField añoini;
    @FXML
    private TextField añofin;
    @FXML
    private TextField preini;
    @FXML
    private TextField prefin;
    @FXML
    private TableView<VehículoTableView> tv;
    @FXML
    private TableColumn<VehículoTableView, String> tvplaca;
    @FXML
    private TableColumn<VehículoTableView, String> tvmodelo;
    @FXML
    private TableColumn<VehículoTableView, String> tvmarca;
    @FXML
    private TableColumn<VehículoTableView, String> tvmotor;
    @FXML
    private TableColumn<VehículoTableView, String> tvprecio;
    @FXML
    private TableColumn<VehículoTableView, String> tvaño;
    @FXML
    private TextField placaoferta;
    @FXML
    private TextField preciooferta;
    @FXML
    private Button oferta;
    @FXML
    private Button regresarO;
    @FXML
    private Button actualizadatos;
    @FXML
    private Button logout;
    @FXML
    private TextField nom;
    @FXML
    private TextField ape;
    @FXML
    private TextField org;
    @FXML
    private TextField user;
    @FXML
    private TextField rol;
    @FXML
    private TextField cor;
    @FXML
    private ImageView nuevoV;
    @FXML
    private Button subir;
    @FXML
    private ImageView ofertarV;
    @FXML
    private CheckBox filtroREC;
    @FXML
    private CheckBox filtroAÑO;
    @FXML
    private CheckBox filtroPRE;
    @FXML
    private TableView<VehículoTableView> tvo;
    @FXML
    private TableColumn<VehículoTableView, String> tvoplaca;
    @FXML
    private TableColumn<VehículoTableView, String> tvomarca;
    @FXML
    private TableColumn<VehículoTableView, String> tvomodelo;
    @FXML
    private TableColumn<VehículoTableView, String> tvomotor;
    @FXML
    private TableColumn<VehículoTableView, String> tvoprecio;
    @FXML
    private TableColumn<VehículoTableView, String> tvoemail;
    @FXML
    private ImageView imageOferta;
    @FXML
    private TextField ofertarPLaca;
    private Vehículo veh; private Comprador com; private Vendedor ven;
    private Persona persona; private Oferta of;
    private String ruta; //Ruta de imagen
    private int posiciónTabla;
    //private String tipo; //
    /**subir
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setComboBoxRol(); setComboBoxTipoV(); formatosTextfield(); posiciónTabla = -1; showOfertas(); formatoNumeros();
        setComboBoxVeh(); showOfertas();
        
    }
    
    private void formatosTextfield() {
        Nombre.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        Apellido.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        Correo.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,40}"))? condicion: null ));
        Organización.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        Usuario.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        Contraseña.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        ConfContraseña.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        placa.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        marca.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        modelo.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        tipoMotor.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        color.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        tipoCombustible.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        transmisión.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
    }
    private void formatoNumeros() {
        precio.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches("[0-9]{0,9}"))? condicion: null ));
        vidrios.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches("[0-9]{0,9}"))? condicion: null ));
        recorrido.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches("[0-9]{0,9}"))? condicion: null ));
        año.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches("[0-9]{0,9}"))? condicion: null ));
    }
    @FXML
    private void setVidrioTextFieldToNull() {
        if (cbx.getValue().toString().equals("Motocicleta")) {
            vidrios.setText("--");
            vidrios.setEditable(false);
        } else {
            vidrios.setEditable(true);
        }
    }
    @FXML
    private void setConf(KeyEvent event) {
        if (Contraseña.getText().equals(ConfContraseña.getText())) {
            conf.setVisible(true);
        } else {
            conf.setVisible(false);
        }        
    }
    private void setComboBoxRol() {
        ArrayList<String> lista = new ArrayList<>();
        lista.add("Comprador && Vendedor"); lista.add("Comprador"); lista.add("Vendedor");
        cbx1.setItems(FXCollections.observableArrayList(lista));
    }
    private void setComboBoxTipoV() {
        ArrayList<String> lista = new ArrayList<>();
        lista.add("Auto");
        lista.add("Camioneta"); lista.add("Camión"); lista.add("Motocicleta");
        cbx.setItems(FXCollections.observableArrayList(lista));
    }
    private void setComboBoxVeh() {
        ArrayList<String> lista = new ArrayList<>();
        lista.add("Auto");
        lista.add("Camioneta"); lista.add("Camión"); lista.add("Motocicleta");
        cbxVehículo.setItems(FXCollections.observableArrayList(lista));
    }
    private void creaciónVehículos(String campo, String[] datosVehículo, Vendedor vendedor, String rutaImg, HashMap<String, Object> mapaVehículos) {
        switch(campo) {
            case "Auto":
                createAuto(datosVehículo, vendedor, rutaImg, mapaVehículos); break;
            case "Camioneta":
                createCamioneta(datosVehículo, vendedor, rutaImg, mapaVehículos); break;
            case "Camión":
                createCamión(datosVehículo, vendedor, rutaImg, mapaVehículos); break;
            case "Motocicleta":
                createMotocicleta(datosVehículo, vendedor, rutaImg, mapaVehículos); break;
        }
    }
    private Persona crearVendedor(Persona persona) {
        String[] datos = {persona.getNombre(), persona.getApellido(), persona.getEmail(), persona.getOrganización(), persona.getUsuario(), persona.getClave()};
        Persona perso = new Vendedor(datos, Vendedor.nextCode());
        ven = (Vendedor) perso;
        return perso;
    }private Persona crearComprador(Persona persona) {
        String[] datos = {persona.getNombre(), persona.getApellido(), persona.getEmail(), persona.getOrganización(), persona.getUsuario(), persona.getClave()};
        Persona perso = new Comprador(datos, Comprador.nextCode());
        com = (Comprador) perso;
        return perso;
    }
    @FXML
    private void RegistrarVehículo() {
        if (nuevoV.getImage() == null) {
            Alert a = new Alert(AlertType.WARNING, "Ingrese una imagen"); a.show();
        }else if (ruta.length() == 0) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Suba una imagen por favor"); a.show();
        } else if ((String)cbx.getValue() != null && ruta.length() != 0) {
            if (!validacionesDatosAuto()) {
                System.out.println(marca.getText().getClass());
                String[] datosV = {placa.getText(), marca.getText(), modelo.getText(), año.getText(), recorrido.getText(), color.getText(), precio.getText(), tipoMotor.getText()};
                System.out.println(Arrays.toString(datosV));
                HashMap<String, Object> mapa = new HashMap<String, Object>();
                creaciónVehículos((String)cbx.getValue(), datosV, (Vendedor) crearVendedor(persona), ruta, mapa);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Datos incorrectos"); a.show();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Seleccione un tipo de Vehículo"); a.show();
        }
    }
    private void createAuto(String[] datos, Vendedor vendedorAuto, String rutaImagenA, HashMap<String, Object> mapaAuto) {
        String[] datosAuto = {tipoCombustible.getText(), vidrios.getText(), transmisión.getText(), String.valueOf(Auto.nextCode())};
        System.out.println(vidrios.getText().getClass());
        System.out.println(Arrays.toString(datosAuto));
        Vehículo auto = new Auto(datos, vendedorAuto, rutaImagenA, datosAuto);
        System.out.println(auto.toString());
        if (Extras.deserializar("Auto") != null) {
            mapaAuto = Extras.deserializar("Auto");
            ingresoVehículo("Auto", mapaAuto.containsKey(auto.getPlaca()), mapaAuto, auto);
                    System.out.println(auto.toString());

        } else {
            mapaAuto.put(auto.getPlaca(), auto);
            Extras.serializar(mapaAuto, "Auto");
            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Auto registrado con éxito"); a.show();
                    System.out.println(auto.toString());

        }
    }
    private void createCamioneta(String[] datos, Vendedor vendedorCamioneta, String rutaImagenCA, HashMap<String, Object> mapaCamioneta) {
        String[] datosCamioneta = {tipoCombustible.getText(), vidrios.getText(), transmisión.getText(), String.valueOf(Camioneta.nextCode())};
        Vehículo camioneta = new Camioneta(datos, vendedorCamioneta, rutaImagenCA, datosCamioneta);
        if (Extras.deserializar("Camioneta") != null) {
            mapaCamioneta = Extras.deserializar("Camioneta");
            ingresoVehículo("Camioneta", mapaCamioneta.containsKey(camioneta.getPlaca()), mapaCamioneta, camioneta);
        } else {
            mapaCamioneta.put(camioneta.getPlaca(), camioneta);
            Extras.serializar(mapaCamioneta, "Camioneta");
            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Camioneta registrado con éxito"); a.show();
        }
    }
    private void createCamión(String[] datos, Vendedor vendedorCamión, String rutaImagenC, HashMap<String, Object> mapaCamión) {
        String[] datosCamión = {tipoCombustible.getText(), vidrios.getText(), transmisión.getText(), String.valueOf(Camión.nextCode())};
        Vehículo camión = new Camión(datos, vendedorCamión, rutaImagenC, datosCamión);
        if (Extras.deserializar("Camión") != null) {
            mapaCamión = Extras.deserializar("Camión");
            ingresoVehículo("Camión", mapaCamión.containsKey(camión.getPlaca()), mapaCamión, camión);
        } else {
            mapaCamión.put(camión.getPlaca(), camión);
            Extras.serializar(mapaCamión, "Camión");
            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Camión registrado con éxito"); a.show();
        }
    }
    private void createMotocicleta(String[] datos, Vendedor vendedorMotocicleta, String rutaImagenM, HashMap<String, Object> mapaMotocicleta) {
        String[] datosMotocicleta = {tipoCombustible.getText(), transmisión.getText(), String.valueOf(Motocicleta.nextCode())};
        Vehículo motocicleta = new Motocicleta(datos, vendedorMotocicleta, rutaImagenM, datosMotocicleta);
        if (Extras.deserializar("Motocicleta") != null) {
            mapaMotocicleta = Extras.deserializar("Motocicleta");
            ingresoVehículo("Motocicleta", mapaMotocicleta.containsKey(motocicleta.getPlaca()), mapaMotocicleta, motocicleta);
        } else {
            mapaMotocicleta.put(motocicleta.getPlaca(), motocicleta);
            Extras.serializar(mapaMotocicleta, "Motocicleta");
            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Motocicleta registrada con éxito"); a.show();
        }
    }
    private void ingresoVehículo(String string, boolean existe, HashMap<String, Object> mapaVehículo, Vehículo vehiculo) {
        if (existe) {
            Alert a = new Alert(Alert.AlertType.WARNING, string + "ya en el sistema"); a.show();
        } else {
            mapaVehículo.put(vehiculo.getPlaca(), vehiculo);
            Extras.serializar(mapaVehículo, string);
            Alert a = new Alert(Alert.AlertType.CONFIRMATION, string + "registrada con éxito"); a.show();
        }
    }
    
    private void ingresoNuevaPersona(HashMap<String, Object> mapaPersonas, String[] datosPersonas, String string) {
        mapaPersonas = Extras.deserializar(string);
        Persona persona = new Persona(datosPersonas);
        if (mapaPersonas.containsKey(Correo.getText())) {
            Alert a = new Alert(Alert.AlertType.WARNING, "Correo en uso actualmente"); a.show();
        } else {
            mapaPersonas.put(persona.getEmail(), persona);
            Extras.serializar(mapaPersonas, "Persona");
            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Usuario creado correctamente"); a.show();
        }
    }
    private void ingresoPersonas(HashMap<String, Object> mapaPersonas, String[] datosPersonas, String string) {
        Persona persona = new Persona(datosPersonas);
        mapaPersonas.put(persona.getEmail(), persona);
        Extras.serializar(mapaPersonas, string);
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Usuario creado correctamente");
        a.show();
    }
    private void registroMain(HashMap<String, Object> mapaMain, String[] datosMain) {
        if (Extras.deserializar("Persona") != null) {
            ingresoNuevaPersona(mapaMain, datosMain, "Persona");
        } else {
            ingresoPersonas(mapaMain, datosMain, "Persona");
        }
    }
    private void ingresoNuevosCompradores(HashMap<String, Object> mapaCompradores, String[] datosCompradores, String string) {
        mapaCompradores = Extras.deserializar(string);
        Persona comprador = new Comprador(datosCompradores, Comprador.nextCode());
        if (mapaCompradores.containsKey(Correo.getText())) {
            Alert a = new Alert(Alert.AlertType.WARNING, "Correo en uso actualmente"); a.show();
        } else {
            mapaCompradores.put(comprador.getEmail(), comprador);
            Extras.serializar(mapaCompradores, string);
            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Usuario creado correctamente");
            a.show();
        }
    }
    private void ingresoCompradores(HashMap<String, Object> mapaCompradores, String[] datosCompradores, String string) {
        Persona comprador = new Comprador(datosCompradores, Comprador.nextCode());
        mapaCompradores.put(comprador.getEmail(), comprador);
        Extras.serializar(mapaCompradores, string);
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Usuario creado correctamente");
        a.show();
    }
    private void registroComprador(HashMap<String, Object> mapaComprador, String[] datosComprador) {
        if (Extras.deserializar("Comprador") != null) {
            ingresoNuevosCompradores(mapaComprador, datosComprador, "Comprador");
        } else {
            ingresoCompradores(mapaComprador, datosComprador, "Comprador");
        }
    }
    private void ingresoNuevosVendedores(HashMap<String, Object> mapaVendedores, String[] datosVendedores, String string) {
        mapaVendedores = Extras.deserializar(string);
        Persona vendedor = new Vendedor(datosVendedores, Comprador.nextCode());
        if (mapaVendedores.containsKey(Correo.getText())) {
            Alert a = new Alert(Alert.AlertType.WARNING, "Correo en uso actualmente"); a.show();
        } else {
            mapaVendedores.put(vendedor.getEmail(), vendedor);
            Extras.serializar(mapaVendedores, string);
            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Usuario creado correctamente");
            a.show();
        }
    }
    private void ingresoVendedores(HashMap<String, Object> mapaVendedores, String[] datosVendedores, String string) {
        Persona vendedor = new Vendedor(datosVendedores, Comprador.nextCode());
        mapaVendedores.put(vendedor.getEmail(), vendedor);
        Extras.serializar(mapaVendedores, string);
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Usuario creado correctamente");
        a.show();
    }
    private void registroVendedor(HashMap<String, Object> mapaMain, String[] datosMain) {
        if (Extras.deserializar("Comprador") != null) {
            ingresoNuevosVendedores(mapaMain, datosMain, "Comprador");
        } else {
            ingresoVendedores(mapaMain, datosMain, "Comprador");
        }
    }
    private void registrosPorRol(String rol, HashMap<String, Object> mapaRoles, String[] datosRoles) {
        switch (rol) {
            case "Comprador && Vendedor":
                registroMain(mapaRoles, datosRoles); break;
            case "Comprador":
                registroComprador(mapaRoles, datosRoles); break;
            case "Vendedor":
                registroVendedor(mapaRoles, datosRoles); break;
            default:
                Alert a = new Alert(Alert.AlertType.ERROR, "Elija un tipo"); a.show();
                break;
        }
    }
    @FXML
    private void Registro() {
        if (!validacionesDatosRegistro()) {
            String[] datosP = {Nombre.getText(), Apellido.getText(), Correo.getText(), Organización.getText(), Usuario.getText(), Contraseña.getText()};   
            if ((String)cbx1.getValue() != null) {
                String tipo = (String)cbx1.getValue();
                Alert a;
                if (Contraseña.getText().equals(ConfContraseña.getText())) {
                    HashMap<String, Object> mapa = new HashMap<String, Object>();
                    registrosPorRol(tipo, mapa, datosP);
                } else {
                    a = new Alert(Alert.AlertType.WARNING, "Las contraseñas no son iguales..."); a.show();
                }    
            } else {
                Alert a = new Alert(AlertType.WARNING, "Seleccione un Rol"); a.show();
            }
        } else {
            Alert a = new Alert(AlertType.ERROR, "Rellene los recuadros"); a.show();
        }
    }
    @FXML
    private void Logout() {
        try {
            FXMLLoader fxml = App.loadFXMLLoad("Login");
            App.setRoot(fxml);
            LoginController lc = fxml.getController();
            lc.setBasics(persona.getEmail());
        } catch (IOException e) {
            Alert a = new Alert(AlertType.ERROR, e.toString()); a.show();
        }
    }
    
    
    
    @FXML
    private void Clear() {
        placa.clear();
        marca.clear();
        modelo.clear();
        tipoMotor.clear();
        año.clear();
        recorrido.clear();
        color.clear();
        tipoCombustible.clear();
        vidrios.clear();
        transmisión.clear();
        precio.clear();
    }
    @FXML
    private void setEditableFalse() {
        recini.setEditable(filtroREC.isSelected());
        recfin.setEditable(filtroREC.isSelected());
        añoini.setEditable(filtroAÑO.isSelected());
        añofin.setEditable(filtroAÑO.isSelected());
        preini.setEditable(filtroPRE.isSelected());
        prefin.setEditable(filtroPRE.isSelected());
    }
    private String[] setParametros() {
        String[] parametros = new String[6];
        parametros[0] = recini.getText();
        parametros[1] = recfin.getText();
        parametros[2] = añoini.getText();
        parametros[3] = añofin.getText();
        parametros[4] = preini.getText();
        parametros[5] = preini.getText();
        return parametros;
    }
    private void setVehículosInTableView(ArrayList<Vehículo> lista) {
        ArrayList<VehículoTableView> listaVehículos = new ArrayList<>();
        for(Vehículo v: lista) {
            listaVehículos.add(castVehículoToTableView(v));
        }
        tv.setItems(FXCollections.observableArrayList(listaVehículos));
        tvplaca.setCellValueFactory(new PropertyValueFactory<VehículoTableView, String>("Placa"));
        tvmarca.setCellValueFactory(new PropertyValueFactory<VehículoTableView, String>("Marca"));
        tvmodelo.setCellValueFactory(new PropertyValueFactory<VehículoTableView, String>("Modelo"));
        tvmotor.setCellValueFactory(new PropertyValueFactory<VehículoTableView, String>("motor"));
        tvprecio.setCellValueFactory(new PropertyValueFactory<VehículoTableView, String>("Precio"));
        tvaño.setCellValueFactory(new PropertyValueFactory<VehículoTableView, String>("Año"));

    }
    @FXML
    private void searchVehículos() {
        if ((String)cbxVehículo.getValue() != null) {
            // LISTA DE VEHÍCULOS QUE CUMPLEN LOS REQUERIMIENTOS DE BÚSQUEDA
            ArrayList<Vehículo> listaVehículos = Vehículo.buscarVehículo(setParametros(), (String)cbxVehículo.getValue());
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
    private void setImageView(int selection, JFileChooser jf) {
        if (selection == JFileChooser.APPROVE_OPTION) {                
            File fichero = jf.getSelectedFile();
            ruta = fichero.getAbsolutePath();
            try {
                nuevoV.setImage(new Image(new FileInputStream(ruta)));
            } catch (Exception e) {
                Alert a = new Alert(AlertType.ERROR, "Error al cargar la imagen, " + e.toString()); a.show();
            }
        }
    }
    @FXML 
    public void SubirFoto(){
        try {            
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF", "jpg", "gif");
            fileChooser.setFileFilter(filter);
            int seleccion = fileChooser.showOpenDialog((Component)subir.getUserData());
            setImageView(seleccion, fileChooser);
            Alert a = new Alert(AlertType.CONFIRMATION, "Imagen cargada con éxito");
            a.show();
        } catch (HeadlessException | SecurityException e) {
            Alert a = new Alert(AlertType.ERROR, "Error al cargar la imagen... \n" + e.toString());
            a.show();
        }
        
    }
    private Vehículo obtenerVehículoPorPlaca(String placa, String tipoV) {
        HashMap<String, Object> mapaV = Extras.deserializar(tipoV); System.out.println(mapaV.toString());
        Vehículo veh = (Vehículo) mapaV.get(placa); System.out.println(veh.toString());
        return veh;
//return (Vehículo) mapaV.get(placa);
    }
    @FXML
    private void Ofertar() {
        if (!validacionesDatosOferta()) {
            Vehículo vehiculo = obtenerVehículoPorPlaca(placaoferta.getText(), (String)cbxVehículo.getValue());
            of = new Oferta(Oferta.nextCode(), (Comprador) crearComprador(persona), Double.valueOf(preciooferta.getText()), vehiculo.getVendedor(), vehiculo);
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
    private void makeOffert(Vehículo v, HashMap<String, Object> mapaOfertas) {
        v.setIDOferta(of.getIDOferta());
        of.setV(v);
        mapaOfertas.put(String.valueOf(of.getIDOferta()), of);
        Extras.serializar(mapaOfertas, "Oferta");
        Alert a = new Alert(AlertType.CONFIRMATION, "Oferta realizada con éxito"); a.show();
    }
    private Vehículo obtenerVehPorPlaca(String placa) {
        String[] listaV = {"Auto", "Camioneta", "Camión", "Motocicleta"};
        for (String tipoV:listaV) {
            HashMap<String, Object> mapaV = Extras.deserializar(tipoV);
            return (Vehículo) mapaV.get(placa);
        }
        return null;
    }
    @FXML
    private void aceptarOferta() {
        if (ofertarPLaca.getText().length() != 0) {
            HashMap<String, Object> mapaOfertas = new HashMap<String, Object>();
            Vehículo v1 = obtenerVehPorPlaca(ofertarPLaca.getText());
            Oferta of = (Oferta) mapaOfertas.get(String.valueOf(v1.getIDOferta()));
            Vendedor vend = of.getVendedor();
//            of.sendMailOferta(com.getEmail(), vend.getNombre(), vend.getApellido());
        } else {
            Alert a = new Alert(AlertType.ERROR, "Ingrese una placa para ofertar"); a.show();
        }
    }
    private void setReferenciasColumnView() {
        tvoplaca.setCellValueFactory(new PropertyValueFactory<VehículoTableView, String>("Placa"));
        tvomarca.setCellValueFactory(new PropertyValueFactory<VehículoTableView, String>("Marca"));
        tvomodelo.setCellValueFactory(new PropertyValueFactory<VehículoTableView, String>("Modelo"));
        tvomotor.setCellValueFactory(new PropertyValueFactory<VehículoTableView, String>("motor"));
        tvoprecio.setCellValueFactory(new PropertyValueFactory<VehículoTableView, String>("Precio"));
        tvoemail.setCellValueFactory(new PropertyValueFactory<VehículoTableView, String>("Email"));
    }
    @FXML
    private void showOfertas() {
        ArrayList<VehículoTableView> listab = new ArrayList<>();
        if (Extras.deserializar("Oferta") != null) {
            HashMap<String, Object> mapaOfertas = Extras.deserializar("Oferta");
            mapaOfertas.entrySet().stream().map((entry) -> (Oferta)entry.getValue()).filter((of) -> (of.getEmailVendedor().equals(persona.getEmail()))).forEachOrdered((of) -> {
                listab.add(castVehículoToTableView(of.getVehículo()));
            });
            try {
                tvo.setItems(FXCollections.observableArrayList(listab));
                setReferenciasColumnView();
            } catch (Exception e) {
                System.out.println(e.toString());
                System.out.println(e.getMessage());
            }
        } else {
            Alert a = new Alert(AlertType.INFORMATION, "De momento no tiene ofertas"); a.show();
        }
        
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
    @FXML
    private void Actualizar() {
        try {
            FXMLLoader fxml = App.loadFXMLLoad("Registrarse");            
            App.setRoot(fxml);
            RegistrarseController rc = fxml.getController();
            rc.setModoActualizar(persona, "Comprador && Vendedor");
        } catch (IOException e) {
            Alert a = new Alert(AlertType.ERROR, e.toString()); a.show();
        }
    }
    private boolean validacionesDatosAuto() {
        return placa.getText().length() == 0 &&
               marca.getText().length() == 0 &&
               modelo.getText().length() == 0 &&
               tipoMotor.getText().length() == 0 && 
               año.getText().length() == 0 &&
               recorrido.getText().length() == 0 && 
               color.getText().length() == 0 && 
               tipoCombustible.getText().length() == 0 && 
               vidrios.getText().length() == 0 && 
               transmisión.getText().length() == 0 && 
               precio.getText().length() == 0; 
    }
    private boolean validacionesDatosRegistro() {
        // TODOS STRING
        return Nombre.getText().length() == 0 && 
               Apellido.getText().length() == 0 && 
               Correo.getText().length() == 0 && 
               Organización.getText().length() == 0 && 
               Usuario.getText().length() == 0; 
    }
    private boolean validacionesDatosOferta() {
        return placaoferta.getText().length() == 0 && //String
               preciooferta.getText().length() == 0; // Int
    }
    private void setInfoUsuario() {
        nom.setText(persona.getNombre());
        ape.setText(persona.getApellido());
        cor.setText(persona.getEmail());
        org.setText(persona.getOrganización());
        user.setText(persona.getUsuario());
        rol.setText("Comprador && Vendedor");
    }
    public void setBasics(Persona persona) {
        this.persona = persona;
        setInfoUsuario();
    }
    
}
