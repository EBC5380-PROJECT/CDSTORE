/**
 * Address Bean - contains CD Store client address information
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
public class Address implements Serializable{
	static final long serialVersionUID = 41L;
	
	// Getters and Setters are defined below for the values in this bean
	
	// addressId is the primary key for this class and is auto-generated in the database
	
	@Id @GeneratedValue
	@Column(name = "addressId")
	private int addressId;	
	  
	private String street;
    private String city;
    private String province;
    private String country;
    private String postalCode;
    private String phone;
      

    public int getAddressId () {
        return addressId;
    }
     
    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }
    
       
    public String getStreet() {
        return street;
    }
 
    public void setStreet(String street) {
        this.street = street;
    }
 
    public String getCity() {
        return city;
    }
 
    public void setCity(String city) {
        this.city = city;
    }
 
    public String getProvince() {
        return province;
    }
 
    public void setProvince(String province) {
        this.province = province;
    }
    
    public String getCountry() {
        return country;
    }
 
    public void setCountry(String country) {
        this.country = country;
    }
    public String getPostalCode() {
        return postalCode;
    }
 
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public String getPhone() {
        return phone;
    }
 
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
}