
package sqlama.core;

import org.slf4j.LoggerFactory;

/**
 *
 * @author marduke
 */
public class Core implements CoreGUI{
    
    private static Core instance = null;
    
    private Core() {
        SettingsManager.init();
    }
    
    public static CoreGUI getInstance() {
        if (instance == null) {
            instance = new Core();
        }
        return (CoreGUI) instance;
    }
}
