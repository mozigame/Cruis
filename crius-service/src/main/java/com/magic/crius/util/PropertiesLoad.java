package com.magic.crius.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

/**
 * @author zhangjh on 8/17/2016.
 * @version 1.0
 */
public class PropertiesLoad {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesLoad.class);

    private static Properties configProp = null;
    private static final String configPath = "crius-config.properties";

    private static void loadConfig() {
        FileInputStream in = null;

        try {
            URL url = PropertiesLoad.class.getClassLoader().getResource(configPath);
            in = new FileInputStream(url.getFile());
            configProp = new Properties();
            configProp.load(in);
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            configProp = null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {// ignore
                }
            }
        }
    }

    public static Properties getConfigProp() {
        if (configProp == null) {
            setConfigProp();
        }
        return configProp;
    }

    private static void setConfigProp() {
        synchronized (PropertiesLoad.class) {
            if (configProp == null) {
                loadConfig();

                Thread configLoader = new Thread(new Runnable() {
                    public void run() {
                        while (true) {
                            loadConfig();
                            try {
                                // reload every 5 min
                                Thread.sleep(5 * 60 * 1000);
                            } catch (Exception e) {
                                logger.warn("config loader failed caused by "
                                        + e.getMessage(), e);
                            }
                        }
                    }
                });
                configLoader.start();
            }
        }
    }

    public static String getConfigProp(String key, String defaultVal) {
        if (configProp == null) {
            setConfigProp();
        }
        String val = configProp.getProperty(key);
        return val == null ? defaultVal : val;
    }

    /**
     * 所有的mongo数据是否需要检查reqId
     * @return
     */
    public static boolean checkMongoResId() {
        return PropertiesLoad.getConfigProp("check.mongo.reqId", "true").equals("true");
    }

    /**
     * 订单的mongo数据是否需要检查reqId
     * @return
     */
    public static boolean checkOrderMongoResId() {
        return PropertiesLoad.getConfigProp("check.order.mongo.reqId", "false").equals("true");
    }

    /**
     * 所有mongo失败数据是否需要修复
     * @return
     */
    public static boolean repairScheduleFlag() {
        return PropertiesLoad.getConfigProp("repair.schedule.flag", "true").equals("true");
    }

    /**
     * 订单mongo失败数据是否需要修复
     * @return
     */
    public static boolean repairOrderScheduleFlag() {
        return PropertiesLoad.getConfigProp("repair.order.schedule.flag", "true").equals("true");
    }

}
