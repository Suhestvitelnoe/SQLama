
package sqlama.plugin.sql;

/**
 *
 * @author marduke
 */
public abstract class SQLResult {
    
    public abstract long getTime();
    public abstract boolean getSucces();
    public abstract String getErrorMessage();
}
