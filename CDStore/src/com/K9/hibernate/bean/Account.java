package com.K9.hibernate.bean;

import javax.persistence.*;

/**
 * Account Bean - contains CD Store client account information
 * @author MBP
 *
 */

/**
 * @NamedNativeQuery references a stored procedure that is called to get account info for a specific user in the account database table. 
 * Hibernate annotation is used to call the stored procedure sp_getAccountInfo(:userName).
 *
 */
@NamedNativeQueries({	
	@NamedNativeQuery(name = "callValidateCredentials",
			query = "CALL sp_validateCredentials(:userName,:password1)",
			resultClass = Account.class
			),
	
@NamedNativeQuery(
		name = "callRetrieveAccountInfo",
		query = "CALL sp_getAccountInfo(:userName)",
		resultClass = Account.class
		)
	})

@Entity
public class Account{
		
	// Getters and Setters are defined below for the values in this bean
	
	// accountId is the primary key for this class and is auto-generated in the database
	
	@Id @GeneratedValue
	@Column(name = "accountId")
    private int accountId;
	
	private String accountName;
	private String password1;
	private String fName;
	private String lName;
	private int billingAddressId;
	private int shippingAddressId;
	private String email;
	
	 
    
	public int getAccountId() {
        return accountId;
    }
 
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
     
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
      
}