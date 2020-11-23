package com.mthaler.moviemanager.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Credentials {

    public static Properties load() {
        try {
            Properties properties = new Properties();
            File file = new File("credentials.properties");
            FileInputStream is = new FileInputStream(file);
            properties.load(is);
            return properties;
        } catch(IOException ex) {
            System.out.println("Could not load credentials: " + ex);
            return null;
        }
    }

    public static void main(String[] args) {
        Properties properties = Credentials.load();
        System.out.println(properties);
    }
}
