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
 * @author danny
 */
public class VendedorController implements Initializable {
    
    @FXML
    private TextField Nombrevendedor;
    @FXML
    private TextField Apellidovendedor;
    @FXML
    private TextField  Correovendedor;
    @FXML
    private TextField Orgvendedor;
    @FXML
    private TextField Nickvendedor;
    @FXML
    private Text confvendedor;
    @FXML
    private TextField nomvendedor;
    @FXML
    private TextField apevendedor;
    @FXML
    private TextField orgvendedor;
    @FXML
    private TextField uservendedor;
    @FXML
    private TextField corvendedor;
    @FXML
    private TextField rolvendedor;
    @FXML
    private PasswordField Passwordvendedor;
    @FXML
    private PasswordField Password1vendedor;
    @FXML
    private TextField placavendedor;
    @FXML
    private TextField marcavendedor;
    @FXML
    private TextField modelovendedor;
    @FXML
    private TextField tipoMotorvendedor;
    @FXML
    private TextField añovendedor;
    @FXML
    private TextField recorridovendedor;
    @FXML
    private TextField colorvendedor;
    @FXML
    private TextField tipoCombustiblevendedor;
    @FXML
    private TextField vidriosvendedor;
    @FXML
    private TextField transmisiónvendedor;
    @FXML
    private TextField preciovendedor;
    @FXML
    private ComboBox cbxvendedor;
    @FXML
    private ImageView nuevoVvendedor;
    @FXML
    private Button subirve;
    @FXML
    private CheckBox filtroRECvendedor;
    @FXML
    private CheckBox filtroAÑOvendedor;
    @FXML
    private CheckBox filtroPREvendedor;
    @FXML
    private TableView<VehículoTableView> tvOfertavendedor;
    @FXML
    private TableColumn<VehículoTableView, String> tvoplacavendedor;
    @FXML
    private TableColumn<VehículoTableView, String> tvomarcavendedor;
    @FXML
    private TableColumn<VehículoTableView, String> tvomodelovendedor;
    @FXML
    private TableColumn<VehículoTableView, String> tvomotorvendedor;
    @FXML
    private TableColumn<VehículoTableView, String> tvopreciovendedor;
    @FXML
    private TableColumn<VehículoTableView, String> tvoemailvendedor;
    @FXML
    private ImageView imageOfertavendedor;
    @FXML
    private TextField ofertaPlacavendedor;
    private Vendedor v;
    private String rutaImagen;
    private Oferta ofvendedor;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setComboBoxTipoV(); formatoStrings(); formatoNumeros(); showOfertas();
        showOfertas();
    }
    private void formatoStrings() {
        Nombrevendedor.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        Apellidovendedor.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        Correovendedor.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,40}"))? condicion: null ));
        Orgvendedor.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        Nickvendedor.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        Passwordvendedor.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        Password1vendedor.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        placavendedor.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        marcavendedor.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        modelovendedor.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        tipoMotorvendedor.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        colorvendedor.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        tipoCombustiblevendedor.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
        transmisiónvendedor.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches(".{0,15}"))? condicion: null ));
    }
    private void formatoNumeros() {
        añovendedor.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches("[0-9]{0,9}"))? condicion: null ));
        recorridovendedor.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches("[0-9]{0,9}"))? condicion: null ));
        preciovendedor.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches("[0-9]{0,9}"))? condicion: null ));
        vidriosvendedor.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches("[0-9]{0,9}"))? condicion: null ));
    }
    @FXML
    private void setConf(KeyEvent event) {
        if (Passwordvendedor.getText().equals(Password1vendedor.getText())) {
            confvendedor.setVisible(true);
        } else {
            confvendedor.setVisible(false);
        }        
    }
    public void setBasics(Persona ven) {
        this.v = (Vendedor) ven;
        setInfoUsuario();
    }
    private void setImageView(int selection, JFileChooser jf) {
        if (selection == JFileChooser.APPROVE_OPTION) {                
            File fichero = jf.getSelectedFile();
            rutaImagen = fichero.getAbsolutePath();
            try {
                nuevoVvendedor.setImage(new Image(new FileInputStream(rutaImagen)));
            } catch (Exception e) {
                Alert a = new Alert(AlertType.ERROR, "Error al cargar la imagen, " + e.toString());
                a.show();
            }
        }
    }
    @FXML //SI FALLA CAMBIAR ActionEvent por el de awt
    public void SubirFoto(){
        try {            
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF", "jpg", "gif");
            fileChooser.setFileFilter(filter);
            int seleccion = fileChooser.showOpenDialog((Component)subirve.getUserData());
            setImageView(seleccion, fileChooser);
            Alert a = new Alert(AlertType.CONFIRMATION, "Imagen cargada con éxito");
            a.show();
        } catch (HeadlessException | SecurityException e) {
            Alert a = new Alert(AlertType.ERROR, "Error al cargar la imagen... \n" + e.toString());
            a.show();
        }
        
    }
    
    private void setInfoUsuario() {
        nomvendedor.setText(v.getNombre());
        apevendedor.setText(v.getApellido());
        corvendedor.setText(v.getEmail());
        orgvendedor.setText(v.getOrganización());
        uservendedor.setText(v.getUsuario());
        rolvendedor.setText("Vendedor");
    }
    
    @FXML
    private void Clear() {
        placavendedor.clear();
        marcavendedor.clear();
        modelovendedor.clear();
        tipoMotorvendedor.clear();
        añovendedor.clear();
        recorridovendedor.clear();
        colorvendedor.clear();
        tipoCombustiblevendedor.clear();
        vidriosvendedor.clear();
        transmisiónvendedor.clear();
        preciovendedor.clear();
    }
    private boolean validacionesDatosAuto() {
        return placavendedor.getText().length() == 0 &&          
               marcavendedor.getText().length() == 0 &&          
               modelovendedor.getText().length() == 0 &&            
               tipoMotorvendedor.getText().length() == 0 &&      
               añovendedor.getText().length() == 0 &&              
               recorridovendedor.getText().length() == 0 &&          
               colorvendedor.getText().length() == 0 &&             
               tipoCombustiblevendedor.getText().length() == 0 &&    
               vidriosvendedor.getText().length() == 0 &&            
               transmisiónvendedor.getText().length() == 0 &&   
               preciovendedor.getText().length() == 0;              
    }
    private boolean validacionesDatosVendedor() {
        return Nombrevendedor.getText().length() == 0 &&   
               Apellidovendedor.getText().length() == 0 &&        
               Correovendedor.getText().length() == 0 &&       
               Orgvendedor.getText().length() == 0 &&         
               Nickvendedor.getText().length() == 0 &&          
               Passwordvendedor.getText().length() == 0 &&         
               Password1vendedor.getText().length() == 0;
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
    @FXML
    private void RegistrarVehículo() {
        if (rutaImagen.length() == 0) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Suba una imagen por favor"); a.show();
        } else if ((String)cbxvendedor.getValue() != null && rutaImagen.length() != 0) {
            if (!validacionesDatosAuto()) {
                String[] datosV = {placavendedor.getText(), marcavendedor.getText(), modelovendedor.getText(), añovendedor.getText(), recorridovendedor.getText(), colorvendedor.getText(), preciovendedor.getText(), tipoMotorvendedor.getText()};
                HashMap<String, Object> mapa = new HashMap<String, Object>();
                creaciónVehículos((String)cbxvendedor.getValue(), datosV, v, rutaImagen, mapa);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Datos incorrectos"); a.show();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Seleccione un tipo de Vehículo"); a.show();
        }
    }
    @FXML
    private void setVidriosVendedorToNull() {
        if (cbxvendedor.getValue().toString().equals("Motocicleta")) {
            vidriosvendedor.setText("--");
            vidriosvendedor.setEditable(false);
        } else {
            vidriosvendedor.setEditable(true);
        }
    }
    //setVidriosVendedorToNull
    private void createAuto(String[] datos, Vendedor vendedorAuto, String rutaImagenA, HashMap<String, Object> mapaAuto) {
        String[] datosAuto = {tipoCombustiblevendedor.getText(), vidriosvendedor.getText(), transmisiónvendedor.getText(), String.valueOf(Auto.nextCode())};
        Vehículo auto = new Auto(datos, vendedorAuto, rutaImagenA, datosAuto);
        if (Extras.deserializar("Auto") != null) {
            mapaAuto = Extras.deserializar("Auto");
            ingresoVehículo("Auto", mapaAuto.containsKey(auto.getPlaca()), mapaAuto, auto);
        } else {
            mapaAuto.put(auto.getPlaca(), auto);
            Extras.serializar(mapaAuto, "Auto");
            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Auto registrado con éxito"); a.show();
        }
    }
    private void createCamioneta(String[] datos, Vendedor vendedorCamioneta, String rutaImagenCA, HashMap<String, Object> mapaCamioneta) {
        String[] datosCamioneta = {tipoCombustiblevendedor.getText(), vidriosvendedor.getText(), transmisiónvendedor.getText(), String.valueOf(Camioneta.nextCode())};
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
        String[] datosCamión = {tipoCombustiblevendedor.getText(), vidriosvendedor.getText(), transmisiónvendedor.getText(), String.valueOf(Camión.nextCode())};
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
        String[] datosMotocicleta = {tipoCombustiblevendedor.getText(), transmisiónvendedor.getText(), String.valueOf(Motocicleta.nextCode())};
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
    @FXML
    private void Actualizar() {
        try {
            FXMLLoader fxml = App.loadFXMLLoad("Registrarse");            
            App.setRoot(fxml);
            RegistrarseController rc = fxml.getController();
            rc.setModoActualizar((Persona)v, "Vendedor");
        } catch (IOException e) {
            Alert a = new Alert(AlertType.ERROR, e.toString()); a.show();
        }
    }
    private void setComboBoxTipoV() {
        ArrayList<String> lista = new ArrayList<>();
        lista.add("Auto");
        lista.add("Camioneta"); lista.add("Camión"); lista.add("Motocicleta");
        cbxvendedor.setItems(FXCollections.observableArrayList(lista));
    }
    @FXML
    private void Logout() {
        try {
            FXMLLoader fxml = App.loadFXMLLoad("Login");
            App.setRoot(fxml);
            LoginController lc = fxml.getController();
            lc.setBasics(v.getEmail());
        } catch (IOException e) {
            Alert a = new Alert(AlertType.ERROR, e.toString()); a.show();
        }
    }
    private void ingresarVendedor(boolean existe, HashMap<String, Object> mapaVendedor, Persona vendedor) {
        if (existe) {
            Alert a = new Alert(AlertType.ERROR, "Usuario ya existente"); a.show();
        } else {
            mapaVendedor.put(vendedor.getEmail(), vendedor);
            Extras.serializar(mapaVendedor, "Vendedor");
            Alert a = new Alert(AlertType.CONFIRMATION, "Usuario ingresado con éxito"); a.show();
        }
    }
    @FXML
    private void Registro() {
        if (Passwordvendedor.getText().equals(Password1vendedor.getText()) && !validacionesDatosVendedor()) {
            String[] datos_persona = {Nombrevendedor.getText(), Apellidovendedor.getText(), Correovendedor.getText(), Orgvendedor.getText(), Nickvendedor.getText(), Passwordvendedor.getText()};
            Persona vendedor = new Vendedor(datos_persona, Vendedor.nextCode());
            HashMap<String, Object> mapavendedor = new HashMap<String, Object>();
            if (Extras.deserializar("Vendedor") != null) {
                mapavendedor = Extras.deserializar("Vendedor");
                ingresarVendedor(mapavendedor.containsKey(vendedor.getEmail()),mapavendedor, vendedor);
            } else {
                mapavendedor.put(vendedor.getEmail(), vendedor);
                Extras.serializar(mapavendedor, "Vendedor");
                Alert a = new Alert(AlertType.CONFIRMATION, "Usuario ingresado con éxito"); a.show();
            }
        } else if (!Passwordvendedor.getText().equals(Password1vendedor.getText())) {
            Alert a = new Alert(AlertType.ERROR, "Contraseñas no coincidentes"); a.show();
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
    private void showOfertas() {
        ArrayList<VehículoTableView> listab = new ArrayList<>();
        if (Extras.deserializar("Oferta") != null) {
            HashMap<String, Object> mapaOfertas = Extras.deserializar("Oferta");
            mapaOfertas.entrySet().stream().map((entry) -> (Oferta)entry.getValue()).filter((of) -> (of.getEmailVendedor().equals(v.getEmail()))).forEachOrdered((of) -> {
                listab.add(castVehículoToTableView(of.getVehículo()));
            });
            try {
                tvOfertavendedor.setItems(FXCollections.observableArrayList(listab));
                setReferenciasColumnView();
            } catch (Exception e) {
                Alert a = new Alert(AlertType.ERROR, e.toString()); a.show();
            }
        } else {
            Alert a = new Alert(AlertType.INFORMATION, "De momento no tiene ofertas"); a.show();
        }
        
    }
     private void setReferenciasColumnView() {
        tvoplacavendedor.setCellValueFactory(new PropertyValueFactory<VehículoTableView, String>("Placa"));
        tvomarcavendedor.setCellValueFactory(new PropertyValueFactory<VehículoTableView, String>("Marca"));
        tvomodelovendedor.setCellValueFactory(new PropertyValueFactory<VehículoTableView, String>("Modelo"));
        tvomotorvendedor.setCellValueFactory(new PropertyValueFactory<VehículoTableView, String>("motor"));
        tvopreciovendedor.setCellValueFactory(new PropertyValueFactory<VehículoTableView, String>("Precio"));
        tvoemailvendedor.setCellValueFactory(new PropertyValueFactory<VehículoTableView, String>("Email"));
    }
     @FXML
    private void aceptarOferta() {
        if (ofertaPlacavendedor.getText().length() != 0) {
            HashMap<String, Object> mapaOfertas = new HashMap<String, Object>();
            Vehículo v1 = obtenerVehPorPlaca(ofertaPlacavendedor.getText());
            Oferta of = (Oferta) mapaOfertas.get(String.valueOf(v1.getIDOferta()));
            Vendedor vend = of.getVendedor();
//            of.sendMailOferta(v.getEmail(), vend.getNombre(), vend.getApellido());
        } else {
            Alert a = new Alert(AlertType.ERROR, "Ingrese una placa para ofertar"); a.show();
        }
    }
    private Vehículo obtenerVehPorPlaca(String placa) {
        String[] listaV = {"Auto", "Camioneta", "Camión", "Motocicleta"};
        for (String tipoV:listaV) {
            HashMap<String, Object> mapaV = Extras.deserializar(tipoV);
            return (Vehículo) mapaV.get(placa);
        }
        return null;
    }
}