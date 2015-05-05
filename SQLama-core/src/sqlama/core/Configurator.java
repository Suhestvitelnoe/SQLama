
package sqlama.core;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.OutputStreamAppender;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import ch.qos.logback.core.util.StatusPrinter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author MarDuke
 */
public class Configurator {
    
    private static String CONFIG_FILE = "config.json";
    
    public static String PATH = null;
    public static String LOGBACK_PATH = null;
    
    public static void init() {
        File cfg = new File(CONFIG_FILE);
        if (cfg.exists()) {
            PATH = File.separator;
            LOGBACK_PATH = "";
        } else {
            PATH = System.getProperty("user.home") + File.separator + ".sqlama" + File.separator;
            LOGBACK_PATH = "${USERPROFILE}/.sqlama/";
            cfg = new File(PATH + CONFIG_FILE);
            
            if (!cfg.exists()) {
                //create default configuration here
            }
        }
        createLogger();
    }
    
    private static void createLogger() {
        File pathTest = new File(PATH + "log");
        pathTest.mkdirs();
        
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        
        //OutputStream out = new 
        LoggerContext context = (LoggerContext)LoggerFactory.getILoggerFactory();
                
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(context);
        encoder.setPattern("%d %d %d %class %line %level %logger - %msg%n%ex");
        encoder.start();

        // OutputStreamAppender
        OutputStreamAppender<ILoggingEvent> innerApp= new OutputStreamAppender<>();
        innerApp.setName("Inner Appender");
        innerApp.setContext(context);
        innerApp.setEncoder(encoder);
        innerApp.setOutputStream(stream);

        innerApp.start();
        
        
        RollingFileAppender fileApp = new RollingFileAppender();
        
        TimeBasedRollingPolicy policy = new TimeBasedRollingPolicy();
        policy.setMaxHistory(14);
        policy.setParent(fileApp);
        policy.setFileNamePattern(LOGBACK_PATH + "log/%d{yyyy-MM-dd}.log");
        
        fileApp.setName("File Appender");
        fileApp.setFile(PATH + "log/actual.log");
        fileApp.setRollingPolicy(policy);
        fileApp.setContext(context);
        fileApp.setEncoder(encoder);
        fileApp.setAppend(true);
        
        fileApp.start();
        
        
        context.getLogger(Logger.ROOT_LOGGER_NAME).addAppender(innerApp);
        context.getLogger(Logger.ROOT_LOGGER_NAME).addAppender(fileApp);
        
        StatusPrinter.printInCaseOfErrorsOrWarnings(context);
    }
}
