
package sqlama.plugin.api;

import sqlama.settings.SettingMap;

/**
 *
 * @author marduke
 */
public interface Settings {
    public SettingMap get();
    public void set(SettingMap map);
}
