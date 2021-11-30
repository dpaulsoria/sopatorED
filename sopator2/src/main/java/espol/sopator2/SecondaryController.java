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
        grid = new GridPane();
    }
    
    public void setSopator(Sopator s) {
        if (s != null) {
            sopator = s;
            filas = sopator.getFilas();
            columnas = sopator.getColumnas();
            System.out.println(s.toString());
            
            sopator.reorganizarAleatorias();
            System.out.println(s.toString());
            setSizeAnchorPane();
            iniciarSopa();
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
                String c = String.valueOf(sopator.getLetra(i, j));
                Label letra = new Label(c);
                letra.setTextFill(Color.WHITE);
                Pane celda = new Pane();
                
                celda.setMinSize(minSize, minSize);
                celda.getChildren().add(letra);
                seleccionandoCelda(celda);
                grid.add(celda, j, i);
//                anchorPane.getChildren().add(celda);
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
