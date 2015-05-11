
package sqlama.settings;

import java.util.Set;
import java.util.SortedMap;

/**
 *
 * @author MarDuke
 */
public class SettingMap extends Setting {

    private SortedMap<String, Setting> map = null;
    
    public SettingMap(String name) {
        super(name);
    }

    @Override
    public boolean isSingleSetting() {
        return false;
    }
    
    /*public Object getValue(String key) {
        Setting get = map.get(key);
        if (get != null) {
            if (get.isSingleSetting()) {
                return ((SettingValue)get).getValue();
            } else {
                return 
            }
        } else {
            return null;
        }
    }*/
    
    public Setting get(String key) {
        return map.get(key);
    }
    
    public void set(String key, Object value) {
        Setting get = map.get(key);
        if (get == null) {
            return ;
        }
        
        if (get.isSingleSetting()) {
            ((SettingValue)get).setValue(value);
        } else {
            //TODO
        }
    }
    
    public Set<String> keys() {
        return map.keySet();
    }
    
    
}
