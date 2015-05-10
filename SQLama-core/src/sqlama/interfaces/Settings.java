
package sqlama.interfaces;

import sqlama.core.settings.SettingMap;

/**
 *
 * @author marduke
 */
public interface Settings {
    public SettingMap get();
    public void set(SettingMap map);
}
