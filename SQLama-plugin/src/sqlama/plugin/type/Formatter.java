
package sqlama.plugin.type;

/**
 *
 * @author marduke
 */
public abstract class Formatter extends PluginBase {
    
    @Override
    public final boolean isUnique() {
        return true;
    }
    
    @Override
    public final PluginType getType() {
        return PluginType.FORMATTER;
    }
}
