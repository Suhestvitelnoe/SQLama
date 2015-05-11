
package sqlama.plugin.type;

/**
 *
 * @author MarDuke
 */
public abstract class ConnectionManager extends PluginBase{

    @Override
    public final boolean isUnique() {
        return true;
    }
    
    @Override
    public final PluginType getType() {
        return PluginType.CONNECTION_MANAGER;
    }
}
