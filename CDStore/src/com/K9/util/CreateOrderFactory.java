package com.K9.util;

import java.util.Map;
import java.util.ResourceBundle;

import org.json.JSONArray;
import com.K9.hibernate.bean.OrderItem;
import com.K9.hibernate.dao.AccountDAO;
import com.K9.hibernate.dao.OrderItemDAO;
import com.K9.hibernate.dao.OrdersDAO;
import com.K9.session.bean.AccountInfo;
import com.K9.session.bean.ShippingInfo;
import com.google.gson.Gson;


/**
 * 
 * The CreateOrderFactory is used to store an order in the database.
 * 
 * @author Michele
 *
 */
public class CreateOrderFactory {
	
	/**
	 * 
	 * The createOrder method serves to store an order in the order table and all of the order items in the orderItem table.
	 * 
	 * @param shippingInfo
	 * @param shoppingCartInfo
	 */
	
	public String createOrder(String shippingInfo, String shoppingCartInfo){
		
		
		ResourceBundle rb = ResourceBundle.getBundle("com.K9.resources.enums");  //enumerations for the order status 
		
	 try {
		 //declaring local variables
		 OrderItem shippingInfo2;	
		 String shoppingCartInfoInstance;
		 
		 //Need to extract accountName from shoppingCartInfo
		 
		 //creating an instance of ShippingInfo in order 
		 ShippingInfo shippingInfo1 = new ShippingInfo();
		 
		 //The shipping info received from the calling servlet is converted from a Json string to the instance of ShippingInfo class.
		 Gson gson = new Gson();
		 shippingInfo1 = gson.fromJson(shippingInfo, ShippingInfo.class);	
		 String accountName = shippingInfo1.getAccountName();
		 
		 AccountDAO accntDAO = new AccountDAO();
		 String acctInfo = accntDAO.getAccountInfo(accountName);
		 
		 
		 
		 Map jsonJavaRootObject = new Gson().fromJson(acctInfo, Map.class);
         //int accountId= (int) jsonJavaRootObject.get("accountId");
		 
		 
		 //{"accountId":1,"accountName":"mbp","password1":"1000:2fff4da835023641538d7d4937d38dde864e375748b8c30a:22421122fc8b5c67014f58bc0af5d46a081902a9d6bf8467","fName":"michele","lName":"belanger","billingAddressId":5,"shippingAddressId":6,"email":"mbp@gmail.com"}
		// int accntId;
		 
		 
		 AccountInfo accountInfo = new AccountInfo();
		 //accountInfo = gson.fromJson(accountInfo, AccountInfo.class);	
		 
		
	
		 
		 //An instance of OrdersDAO is created so the the addOrder method can be called to store the order in the database.
		 OrdersDAO ordersDAO = new OrdersDAO();
		 String orderId=ordersDAO.addOrder(1, rb.getString("ORDERED"), shippingInfo1.getShippingCharge(), shippingInfo1.getTaxes(), shippingInfo1.getTotalCost());
		 
		 
		 
		//A json array is created containing an array of shoppingCartInfo	 		
		JSONArray jsonList = new JSONArray(shoppingCartInfo);
		
		//An instance of OrderItemDAO is created
		OrderItemDAO orderItem = new OrderItemDAO();
		
		//For every shoppingCartInfo instance, an new OrderItem is inserted into the database
		 for (int i = 0; i < jsonList.length(); i++) {
			  shoppingCartInfoInstance=jsonList.get(i).toString();
			  shippingInfo2 = gson.fromJson(shoppingCartInfoInstance, OrderItem.class);
			//  orderItem.addOrderItem(Integer.valueOf(orderId), shippingInfo2.getCdId(), shippingInfo2.getQuantity()); 	
			  orderItem.addOrderItem(1, shippingInfo2.getCdId(), shippingInfo2.getQuantity()); 	
			}
		 
		 return "";
		
	     } catch (Exception e) {
	    	 System.out.println(e.getMessage());
	    	 e.printStackTrace();
	    	 return ResponseFactory.create(1000);  //returning system level error alert
		  }
	     }
	
	
 }    
