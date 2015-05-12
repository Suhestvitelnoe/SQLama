
package sqlama.interfaces;

import sqlama.settings.SettingMap;

/**
 *
 * @author marduke
 */
public interface CorePublic {
    public SettingMap getSettings();
    public void exitApp(boolean save);
}
