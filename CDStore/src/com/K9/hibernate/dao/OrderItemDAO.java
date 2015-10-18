package com.K9.hibernate.dao;
 
import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import com.K9.hibernate.bean.OrderItem;


public class OrderItemDAO {
	
 
    public int addOrderItem(int orderId, int cdid, int quantity) {
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
            
            
            OrderItem orderItem = new OrderItem();
        	
                     
            orderItem.setOrderId(orderId);
            orderItem.setCdId(cdid);
            orderItem.setQuantity(quantity);
           
            
            session.save(orderItem);
            
            
            transaction.commit();
            System.out.println("\n\n Details Added \n");
            
              
            
            return orderItem.getOrderitemId();
 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
            throw e;
        }
 
    }
   
}