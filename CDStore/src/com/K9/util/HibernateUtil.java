package com.K9.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

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
