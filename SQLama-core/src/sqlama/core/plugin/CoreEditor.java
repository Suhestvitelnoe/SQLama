
package sqlama.core.plugin;

import sqlama.exception.SettingsException;
import sqlama.plugin.api.Settings;
import sqlama.plugin.type.Editor;

/**
 *
 * @author marduke
 */
public class CoreEditor extends Editor {

    @Override
    public String getName() {
        return "CoreEditor";
    }

    @Override
    public String getDescription() {
        return "Basic editor to run sql queries";
    }

    @Override
    public String getAuthor() {
        return "MarDuke";
    }

    @Override
    public String getVersion() {
        return "0.0";
    }

    @Override
    public boolean validateSettings(Settings sett) {
        return true;
    }

    @Override
    public Settings repairSettings(Settings sett) throws SettingsException {
        return null;
    }

    @Override
    public Settings getDefaultSettings() {
        return null;
    }

    @Override
    public void setSettings(Settings sett) {
        
    }
}
