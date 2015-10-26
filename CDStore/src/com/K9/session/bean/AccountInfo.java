package com.K9.session.bean;

import java.io.Serializable;

import javax.persistence.*;

/**
 * AccountInfo Bean - contains CD Store client account information
 * @author MBP
 *
 */




@Entity
public class AccountInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// Getters and Setters are defined below for the values in this bean
	
	// This bean is not persisted in the database.  Used to transfer information from the view to the model.
	
		
	private String accountName;
	private String password1;
	private String salt;
	private String fName;
	private String lName;
	private int billingAddressId;
	private int shippingAddressId;
	private String email;
	
	private String billingAddressStreet;
    private String billingAddressCity;
    private String billingAddressProvince;
    private String billingAddressCountry;
    private String billingAddressPostalCode;
    private String billingAddressPhone;
      
    
    private String shippingAddressStreet;
    private String shippingAddressCity;
    private String shippingAddressProvince;
    private String shippingAddressCountry;
    private String shippingAddressPostalCode;
    private String shippingAddressPhone;
      
    
	     
	 public String getAccountName() {
	        return accountName;
    }
 
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    
    
    public String getPassword1() {
        return password1;
    }
 
    public void setPassword1(String password1) {
        this.password1 = password1;
    }
    
    public String getSalt() {
        return salt;
    }
 
    public void setSalt(String salt) {
        this.salt = salt;
    }
    public String getFName() {
        return fName;
    }
 
    public void setFName(String fName) {
        this.fName = fName;
    }
	  
    public String getLName() {
        return lName;
    }
 
    public void setLName(String lName) {
        this.lName = lName;
    }
	 
	    
   
    public int getbillingAddressId() {
        return billingAddressId;
    }
 
    public void setbillingAddressId(int billingAddressId) {
        this.billingAddressId = billingAddressId;
    }
    
    
    public int getShippingAddressId() {
        return shippingAddressId;
    }
 
    public void setShippingAddressId(int shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }
     
    
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
  
    public String getShippingAddressStreet() {
        return shippingAddressStreet;
    }
 
    public void setShippingAddressStreet(String shippingAddressStreet) {
        this.shippingAddressStreet = shippingAddressStreet;
    }
 
    public String getShippingAddressCity() {
        return shippingAddressCity;
    }
 
    public void setShippingAddressCity(String shippingAddressCity) {
        this.shippingAddressCity = shippingAddressCity;
    }
 
    public String getShippingAddressProvince() {
        return shippingAddressProvince;
    }
 
    public void setShippingAddressProvince(String shippingAddressProvince) {
        this.shippingAddressProvince = shippingAddressProvince;
    }
    
    public String getShippingAddressCountry() {
        return shippingAddressCountry;
    }
 
    public void setShippingAddressCountry(String shippingAddressCountry) {
        this.shippingAddressCountry = shippingAddressCountry;
    }
    public String getShippingAddressPostalCode() {
        return shippingAddressPostalCode;
    }
 
    public void setShippingAddressPostalCode(String shippingAddressPostalCode) {
        this.shippingAddressPostalCode = shippingAddressPostalCode;
    }
    public String getShippingAddressPhone() {
        return shippingAddressPhone;
    }
 
    public void setShippingAddressPhone(String shippingAddressPhone) {
        this.shippingAddressPhone = shippingAddressPhone;
    }
    
    public String getBillingAddressStreet() {
        return billingAddressStreet;
    }
 
    public void setBillingAddressStreet(String billingAddressStreet) {
        this.billingAddressStreet = billingAddressStreet;
    }
 
    public String getBillingAddressCity() {
        return billingAddressCity;
    }
 
    public void setBillingAddressCity(String billingAddressCity) {
        this.billingAddressCity = billingAddressCity;
    }
 
    public String getBillingAddressProvince() {
        return billingAddressProvince;
    }
 
    public void setBillingAddressProvince(String billingAddressProvince) {
        this.billingAddressProvince = billingAddressProvince;
    }
    
    public String getBillingAddressCountry() {
        return billingAddressCountry;
    }
 
    public void setBillingAddressCountry(String billingAddressCountry) {
        this.billingAddressCountry = billingAddressCountry;
    }
    public String getBillingAddressPostalCode() {
        return billingAddressPostalCode;
    }
 
    public void setBillingAddressPostalCode(String billingAddressPostalCode) {
        this.billingAddressPostalCode = billingAddressPostalCode;
    }
    public String getBillingAddressPhone() {
        return billingAddressPhone;
    }
 
    public void setBillingAddressPhone(String billingAddressPhone) {
        this.billingAddressPhone = billingAddressPhone;
    }
 
   
}