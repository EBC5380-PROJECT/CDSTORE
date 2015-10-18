package com.K9.session.bean;
/**
 * Orders Bean - contains order information 
 * @author MBP
 *
 */
//import javax.persistence.Entity;
import java.io.Serializable;
 
//@Entity
public class ShippingInfo implements Serializable{
	static final long serialVersionUID = 41L;
	
	// Getters and Setters are defined below for the values in this bean
	
	// This bean is not persisted in the database.  Simply used to transfer information from the View to the Model.
	
	private int accountId;
    private Double shippingCharge;
    private Double taxes;
    private Double totalCost;
   
    
    public int getAccountId() {
        return accountId;
    }
     
    public void setAccountId(int accountId) {
        this.accountId = accountId;
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