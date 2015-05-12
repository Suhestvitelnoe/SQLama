package sqlama.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import sqlama.interfaces.CorePublic;

public class FXMLController implements Initializable {
    
    private CorePublic core = null;
    
    @FXML
    private Button conns;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
    }
    
    @FXML
    private void exitApp(ActionEvent event) {
        core.exitApp(true);
    }
    
    public void setCore(CorePublic core) {
        this.core = core;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
}
