package com.K9.hibernate.dao;
 
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import com.K9.hibernate.bean.Address;


/**
 * This Data Access Object class is used to access the category table in the database.  The hibernate framework is used to manage the interaction with the database.

 * 
 * @author MBP
 */

 
public class AddressDAO {
 
    public int addAddressDetails(String street, String city, String province, String country, String postalCode, String phone) {
        try {
            // 1. configuring hibernate
        	Configuration  configuration = new Configuration ().configure();
        	
            // 2. create sessionfactory
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
 
            // 3. Get Session object
            Session session = sessionFactory.openSession();
 
            // 4. Starting Transaction
            Transaction transaction = session.beginTransaction();
            
            
            Address addressInfo = new Address();
           
            addressInfo.setStreet(street);
            addressInfo.setCity(city);
            addressInfo.setProvince(province);
            addressInfo.setCountry(country);
            addressInfo.setPostalCode(postalCode);
            addressInfo.setPhone(phone);
            
            session.save(addressInfo);
            
            
            transaction.commit();
             
            
            return addressInfo.getAddressId();
 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
            throw e;
        }
 
    }
    
}