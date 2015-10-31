package com.K9.session.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.io.Serializable;

import javax.persistence.Column;


/**
 * OrderItem Bean - contains the item information that is part of an order
 * @author MBP
 *
 */
 
@Entity
public class OrderItemShoppingCart implements Serializable{
	
	private static final long serialVersionUID = 1L;
		
		// Getters and Setters are defined below for the values in this bean
	
		// orderitemId is the primary key for this class and is auto-generated in the database
		// Hibernate annotations used to define the primary key for table Orderitem.
	
		
	private String accountName;
    private int cdid;	
    private int quantity;
    
	  //[{"orderId":1,"cdid":1,"quantity":3},{"orderId":1,"cdid":2,"quantity":2}]
        
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
    
    public int getQuantity() {
        return quantity;
    }
 
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
 
       
}