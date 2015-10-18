package com.K9.testCases;

import com.K9.WebServices.ProductCatalogService.*;


import com.K9.hibernate.dao.CDDAO;
import com.K9.hibernate.bean.Account;


/**
 * This is a unit test that tests the CategoryDAO class.
 */
import org.junit.Test;

import com.K9.WebServices.OrderProcessService.*;

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



import com.google.gson.Gson;

public class TestOrderProcessService_getAccount {
	{
	
	String accountName;
	String accountInfo = "";
	String password1;
	String json = "";
	
	OrderProcessService service = new OrderProcessService();
	
	/*Account accnt = new Account();
	accnt.setAccountId(1);
	accnt.setAccountName("mbp");
	accnt.setbillingAddressId(1);
	accnt.setEmail("mbp@gmail.com");
	accnt.setShippingAddressId(1);
	accnt.setPassword1("password");*/
	
	
	 
	 try {
		 
		JSONObject jsonObj = new JSONObject("{\"accountName\":\"mbp7\"}");
		accountName = jsonObj.toString();
		
		JSONObject jsonObj2 = new JSONObject("{\"password\":\"password\"}");
		password1 = jsonObj2.toString();
		
		//Gson gson = new Gson();
		//json = gson.toJson(accnt);
		
			
		String result = service.getAccount(accountName, password1);
		
		MessageUtil messageUtil = new MessageUtil(result);
	    String msg = messageUtil.printMessage("TestCategoryDAO:");
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		//throw e;
	}
	 
	 
	 
    
	
	
	}
	  
	   
        @Test
  	   public void testResult() {	  

        	//JSONAssert.assertEquals("ff", categoryString, success);
        	//assertEquals("{"categoryId":1,"categoryName":"COUNTRY"},{"categoryId":2,"categoryName":"ROCK"},{"categoryId":3,"categoryName":"POP"}",categoryString);
  	   }
      
	  
}

	  	   


