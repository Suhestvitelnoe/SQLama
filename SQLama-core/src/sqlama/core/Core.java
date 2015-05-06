
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
        SettingsManager.getLogger().info("cau");
    }
    
    public static CoreGUI getInstance() {
        if (instance == null) {
            instance = new Core();
        }
        return (CoreGUI) instance;
    }
}
