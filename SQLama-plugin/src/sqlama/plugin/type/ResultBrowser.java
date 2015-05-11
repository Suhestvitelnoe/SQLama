
package sqlama.plugin.type;

/**
 *
 * @author marduke
 */
public abstract class ResultBrowser extends PluginBase {
    @Override
    public final boolean isUnique() {
        return false;
    }
    
    @Override
    public final PluginType getType() {
        return PluginType.RESULT_BROWSER;
    }
}
