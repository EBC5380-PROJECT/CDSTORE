package com.K9.WebServices.OrderProcessService;
 
import java.util.Map;


import org.hibernate.HibernateException;

import com.K9.hibernate.dao.*;
import com.google.gson.Gson;

import com.K9.hibernate.bean.AccountInfo;


public class OrderProcessService {
	
	
	public String creatAccount(String accountName, String accountInfo) {
	       
		{
		 
		 
		 try {
			 	 
			 
	              
	        	         
	         AccountInfo accntInfo = new AccountInfo();
	         
	         Gson gson = new Gson();
	         accntInfo = gson.fromJson(accountInfo, AccountInfo.class);	
	         
	         Map jsonJavaRootObject = new Gson().fromJson(accountName, Map.class);
	         String accountName1=(String) jsonJavaRootObject.get("accountName");
			 
			 
	         AccountDAO accntDAO = new AccountDAO();		 
			 //determine whether the account name is unique
			 boolean validAccountName = accntDAO.isUserNameUnique(accountName1);
			 
			 if (validAccountName) {
				 // Need to store both the billing address and shipping address
				 AddressDAO addressDAO = new AddressDAO();
				int billingAddressId = addressDAO.addAddressDetails(accntInfo.getBillingAddressStreet(), accntInfo.getBillingAddressCity(), accntInfo.getBillingAddressProvince(), accntInfo.getBillingAddressCountry(), accntInfo.getBillingAddressPostalCode(), accntInfo.getBillingAddressPhone());
				 
				 AddressDAO addressDAO1 = new AddressDAO();
				 int shippingAddressId = addressDAO.addAddressDetails(accntInfo.getShippingAddressStreet(), accntInfo.getShippingAddressCity(), accntInfo.getShippingAddressProvince(), accntInfo.getShippingAddressCountry(), accntInfo.getShippingAddressPostalCode(), accntInfo.getShippingAddressPhone());
					
				 
				 //Store the account information
				 accntDAO.addAccountDetails(accntInfo.getAccountName(), accntInfo.getPassword1(), billingAddressId, shippingAddressId, accntInfo.getEmail());
				 
				 return "";
				 
			 } else {
				 return "error Message:  Account name is not unique";
				 
			 }
			 
			
			 
			 
		   } catch (HibernateException e) {
	            System.out.println(e.getMessage());
	            System.out.println("error");
	            //return e.getMessage();
	            throw e;
	        }
		 
		}
		
	}        	

	 
 	 
public String getAccount(String accountName, String password) {
    
	
	 String acctName;
	 String accountName1;
	 String acctInfo;
	 String  password1;	
	 
	 try {
			 
		 	 
		 
         Map jsonJavaRootObject = new Gson().fromJson(accountName, Map.class);
         accountName1=(String) jsonJavaRootObject.get("accountName");
         
         Map jsonJavaRootObject2 = new Gson().fromJson(password, Map.class);
         password1=(String) jsonJavaRootObject2.get("password");
		 
		 AccountDAO accntDAO = new AccountDAO();
		 //determine whether the user credentials are valid
		 boolean validUserCredentials = accntDAO.areCredentialsValid(accountName1, password1);
		 
		 if (validUserCredentials) 
			acctInfo = accntDAO.getAccountInfo(accountName1);
		 else
			 return "Error Message: UserName or password is invalid";
		 
			return acctInfo;
		 
		   
		 
	   } catch (HibernateException e) {
           System.out.println(e.getMessage());
           System.out.println("error");
           //return e.getMessage();
           throw e;
       }
  }        	



public String createOrder(String shoppingCartInfo, String shippingInfo) {
    
	 
	 try {

		 
		 return "jj";
		 
		 
	   } catch (HibernateException e) {
          System.out.println(e.getMessage());
          System.out.println("error");
          return e.getMessage();
          //throw e;
      }
 }        	



public String confirmOrder(String purchaseOrder, String shippingInfo, String PaymentInfo) {
    
	 
	 try {

		 
		 return "jj";
		 
		 
	   } catch (HibernateException e) {
          System.out.println(e.getMessage());
          System.out.println("error");
          return e.getMessage();
          //throw e;
      }
 }        	


}