/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.sopator2;

import collection.CircularLinkedList;
import generator.Sopator;
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
    private TextField life;
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
    
    private Scene scene;
    private Sopator sopator;
    private int filas;
    private int columnas;
    private double alto;
    private double ancho;
    private final int VGAP = 10;
    private final int HGAP = 10;
    private int cambios;
    private int vidas;
    private int puntos;
    private final int minSize = 65;
    private double minSize_letras;

    /**
     * Initializes the controller class.
     */
    
    public void SecondaryController(Stage stage) {
        generarSopa();
        this.scene = new Scene(root, 600,600);
        
        
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
    
    public Scene getScene() {
        return scene;
    }

//    @Override
//    public void initialize(URL url, ResourceBundle rb) {        
//        cambios = 2;
//        vidas = 3;
//        
//    }
//    
//    private void cleanBoard() {
//        matriz.getChildren().clear();
//        right.getChildren().clear();
//        left.getChildren().clear();
//        top.getChildren().clear();
//        bot.getChildren().clear();
//    }
//    
//    private void getData() {
//        filas = sopator.getFilas();
//        columnas = sopator.getColumnas();
//                
//        //tamaños dinámicos
//        alto = 500 / filas;
//        ancho = 500 / columnas;
//        if (ancho < alto)
//            minSize_letras = ancho / 2;
//        else
//            minSize_letras = alto / 2;
//
//    }
//
//    private boolean isSelected(StackPane p) {
//        return true;
//    }
//    
//    public void setSopator(Sopator s) {
//        sopator = s;
//        cleanBoard();
//        getData();
//        System.out.println(s.toString());
//        grid = new GridPane();
//
//        
//        for (int f = 0; f<sopator.getSopa_Letras().size(); f++) {
//            CircularLinkedList<Character> currentFila = sopator.getFila(f);
//            for (int c = 0; c<currentFila.size(); c++) {
//
//                StackPane celda = new StackPane();
//                Pane background = new Pane();
//                celda.setPrefSize(500,500);
//                celda.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 1px;");
//                celda.setCursor(Cursor.HAND);
//
//                Label letter = new Label(String.valueOf(sopator.getLetra(f, c)));
//                letter.setMouseTransparent(true);
//                
//                celda.setStyle("-fx-font-family: 'Tahoma'; -fx-font-size: 50px;");
//
//                background.setStyle("-fx-background-color: #5169FF;");
//                background.setMouseTransparent(true);
//                
//                celda.getChildren().add(background);
//                celda.getChildren().add(letter);
//                StackPane.setAlignment(letter, Pos.CENTER);
//                grid.add(celda, c, f);
//                
//            }
//        }
//        
//        for (int i = 0; i<columnas; i++) {
//
////            StackPane down_arrow = crearFlechaColumna(width, tamaño_letra, n_columna, "⮟", false);
////            bottomHBox.getChildren().add(down_arrow);
////
////            StackPane up_arrow = crearFlechaColumna(width, tamaño_letra, n_columna, "⮝", true);
////            topHBox.getChildren().add(up_arrow);
//        }
//        
//        matriz.getChildren().add(grid);
//            
//            
//
//    }

  
    
}
