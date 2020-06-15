package com.osfocus.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
    private static final String APP_PROPS = "application.properties";

    static Properties props = new Properties();

    static {
        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream(APP_PROPS));
        } catch (IOException e) {
            e.printStackTrace();;
        }
    }

    public static Object get(String key) {
        if (props == null) return null;
        return props.get(key);
    }

    public static void main(String[] args) {
        System.out.println(PropertyMgr.get("initTankCount"));
    }
}
