package com.K9.hibernate.bean;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Column;
import java.io.Serializable;


/**
 * Orders Bean - contains order information 
 * @author MBP
 *
 */
 
@NamedNativeQuery(
		name = "callUpdateStatusProcedure",
		query = "CALL sp_updateOrderStatus(:orderId, :orderStatus, :accountId)",
		resultClass = Orders.class
		)


@Entity
public class Orders implements Serializable{
	static final long serialVersionUID = 1L;
	
	// Getters and Setters are defined below for the values in this bean
	
		// orderId is the primary key for this class and is auto-generated in the database
		// Hibernate annotations used to define the primary key for table Orders.
	
	@Id @GeneratedValue
	@Column(name = "orderId")
	private int orderId;
	
	private int accountId;
	
    private String status;
    private Double shippingCharge;
    private Double taxes;
    private Double totalCost;
    
	  

    public int getOrderId () {
        return orderId;
    }
     
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    
    public int getAccountId () {
        return accountId;
    }
     
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
 
    
    public String getStatus() {
        return status;
    }
 
    public void setStatus(String status) {
        this.status = status;
    }
 
    public Double getShippingCharge() {
        return shippingCharge;
    }
     
    public void setShippingCharge(Double shippingCharge) {
        this.shippingCharge = shippingCharge;
    }
    
    public Double getTaxes() {
        return taxes;
    }
     
    public void setTaxes(Double taxes) {
        this.taxes = taxes;
    }
    
    public Double getTotalCost() {
        return totalCost;
    }
     
    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }
}