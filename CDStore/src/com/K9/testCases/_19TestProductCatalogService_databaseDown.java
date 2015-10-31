package com.K9.testCases;

import com.K9.WebServices.ProductCatalogService.*;
import org.junit.Test;
import com.K9.util.MessageUtil;


/**
 * This is a unit test that tests the CategoryDAO class.
 */




public class _19TestProductCatalogService_databaseDown {
	{
	try{
	
		ProductCatalogService service = new ProductCatalogService();
	
		String j = service.getCategoryList();
		MessageUtil messageUtil = new MessageUtil();
   	    messageUtil.printMessage("TestCDDAO_getCategoryList Test Result: " + j);
        
	
        
        String j1 = service.getProductList();
        messageUtil.printMessage("getProductList:" + j1);
        
        String j2 = service.getProductInfo(2);
        messageUtil.printMessage("getProductInfo:" + j2);
        
        String j3 = service.getProductListByCategory(2);
        messageUtil.printMessage("getProductListByCategory:" +j3);
        
	} catch (Exception e) {
	
	}

}
        @Test
  	   public void testResult() {	
        	
        }
      
 
}

	  	   


