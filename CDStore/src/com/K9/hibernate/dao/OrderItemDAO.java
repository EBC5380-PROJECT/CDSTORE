package com.K9.hibernate.dao;
 
import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.K9.hibernate.bean.OrderItem;
import com.K9.util.HibernateUtil;
import com.K9.util.ResponseFactory;


/**
 * This Data Access Object class is used to access the OrderItem table in the database.  The hibernate framework is used to manage the interaction with the database.

 * 
 * @author MBP
 */


public class OrderItemDAO {
	
 /**
  * 
  * The addOrderItem method is used to add the items ordered within a given order.
  * 
  * @param orderId
  * @param cdid
  * @param quantity
  * @return
  */
    public String addOrderItem(int orderId, int cdid, int quantity) {
        try {
        	
        	//The following steps are specific to Hibernate and are used to establish connectivity and a session with the database
        	         	
        	//Configure Hibernate and get the sessionFactory and get a session object
        	
        	Session session = HibernateUtil.getSessionFactory().openSession();
        	
        	            
        	//Starting Transaction
        	 Transaction transaction=session.beginTransaction(); 
        	 
        	 
            //Create a new instance of OrderItem in order to save the order item details in the database.
            OrderItem orderItem = new OrderItem();
        	
                     
            orderItem.setOrderId(orderId);
            orderItem.setCdId(cdid);
            orderItem.setQuantity(quantity);
           
            //Saving orderItem in the database
            session.save(orderItem);
            
            //committing the transaction
            transaction.commit();
            session.close();
            
            //returning the unique identifier of the orderItem just inserted into the database.
            return Integer.toString(orderItem.getOrderitemId());
 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return ResponseFactory.create(1000);  //returning system level error alert
        }
 
    }
   
}