package com.K9.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * 
 * HibernateUtil is used to initiate a connection to the database through Hibernate.
 * 
 * @author Michele
 *
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            // Create a new configuration and configure it using the hibernate.cfg values
            
        	Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            //Instantiate a new ServiceRegistry
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory= configuration.buildSessionFactory(serviceRegistry);
        	
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
        
            }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}