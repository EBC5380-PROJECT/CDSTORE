package com.K9.hibernate.dao;
 
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.K9.hibernate.bean.Address;
import com.K9.util.HibernateUtil;



/**
 * This Data Access Object class is used to access the Address table in the database.  The hibernate framework is used to manage the interaction with the database.

 * 
 * @author MBP
 */

 
public class AddressDAO {
 
	/**
	 * The addAddressDetails method is used to store the shipping or billing address.
	 * 
	 * @param street
	 * @param city
	 * @param province
	 * @param country
	 * @param postalCode
	 * @param phone
	 * @return
	 */
    public int addAddressDetails(String street, String city, String province, String country, String postalCode, String phone) {
        try {
        	/**
        	 * The following steps are specific to Hibernate and are used to establish connectivity and a session with the database
        	 * 
        	 */
        	
        	//Configure Hibernate and get the sessionFactory and get a session object
        	
        	Session session = HibernateUtil.getSessionFactory().openSession();
        	
        	            
        	//Starting Transaction
        	 Transaction transaction=session.beginTransaction();           
            
        	 //A new instance of Address is created in order to set the values passed from the calling class.
            Address addressInfo = new Address();
           
            addressInfo.setStreet(street);
            addressInfo.setCity(city);
            addressInfo.setProvince(province);
            addressInfo.setCountry(country);
            addressInfo.setPostalCode(postalCode);
            addressInfo.setPhone(phone);
            
            //Saving the addressInfo to the database
            session.save(addressInfo);
            
            //committing the transaction
            transaction.commit();
             
            //the Id of the newly created row in the Address table is returned to the calling class
            return addressInfo.getAddressId();
 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw e;
        }
 
    }
    
}