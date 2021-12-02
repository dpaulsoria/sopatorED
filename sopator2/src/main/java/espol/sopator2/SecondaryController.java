/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.sopator2;

import collection.CircularLinkedList;
import generator.Sopator;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import util.Letra;

/**
 * FXML Controller class
 *
 * @author danny
 */
public class SecondaryController {

    @FXML
    private AnchorPane matriz;
    @FXML
    private BorderPane root;
    @FXML
    private VBox left;
    @FXML
    private VBox right;
    @FXML
    private HBox top;
    @FXML
    private HBox bot;
    @FXML
    private GridPane grid;
    @FXML
    private RadioButton desplazar;
    @FXML
    private TextField points;
    @FXML
    private TextField changes;
    @FXML
    private Button revisar;
    @FXML
    private Button revolver;
    @FXML
    private ToggleButton agg;
    @FXML
    private ToggleButton del;
    @FXML
    private RadioButton col;
    @FXML
    private RadioButton fila;
    @FXML
    private Button giveup;
    @FXML
    private Label vidasT;
    
    private Scene scene;
    private Sopator sopator;
    private int filas;
    private int columnas;
    private int vidas=3;
    private double alto;
    private double ancho;
    private final int VGAP = 10;
    private final int HGAP = 10;
    private int cambios;
    private int puntos;
    private final int minSize = 65;
    private double minSize_letras;
    

    /**
     * Initializes the controller class.
     */
    
    public void SecondaryController(Stage stage) {
        vidasT.setText(Integer.toString(vidas));
        this.scene = new Scene(root, 600,600);
        generarSopa();
        
        
        
    }
    public void setSopator(Sopator s) {
        this.sopator = s;
        setData();
        grid = new GridPane();
        generarSopa();
        grid.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 1px;");
    }
    private void setData() {
        this.filas = sopator.getFilas();
        this.columnas = sopator.getColumnas();
    }
    
    public void generarSopa() {
        String letra;
        for (int i = 0; i<sopator.getSopa_Letras().size(); i++) {
            CircularLinkedList<Letra> fila = sopator.getFila(i);
            for (int j = 0; j<fila.size(); j++) {
                grid.add(new Label(sopator.getLetra(i,j).toString()), j, i);
            }            
        }    
        grid.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 1px;");
        matriz.getChildren().add(grid);

        this.scene = new Scene(root, 1000,1500);
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(25*4);
        grid.setHgap(50*4);
        
    }
    public void salir() throws IOException{
        App.setRoot("primary");
    }
    
    public Scene getScene() {
        return scene;
    }

    
  
    
}
