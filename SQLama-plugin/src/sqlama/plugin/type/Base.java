
package sqlama.plugin.type;

import sqlama.plugin.api.Settings;

/**
 *
 * @author marduke
 */
public abstract class Base {
    
    public abstract boolean validateSettings(Settings sett);
    public abstract Settings getDefaultSettings();
    public abstract void setSettings(Settings sett);
}
