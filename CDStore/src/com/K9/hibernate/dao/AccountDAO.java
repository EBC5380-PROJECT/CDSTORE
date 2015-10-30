package com.K9.hibernate.dao;
 
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.K9.hibernate.bean.Account;
import com.K9.util.HibernateUtil;
import com.K9.util.PasswordHash;
import com.K9.util.ResponseFactory;
import com.google.gson.Gson;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

/**
 * This Data Access Object class is used to access the account table in the database.  The hibernate framework is used to manage the interaction with the database.

 * 
 * @author MBP
 */

		
 
public class AccountDAO {
 
	/**
	 * 
	 * The addAccountDetails method is used to insert a new account into the database.
	 * 
	 * @param accountName
	 * @param password1
	 * @param fName
	 * @param lName
	 * @param billingAddressId
	 * @param shippingAddressId
	 * @param email
	 * @return
	 */
    public boolean addAccountDetails(String accountName, String password1,String fName, String lName, int billingAddressId, int shippingAddressId, String email) {
        try {
        	/**
        	 * The following steps are specific to Hibernate and are used to establish connectivity and a session with the database
        	 * 
        	 */
        	
        	//Configure Hibernate and get the sessionFactory and get a session object
        	
        	Session session = HibernateUtil.getSessionFactory().openSession();
        	
        	            
        	//Starting Transaction
        	 Transaction transaction=session.beginTransaction();    
        	 
        	//Instantiating a new Account class and setting the values to be stored in the account table.
                        
            Account accountInfo = new Account();
            
            accountInfo.setAccountName(accountName);
            accountInfo.setPassword1(password1);
            accountInfo.setFName(fName);
            accountInfo.setLName(lName);
            accountInfo.setbillingAddressId(billingAddressId);
            accountInfo.setShippingAddressId(shippingAddressId);
            accountInfo.setEmail(email);
            
           // Saving the account information in the account table.
            session.save(accountInfo);
            
            //Committing the transaction in the database.

            transaction.commit();
            
                       
            return true;
 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
           // return false;
            throw e;
        }
 
    }
    
    
    /**
     * The isUserNameUnique method serves to check whether a userName is unique or not.  Upon registering, the user must provide a userName that is unique.
     * 
     * @param userName
     * @return
     */
	@SuppressWarnings("rawtypes")
    public boolean isUserNameUnique(String userName) {
    	 try {
    		 /**
         	 * The following steps are specific to Hibernate and are used to establish connectivity and a session with the database
         	 * 
         	 */
         	
         	//Configure Hibernate and get the sessionFactory and get a session object
         	
         	Session session = HibernateUtil.getSessionFactory().openSession();
         	
         	            
         	//Starting Transaction
         	 Transaction transaction=session.beginTransaction();
         	 
         	 //Query is executed in order to verify whether the userName is unique or not.
             Query query = session.getNamedQuery("callRetrieveAccountInfo")
            		 .setParameter("userName", userName);
             
             ArrayList accountList = (ArrayList) query.list();
             
             //commit transaction
             transaction.commit();
                         		 
             if (accountList.isEmpty())
             	return true; //userName is unique
             else
            	 return false;  //userName was found in the database therefore it is not unique
             
             
             
    	 } catch (HibernateException e) {
             System.out.println(e.getMessage());
             e.printStackTrace();
             //return false;
             throw e;
         }
    
    }
	

	/**
	 * 
	 * The areCredentialsValid method is called in order to validate if the user credentials the user provided while logging in are valid.
	 * 
	 * 
	 * @param userName
	 * @param password1
	 * @return
	 * 
	 */
    public boolean areCredentialsValid(String userName, String password1) {
        try {
        	//Define local variable
        	boolean validPassword = false;
        	
        	/**
        	 * The following steps are specific to Hibernate and are used to establish connectivity and a session with the database
        	 * 
        	 */
        	
        	//Configure Hibernate and get the sessionFactory and get a session object
        	
        	Session session = HibernateUtil.getSessionFactory().openSession();
        	
        	            
        	//Starting Transaction
        	 Transaction transaction=session.beginTransaction();
        	 
        	//Query executed in order to retrieve the account information for the userName provided by user
            Query query = session.getNamedQuery("callRetrieveAccountInfo")
              		 .setParameter("userName", userName);
          
            //if the userName was found in the database, the following is performed
            if (query.list().isEmpty() != true) {
	            Account accountQueryResult = (Account) query.uniqueResult();  //Map the information returned in the query to an instance of Account
	            
	            //validate password by sending the password provided by the user in clear text and the password hash returned in the query.
	            validPassword = PasswordHash.validatePassword(password1, accountQueryResult.getPassword1());
	            
            } 
           //commit the transaction
            transaction.commit();
            
                             	
            if (validPassword)
            	return true;  //return true if the credentials pass validation
            else
           	 return false;  // return false if the credentials fail validation
            
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
        	System.out.println(e.getMessage());
        	e.printStackTrace();
            return false;
        	//throw e;
        }
 
    }
 
    public String getAccountInfo(String userName) {
   	 try {
   		 //Declare local variable
   		 	String json = "";
   		 	
   		 /**
        	 * The following steps are specific to Hibernate and are used to establish connectivity and a session with the database
        	 * 
        	 */
        	
        	//Configure Hibernate and get the sessionFactory and get a session object
        	
        	Session session = HibernateUtil.getSessionFactory().openSession();
        	
        	            
        	//Starting Transaction
        	 Transaction transaction=session.beginTransaction();
        	 
        	 
        	 
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
            e.printStackTrace();
            return ResponseFactory.create(1000);  //returning system level error alert
        }
   
   }
 
    
    
}