package com.K9.util;

//import javax.imageio.spi.ServiceRegistry;


//import org.hibernate.boot.registry.StandardServiceRegistry;



import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
/*
public class HibernateUtil {

      private static final SessionFactory sessionFactory;

      static {
          try {
        	  
        	  // 1. configuring hibernate
          		Configuration  configuration = new Configuration ().configure();
          		
          		
          		
          	// 2. create sessionfactory
        	  StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
              sessionFactory = configuration.buildSessionFactory(builder.build());
              
              
          } catch (Throwable ex) {
              // Make sure you log the exception, as it might be swallowed
              System.err.println("Initial SessionFactory creation failed." + ex);
              throw new ExceptionInInitializerError(ex);
          }
      }

      public static SessionFactory getSessionFactory() {
          return sessionFactory;
      }

}
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
*/






public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            //return new Configuration().configure().buildSessionFactory(
              //  new StandardServiceRegistryBuilder().build() );
        	Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory= configuration.buildSessionFactory(serviceRegistry);
        	
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        
            }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}