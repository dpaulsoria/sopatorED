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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import util.Desplazamiento;
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
    private Text txtNum;
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
    private RadioButton col1;
    @FXML
    private RadioButton fila1;
    @FXML
    private Button giveup;
    @FXML
    private ToggleButton desplazar;
    @FXML
    private Button ejecutarDesplazar;
    @FXML
    private Label vidasLabel;
    @FXML
    private TextField numAE;
    @FXML
    private Button accionbutton;
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
    private String UnselectedStyle = " -fx-background-color: white;";
    private String borderStyle = "-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 1px;";
    private String labelStyle = "-fx-font-size: 14px;";
    
    private Desplazamiento d;
    private Letra currentLetter;
    
    private CircularLinkedList<Letra> seleccionadas = new CircularLinkedList();
    private ArrayList<Pair> coordenadas = new ArrayList<>();
    private ArrayList<Palabra> encontradas = new ArrayList<>();

    @FXML
    private BorderPane root_father;
    @FXML
    private VBox palabrasEncontradas;
    @FXML
    private Label cantPalabrasLabel;
    @FXML
    private RadioButton col11;
    @FXML
    private RadioButton fila11;
    @FXML
    private TextField numAE1;

    private Label tiempo;
    
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
        generarSopa();
    }
    
    @FXML
    public void aleatorio() {
        this.sopator.reorganizar();
        generarSopa();
    }
    
    private void pierdeVida() {
        vidas = Integer.valueOf(vidasLabel.getText());
        System.out.println(vidas);
        vidas--;
        points.setText(0+"");
        puntos = 0;
        vidasLabel.setText(vidas + "");
    }
    private void inicializar() {
        fila.setVisible(false);
        col.setVisible(false);
        numAE.setVisible(false);
        accionbutton.setVisible(false);
        fila1.setVisible(false);
        col1.setVisible(false);
        fila11.setVisible(false);
        col11.setVisible(false);
        ejecutarDesplazar.setVisible(false);
        numAE1.setVisible(false);
        numAE.setTextFormatter(new TextFormatter<>(condicion -> (condicion.getControlNewText().matches("[0-9]{0,2}")) ? condicion:null));
        
    }
    
    public void setSopator(Sopator s) {
        inicializar();
        this.sopator = s;
        generarSopa();
    
    }
    private void setData() {
        this.filas = sopator.getFilas();
        this.columnas = sopator.getColumnas();
    }
    
    public void generarSopa() {
        //inicializa hilo que muestra el contador
        this.matriz = new AnchorPane();
        
        setData();
        grid = new GridPane();
        matriz.getChildren().add(grid);
        showVidas();
//        tiempo.setVisible(false);
//        txtNum.setVisible(false);
//        if(sopator.getExtremo()){
//            tiempo.setVisible(true);
//            txtNum.setVisible(true);
//            Thread hiloCuenta = new Thread(new cronometro());
//            hiloCuenta.setDaemon(true);
//            hiloCuenta.start();
//        }
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
            if (encontradas.contains(wordToCheck)) {
                alerta("Palabra ya encontrada");
                
            } else if (sopator.confirmarPalabraEnBase(wordToCheck)) {
                wordToCheck.setEncontrada(true);
                encontradas.addLast(wordToCheck);
                bloquearLetras(wordToCheck);
                actualizarEncontradas();
                a??adirPuntos(wordToCheck);
                seleccionadas.clear();                
            } else {
                quitarPuntos(wordToCheck);
                unSelectCeldas(seleccionadas);
                seleccionadas.clear();
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
        cantPalabrasLabel.setText(String.valueOf(size));
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
    private boolean validarLetra() {
        if (currentLetter.validarSelectLetra(seleccionadas.getLast().getContent())) {
            return true;
        }
        return false;
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
            currentLetter  = letraN;
            if (validarLetra()) {
                
            }
            if (!letraN.noUnselect) {
                if (!letraN.isSelected()) {
                    System.out.println("Ahora est?? seleccionada " + letraN);
                    pane.setStyle(pane.getStyle() + selectedStyle);
                    letraN.setSelected(true);                    
                    seleccionadas.addLast(letraN);
                } else {
                    System.out.println("Ahora NO est?? seleccionada " + letraN);
                    pane.setStyle(UnselectedStyle); // Unselect
                    letraN.setSelected(false);
                    int i = seleccionadas.indexOf(letraN);

                    System.out.println("index: " + i);
                    seleccionadas.remove(i);
                }
                System.out.println("Seleccionadas actualmente " + seleccionadas.toString());

            } else {
                System.out.println("Ahora est?? seleccionada " + letraN);
                seleccionadas.addLast(letraN);
                System.out.println("Seleccionadas actualmente " + seleccionadas.toString());
                
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
            } else {
                pane.setStyle(UnselectedStyle + borderStyle);
            }
            
        });
        pane.setOnMouseExited(e -> {
            // Ponemos los estilos necesarios en caso del evento
            if (!letraN.isSelected()) {
                pane.setStyle(UnselectedStyle + borderStyle);
            } else {
                pane.setStyle(selectedStyle);
            }
            
        });
        return pane;
    }
    private void alerta(String mensaje) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, mensaje);
        a.show();
    }
    private void a??adirPuntos(Palabra p) {
        int size = p.getSize();
        this.puntos = Integer.valueOf(points.getText());        
        this.puntos += size;
        alerta("Felicidades, gan?? " + size + " puntos");
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
            alerta("Sorry, perdi?? " + size + " puntos");
            
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

        
    private void comprobarOpciones() {
        if (del.isSelected() || agg.isSelected()) {
            fila.setVisible(true);
            col.setVisible(true);
        } else {
            fila.setVisible(false);
            col.setVisible(false);
        }
    }
    
    @FXML
    public void setColumna() {

        if (!col.isSelected()) {
            numAE.setVisible(false);
            accionbutton.setVisible(false);            
        } else {
            fila.setSelected(false);
            comprobarRadioButton();

        }
    }
    
    @FXML
    public void setFila() {

        if (!fila.isSelected()) {
            numAE.setVisible(false);
            accionbutton.setVisible(false);            
        } else {
            col.setSelected(false);
            comprobarRadioButton();
        }


    }
    
    public void setColumnaAdespl(ActionEvent event) {
        fila1.setSelected(false);
        numAE.setVisible(false);
        
        comprobarRadioButton();
        
        
    }
    
    public void setFilaAdespl() {
        col1.setSelected(false);
        comprobarRadioButton();
    }
    
    public void comprobarRadioButton() {
        if (col.isVisible() || fila.isVisible() && (agg.isSelected() || col.isSelected())) {
            numAE.setVisible(true);
            accionbutton.setVisible(true);
        } else {
            numAE.setVisible(false);
            accionbutton.setVisible(false);
        }
    }
    
    @FXML
    public void toggleAgg(ActionEvent event){
        if (agg.isSelected()) {
            del.setSelected(false);
            agg.setStyle("-fx-background-color: #278d91");
            del.setStyle("-fx-background-color: #30B5BA");
            comprobarOpciones();
            desSelected();
        } else {
            agg.setStyle("-fx-background-color: #30B5BA");
            numAE.setVisible(false);
            accionbutton.setVisible(false);
        }
        comprobarOpciones();
        desSelected();
    }
    
    private void desSelected() {
        fila.setSelected(false);
        col.setSelected(false);
    }
    
    @FXML
    public void accion() {
        // Mostrar cambios
        this.cambios = Integer.valueOf(changes.getText());
        
        if (cambios > 0) {
            
            System.out.println("Cambios permitidos actualmente:" + cambios);

            if (agg.isSelected()) {

                if (col.isSelected()) {
                    int num = Integer.valueOf(numAE.getText());
                    // A??adir columna
                    if (validarInsercion(num, columnas)) {
                        System.out.println("A??adir 1 columna en " +num);
                        this.sopator.a??adirColumna(num);
                        clean();

                    }

                } else if (fila.isSelected()) {
                    int num = Integer.valueOf(numAE.getText());
                    // A??adir fila
                    if (validarInsercion(num, filas)) {
                        System.out.println("A??adir 1 fila en " +num);
                        this.sopator.a??adirFila(num);
                        clean();
                    }

                }


            } else if (del.isSelected()) {

                if (col.isSelected()) {
                    int num = Integer.valueOf(numAE.getText());
                    // Eliminar columna
                    if (validarInsercion(num, columnas)) {
                        System.out.println("Eliminar 1 columna en " +num);
                        this.sopator.eliminarColumna(num);
                        clean();
                    }

                } else if (fila.isSelected()) {
                    int num = Integer.valueOf(numAE.getText());
                    // Eliminar fila
                    if (validarInsercion(num, filas)) {
                        System.out.println("Eliminar 1 fila en " +num);
                        this.sopator.eliminarFila(num);
                        clean();
                    }

                }

            }
        } else {
            // Si los cambios son menores a 0
            agg.setVisible(false);
            del.setVisible(false);
            fila.setVisible(false);
            col.setVisible(false);
            numAE.setVisible(false);
            agg.setSelected(false);
            del.setSelected(false);
            fila.setSelected(false);
            col.setSelected(false);
            
        }
        // Restar cabmios
        this.cambios--;
        this.changes.setText(cambios + "");
        
        
        col.setVisible(false);
        fila.setVisible(false);
        numAE.setVisible(false);
        accionbutton.setVisible(false);
        col.setSelected(false);
        fila.setSelected(false);
        agg.setSelected(false);
        del.setSelected(false);
        
    }
    
    private boolean validarInsercion(int num, int fila_col) {
        return (num>-1 && fila_col > num);
    }
        
    @FXML
    public void toggleDel(ActionEvent event){
        if (del.isSelected()) {
            agg.setSelected(false);
            del.setStyle("-fx-background-color: #278d91");
            agg.setStyle("-fx-background-color: #30B5BA");
            comprobarOpciones();
            desSelected();
        } else {
            del.setStyle("-fx-background-color: #30B5BA");
            numAE.setVisible(false);
            accionbutton.setVisible(false);
        }
        comprobarOpciones();
        desSelected();
    }
    
    
    public void desplazar() {
        
    }

    @FXML
    private void toggleDesp(ActionEvent event) {
        if (desplazar.isSelected()) {
            desplazar.setStyle("-fx-background-color: #278d91");
            fila1.setVisible(true);
            col1.setVisible(true);
            
            
        } else {
            del.setStyle("-fx-background-color: #30B5BA");
            fila1.setVisible(false);
            col1.setVisible(false);
            fila11.setVisible(false);
            col11.setVisible(false);
            ejecutarDesplazar.setVisible(false);
        }
    }
    class cronometro implements Runnable {

        private int count = 60;

        private void incrementCount() {
            count--;
            txtNum.setText(Integer.toString(count));
            System.out.println(count==0);
            if(count==0){
                try {
                    salir();
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
;
            }
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                }

                incrementCount();
            }
        }

    }
    
    @FXML
    public void setFila1() {
        if (fila1.isSelected()) {
            col1.setSelected(false);
            fila11.setVisible(true);
            col11.setVisible(true);
            col11.setText("Derecha");
            fila11.setText("Izquierda");
            ejecutarDesplazar.setVisible(false);
            numAE1.setVisible(false);
        } else {
            fila11.setVisible(false);
            col11.setVisible(false);
            fila11.setSelected(false);
            col11.setSelected(false);
            ejecutarDesplazar.setVisible(false);
            numAE1.setVisible(false);
        }
    } 
    
    @FXML
    public void setCol1() {
        if (col1.isSelected()) {
            fila1.setSelected(false);
            fila11.setVisible(true);
            col11.setVisible(true);
            col11.setText("Arriba");
            fila11.setText("Abajo");
            ejecutarDesplazar.setVisible(false);
            numAE1.setVisible(false);
        } else {
            fila11.setVisible(false);
            col11.setVisible(false);
            fila11.setSelected(false);
            col11.setSelected(false);
            ejecutarDesplazar.setVisible(false);
            numAE1.setVisible(false);
        }
    }
    
    @FXML
    public void setDesplCol() {
        if (col11.isSelected()) {
            fila11.setSelected(false);
            numAE1.setVisible(true);
            ejecutarDesplazar.setVisible(true);
            if (fila1.isSelected() && col11.isSelected()) {
                setDesplazamiento(new Pair(1, 0), validarDes());
            } else if (col1.isSelected() && fila11.isSelected()) {
                setDesplazamiento(new Pair(0, 1), validarDes());
            }
        } else {
            numAE1.setVisible(false);
            ejecutarDesplazar.setVisible(false);
        }
    }
    @FXML
    public void setDesplFila() {
        if (fila11.isSelected()) {
            col11.setSelected(false);
            numAE1.setVisible(true);
            ejecutarDesplazar.setVisible(true);
            if (fila1.isSelected() && fila11.isSelected()) {
                setDesplazamiento(new Pair(-1, 0), validarDes());
            } else if (col1.isSelected() && fila11.isSelected()) {
                setDesplazamiento(new Pair(0, -1), validarDes());
            }
        } else {
            numAE1.setVisible(false);
            ejecutarDesplazar.setVisible(false);
        }
    }
    
    @FXML
    public void ejecutarDespl() {
        switch(d.toString()) {
            case "Arriba":                
                break;
            case "Abajo":
                break;
            case "Izquierda":
                sopator.desplazarFilaIzq(d.getFilaCol());
                clean();
                break;
            case "Derecha":
                sopator.desplazarFilaDer(d.getFilaCol());
                clean();
                break;
        }
        
    }
    private void setDesplazamiento(Pair p, int i) {
        this.d = new Desplazamiento(p, i);
        System.out.println(d.toString());
        
    }
    
    private int validarDes() {
        int num = Integer.valueOf(numAE1.getText());
        if (num > -1 && num < filas) {
            return num;
        }
        return -1;
    }
    
}
