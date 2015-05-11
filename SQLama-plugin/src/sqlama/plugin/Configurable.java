
package sqlama.plugin;

import sqlama.exception.SettingsException;
import sqlama.plugin.api.Settings;

/**
 *
 * @author MarDuke
 */
public interface Configurable {
    public boolean validateSettings(Settings sett);
    public Settings repairSettings(Settings sett) throws SettingsException;
    public Settings getDefaultSettings();
    public void setSettings(Settings sett);
}
