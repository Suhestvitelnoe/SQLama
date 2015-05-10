package sqlama.gui;

import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 *
 * @author MarDuke
 */
public class ResourceHandler extends ResourceBundle {

    private final ResourceBundle resourceBundle;

    public ResourceHandler(String window, String locale) {
        resourceBundle = ResourceBundle.getBundle("resources/i18n." + window + "_" + locale);
    }

    @Override
    protected Object handleGetObject(String key) {
        try {
            return resourceBundle.getObject(key);
        } catch (MissingResourceException mre) {
            return "!!!MISSING " + key ;
        }
    }

    @Override
    public Enumeration<String> getKeys() {
        return resourceBundle.getKeys();
    }

    @Override
    public boolean containsKey(String key) {
        return true;
    }

}
