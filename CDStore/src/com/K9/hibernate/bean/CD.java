/**
 * CD Bean - contains CD Store CD information
 * @author MBP
 *
 */
package com.K9.hibernate.bean;

import javax.persistence.*;
import java.io.Serializable;


/**
 * @NamedNativeQueries references stored procedures used to retrieve data from the database.
 * 
 *  callCDProcedure reference a stored procedure that is called to get a list of all CDs contained in the CD database table. 
 * Hibernate annotation is used to call the stored procedure sp_getCDList().
 * 
 * callProductListByCategoryProcedure reference a stored procedure that is called to get a list of all CDs by category contained in the CD database table. 
 * Hibernate annotation is used to call the stored procedure sp_getProductListByCategory(:categoryId).
 * 
 * callDetailedProductInfoProcedure reference a stored procedure that is called to get detailed product information for a specific CD contained in the CD database table. 
 * Hibernate annotation is used to call the stored procedure sp_getDetailedProductInfo(:productId).
 * 
 *
 */


@NamedNativeQueries({	
	@NamedNativeQuery(
		name = "callCDProcedure",
		query = "CALL sp_getCDList()",
		resultClass = CD.class
		),
	
	@NamedNativeQuery(
			name = "callProductListByCategoryProcedure",
			query = "CALL sp_getProductListByCategory(:categoryId)",
			resultClass = CD.class
			),
	
	@NamedNativeQuery(
			name = "callDetailedProductInfoProcedure",
			query = "CALL sp_getDetailedProductInfo(:productId)",
			resultClass = CD.class
			)
})


@Entity
public class CD implements Serializable{
	static final long serialVersionUID = 1L;
	
	// Getters and Setters are defined below for the values in this bean
	
		// cdId is the primary key for this class and is auto-generated in the database
		// Hibernate annotations used to define the primary key for table CD.
	
	@Id @GeneratedValue
	@Column(name = "cdId")
    private int cdId;
    
	private String title;
    private String artistName;
    private String description;
    private int categoryId;
    private Double price;
    private String image;
    
    public CD() {}
 
    public int getCdId() {
        return cdId;
    }
 
    public void setCdId(int cdId) {
        this.cdId = cdId;
    }
 
    
    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }
 
    public String getArtistName() {
        return artistName;
    }
 
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
 
    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getCategoryId() {
        return categoryId;
    }
 
    public void setCategory(int categoryId) {
        this.categoryId = categoryId;
    }
 
    public Double getPrice() {
        return price;
    }
 
    public void setPrice(Double price) {
        this.price = price;
    }
 
    public String getImage() {
        return image;
    }
 
    public void setImage(String image) {
        this.image = image;
    }
    
    
}