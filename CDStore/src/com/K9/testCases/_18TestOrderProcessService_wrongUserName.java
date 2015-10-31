package com.K9.testCases;


/**
 * This is a unit test that tests the CategoryDAO class.
 */
import org.junit.Test;

import com.K9.WebServices.OrderProcessService.*;
import com.K9.util.MessageUtil;

import org.json.*;

public class _18TestOrderProcessService_wrongUserName {
	{
	
	String accountName;
	String password1;
	
	OrderProcessService service = new OrderProcessService();
	
		 
	 try {
		 
		JSONObject jsonObj = new JSONObject("{\"accountName\":\"mbp1\"}");
		accountName = jsonObj.toString();
		
		JSONObject jsonObj2 = new JSONObject("{\"password\":\"password\"}");
		password1 = jsonObj2.toString();
		
			
		String result = service.getAccount(accountName, password1);
		
		MessageUtil messageUtil = new MessageUtil();
	    messageUtil.printMessage("_18TestOrderProcessService_wrongUserName: " + result);
	    
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

	  	   


