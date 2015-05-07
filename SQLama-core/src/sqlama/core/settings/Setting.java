
package sqlama.core.settings;

/**
 *
 * @author MarDuke
 */
public abstract class Setting {
    
    protected String name;

    public Setting(String name) {
        this.name = name;
        
    }
    
    public abstract boolean isSingleSetting();

    
    public String getName() {
        return name;
    }
    
    
}
