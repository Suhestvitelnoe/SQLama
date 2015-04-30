package sqlama.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class FXMLController implements Initializable {
    
    @FXML
    private Button conns;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
    }
    
    @FXML
    private void exitApp(ActionEvent event) {
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
}
