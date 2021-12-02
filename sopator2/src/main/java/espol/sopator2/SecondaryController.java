/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.sopator2;

import collection.ArrayList;
import collection.CircularLinkedList;
import generator.Sopator;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
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
import util.Pair;
import util.Palabra;

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
    private GridPane grid;
    @FXML
    private RadioButton desplazar;
    @FXML
    private Label points;
    @FXML
    private Label changes;
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
    private Label vidasLabel;
    
    private Sopator sopator;
    private int filas;
    private int columnas;
    private int vidas=3;
    private int cambios=2;
    private double alto;
    private double ancho;
    private double letterSize;
    private final int VGAP = 10;
    private final int HGAP = 10;
    private int puntos = 0;
    private final int minSize = 65;
    private double minSize_letras;
    private Letra selec = null;
    private Character id = null;
    private String palabra="";
    private String selectedStyle = " -fx-background-color: #39ad77;";
    private String UnselectedStyle = " -fx-background-color: #39ad77;";
    private String borderStyle = "-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 1px;";
    
    private CircularLinkedList<Letra> seleccionadas = new CircularLinkedList();
    private ArrayList<Pair> coordenadas = new ArrayList<>();
    

    private boolean revisarMode = false;
    @FXML
    private BorderPane root_father;
    
    /**
     * Initializes the controller class.
     */
    
    public void showVidas() {
        vidasLabel.setText(Integer.toString(vidas));
        points.setText(Integer.toString(puntos));
        changes.setText(Integer.toString(cambios));
    }
    
    private void pierdeVida() {
        vidas--;
    }
    
    public void setSopator(Sopator s) {
        this.sopator = s;
        setData();
        grid = new GridPane();
        generarSopa();
        showVidas();
        grid.setStyle(borderStyle);
    
    }
    private void setData() {
        this.filas = sopator.getFilas();
        this.columnas = sopator.getColumnas();
    }
    
    public void generarSopa() {

        ancho = 400 / sopator.getFilas();
        alto = 400 / sopator.getColumnas();
        
        if (ancho >= alto){
            letterSize=alto / 2;
        }else{
            letterSize=ancho / 2;
        }
            
        for (int i = 0; i<sopator.getSopa_Letras().size(); i++) {
            CircularLinkedList<Letra> fila = sopator.getFila(i);
            System.out.println(fila);
            for (int j = 0; j<fila.size(); j++) {
                Letra letra=new Letra(fila.get(j).getLetra());
                letra.setUbi(new Pair(i,j));
                StackPane pane = crearTablero(alto,ancho,letterSize, letra);
                grid.add(pane, j, i);                
            }    
        
        }    
        grid.setStyle(borderStyle); // Le da un doble borde
        matriz.getChildren().addAll(grid);
        grid.getChildren().addAll(root);
       
        
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(25*4);
        grid.setHgap(50*4);
        
    }
    
    @FXML
    public void revisar() {
        if (seleccionadas.size() == 0) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Por favor seleccione para revisar su palabra");
            a.show();
        } else {
            Palabra wordToCheck = Palabra.formarPalabra(seleccionadas);
            if (sopator.confirmarPalabraEnBase(wordToCheck)) {
                wordToCheck.setEncontrada(true);
                añadirPuntos(wordToCheck);
                seleccionadas = new CircularLinkedList();                
            } else {
                quitarPuntos(wordToCheck);
            }
        }
    }
    
    private StackPane crearTablero(double altura,double ancho,double letraT, Letra letraN) {
        Character c = letraN.getLetra();
        StackPane pane = new StackPane();
        pane.setPrefSize(ancho, altura);
        pane.setStyle(borderStyle);
        Label letra = new Label(String.valueOf(c)); 
        letra.setStyle("-fx-font-family: 'Cooper Black'; -fx-font-size: " + letraT + "px;");;
        pane.getChildren().add(letra);
        StackPane.setAlignment(letra, Pos.CENTER);
        
        
        pane.setOnMouseClicked(e -> {
            System.out.println("Clickeo " + letraN);
            if (!letraN.isSelected()) {
                System.out.println("Ahora está seleccionada " + letraN);
                pane.setStyle(pane.getStyle() + selectedStyle);
                letraN.setSelected(true);
                seleccionadas.addLast(letraN);
            } else {
                System.out.println("Ahora NO está seleccionada " + letraN);
                pane.setStyle(UnselectedStyle); // Unselect
                letraN.setSelected(false);
                int i = seleccionadas.indexOf(letraN);
                
                System.out.println("index: " + i);
                seleccionadas.remove(seleccionadas.indexOf(letraN));
            }
            
            System.out.println("Seleccionadas actualmetne " + seleccionadas.toString());
            
        });
        
        pane.setOnMousePressed((MouseEvent me) -> {
//            if(letraN.isSelected()) {
//                letraN.setSelected(false);
//                pane.setStyle(borderStyle); // Unselect
//                seleccionadas.remove(seleccionadas.indexOf(letraN));
//            } else {
//                letraN.setSelected(true);
//                seleccionadas.addLast(letraN);
//                pane.setStyle(pane.getStyle() + selectedStyle);
//            }

        });
        
        
        pane.setOnMouseReleased((MouseEvent me) -> {
            
            

        });
        
        
        
//        pane.setOnMouseClicked(e -> {
//            if (selec == null) {
//                selec = letraN;
//                id= letraN.getLetra();
//                palabra+=id;
//                System.out.println("Letra: " + id);
//                verificarLetra(pane);
//                seleccionadas.addLast(letraN);
//                
//            }else{
//                if (!(seleccionadas.contains(letraN))) {
//                    id = letraN.getLetra();
//                    verificarLetra(pane);
//                    seleccionadas.addLast(letraN);
//                    palabra += id;
//                } else {
//                    System.out.println("Ya selecciono esta letra");
//                }
//                
//                
//            }
//            if (revisarMode)                
//                System.out.println(palabra);
//        });
        pane.setOnMouseEntered(e -> {
            // Ponemos los estilos necesarios en caso del evento
            if (!letraN.isSelected()) {
                pane.setStyle(pane.getStyle() + selectedStyle);
            }
            
        });
        pane.setOnMouseExited(e -> {
            // Ponemos los estilos necesarios en caso del evento
            if (!letraN.isSelected()) {
                pane.setStyle(borderStyle);
            }
            
        });
        return pane;
    }
    
    private void añadirPuntos(Palabra p) {
        this.puntos = Integer.valueOf(points.getText());
        this.puntos += p.getSize();
        this.points.setText(this.puntos + "");
    }
    private void quitarPuntos(Palabra p) {
        this.puntos = Integer.valueOf(points.getText());
        this.puntos -= p.getSize();
        this.points.setText(this.puntos + "");
        if (this.puntos<0)
            gameOver();
    }
    
    private void gameOver() {
        Alert a = new Alert(Alert.AlertType.INFORMATION, "Perdiste :p");
        a.show();
        try {
            salir();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    
    public void verificarLetra(Pane f){
        Pane cuadro = new Pane();
        cuadro.setStyle("-fx-background-color: #5169FF;");
        cuadro.setMouseTransparent(true);
        cuadro.setOpacity(0.20);
        f.getChildren().add(cuadro);
        
    }
    @FXML
    public void salir() throws IOException{
        App.setRoot("primary");
    }


}
