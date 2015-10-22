package com.K9.hibernate.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Column;
import java.io.Serializable;

/**
 * This bean class represents the different CD categories for the online store.
 * 
 * @author MBP
 *  
 *
 */

/**
 * @NamedNativeQuery references a stored procedure that is called to get all CD categories contained in the category database table. 
 * Hibernate annotation is used to call the stored procedure sp_getCategoryList().
 *
 */
@NamedNativeQuery(
		name = "callCategoryProcedure",
		query = "CALL sp_getCategoryList()",
		resultClass = Category.class
		)

@Entity
public class Category implements Serializable{
	
	static final long serialVersionUID = 1L;
	
	// Getters and Setters are defined below for the values in this bean
	
	// categoryId is the primary key for this class and is auto-generated in the database
	// Hibernate annotations used to define the primary key for table Category.
	
	@Id @GeneratedValue
	@Column(name = "categoryId")
    private int categoryId;
	
	private String categoryName;
     
    public int getCategoryId() {
        return categoryId;
    }
 
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
 
    public String getCategoryName() {
        return categoryName;
    }
 
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
 
  
 
}

 

