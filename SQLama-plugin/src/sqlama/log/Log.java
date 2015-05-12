package sqlama.log;

import org.slf4j.Logger;


/**
 *
 * @author MarDuke
 */
public final class Log {

    private static Logger log;

    public static void setLogger(Logger logger) {
        if (log == null) {
            log = logger;
        }
    }
    
    public static Logger get() {
        return log;
    }
}

