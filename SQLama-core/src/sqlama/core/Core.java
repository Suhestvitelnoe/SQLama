
package sqlama.core;

import org.slf4j.LoggerFactory;

/**
 *
 * @author marduke
 */
public class Core implements CoreGUI{
    
    private static Core instance = null;
    
    private Core() {
        Configurator.init();
        LoggerFactory.getLogger(this.getClass()).info("125");
    }
    
    public static CoreGUI getInstance() {
        if (instance == null) {
            instance = new Core();
        }
        return (CoreGUI) instance;
    }
}
