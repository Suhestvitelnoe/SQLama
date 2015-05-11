
package sqlama.settings;

/**
 *
 * @author MarDuke
 */
public class SettingValue<T> extends Setting {

    private T value;
    
    public SettingValue(String name, T value) {
        super(name);
        this.value = value;
    }

    @Override
    public boolean isSingleSetting() {
        return true;
    }
    
    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
