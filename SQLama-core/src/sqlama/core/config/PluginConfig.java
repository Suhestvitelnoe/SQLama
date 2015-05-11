
package sqlama.core.config;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import sqlama.exception.SqlamaException;
import sqlama.plugin.api.Settings;
import sqlama.plugin.type.PluginBase;

/**
 *
 * @author MarDuke
 */
public class PluginConfig {
    
    private final String classPath;
    private final String file;
    private boolean enabled;
    private ClassLoader classloader;
    private ArrayList<PluginBase> instances;

    public PluginConfig(String classPath, String file, boolean enabled) {
        this.classPath = classPath;
        this.file = file;
        this.enabled = enabled;
        this.instances = new ArrayList<>();
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

    public ClassLoader getClassloader() throws SqlamaException {
        if (classloader == null) {
            if (file == null && classPath.startsWith("sqlama.core.plugin.")) {
                classloader = this.getClassloader();
            } else {
                try {
                    classloader = new URLClassLoader(new URL[] {new URL("jar:file:" + file + "!/")});
                }catch (Exception ex) {
                    throw new SqlamaException(ex);
                }
            }
        }
        return classloader;
    }
    
    public PluginBase getNewInstance() throws SqlamaException {
        if (classloader == null) {
            getClassloader();
        }
        
        try {
            Object obj = classloader.loadClass(classPath).newInstance();
            if (obj instanceof PluginBase) {
                return (PluginBase) obj;
            } else {
                throw new SqlamaException("Bad class path");
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            throw new SqlamaException(ex);
        }
    }
    
    public boolean validate(Settings sett) throws SqlamaException {
        if (classloader == null) {
            getClassloader();
        }
        
        try {
            Class<?> cls = classloader.loadClass(classPath);
            if (!cls.isAssignableFrom(PluginBase.class)) {
                throw new SqlamaException("Bad class path");
            }
            
            Object inst = cls.newInstance();
            PluginBase pl = (PluginBase) inst;
            pl.
            
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            throw new SqlamaException(ex);
        }
    }
}
