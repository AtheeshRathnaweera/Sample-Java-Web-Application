package com.atheesh.samplejavawebapp.utils;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.Properties;

public class AppProperties {

    private static Properties properties = null;

    public AppProperties() {
    }

    private AppProperties(ServletContext context){

        // read the properties file
        try {
            properties = new Properties();
            properties.load(context.getResourceAsStream("WEB-INF/properties/application.properties"));
            System.out.println("Property initialization success.");
        } catch (IOException e) {
            System.out.println("Property initialization failed. " +e);
        }
    }

    public void createProperties(ServletContext context){
        if(properties == null){
            new AppProperties(context);
        }
    }

    public static Object getProperty(String key){
        return properties.get(key);
    }
}
