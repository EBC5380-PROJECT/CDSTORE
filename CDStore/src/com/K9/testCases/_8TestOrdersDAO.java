package com.K9.testCases;

/**
 * This is a unit test that tests the CategoryDAO class.
 */
import org.junit.Test;

import com.K9.hibernate.dao.OrdersDAO;
import com.K9.util.MessageUtil;



public class _8TestOrdersDAO {
	
	{
		try {
			
			OrdersDAO ordersDAO=new OrdersDAO();

			
			 int accountId=1;

			 String status="ordered";

			 Double shippingCharge=5.99;

			 Double taxes=8.50;

			 Double totalCost=62.25;
	        
	        String orderId = ordersDAO.addOrder(accountId, status, shippingCharge, taxes, totalCost);
	        
	        MessageUtil messageUtil = new MessageUtil();
		    messageUtil.printMessage("TestCDDAO_getProductListWithCategory Test Result: " + orderId);

		} catch (Exception e) {
			
		}
	}
			    
	        
         
        @Test
  	   public void testResult() {	  

        }
      
        

}
	  	   

	  


