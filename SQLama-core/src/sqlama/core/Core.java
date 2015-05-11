
package sqlama.core;

import sqlama.interfaces.CorePublic;
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
        settMan.getLogger().info("SQLama started");
        pluginer = new Pluginer(settMan);
        pluginer.validate();
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
}
