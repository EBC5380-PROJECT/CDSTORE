package com.K9.testCases;

/**
 * This is a unit test that tests the CdDAO class.
 */
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import org.skyscreamer.jsonassert.*;

import com.K9.WebServices.ProductCatalogService.ProductCatalogService;
import com.K9.util.*;
import org.json.*;
import com.google.gson.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;


public class TestCDDAO_getProductListWithCategory {
	   
	ProductCatalogService productCatalogServiceDAO=new ProductCatalogService();
        
		int categoryId = 2;
        String cdString = productCatalogServiceDAO.getProductListByCategory(categoryId);
        MessageUtil messageUtil = new MessageUtil(cdString);
        String msg = messageUtil.printMessage("TestCDDAO_getProductListWithCategory:");
       //  [{"cdId":1,"title":"Johnny Cash Greatest Hits","artistName":"Jonny Cash","description":"Greatest Hits, Vol. 1 is a compilation album by country singer Johnny Cash, released in 1967 on Columbia Records. It is notable in that it marks the first appearance of ","price":1599.0,"image":"image1"}]

         
        @Test
  	   public void testResult() {	  

        	//JSONAssert.assertEquals("ff", categoryString, success);
        	//assertEquals("ff", cdString);
  	   }
      
       

}
	  	   



