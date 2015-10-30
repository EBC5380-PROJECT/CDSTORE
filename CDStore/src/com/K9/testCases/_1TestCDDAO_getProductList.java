package com.K9.testCases;

/**
 * This is a unit test that tests the CdDAO class.
 */
import org.junit.Test;
import com.K9.hibernate.dao.CDDAO;
import com.K9.util.*;


public class _1TestCDDAO_getProductList {
	   
	{
      	  try {
      		CDDAO cdDAO=new CDDAO();
            
            String cdString = cdDAO.getProductList();
            MessageUtil messageUtil = new MessageUtil();
            messageUtil.printMessage("TestCDDAO_getProductList Test Result: " + cdString);
              
      	  } catch (Exception e) {
      		  
      	  }
              
      	}  
         
        @Test
  	   public void testResult() {	  

  	   }
      
        

}
	  	   



