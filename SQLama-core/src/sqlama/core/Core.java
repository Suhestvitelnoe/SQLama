
package sqlama.core;

import sqlama.interfaces.CorePublic;
import sqlama.log.Log;
import sqlama.settings.SettingMap;

/**
 *
 * @author marduke
 */
public class Core implements CorePublic{
    
    private static Core instance = null;
    private SettingsManager settMan = null;
    private Pluginer pluginer = null;
    
    private Core() {
        settMan = SettingsManager.getInstance();
        pluginer = new Pluginer(settMan);
        if (!pluginer.validate()) {
            Log.get().error("Validation failed, program exiting...");
            exitApp(false);
        } else {
            Log.get().info("SQLama started");
        }
    }
    
    public static CorePublic getInstance() {
        if (instance == null) {
            instance = new Core();
        }
        return (CorePublic) instance;
    }

    @Override
    public SettingMap getSettings() {
        return null;
    }
    
    @Override
    public final void exitApp(boolean save) {
        if (save) {
            //todo 
        }
        System.exit(0);
    }
}
