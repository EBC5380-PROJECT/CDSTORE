/**
 * OrderItem Bean - contains the item information that is part of an order
 * @author MBP
 *
 */
package com.K9.hibernate.bean;

import javax.persistence.Entity;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import java.io.Serializable;
 
@Entity
public class OrderItem implements Serializable{
	static final long serialVersionUID = 41L;
	
	// Getters and Setters are defined below for the values in this bean
	
		// orderitemId is the primary key for this class and is auto-generated in the database
		// Hibernate annotations used to define the primary key for table Orderitem.
	
	@Id @GeneratedValue
	@Column(name = "orderitemId")
	private int orderitemId;
	
	private int orderId;
    private int cdid;	
    private int quantity;
    
	  
    public int getOrderitemId () {
        return orderitemId;
    }
     
    public void setOrderitemId(int orderitemId) {
        this.orderitemId = orderitemId;
    }
    
    public int getOrderId () {
        return orderId;
    }
     
    public void setOrderId(int orderId) {
        this.orderId = orderId;
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