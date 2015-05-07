
package sqlama.core;

import sqlama.core.settings.SettingsManager;

/**
 *
 * @author marduke
 */
public class Core implements CoreGUI{
    
    private static Core instance = null;
    private SettingsManager settMan = null;
    
    private Core() {
        settMan = SettingsManager.getInstance();
    }
    
    public static CoreGUI getInstance() {
        if (instance == null) {
            instance = new Core();
        }
        return (CoreGUI) instance;
    }
}
