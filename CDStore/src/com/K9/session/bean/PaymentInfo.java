package com.K9.session.bean;


/**
 * Orders Bean - contains order information 
 * @author MBP
 *
 */

 
public class PaymentInfo {
	
	// Getters and Setters are defined below for the values in this bean
	
	// This bean is not persisted in the database.  Simply used to transfer information from the View to the Model.
	
    private String creditCardHolderName;
    private String creditCardNumber;
    private String expiryDate;
    private int ccv;
    
   
    
    public String getCreditCardHolderName() {
        return creditCardHolderName;
    }
     
    public void setCreditCardHolderName(String creditCardHolderName) {
        this.creditCardHolderName = creditCardHolderName;
    }
    public String getExpiryDate() {
        return expiryDate;
    }
     
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    public int getCcv() {
        return ccv;
    }
     
    public void setCcv(int ccv) {
        this.ccv = ccv;
    }
    
    public String getCreditCardNumber() {
        return creditCardNumber;
    }
     
    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
    
   
}