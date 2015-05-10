
package sqlama.core;

import sqlama.interfaces.Core;
import sqlama.core.settings.SettingMap;
import sqlama.core.settings.SettingsManager;

/**
 *
 * @author marduke
 */
public class CoreImpl implements Core{
    
    private static CoreImpl instance = null;
    private SettingsManager settMan = null;
    
    private CoreImpl() {
        settMan = SettingsManager.getInstance();
        settMan.getLogger().info("SQLama started");
    }
    
    public static Core getInstance() {
        if (instance == null) {
            instance = new CoreImpl();
        }
        return (Core) instance;
    }

    @Override
    public SettingMap getSettings() {
        return null;
    }
}
