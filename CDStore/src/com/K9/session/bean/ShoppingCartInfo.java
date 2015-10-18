package com.K9.session.bean;

import java.io.Serializable;
import java.util.List;

import com.K9.hibernate.bean.OrderItem;

/**
 * Shopping Cart Info Bean - contains order information 
 * @author MBP
 *
 */


public class ShoppingCartInfo implements Serializable{
	static final long serialVersionUID = 41L;
	
	// Getters and Setters are defined below for the values in this bean
	
	// This bean is not persisted in the database.  Simply used to transfer information from the View to the Model.
	
		
	//private String accountName;	
   // private int cdid;	
   // private int quantity;
    
    private List<OrderItem> orderItems;
   // private List<Class2> elements;

    
    public List<OrderItem> getOrderItem() {
    	return orderItems;
    }
    
    public void setOrderItem(List<OrderItem> orderItems){
    	 this.orderItems = orderItems;
    }
    /*
	  
    public String getAccountName () {
        return accountName;
    }
     
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    
    public int getCdId () {
        return cdid;
    }
     
    public void setCdId(int cdid) {
        this.cdid = cdid;
    }
    
    public int getQuantity () {
        return quantity;
    }
     
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
 */
    
 }

