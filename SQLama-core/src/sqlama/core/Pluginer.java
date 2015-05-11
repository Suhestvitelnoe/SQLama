
package sqlama.core;

import java.util.ArrayList;
import java.util.HashMap;
import sqlama.core.config.PluginerConfig;
import sqlama.plugin.type.PluginType;
import sqlama.interfaces.SettingsManagerPublic;

/**
 *
 * @author MarDuke
 */
public class Pluginer {
    
    private ArrayList<PluginerConfig> plugins;
    private SettingsManagerPublic settMan = null;
    HashMap<PluginType, Object> map = null;

    public Pluginer(SettingsManagerPublic settMan) {
        this.settMan = settMan;
        //todo core plugins
    }
    
    public boolean validate() {
        //validate plugins enabling
        map = new HashMap<>();
        plugins = settMan.getPlugins();
        
        for(PluginerConfig conf: plugins) {
            if (conf.getEnabled()) {
                continue;
            }
            //boolean isInner = conf.getClassPath().startsWith("sqlama.core.plugin.") && conf.getFile() == null;
            
        }
        
        //validate plugins settings
        return true;
    }
    
    
    
}
