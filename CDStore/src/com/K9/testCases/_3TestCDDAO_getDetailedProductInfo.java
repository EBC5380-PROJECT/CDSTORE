package com.K9.testCases;

/**
 * This is a unit test that tests the CdDAO class.
 */
import org.junit.Test;

import com.K9.hibernate.dao.CDDAO;

import com.K9.util.*;



public class _3TestCDDAO_getDetailedProductInfo {
	   
		{
      	  try {
      		CDDAO cdDAO=new CDDAO();
            
    		int productId = 2;
            String cdString = cdDAO.getProductInfo(productId);
      	    MessageUtil messageUtil = new MessageUtil();
      	    messageUtil.printMessage("TestCDDAO_getDetailedProductInfo Test Result: " + cdString);
              
      	  } catch (Exception e) {
      		  
      	  }
              
      	}    
         
        @Test
  	   public void testResult() {	  

        	
  	   }
      
        

}
	  	   



