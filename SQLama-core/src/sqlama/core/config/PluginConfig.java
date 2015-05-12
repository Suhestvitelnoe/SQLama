
package sqlama.core.config;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import sqlama.exception.SettingsException;
import sqlama.exception.SqlamaException;
import sqlama.log.Log;
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
    private Settings settings;

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
                classloader = this.getClass().getClassLoader();
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
                PluginBase plg = (PluginBase) obj;
                instances.add(plg);
                return plg;
            } else {
                throw new SqlamaException("Bad class path");
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            throw new SqlamaException(ex);
        }
    }
    
    public void setSettings(Settings sett) {
        settings = sett;
    }
    
    public void validate() throws SqlamaException, SettingsException {
        if (classloader == null) {
            getClassloader();
        }
        
        try {
            Settings sett = settings;
            Class<?> cls = classloader.loadClass(classPath);
            Object inst = cls.newInstance();
            if (!(inst instanceof PluginBase)) {
                throw new SqlamaException("Bad class path");
            }
            
            PluginBase pl = (PluginBase) inst;
            if (!pl.validateSettings(sett)) {
                Log.get().warn("Validation problem, try to repair...");
                Settings newSett = pl.repairSettings(sett);
                if (newSett == null) {
                    Log.get().warn("Repair failed, default settings loaded");
                    settings = pl.getDefaultSettings();
                } else {
                    Log.get().info("Repair seccessfull");
                    settings = newSett;
                }
            } else {
                Log.get().info("Validation successfull");
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            throw new SqlamaException(ex);
        }
    }
}
