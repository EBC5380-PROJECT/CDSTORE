package com.K9.util;

import java.util.Map;
import java.util.ResourceBundle;

import org.json.JSONArray;

import com.K9.hibernate.bean.Account;
import com.K9.hibernate.bean.OrderItem;
import com.K9.hibernate.dao.AccountDAO;
import com.K9.hibernate.dao.OrderItemDAO;
import com.K9.hibernate.dao.OrdersDAO;
import com.K9.session.bean.AccountInformation;
import com.K9.session.bean.ShippingInfo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


/**
 * 
 * The CreateOrderFactory is used to store an order in the database.
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
		 
		 	//return the Json String to be returned to the calling servlet
	    	return Integer.toString(accntId);
		 
		
	     } catch (Exception e) {
	    	 System.out.println(e.getMessage());
	    	 e.printStackTrace();
	    	 return ResponseFactory.create(1000);  //returning system level error alert
		  }
	     }
	
	
 }    
