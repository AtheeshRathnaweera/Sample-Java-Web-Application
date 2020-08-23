package com.atheesh.samplejavawebapp.listners;


import com.atheesh.samplejavawebapp.utils.AppProperties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitialListner implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("initial listner started.");
        ServletContext context = servletContextEvent.getServletContext();

        //read the properties file
        AppProperties appPropCreation = new AppProperties();
        appPropCreation.createProperties(context);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("initial listner destroyed.");
    }
}
