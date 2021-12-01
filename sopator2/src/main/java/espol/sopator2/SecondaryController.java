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

/**
 * FXML Controller class
 *
 * @author danny
 */
public class SecondaryController implements Initializable {

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
    
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
    }
    public void setSopator(Sopator s) {
        this.sopator = s;
        setData();
        
        generarSopa();
        grid.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 1px;");
    }
    private void setData() {
        this.filas = sopator.getFilas();
        this.columnas = sopator.getColumnas();
    }
    
    public void generarSopa() {
        //Limpio la Sopa Anterior
        matriz.getChildren().clear();
        left.getChildren().clear();
        right.getChildren().clear();
        top.getChildren().clear();
        bot.getChildren().clear();

        //Creo mi gridpane que muestra mi sopa de letras
        grid = new GridPane();

        //Obtengo mi sopa de letras que lleva el control por detras del javafx
        

        //tamaños dinámicos
        double width = 500 / columnas;
        double height = 500 / filas;
        double tamaño_letra = (width >= height) ? height / 2 : width / 2;

        //Recorro mi sopa por fila y columna, uso for porque es esencial guardar las coordenadas (x,y) para el gridpana
        for (int y = 0; y < sopator.getSopa_Letras().size(); y++) {

            CircularLinkedList<Character> fila = sopator.getFila(y); //Obtengo mi lista que hace de fila

            int n_fila = y + 1;

            //creación flechas para mover filas
            StackPane leftArrow = crearFlechaFila(height, tamaño_letra, n_fila, "⮜", true);
            left.getChildren().add(leftArrow);

            StackPane rightArrow = crearFlechaFila(height, tamaño_letra, n_fila, "⮞", false);
            right.getChildren().add(rightArrow);

            // creación casillas con letras
            for (int x = 0; x < fila.size(); x++) {

                String letra = String.valueOf(sopator.getLetra(y,x));

                StackPane pane = crearCasilla(width, height, tamaño_letra, letra);

                grid.add(pane, x, y); //Agrego al gridpane el contener en la posicion X,Y

            }
        }

        // Creación flechas para mover Columnas
        for (int i = 0; i < sopator.getFila(0).size(); i++) {

            int n_columna = i + 1;

            StackPane down_arrow = crearFlechaColumna(width, tamaño_letra, n_columna, "⮟", false);
            bot.getChildren().add(down_arrow);

            StackPane up_arrow = crearFlechaColumna(width, tamaño_letra, n_columna, "⮝", true);
            top.getChildren().add(up_arrow);
        }

        matriz.getChildren().add(grid);

    }
    private StackPane crearCasilla(double width, double height, double tamaño_letra, String sd) {

        //Extraigo el valor del objeto letra
        //creación letras
        StackPane pane = new StackPane();
        pane.setPrefSize(width, height);
        pane.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 1px;");
        pane.setCursor(Cursor.HAND);

        Label letra = new Label(sd); //Lo contengo en un label para mostrar
        letra.setMouseTransparent(true);

        letra.setStyle("-fx-font-family: 'Tahoma'; -fx-font-size: " + tamaño_letra + "px;");

        Pane fondo = new Pane();
        fondo.setStyle("-fx-background-color: #5169FF;");
        fondo.setMouseTransparent(true);
        fondo.setOpacity(0.20*5);
        pane.setDisable(true);
        letra.setVisible(false);

        pane.getChildren().add(fondo);
        pane.getChildren().add(letra);
        StackPane.setAlignment(letra, Pos.CENTER);

//        pane.setOnMouseClicked(t -> seleccionarLetra(fondo));

        pane.setOnMouseEntered(e -> mouseEnteredLetter(pane));
        pane.setOnMouseExited(e -> mouseExitedLetter(pane));
        pane.setOnMousePressed(e -> mouseExitedLetter(pane));
        pane.setOnMouseReleased(e -> mouseEnteredLetter(pane));

        return pane;

    }
    private StackPane crearFlechaColumna(double width, double tamaño_letra, int n_columna, String flecha, boolean up) {

        StackPane pane = new StackPane();
        pane.setPrefSize(width, 30);
        pane.setCursor(Cursor.HAND);
        pane.setOpacity(0);

        Label arrow = new Label(flecha);
        arrow.setStyle("-fx-font-size: " + tamaño_letra * 0.8 + "px;");
        arrow.setMouseTransparent(true);

        pane.getChildren().add(arrow);
        StackPane.setAlignment(arrow, Pos.CENTER);

        pane.setOnMouseEntered(e -> mouseEnteredArrow(pane));
        pane.setOnMouseExited(e -> mouseExitedArrow(pane));


        return pane;
    }
    private StackPane crearFlechaFila(double height, double tamaño_letra, int n_fila, String flecha, boolean left) {
        StackPane pane = new StackPane();
        pane.setPrefHeight(height);
        pane.setCursor(Cursor.HAND);
        pane.setOpacity(0);

        Label arrow = new Label(flecha);
        arrow.setStyle("-fx-font-size: " + tamaño_letra + "px;");
        arrow.setMouseTransparent(true);

        pane.getChildren().add(arrow);
        StackPane.setAlignment(arrow, Pos.CENTER_RIGHT);

        pane.setOnMouseEntered(e -> mouseEnteredArrow(pane));
        pane.setOnMouseExited(e -> mouseExitedArrow(pane));

        if (left) {
            pane.setOnMouseClicked(e -> moveRowBackwards(n_fila));
        } else {
            pane.setOnMouseClicked(e -> moveRowForward(n_fila));
        }

        return pane;

    }

    private void mouseEnteredLetter(StackPane p) {

        p.setStyle(p.getStyle() + " -fx-background-color: #BFE1FF;");
    }
    
   private void mouseExitedLetter(StackPane p) {

        p.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 1px;"); 
    }
    private void mouseEnteredArrow(StackPane p) {
        FadeTransition f = new FadeTransition(javafx.util.Duration.millis(250), p);
        f.setFromValue(p.getOpacity());
        f.setToValue(1);
        f.play();
    }

    private void mouseExitedArrow(StackPane p) {
        FadeTransition f = new FadeTransition(javafx.util.Duration.millis(250), p);
        f.setFromValue(p.getOpacity());
        f.setToValue(0);
        f.play();
    }

    private void moveRowForward(int n_fila) {
        sopator.getFila(n_fila).desplazarDer();
        refrescarSopa();
    }
    private void moveRowBackwards(int n_fila) {
        sopator.getFila(n_fila).desplazarIzq();
        refrescarSopa();
    }
    
    private void refrescarSopa() {
        generarSopa();
        grid.setStyle("-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 1px;");
//        letras = new ArrayList ();
    }
    void AbrirVentana() {
        VBox rootNuevaVentana = new VBox();
        Label label = new Label();

        Button b = new Button("Ok");

        rootNuevaVentana.getChildren().addAll(label, b);
        rootNuevaVentana.setAlignment(Pos.CENTER);
        rootNuevaVentana.setSpacing(20);
        rootNuevaVentana.setPadding(new Insets(10, 15, 10, 15));
        Stage s = new Stage();
        Scene sce = new Scene(rootNuevaVentana);
        b.setOnAction(t -> s.close());
        s.setScene(sce);
        s.setTitle("Mensaje");
        s.show();

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
