
package sqlama.exception;

import sqlama.plugin.type.PluginBase;

/**
 *
 * @author MarDuke
 */
public class SettingsException extends Exception{
    
    public PluginBase plugin;
    public String setting;
    public Object value;

    public SettingsException(PluginBase plugin, String setting, Object value) {
        this.plugin = plugin;
        this.setting = setting;
        this.value = value;
    }

    public SettingsException(PluginBase plugin, String setting, Object value, Throwable cause) {
        super(cause);
        this.plugin = plugin;
        this.setting = setting;
        this.value = value;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
