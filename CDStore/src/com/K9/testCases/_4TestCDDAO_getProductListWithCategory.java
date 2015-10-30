package com.K9.testCases;

/**
 * This is a unit test that tests the CdDAO class.
 */
import org.junit.Test;

import com.K9.WebServices.ProductCatalogService.ProductCatalogService;
import com.K9.util.*;


public class _4TestCDDAO_getProductListWithCategory {
	   
	    {
        	  try {
        		ProductCatalogService productCatalogServiceDAO=new ProductCatalogService();
        	        
        		int categoryId = 2;
        	    String cdString = productCatalogServiceDAO.getProductListByCategory(categoryId);
        	    MessageUtil messageUtil = new MessageUtil();
        	    messageUtil.printMessage("TestCDDAO_getProductListWithCategory Test Result: " + cdString);
                
        	  } catch (Exception e) {
        		  
        	  }
                
        	}    
        @Test
  	   public void testResult() {	  

  	   }
      
       

}
	  	   



