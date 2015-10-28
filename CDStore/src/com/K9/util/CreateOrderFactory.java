package com.K9.util;

import java.util.ResourceBundle;

import org.json.JSONArray;
import com.K9.hibernate.bean.OrderItem;
import com.K9.hibernate.dao.OrderItemDAO;
import com.K9.hibernate.dao.OrdersDAO;
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
	
	public void createOrder(String shippingInfo, String shoppingCartInfo){
		
		
		ResourceBundle rb = ResourceBundle.getBundle("com.K9.resources.enums");  //enumerations for the order status 
		
	 try {
		 //declaring local variables
		 OrderItem shippingInfo2;	
		 String shoppingCartInfoInstance;
		 
		 //creating an instance of ShippingInfo in order 
		 ShippingInfo shippingInfo1 = new ShippingInfo();
		 
		 //The shipping info received from the calling servlet is converted from a Json string to the instance of ShippingInfo class.
		 Gson gson = new Gson();
		 shippingInfo1 = gson.fromJson(shippingInfo, ShippingInfo.class);	
		 
		 //An instance of OrdersDAO is created so the the addOrder method can be called to store the order in the database.
		 OrdersDAO ordersDAO = new OrdersDAO();
		 int orderId=ordersDAO.addOrder(shippingInfo1.getAccountId(), rb.getString("ORDERED"), shippingInfo1.getShippingCharge(), shippingInfo1.getTaxes(), shippingInfo1.getTotalCost());
		 
		//A json array is created containing an array of shoppingCartInfo	 		
		JSONArray jsonList = new JSONArray(shoppingCartInfo);
		
		//An instance of OrderItemDAO is created
		OrderItemDAO orderItem = new OrderItemDAO();
		
		//For every shoppingCartInfo instance, an new OrderItem is inserted into the database
		 for (int i = 0; i < jsonList.length(); i++) {
			  shoppingCartInfoInstance=jsonList.get(i).toString();
			  shippingInfo2 = gson.fromJson(shoppingCartInfoInstance, OrderItem.class);
			  orderItem.addOrderItem(orderId, shippingInfo2.getCdId(), shippingInfo2.getQuantity()); 	 
			}
		
	     } catch (Exception f) {
	    	 System.out.println(f.getMessage());
		     try {
				throw f;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
	     }
	
	
 }    
