/**
 * This bean class represents Order status of an order.
 * 
 * @author MBP
 *  
 *
 */
package com.K9.hibernate.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import java.io.Serializable;


@Entity
public class OrderStatus implements Serializable{
	
	static final long serialVersionUID = 1L;
	
	// Getters and Setters are defined below for the values in this bean
	
		// statusId is the primary key for this class and is auto-generated in the database
		// Hibernate annotations used to define the primary key for table OrderStatus.
	
	@Id @GeneratedValue
	@Column(name = "statusId")
    private int statusId;
	
	private String statusName;
     
    public int getStatusId() {
        return statusId;
    }
 
    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
 
    public String getStatusName() {
        return statusName;
    }
 
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
 
  
 
}

 

