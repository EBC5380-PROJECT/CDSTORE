package com.K9.util;


import com.K9.hibernate.bean.Account;
import com.K9.hibernate.dao.AccountDAO;
import com.K9.session.bean.ShippingInfo;
import com.google.gson.Gson;


/**
 * 
 * The GetAccountIdUtil is used to retrieve the accountId from the shippingInfo being passed by the calling servlet.
 * 
 * @author Michele
 *
 */
public class GetAccountIdUtil {
	
	/**
	 * 
	 * The createOrder method serves to store an order in the order table and all of the order items in the orderItem table.
	 * 
	 * @param shippingInfo
	 * @param shoppingCartInfo
	 */
	
	public String getId(String shippingInfo){
		
	 try {
				 
		 //Need to extract accountName from shippingInfo
		 
		 //creating an instance of ShippingInfo in order 
		 ShippingInfo shippingInfo1 = new ShippingInfo();
		 
		 //The shipping info received from the calling servlet is converted from a Json string to the instance of ShippingInfo class.
		 Gson gson = new Gson();
		 shippingInfo1 = gson.fromJson(shippingInfo, ShippingInfo.class);	
		 String accountName = shippingInfo1.getAccountName();
		 
		 AccountDAO accntDAO = new AccountDAO();
		 String acct = accntDAO.getAccountInfo(accountName);
		 
		 Account jobj = new Gson().fromJson(acct, Account.class);
		 
		 int accntId = jobj.getAccountId();			 
		 
		 	//return accntId to the caller
	    	return Integer.toString(accntId);
		 
		
	     } catch (Exception e) {
	    	 System.out.println(e.getMessage());
	    	 e.printStackTrace();
	    	 return ResponseFactory.create(1000);  //returning system level error alert
		  }
	     }
	
	
 }    
