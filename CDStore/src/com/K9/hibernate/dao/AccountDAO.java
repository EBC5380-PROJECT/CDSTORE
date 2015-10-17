package com.K9.hibernate.dao;
 
import org.hibernate.HibernateException;



import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import com.K9.hibernate.bean.Account;
import com.google.gson.Gson;

import java.util.ArrayList;
 
public class AccountDAO {
 
    public boolean addAccountDetails(String accountName, String password1, int billingAddressId, int shippingAddressId, String email) {
        try {
            // 1. configuring hibernate
        	Configuration  configuration = new Configuration ().configure();
        	
            // 2. create sessionfactory
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
 
            // 3. Get Session object
            Session session = sessionFactory.openSession();
 
            // 4. Starting Transaction
            Transaction transaction = session.beginTransaction();
            
            Account accountInfo = new Account();
            
            accountInfo.setAccountName(accountName);
            accountInfo.setPassword1(password1);
            accountInfo.setbillingAddressId(billingAddressId);
            accountInfo.setShippingAddressId(shippingAddressId);
            accountInfo.setEmail(email);
            
            session.save(accountInfo);
            transaction.commit();
            System.out.println("\n\n Details Added \n");
            
              
            
            return true;
 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
            return false;
        }
 
    }
    
	@SuppressWarnings("rawtypes")
    public boolean isUserNameUnique(String userName) {
    	 try {
             // 1. configuring hibernate
         	Configuration  configuration = new Configuration ().configure();
         	
             // 2. create sessionfactory
             StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
             SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
  
             // 3. Get Session object
             Session session = sessionFactory.openSession();
  
             // 4. Starting Transaction
             Transaction transaction = session.beginTransaction();
             
             Query query = session.getNamedQuery("callRetrieveAccountInfo")
            		 .setParameter("userName", userName);
             
             ArrayList accountList = (ArrayList) query.list();
             
             transaction.commit();
             System.out.println("\n\n Details Added \n");
            		 
             if (accountList.isEmpty())
             	return true;
             else
            	 return false;
             
             
             
    	 } catch (HibernateException e) {
             System.out.println(e.getMessage());
             System.out.println("error");
             return false;
         }
    
    }
	
	
	 @SuppressWarnings("rawtypes")
    public boolean areCredentialsValid(String userName, String password1) {
        try {
            // 1. configuring hibernate
        	Configuration  configuration = new Configuration ().configure();
        	
            // 2. create sessionfactory
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
 
            // 3. Get Session object
            Session session = sessionFactory.openSession();
 
            // 4. Starting Transaction
            Transaction transaction = session.beginTransaction();
                     
            Query query = session.getNamedQuery("callValidateCredentials")
           		 .setParameter("userName", userName)
           		 .setParameter("password1", password1);
            
            ArrayList accountList = (ArrayList) query.list();
            
            transaction.commit();
            System.out.println("\n\n Details Added \n");
           		 
            if (accountList.isEmpty())
            	return false;
            else
           	 return true;
            
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
            throw e;
        }
 
    }
 
	 @SuppressWarnings("rawtypes")
    public String getAccountInfo(String userName) {
   	 try {
            // 1. configuring hibernate
        	Configuration  configuration = new Configuration ().configure();
        	
            // 2. create sessionfactory
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
 
            // 3. Get Session object
            Session session = sessionFactory.openSession();
 
            // 4. Starting Transaction
            Transaction transaction = session.beginTransaction();
            
            /**
             * The following method session.getNamedQuery calls a stored procedure which is defined in the Account bean.
             */
            
            Query query = session.getNamedQuery("callRetrieveAccountInfo")
              		 .setParameter("userName", userName);
            
            /**
             * The result of the call to the stored procedure is returned and stored in the accountList ArrayList.
             */
            ArrayList accountList = (ArrayList) query.list();
            
            /**
             * The accountList is transformed into a Json string.
             */
        	String json = new Gson().toJson(accountList);
        	System.out.println(json); 
            
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