
package sqlama.exception;

/**
 *
 * @author MarDuke
 */
public class SqlamaException extends Exception {

    public SqlamaException(String message) {
        super(message);
    }

    public SqlamaException(Throwable cause) {
        super(cause);
    }

    public SqlamaException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
