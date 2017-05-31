package com.magic.crius.util;


import org.apache.log4j.Logger;

/**
 * Created by zjh on 2016/11/10.
 */
public class CriusLog {
    private static Logger log = Logger.getLogger("debug");
    private static Logger infoLog = Logger.getLogger("infofile");
    private static Logger warnLog = Logger.getLogger("warn");
    private static Logger errorLog = Logger.getLogger("error");


    public static void debug(Object msg) {
        log.debug(msg);
    }

    public void debug(Object msg, Throwable t) {
        log.debug(msg, t);
    }


    public static void info(Object msg) {
        infoLog.info(msg);
    }

    public static void info(Object msg, Throwable t) {
        infoLog.info(msg, t);
    }

    public static void warn(Object msg) {
        warnLog.warn(msg);
    }

    public static void warn(Object msg, Throwable t) {
        warnLog.warn(msg, t);
    }

    public static void error(String msg) {
        errorLog.error(msg);
    }

    public static void error(Object msg, Throwable t) {
        errorLog.error(msg, t);
    }

}
