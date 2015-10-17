package com.K9.testCases;

/**
 * This is a unit test that tests the CdDAO class.
 */
import org.junit.Test;



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
	   
		CDDAO cdDAO=new CDDAO();
        
		int productId = 2;
        String cdString = cdDAO.getProductInfo(productId);
        MessageUtil messageUtil = new MessageUtil(cdString);
        String msg = messageUtil.printMessage("TestCDDAO_getDetailedProductInfo:");
       //  [{"cdId":2,"title":"16 Biggest Hits","artistName":"artist","description":"16 Biggest Hits is a compilation album by country singer Willie Nelson. It was released on July 14, 1998","price":1299.0,"image":"image2"}]

         
        @Test
  	   public void testResult() {	  

        	//JSONAssert.assertEquals("ff", categoryString, success);
        	//assertEquals("ff", cdString);
  	   }
      
        

}
	  	   



