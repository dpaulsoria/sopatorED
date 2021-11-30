package espol.sopator2;

import generator.Sopator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;

public class SecondaryController implements Initializable {

    @FXML
    private GridPane grid; 
    private Sopator sopator;
    
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
    }
    
    public void setSopator(Sopator s) {
        if (s != null) {
            sopator = s;
            System.out.println(s.toString());
        } else {
            Alert a = new Alert(AlertType.WARNING, "Sopa de letras vacía");
            a.show();
        }
    }
}