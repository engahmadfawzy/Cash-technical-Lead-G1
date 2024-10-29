package com.unicorns.properties_reading;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

public class ReadPropertiesFile {

    private static final String dataConfigPath = System.getProperty("user.dir")+"/resources/config/data-config.properties";
    private static String propertiesPath = "resources/";

    public static Properties setProperties() throws IOException {
        Properties configProperties = new Properties();
        FileInputStream inputStream = new FileInputStream(dataConfigPath);
        configProperties.load(inputStream);
        return configProperties;
    }

    ;public static void loadProperties() {
        Properties properties = new Properties();
        Collection<File> propertiesFilesList;
        propertiesFilesList = FileUtils.listFiles(new File(propertiesPath), new String[] { "properties" }, true);
        propertiesFilesList.forEach(propertyFile -> {
            try {
                properties.load(new FileInputStream(propertyFile));
            }
        catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
            properties.putAll(System.getProperties());
            System.getProperties().putAll(properties);    });
    }
}
