package com.K9.testCases;

/**
 * This is a unit test that tests the CategoryDAO class.
 */
import org.junit.Test;

import com.K9.hibernate.dao.OrderItemDAO;

public class _11TestOrderItemDAO {
	
	  
			    
	OrderItemDAO orderItemDAO=new OrderItemDAO();

		
		private int orderId=1;

		private int cdid=1;

		private int quantity=5;

		
        
        String orderItemId = orderItemDAO.addOrderItem(orderId, cdid, quantity);
        
       
      
   
        @Test
  	   public void testResult() {	  

         }
      
        

}
	  	   

	  


