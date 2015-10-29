package com.K9.testCases;

import com.K9.WebServices.ProductCatalogService.*;
import com.K9.hibernate.dao.CDDAO;

/**
 * This is a unit test that tests the CategoryDAO class.
 */
import org.junit.Test;

import com.K9.WebServices.ProductCatalogService.*;

import static org.junit.Assert.assertEquals;
import org.skyscreamer.jsonassert.*;
import com.K9.util.*;

import org.hibernate.HibernateException;
import org.json.*;
import com.google.gson.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import com.K9.util.*;


import com.google.gson.Gson;

public class TestProductCatalogService {
	
	
	ProductCatalogService service = new ProductCatalogService();
	/*	 
		String j = service.getCategoryList();
		MessageUtil messageUtil = new MessageUtil(j);
        String msg = messageUtil.printMessage("getCategoryList:");
	
        
        String j1 = service.getProductList();
		MessageUtil messageUtil1 = new MessageUtil(j1);
        String msg1 = messageUtil1.printMessage("getProductList:");
        
        String j2 = service.getProductInfo(1);
		MessageUtil messageUtil2 = new MessageUtil(j2);
        String msg2 = messageUtil2.printMessage("getProductInfo:");
        
        String j3 = service.getProductListByCategory(1);
		MessageUtil messageUtil3 = new MessageUtil(j3);
        String msg3 = messageUtil3.printMessage("getProductListByCategory:");
        
        
        */
	  
	   
        @Test
  	   public void testResult() {	  

        	//JSONAssert.assertEquals("ff", categoryString, success);
        	//assertEquals("{"categoryId":1,"categoryName":"COUNTRY"},{"categoryId":2,"categoryName":"ROCK"},{"categoryId":3,"categoryName":"POP"}",categoryString);
  	   }
      
	  
}

	  	   


