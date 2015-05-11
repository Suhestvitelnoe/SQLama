
package sqlama.core.config;

/**
 *
 * @author MarDuke
 */
public class PluginerConfig {
    
    private final String classPath;
    private final String file;
    private boolean enabled;

    public PluginerConfig(String classPath, String file, boolean enabled) {
        this.classPath = classPath;
        this.file = file;
        this.enabled = enabled;
    }

    public String getClassPath() {
        return classPath;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public String getFile() {
        return file;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
