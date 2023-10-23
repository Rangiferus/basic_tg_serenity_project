package com.company.basic_project.utils;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class UtilLog {
    private static String consoleLogLevel;
    private static String apiLogLevel;

    public static void pauseLogs() {
        apiLogLevel = UtilRest.getLogLevel();
        UtilRest.setLogLevelNone();
        consoleLogLevel = ((Logger)
                LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME))
                .getLevel().levelStr;
        ((ch.qos.logback.classic.Logger)
                LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME))
                .setLevel(Level.toLevel("ERROR"));
    }

    public static void resumeLogs() {
        UtilRest.setLogLevel(apiLogLevel);
        ((ch.qos.logback.classic.Logger)
                LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME))
                .setLevel(Level.toLevel(consoleLogLevel));
    }
}
