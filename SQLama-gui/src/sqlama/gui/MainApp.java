package sqlama.gui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sqlama.core.Core;
import sqlama.interfaces.CorePublic;

public class MainApp extends Application {

    public static final String VERSION = "0.0";
    private CorePublic core;
    
    @Override
    public void start(Stage stage) throws Exception {
        //load core
        core = Core.getInstance();
        
        //start window
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/MainWnd.fxml"));
        loader.setResources(new ResourceHandler("MainWnd", "en"));
        Parent root = (Parent) loader.load();
        FXMLController controller = loader.getController();
        controller.setCore(core);
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("resources/styles/MainWnd.css");
        stage.setTitle("SQLama " + VERSION);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
