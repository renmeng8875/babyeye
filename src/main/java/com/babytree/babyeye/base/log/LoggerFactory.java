package com.babytree.babyeye.base.log;

import com.babytree.babyeye.base.config.ProfilingConfig;
import com.babytree.babyeye.base.constant.PropertyValues;

import java.util.HashMap;
import java.util.Map;

public final class LoggerFactory {

    private static final Map<String, ILogger> LOGGER_MAP = new HashMap<>();

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                for (ILogger writer : LOGGER_MAP.values()) {
                    writer.preCloseLog();
                }

                for (ILogger writer : LOGGER_MAP.values()) {
                    writer.closeLog();
                }
            }
        }));
    }

    public static synchronized ILogger getLogger(String logFile) {
        logFile = logFile.trim();

        if (logFile.equalsIgnoreCase(PropertyValues.NULL_FILE)) {
            return new NullLogger();
        } else if (logFile.equalsIgnoreCase(PropertyValues.STDOUT_FILE)) {
            return new StdoutLogger();
        }

        ILogger logger = LOGGER_MAP.get(logFile);
        if (logger != null) {
            return logger;
        }

        logger = new AutoRollingLogger(logFile, ProfilingConfig.getInstance().getLogRollingTimeUnit());
        LOGGER_MAP.put(logFile, logger);
        return logger;
    }
}
