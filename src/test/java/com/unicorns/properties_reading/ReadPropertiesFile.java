package com.unicorns.properties_reading;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertiesFile {

    private static final String dataConfigPath = System.getProperty("user.dir")+"/resources/config/data-config.properties";


    public static Properties setProperties() throws IOException {
        Properties configProperties = new Properties();
        FileInputStream inputStream = new FileInputStream(dataConfigPath);
        configProperties.load(inputStream);
        return configProperties;
    }
}
