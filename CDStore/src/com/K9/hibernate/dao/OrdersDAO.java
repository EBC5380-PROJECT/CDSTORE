package com.K9.hibernate.dao;
 
import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.sql.Update;

import com.K9.hibernate.bean.Orders;
import com.google.gson.Gson;



public class OrdersDAO {
	
 
    public int addOrder(int accountId, String status, Double shippingCharge, Double taxes, Double totalCost) {
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
            
            
            Orders ordersInfo = new Orders();
        	
                     
            ordersInfo.setAccountId(accountId);
            ordersInfo.setStatus(status);
            ordersInfo.setShippingCharge(shippingCharge);
            ordersInfo.setTaxes(taxes);
            ordersInfo.setTotalCost(totalCost);
           
            
            session.save(ordersInfo);
            
            
            transaction.commit();
            System.out.println("\n\n Details Added \n");
            
              
            
            return ordersInfo.getOrderId();
 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
            throw e;
        }
 
    }
    
    public void updateOrderStatus(int orderId, String orderStatus) {
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
            
            
            //Orders ordersStatus = new Orders();
        	
            /**
             * The following method session.getNamedQuery calls a stored procedure which is defined in the CD bean.
             */   
            
            System.out.println(orderStatus);
            System.out.println(orderId);
            
            Query query = session.getNamedQuery("callUpdateStatusProcedure")
            		 .setParameter("orderId", orderId)
            		 .setParameter("orderStatus", orderStatus);
            
            int result = query.executeUpdate();
            System.out.println("result"+result);
            
                     
            /**
             * The transaction is finalised by calling the commit method.
             */  

            transaction.commit();
            
                        
              
 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
            throw e;
        }
 
    }

}