/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.sopator2;

import generator.Sopator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author danny
 */
public class SecondaryController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private GridPane grid;
    @FXML
    private TextField vidas;
    @FXML
    private TextField cambios;
    @FXML
    private RadioButton primaryButton;
    private Sopator sopator;
    private int filas;
    private int columnas;
    private final int VGAP = 10;
    private final int HGAP = 10;
    private int changes;
    private int life;
    private final int minSize = 65;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        changes = 2;
        life = 3;
//        grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(VGAP);
        grid.setHgap(HGAP);
        System.out.println("changes: " + changes);
        
    }
    
    public void setSopator(Sopator s) {
        if (s != null) {
            sopator = s;
            filas = sopator.getFilas();
            columnas = sopator.getColumnas();
            System.out.println(s.toString());
            
//            sopator.reorganizarAleatorias();
//            System.out.println(s.toString());
            setSizeAnchorPane();
            iniciarSopa();
            System.out.println(grid.getColumnConstraints().get(1));
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING, "Sopa de letras vacía");
            a.show();
        }
    }
    
    private void setSizeAnchorPane() {
        anchorPane.setMinSize(filas * minSize, columnas * minSize);
    }
    
    private void iniciarSopa() {
        for (int i = 0; i<filas; i++) {
            for (int j = 0; j<columnas; j++) {
                Label letra = new Label(String.valueOf(sopator.getLetra(i, j)));
//                letra.setTextFill(Color.WHITE);
                letra.setMinSize(minSize, minSize);
                grid.add(letra, j, i);
//                celda.getChildren().add(letra);
////                seleccionandoCelda(celda);
//                GridPane.setConstraints(letra, j, i);
//                anchorPane.getChildren().add(letra);
                
            }
        }        
    }
    
    private void seleccionandoCelda(Pane celda) {        
        celda.setOnMousePressed((MouseEvent me) -> {
            
        });
        
        celda.setOnMouseReleased((MouseEvent me) -> {
            
        });
        
        celda.setOnMouseDragged((MouseEvent me) -> {
            
        });
    }
    
    private void clickRelease() {
        
    }
}
