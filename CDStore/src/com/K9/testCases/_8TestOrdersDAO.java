package com.K9.testCases;

/**
 * This is a unit test that tests the CategoryDAO class.
 */
import org.junit.Test;

import com.K9.hibernate.dao.OrdersDAO;



public class _8TestOrdersDAO {
	
	  
			    
	OrdersDAO ordersDAO=new OrdersDAO();

		
		private int accountId=1;

		private String status="ordered";

		private Double shippingCharge=5.99;

		private Double taxes=8.50;

		private Double totalCost=62.25;
        
        int orderId = ordersDAO.addOrder(accountId, status, shippingCharge, taxes, totalCost);
        
         
        @Test
  	   public void testResult() {	  

        }
      
        

}
	  	   

	  


