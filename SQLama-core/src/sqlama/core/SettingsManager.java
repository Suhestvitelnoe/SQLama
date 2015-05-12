package sqlama.core;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import ch.qos.logback.core.util.StatusPrinter;
import java.io.File;
import java.util.ArrayList;
import java.util.Properties;
import org.slf4j.LoggerFactory;
import sqlama.core.config.PluginConfig;
import sqlama.interfaces.SettingsManagerPublic;
import sqlama.log.Log;

/**
 *
 * @author MarDuke
 */
public final class SettingsManager implements SettingsManagerPublic {

    private static final String CONFIG_FILE = "config.json";

    public static String PATH = null;
    private static SettingsManager instance;

    private Logger log;
    private ArrayList<PluginConfig> plugins;
    private boolean createDefault = false;

    public static SettingsManager getInstance() {
        if (instance == null) {
            instance = new SettingsManager();
        }
        return instance;
    }

    private SettingsManager() {
        init();
        createLogger();
        if (createDefault) {
            log.info("Configuration file not found. Created and saved by default");
        }

        loadPlugins();
        loadConfigurations();
    }

    public void init() {
        File cfg = new File(CONFIG_FILE);
        plugins = new ArrayList<>();
        if (cfg.exists()) {
            PATH = File.separator;
        } else {
            PATH = System.getProperty("user.home") + File.separator + ".sqlama" + File.separator;
            cfg = new File(PATH + CONFIG_FILE);

            if (!cfg.exists()) {
                createDefault = true;
            }
        }
    }

    private void loadPlugins() {
        log.info("Loading plugins");
        if (createDefault) {
            log.info("Loading default plugins");
            addPlugin("sqlama.core.plugin.CoreEditor", null, true);
        } else {
            //todo get plugin list
        }

        log.info("Plugins loaded");
    }
    
    private void addPlugin(String classPath, String file, boolean enabled) {
        plugins.add(new PluginConfig(classPath, file, enabled));
        log.info("Plugin " + classPath + " from file " + file + (enabled ? " is " : " is not") + " enabled");
    }

    private void loadConfigurations() {
        log.info("Loading configuration");
        // load settings of each plugin
        log.info("Configuraton loaded");
    }

    private void createLogger() {
        if (log != null) {
            return;
        }
        String logPattern = "%date{HH:mm:ss.SSS} [%t] %class:%method on %line %level - %msg%n%xEx{full}";

        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        log = context.getLogger("ROOT");
        context.reset();

        File pathTest = new File(PATH + "log");
        pathTest.mkdirs();

        //ByteArrayOutputStream stream = new ByteArrayOutputStream();
        /*PatternLayoutEncoder encoderInner = new PatternLayoutEncoder();
         encoderInner.setContext(context);
         encoderInner.setImmediateFlush(true);
         encoderInner.setPattern(logPattern);
         encoderInner.start();

         // OutputStreamAppender
         OutputStreamAppender<ILoggingEvent> appenderInner= new OutputStreamAppender<>();
         appenderInner.setName("Inner Appender");
         appenderInner.setContext(context);
         appenderInner.setEncoder(encoderInner);
         appenderInner.setOutputStream(stream);

         appenderInner.start();*/
        PatternLayoutEncoder encoderFile = new PatternLayoutEncoder();
        encoderFile.setContext(context);
        encoderFile.setImmediateFlush(true);
        encoderFile.setPattern(logPattern);
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
        encoderConsole.setPattern(logPattern);
        encoderConsole.start();

        ConsoleAppender appenderConsole = new ConsoleAppender();
        appenderConsole.setEncoder(encoderConsole);
        appenderConsole.setContext(context);
        appenderConsole.start();

        //logger.addAppender(appenderInner);
        log.addAppender(appenderFile);
        log.addAppender(appenderConsole);

        log.info("Application SQLama starting");
        log.info("System variables:");
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
            "user.language",};
        for (String key : printProps) {
            log.info(key + " : " + properties.getProperty(key));
        }

        StatusPrinter.printInCaseOfErrorsOrWarnings(context);
        
        Log.setLogger(log);
    }

    @Override
    public ArrayList<PluginConfig> getPlugins() {
        return plugins;
    }
}
