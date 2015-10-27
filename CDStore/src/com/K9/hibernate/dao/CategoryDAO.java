package com.K9.hibernate.dao;
 

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.K9.util.HibernateUtil;
import com.google.gson.Gson;
import java.util.ArrayList;

/**
 * This Data Access Object class is used to access the category table in the database.  The hibernate framework is used to manage the interaction with the database.

 * 
 * @author MBP
 */
 

public class CategoryDAO {
	
	/**
	 * The getCategoryList method retrieves all categories from the category database table.
	 * 
	 */
	
	@SuppressWarnings("rawtypes")
	public String getCategoryList() {
        try {
        	/**
        	 * The following steps 1-4 are specific to Hibernate and are used to establish connectivity and a session with the database
        	 * 
        	 */
        	
        	//Configure Hibernate and get the sessionFactory
        	//SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        	Session session = HibernateUtil.getSessionFactory().openSession();
        	
        	// Get Session object
            //Session session = sessionFactory.openSession();
 
            
        	//Starting Transaction
        	 Transaction transaction=session.beginTransaction();
 
                        
            /**
             * The following method session.getNamedQuery calls a stored procedure which is defined in the Category bean.
             */
            
            Query query = session.getNamedQuery("callCategoryProcedure");
            
            /**
             * The result of the call to the stored procedure is returned and stored in the categoryList ArrayList.
             */
            ArrayList categoryList = (ArrayList) query.list();
            
            /**
             * The catelogyList is transformed into a Json string.
             */
        	String json = new Gson().toJson(categoryList);
            
        	/**
             * The transaction is finalised by calling the commit method.
             */
        	transaction.commit();
        	
        	/**
             * The json string is returned to the caller.
             */
            return json;
 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
            throw e;
        }
 
    }
    
     
}
 
