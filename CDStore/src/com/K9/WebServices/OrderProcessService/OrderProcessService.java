package com.K9.WebServices.OrderProcessService;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.hibernate.HibernateException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.K9.hibernate.dao.*;
import com.K9.hibernate.bean.Orders;
import com.K9.hibernate.bean.OrderItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.K9.session.bean.*;
import java.lang.reflect.*;


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
		 OrderItem shippingInfo2;	
		 String shoppingCartInfoInstance;
		 
		 ShippingInfo shippingInfo1 = new ShippingInfo();
		 
		 Gson gson = new Gson();
		 shippingInfo1 = gson.fromJson(shippingInfo, ShippingInfo.class);	
		 
		 OrdersDAO ordersDAO = new OrdersDAO();
		 int orderId=ordersDAO.addOrder(shippingInfo1.getAccountId(), "Ordered", shippingInfo1.getShippingCharge(), shippingInfo1.getTaxes(), shippingInfo1.getTotalCost());
		 
			 		
		JSONArray jsonList = new JSONArray(shoppingCartInfo);		
		OrderItemDAO orderItem = new OrderItemDAO();
			
		 for (int i = 0; i < jsonList.length(); i++) {
			  shoppingCartInfoInstance=jsonList.get(i).toString();
			  shippingInfo2 = gson.fromJson(shoppingCartInfoInstance, OrderItem.class);
			  orderItem.addOrderItem(orderId, shippingInfo2.getCdId(), shippingInfo2.getQuantity()); 	 
			}
		
		 return "jj";
		    
		 
	   } catch (HibernateException e) {
          System.out.println(e.getMessage());
          System.out.println("error");
          return e.getMessage();
          //throw e;
      } catch (JSONException e) {
    	  return e.getMessage();
      }
	
 }        	



public void confirmOrder(String purchaseOrder, String shippingInfo, String paymentInfo) {
	 
	 
	 try {

		 //call to Authorisation Service.  Returns whether the credit card purchase is authorised or not.
		 //TODO -  Talk with Kaifan
		 
		 boolean authorisedPurchase;
		 String status="";
		
		 
		 Gson gson = new Gson();
         Orders orders = gson.fromJson(purchaseOrder, Orders.class);	
		 
         System.out.println("accountID"+orders.getOrderId());
         
		 authorisedPurchase = true;
		 
		 if (authorisedPurchase)
			 status="Processed";		
		 else 
			 status="Denied";	
		 
		 status="test2";
		 System.out.println("status"+ status);
		 OrdersDAO ordersDAO = new OrdersDAO(); 
		 ordersDAO.updateOrderStatus(orders.getOrderId(), status);
		
		 
	   } catch (HibernateException e) {
          System.out.println(e.getMessage());
          System.out.println("error");
          throw e;
      }
 }        	


}