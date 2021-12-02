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
    @FXML
    private TextField numAE;
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
    private String labelStyle = "-fx-font-size: 14px;";
    
    private CircularLinkedList<Letra> seleccionadas = new CircularLinkedList();
    private ArrayList<Pair> coordenadas = new ArrayList<>();
    private ArrayList<Palabra> encontradas = new ArrayList<>();

    private boolean revisarMode = false;
    @FXML
    private BorderPane root_father;
    @FXML
    private VBox palabrasEncontradas;
    
    /**
     * Initializes the controller class.
     */
    
    public void showVidas() {
        vidasLabel.setText(Integer.toString(vidas));
        points.setText(Integer.toString(puntos));
        changes.setText(Integer.toString(cambios));
    }
    
    public void clean() {
        root = new BorderPane();
        root_father.setCenter(root);
        
    }
    
    public void aleatorio() {
        this.sopator.reorganizar();
        generarSopa();
    }
    
    private void pierdeVida() {
        vidas = Integer.valueOf(vidasLabel.getText());
        System.out.println(vidas);
        vidas--;
        vidasLabel.setText(vidas + "");
    }
    
    public void setSopator(Sopator s) {
        this.sopator = s;
        generarSopa();
    
    }
    private void setData() {
        this.filas = sopator.getFilas();
        this.columnas = sopator.getColumnas();
    }
    
    public void generarSopa() {
        this.matriz = new AnchorPane();
        
        setData();
        grid = new GridPane();
        matriz.getChildren().add(grid);
        showVidas();
        grid.setStyle(borderStyle);
        ancho = 400 / sopator.getFilas();
        alto = 400 / sopator.getColumnas();
        col.setVisible(false);
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
        root.getChildren().add(matriz);
        
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
                encontradas.addLast(wordToCheck);
                bloquearLetras(wordToCheck);
                actualizarEncontradas();
                añadirPuntos(wordToCheck);
                seleccionadas = new CircularLinkedList();                
            } else {
                quitarPuntos(wordToCheck);
                unSelectCeldas(seleccionadas);
            }
        }
    }
    
    private void bloquearLetras(Palabra w) {
        for (int i = 0; i<w.getSize(); i++) {
            w.getLetra(i).noUnselect = true;
        }
    }
    
    private void actualizarEncontradas() {
        int size = encontradas.size();
        if (size < 1) {
            alerta("No hay palabras para mostrar");
        } else {
            Palabra p = encontradas.get(size-1);  
            int childSize = left.getChildren().size();
            System.out.println("ChildSize: " + childSize);
            System.out.println("p: " + p.toString());
            palabrasEncontradas.getChildren().add(childSize, getLabelPalabra(p));
        }
    }
    
    private Label getLabelPalabra(Palabra p) {
        Label l = new Label(p.toString());
        l.setStyle(labelStyle);
        return l;
    }
    private void unSelectCeldas(CircularLinkedList<Letra> lista) {
        
        for(Letra l:lista) {
            Pane celdaPadre;
        }
    }
    
    
    private StackPane crearTablero(double altura,double ancho,double letraT, Letra letraN) {
        Character c = letraN.getLetra();
        StackPane pane = new StackPane();
        pane.setPrefSize(ancho, altura);
        pane.setStyle(borderStyle+"-fx-background-color: white;");
        Label letra = new Label(String.valueOf(c)); 
        letra.setStyle("-fx-font-family: 'Cooper Black'; -fx-font-size: " + letraT + "px;");;
        pane.getChildren().add(letra);
        StackPane.setAlignment(letra, Pos.CENTER);
        
        
        pane.setOnMouseClicked(e -> {
            System.out.println("Clickeo " + letraN);
            if (!letraN.noUnselect) {
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

            } else {
                System.out.println("Ahora está seleccionada " + letraN);
                seleccionadas.addLast(letraN);
                System.out.println("Seleccionadas actualmetne " + seleccionadas.toString());
                
            }           
            
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
    private void alerta(String mensaje) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, mensaje);
        a.show();
    }
    private void añadirPuntos(Palabra p) {
        int size = p.getSize();
        this.puntos = Integer.valueOf(points.getText());        
        this.puntos += size;
        alerta("Felicidades, ganó " + size + " puntos");
        this.points.setText(this.puntos + "");
    }
    private void quitarPuntos(Palabra p) {
        int size = p.getSize();
        this.puntos = Integer.valueOf(points.getText());
        if (this.puntos <= 0) {
            pierdeVida();
        } else if (this.puntos>0) {
            this.puntos -= size;
            if (this.puntos < 0)
                pierdeVida();
        }
        this.points.setText(this.puntos + "");
        if (this.vidas==0)
            gameOver();
        else{
            alerta("Sorry, perdió " + size + " puntos");
        }
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

    @FXML
    public void btnAgregar(ActionEvent event){
        del.setVisible(false);
        col.setVisible(true);
        fila.setVisible(true);
        numAE=null;
        
        
    }
        
    @FXML
    public void agregarColumna(ActionEvent event){
        fila.setSelected(false);
        if(!numAE.getText().equals(null)){
            System.out.println(numAE.getText());
        }
        
    }
        
    @FXML
    public void agregarFila(ActionEvent event){
        col.setSelected(false);
        if(!numAE.getText().equals(null)){
            System.out.println(numAE.getText());
        }
    }
}
