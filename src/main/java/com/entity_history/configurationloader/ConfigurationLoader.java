package com.entity_history.configurationloader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ConfigurationLoader {
    static Properties prop = new Properties();
    static ConfigurationLoader INSTANCE = null;
    static String fileName = null;

    private ConfigurationLoader() {
        InputStream input = null;
        try {
            String currentDir = System.getProperty("user.dir");
            String filename = currentDir+"\\"+"config.properties";
            input = new FileInputStream(filename);
            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private ConfigurationLoader(String fileName) {
        InputStream input = null;
        try {
            input = new FileInputStream(fileName);
            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static ConfigurationLoader getInstance() {

        if (INSTANCE == null) {
            if (fileName != null) {
                INSTANCE = new ConfigurationLoader(fileName);
            } else {
                INSTANCE = new ConfigurationLoader();
            }
        }
        return INSTANCE;
    }

    public static Properties getProperties() {
        return prop;
    }
}
