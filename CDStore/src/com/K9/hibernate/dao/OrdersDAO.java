package com.K9.hibernate.dao;
 
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.K9.hibernate.bean.Orders;
import com.K9.util.HibernateUtil;
import com.K9.util.ResponseFactory;


/**
 * This Data Access Object class is used to access the Orders table in the database.  The hibernate framework is used to manage the interaction with the database.

 * 
 * @author MBP
 */



public class OrdersDAO {
	
 /**
  * 
  * The addOrder method is called when an order is to be saved in the database.
  * 
  * @param accountId
  * @param status
  * @param shippingCharge
  * @param taxes
  * @param totalCost
  * @return
  */
    public String addOrder(int accountId, String status, Double shippingCharge, Double taxes, Double totalCost) {
        try {
        	
        	 //The following steps are specific to Hibernate and are used to establish connectivity and a session with the database
        	 
        	//Configure Hibernate and get the sessionFactory and get a session object
        	
        	Session session = HibernateUtil.getSessionFactory().openSession();
        	
        	            
        	//Starting Transaction
        	 Transaction transaction=session.beginTransaction();           
            
        	 
        	//An instance of the Orders bean is created in order to store the data in the database.
            Orders ordersInfo = new Orders();
        	
                     
            ordersInfo.setAccountId(accountId);
            ordersInfo.setStatus(status);
            ordersInfo.setShippingCharge(shippingCharge);
            ordersInfo.setTaxes(taxes);
            ordersInfo.setTotalCost(totalCost);
           
            //saving the ordersInfo to the database
            session.save(ordersInfo);
            
            //committing the transaction
            transaction.commit();
            session.close();
            String orderId = Integer.toString(ordersInfo.getOrderId());
            
            //the unique Id of the row just created in the Order table is returned to the calling class
            return orderId;
 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return ResponseFactory.create(1000);  //returning system level error alert
        }
 
    }
    
    
    /**
     * 
     * The updateOrderStatus method is used to update the order status in the order table after the credit card information is validated.
     * 
     * 
     * @param orderId
     * @param orderStatus
     * @param accountId
     */
    public String updateOrderStatus(int orderId, String orderStatus, int accountId) {
        try {
        	
        	//The following steps are specific to Hibernate and are used to establish connectivity and a session with the database
        	         	
        	//Configure Hibernate and get the sessionFactory and get a session object
        	
        	Session session = HibernateUtil.getSessionFactory().openSession();
        	
        	            
        	//Starting Transaction
        	 Transaction transaction=session.beginTransaction();           
            
            //Orders ordersStatus = new Orders();
        	
            
            //The following method session.getNamedQuery calls a stored procedure which is defined in the Orders bean.
             
                        
            Query query = session.getNamedQuery("callUpdateStatusProcedure")
            		 .setParameter("orderId", orderId)
            		 .setParameter("orderStatus", orderStatus)
            		 .setParameter("accountId", accountId);
            
            //the update query is executed
            query.executeUpdate();
                      
                     
           
             //The transaction is finalised by calling the commit method.
             transaction.commit();
             session.close();
             
             return "";
            
             
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return ResponseFactory.create(1000);  //returning system level error alert
        }
 
    }

}