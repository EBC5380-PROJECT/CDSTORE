package com.K9.session.bean;

import java.util.List;

import com.K9.hibernate.bean.OrderItem;

/**
 * Shopping Cart Info Bean - contains order information 
 * @author MBP
 *
 */


public class ShoppingCartInfo {
	
	// Getters and Setters are defined below for the values in this bean
	
	// This bean is not persisted in the database.  Simply used to transfer information from the View to the Model.
	
	    
    private List<OrderItem> orderItems;
      
    public List<OrderItem> getOrderItem() {
    	return orderItems;
    }
    
    public void setOrderItem(List<OrderItem> orderItems){
    	 this.orderItems = orderItems;
    }
      
 }

