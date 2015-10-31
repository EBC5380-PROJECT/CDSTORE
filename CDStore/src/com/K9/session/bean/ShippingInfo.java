package com.K9.session.bean;

import java.io.Serializable;

/**
 * ShippingInfo Bean - contains shipping information 
 * @author MBP
 *
 */
 

public class ShippingInfo implements Serializable{
		
	private static final long serialVersionUID = 1L;
	// Getters and Setters are defined below for the values in this bean
	
	// This bean is not persisted in the database.  Simply used to transfer information from the View to the Model.
	
	private String accountName;
    private Double shippingCharge;
    private Double taxes;
    private Double totalCost;
   
    //{"accountId":1,"shippingCharge":5.25,"taxes":4.25,"totalCost":50.32}
    public String getAccountName() {
        return accountName;
    }
     
    public void setAccountName(String accountName) {
        this.accountName = accountName;
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