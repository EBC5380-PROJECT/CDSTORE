package com.K9.WebServices.OrderProcessService;
 

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.hibernate.HibernateException;
import org.json.JSONArray;
import org.json.JSONException;
import com.K9.hibernate.dao.*;
import com.K9.hibernate.bean.Orders;
import com.K9.hibernate.bean.OrderItem;
import com.google.gson.Gson;
import com.K9.session.bean.*;



public class OrderProcessService {
	
	 @SuppressWarnings("rawtypes")		
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
				 int shippingAddressId = addressDAO1.addAddressDetails(accntInfo.getShippingAddressStreet(), accntInfo.getShippingAddressCity(), accntInfo.getShippingAddressProvince(), accntInfo.getShippingAddressCountry(), accntInfo.getShippingAddressPostalCode(), accntInfo.getShippingAddressPhone());
					
				 
				 //Store the account information
				 accntDAO.addAccountDetails(accntInfo.getAccountName(), accntInfo.getPassword1(), billingAddressId, shippingAddressId, accntInfo.getEmail());
				 
				 return "";
				 
			 } else {
				 Locale locale = new Locale("en","US");
				 ResourceBundle errorMessage = ResourceBundle.getBundle("com.K9.MessageBundle",locale);
				 return errorMessage.getString("USER_NAME_NOT_UNIQUE_ERROR");
				 
			 }
			 
			
			 
			 
		   } catch (HibernateException e) {
	            System.out.println(e.getMessage());
	            System.out.println("error");
	            throw e;
	        }
		 
		}
		
	}        	

	 
@SuppressWarnings("rawtypes") 	 
public String getAccount(String accountName, String password) {
    
	
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
		 else {
			 Locale locale = new Locale("en","US");
			 ResourceBundle errorMessage = ResourceBundle.getBundle("com.K9.MessageBundle",locale);
			 return errorMessage.getString("LOGIN_ERROR");
		 }
		 
			return acctInfo;
		 
		   
		 
	   } catch (HibernateException e) {
           System.out.println(e.getMessage());
           System.out.println("error");
           throw e;
       }
  }        	



public void createOrder(String shoppingCartInfo, String shippingInfo) throws JSONException {
	
	ResourceBundle rb = ResourceBundle.getBundle("com.K9.enums"); // prop.properties

	
	 
	 try {
		 OrderItem shippingInfo2;	
		 String shoppingCartInfoInstance;
		 
		 ShippingInfo shippingInfo1 = new ShippingInfo();
		 
		 Gson gson = new Gson();
		 shippingInfo1 = gson.fromJson(shippingInfo, ShippingInfo.class);	
		 
		 OrdersDAO ordersDAO = new OrdersDAO();
		 int orderId=ordersDAO.addOrder(shippingInfo1.getAccountId(), rb.getString("ORDERED"), shippingInfo1.getShippingCharge(), shippingInfo1.getTaxes(), shippingInfo1.getTotalCost());
		 
			 		
		JSONArray jsonList = new JSONArray(shoppingCartInfo);		
		OrderItemDAO orderItem = new OrderItemDAO();
			
		 for (int i = 0; i < jsonList.length(); i++) {
			  shoppingCartInfoInstance=jsonList.get(i).toString();
			  shippingInfo2 = gson.fromJson(shoppingCartInfoInstance, OrderItem.class);
			  orderItem.addOrderItem(orderId, shippingInfo2.getCdId(), shippingInfo2.getQuantity()); 	 
			}
		
		 } catch (JSONException f) {
	   	  	System.out.println(f.getMessage());
	        System.out.println("error");
	   	  throw f;
	     } catch (Exception f) {
	    	 System.out.println(f.getMessage());
		     System.out.println("error");
		   	  throw f;
	     }
     
	
 }        	



public void confirmOrder(String purchaseOrder, String shippingInfo, String paymentInfo) {
	 
	ResourceBundle rb = ResourceBundle.getBundle("com.K9.enums"); // prop.properties
	 
	 try {

		 //call to Authorisation Service.  Returns whether the credit card purchase is authorised or not.
		 //TODO -  Talk with Kaifan
		 
		 boolean authorisedPurchase;
		 String status="";
		
		 
		 Gson gson = new Gson();
         Orders orders = gson.fromJson(purchaseOrder, Orders.class);	
		 
         // once the web service call to authorisation ws is completed, the followning line of code will be removed
         //TODO
		 authorisedPurchase = true;
		 
		 if (authorisedPurchase)
			 status= rb.getString("PROCESSED");		
		 else 
			 status=rb.getString("DENIED");	
		
		 
		 OrdersDAO ordersDAO = new OrdersDAO(); 
		 
		 ordersDAO.updateOrderStatus(orders.getOrderId(), status, orders.getAccountId());
		
		 
	   } catch (HibernateException e) {
          System.out.println(e.getMessage());
          System.out.println("error");
          throw e;
      }
 }        	


}