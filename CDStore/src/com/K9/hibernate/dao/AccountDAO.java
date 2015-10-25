package com.K9.hibernate.dao;
 
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.json.JSONException;

import com.K9.hibernate.bean.Account;
import com.K9.util.PasswordHash;
import com.google.gson.Gson;


import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

/**
 * This Data Access Object class is used to access the category table in the database.  The hibernate framework is used to manage the interaction with the database.

 * 
 * @author MBP
 */

		
 
public class AccountDAO {
 
    public boolean addAccountDetails(String accountName, String password1,String fName, String lName, int billingAddressId, int shippingAddressId, String email) {
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
            accountInfo.setFName(fName);
            accountInfo.setLName(lName);
            accountInfo.setbillingAddressId(billingAddressId);
            accountInfo.setShippingAddressId(shippingAddressId);
            accountInfo.setEmail(email);
            
            session.save(accountInfo);
            transaction.commit();
                       
            return true;
 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
            throw e;
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
                         		 
             if (accountList.isEmpty())
             	return true;
             else
            	 return false;
             
             
             
    	 } catch (HibernateException e) {
             System.out.println(e.getMessage());
             System.out.println("error");
             throw e;
         }
    
    }
	

    public boolean areCredentialsValid(String userName, String password1) throws NoSuchAlgorithmException, InvalidKeySpecException, JSONException {
        try {
        	
        	boolean validPassword = false;
        	
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
          
            if (query.list().isEmpty() != true) {
	            Account accountQueryResult = (Account) query.uniqueResult();
	            
	            //validate password
	            validPassword = PasswordHash.validatePassword(password1, accountQueryResult.getPassword1());
	            
	           
	            System.out.println("password: "+ accountQueryResult.getPassword1());
            } 
            
            transaction.commit();
            
                             	
            if (validPassword)
            	return true;
            else
           	 return false;
            
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
            throw e;
        }
 
    }
 
    public String getAccountInfo(String userName) {
   	 try {
   		 	String json = "";
   		 	
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
             * The result of the call to the stored procedure is returned and stored in the accountQueryResult.
             */
            if (query.list().isEmpty() != true) {
	            Account accountQueryResult = (Account) query.uniqueResult();
	            
	            /**
	             * The accountQueryResult is transformed into a Json string.
	             */
	        	json = new Gson().toJson(accountQueryResult);
	        	System.out.println(json); 
	            
            } 
            
                       
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
            throw e;
        }
   
   }
 
    
    
}