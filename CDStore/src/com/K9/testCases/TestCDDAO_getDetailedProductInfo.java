package com.K9.testCases;

/**
 * This is a unit test that tests the CdDAO class.
 */
import org.junit.Test;

import com.K9.WebServices.ProductCatalogService.ProductCatalogService;
import com.K9.hibernate.dao.CDDAO;

import static org.junit.Assert.assertEquals;
import org.skyscreamer.jsonassert.*;
import com.K9.util.*;
import org.json.*;
import com.google.gson.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;


public class TestCDDAO_getDetailedProductInfo {
	   
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

        	//JSONAssert.assertEquals("ff", categoryString, success);
        	//assertEquals("ff", cdString);
  	   }
      
        

}
	  	   



