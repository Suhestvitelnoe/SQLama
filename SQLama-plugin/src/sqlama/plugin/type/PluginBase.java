
package sqlama.plugin.type;

import sqlama.plugin.Configurable;

/**
 *
 * @author marduke
 */
public abstract class PluginBase implements Configurable {
    
    protected boolean enabled = false;
    
    public abstract String getName();
    public abstract String getDescription();
    public abstract String getAuthor();
    public abstract String getVersion();
    /**
     * can be enabled with another plugin same type
     * @return 
     */
    public abstract boolean isUnique();
    public abstract PluginType getType();
    
    
    public boolean isEnabled() {
        return enabled;
    }
    
    public void setEnabled(boolean ena) {
        enabled = ena;
    }
}
