
package sqlama.core;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
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
    private static Logger logger;
    
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
                
        PatternLayoutEncoder encoderInner = new PatternLayoutEncoder();
        encoderInner.setContext(context);
        encoderInner.setImmediateFlush(true);
        encoderInner.setPattern("%date{HH:mm:ss.SSS} [%t] %class:%method on %line %level - %msg%n%xEx{full}");
        encoderInner.start();

        // OutputStreamAppender
        OutputStreamAppender<ILoggingEvent> appenderInner= new OutputStreamAppender<>();
        appenderInner.setName("Inner Appender");
        appenderInner.setContext(context);
        appenderInner.setEncoder(encoderInner);
        appenderInner.setOutputStream(stream);

        appenderInner.start();
        
        
        PatternLayoutEncoder encoderFile = new PatternLayoutEncoder();
        encoderFile.setContext(context);
        encoderFile.setImmediateFlush(true);
        encoderFile.setPattern("%date{HH:mm:ss.SSS} [%t] %class:%method on %line %level - %msg%n%xEx{full}");
        encoderFile.start();

        RollingFileAppender appenderFile = new RollingFileAppender();
        
        TimeBasedRollingPolicy policy = new TimeBasedRollingPolicy();
        policy.setMaxHistory(14);
        policy.setParent(appenderFile);
        policy.setContext(context);
        policy.setFileNamePattern(PATH + "log/%d.log");
        policy.start();
        
        appenderFile.setName("File Appender");
        appenderFile.setRollingPolicy(policy);
        appenderFile.setContext(context);
        appenderFile.setEncoder(encoderFile);
        appenderFile.setAppend(true);
        
        appenderFile.start();
        
        PatternLayoutEncoder encoderConsole = new PatternLayoutEncoder();
        encoderConsole.setContext(context);
        encoderConsole.setImmediateFlush(true);
        encoderConsole.setPattern("%date{HH:mm:ss.SSS} [%t] %class:%method on %line %level - %msg%n%xEx{full}");
        encoderConsole.start();

        ConsoleAppender appenderConsole = new ConsoleAppender();
        appenderConsole.setEncoder(encoderConsole);
        appenderConsole.setContext(context);
        appenderConsole.start();
        
        logger.addAppender(appenderInner);
        logger.addAppender(appenderFile);
        logger.addAppender(appenderConsole);
        
        logger.info("Application SQLama starting");
        logger.info("System variables:");
        Properties properties = System.getProperties();
        String[] printProps = {
            "java.runtime.name",
            "java.vm.name",
            "java.vm.vendor",
            "java.vm.version",
            "java.vm.name",
            "java.runtime.version",
            "java.class.version",
            "javafx.runtime.version",
            "os.name",
            "os.version",
            "sun.os.patch.level",
            "sun.jnu.encoding",
            "file.encoding",
            "user.language",
        };
        for(String key : printProps) {
            logger.info(key + " : " + properties.getProperty(key));
        }
        
        StatusPrinter.printInCaseOfErrorsOrWarnings(context);
    }
}
