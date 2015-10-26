package com.K9.util;


import java.util.Map;
import java.util.ResourceBundle;

import org.json.JSONArray;

import com.K9.hibernate.bean.OrderItem;
import com.K9.hibernate.dao.OrderItemDAO;
import com.K9.hibernate.dao.OrdersDAO;
import com.K9.session.bean.ShippingInfo;
import com.google.gson.Gson;

public class CreateOrderFactory {
	
	public void createOrder(String shippingInfo, String shoppingCartInfo){
		
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
		
	     } catch (Exception f) {
	    	 System.out.println(f.getMessage());
		     System.out.println("error");
		   	  try {
				throw f;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     }
	}
	
 }    
