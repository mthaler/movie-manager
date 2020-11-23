package com.mthaler.moviemanager.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Credentials {

    public static Properties load() throws IOException {
        Properties properties = new Properties();
        File file = new File("credentials.properties");
        FileInputStream is = new FileInputStream(file);
        properties.load(is);
        return properties;
    }

    public static void main(String[] args) throws IOException {
        Properties properties = Credentials.load();
        System.out.println(properties);
    }
}
