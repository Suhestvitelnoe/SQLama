
package sqlama.core;

import java.util.ArrayList;
import java.util.HashMap;
import sqlama.core.config.PluginConfig;
import sqlama.exception.SettingsException;
import sqlama.exception.SqlamaException;
import sqlama.plugin.type.PluginType;
import sqlama.interfaces.SettingsManagerPublic;
import sqlama.log.Log;

/**
 *
 * @author MarDuke
 */
public class Pluginer {
    
    private ArrayList<PluginConfig> plugins;
    private SettingsManagerPublic settMan = null;
    HashMap<PluginType, Object> map = null;

    public Pluginer(SettingsManagerPublic settMan) {
        this.settMan = settMan;
        //todo core plugins
    }
    
    public boolean validate() {
        Log.get().info("Plugin validation");
        //validate plugins enabling
        map = new HashMap<>();
        plugins = settMan.getPlugins();
        
        for(PluginConfig conf: plugins) {
            /*if (conf.getEnabled()) {
                continue;
            }*/
            //boolean isInner = conf.getClassPath().startsWith("sqlama.core.plugin.") && conf.getFile() == null;
            Log.get().info("Validate " + conf.getClassPath());
            try {
                conf.validate();
            } catch (SqlamaException | SettingsException ex) {
                Log.get().error("", ex);
                return false;
            }
        }
        
        //validate plugins settings
        return true;
    }
    
    
    
}
