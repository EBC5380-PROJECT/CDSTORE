package com.K9.testCases;



/**
 * This is a unit test that tests the CategoryDAO class.
 */
import org.junit.Test;

import com.K9.WebServices.OrderProcessService.*;
import com.K9.session.bean.ShippingInfo;
import com.K9.util.MessageUtil;

//import static org.junit.Assert.assertEquals;


//import org.skyscreamer.jsonassert.*;

import com.google.gson.*;

public class _12TestOrderProcessService_createOrder {
	{
	
	OrderProcessService service = new OrderProcessService();
	
	ShippingInfo shippingInfo = new ShippingInfo();

	shippingInfo.setAccountId(1);
	shippingInfo.setShippingCharge(7.25);
	shippingInfo.setTaxes(5.25);
	shippingInfo.setTotalCost(60.32);
	
		
	try {
		 
		
		Gson gson = new Gson();
		String shippingInfo1 = gson.toJson(shippingInfo);
		
		
		String jsonData="[{\"accountName\":\"mbp\",\"cdid\":\"1\",\"quantity\":\"3\"},{\"accountName\":\"mbp\",\"cdid\":\"2\",\"quantity\":\"2\"}]";
		
		
		String result = service.createOrder(jsonData, shippingInfo1);
	
		MessageUtil messageUtil = new MessageUtil();
	    messageUtil.printMessage("_12TestOrderProcessService_createOrder: " + result);	
		
			
	} catch (Exception e) {
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

	  	   


