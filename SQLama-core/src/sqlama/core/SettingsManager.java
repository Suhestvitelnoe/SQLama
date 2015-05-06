
package sqlama.core;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.OutputStreamAppender;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import ch.qos.logback.core.util.StatusPrinter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Properties;
import org.slf4j.LoggerFactory;

/**
 *
 * @author MarDuke
 */
public class SettingsManager {
    
    private static String CONFIG_FILE = "config.json";
    
    public static String PATH = null;
    
    public static Logger logger;
    
    public static Logger getLogger() {
        return logger;
    }
    
    public static void init() {
        File cfg = new File(CONFIG_FILE);
        if (cfg.exists()) {
            PATH = File.separator;
        } else {
            PATH = System.getProperty("user.home") + File.separator + ".sqlama" + File.separator;
            cfg = new File(PATH + CONFIG_FILE);
            
            if (!cfg.exists()) {
                //create default configuration here
            }
        }
        createLogger();
    }
    
    private static void createLogger() {
        if (logger != null) {
            return;
        }
        LoggerContext context = (LoggerContext)LoggerFactory.getILoggerFactory();
        logger = context.getLogger("ROOT");
        context.reset();

        File pathTest = new File(PATH + "log");
        pathTest.mkdirs();
        
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        
        //OutputStream out = new 
                
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(context);
        encoder.setPattern("%d [%t] %class:%method on %line %level - %msg%n%xEx{full}");
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
        policy.setContext(context);
        policy.setFileNamePattern(PATH + "log/%d{yyyy-MM-dd}.log");
        policy.start();
        
        fileApp.setName("File Appender");
        fileApp.setFile(PATH + "log/actual.log");
        fileApp.setRollingPolicy(policy);
        fileApp.setContext(context);
        fileApp.setEncoder(encoder);
        fileApp.setAppend(true);
        
        fileApp.start();
        
        logger.addAppender(innerApp);
        logger.addAppender(fileApp);
        
        //context.start();
        
        logger.info("Application SQLama starting");
        logger.info("System variables:");
        Properties properties = System.getProperties();
        String[] printProps = {
            "java.runtime.name",
            "java.vm.name",
            "java.vm.vendor",
            "java.vm.version",
            "java.runtime.version",
            "java.class.version",
            "javafx.runtime.version",
            "os.name",
            "os.version",
            "file.encoding",
            "user.language",
        };
        for(String key : printProps) {
            logger.info(key + " : " + properties.getProperty(key));
        }
        
        StatusPrinter.printInCaseOfErrorsOrWarnings(context);
    }
}
