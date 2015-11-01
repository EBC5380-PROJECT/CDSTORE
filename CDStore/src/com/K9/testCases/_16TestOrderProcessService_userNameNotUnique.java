package com.K9.testCases;



/**
 * This is a unit test that tests the CategoryDAO class.
 */
import org.junit.Test;


import com.K9.WebServices.OrderProcessService.*;
import com.K9.session.bean.AccountInformation;
import com.K9.util.MessageUtil;

//import static org.junit.Assert.assertEquals;


//import org.skyscreamer.jsonassert.*;

import org.json.*;
import com.google.gson.*;

public class _16TestOrderProcessService_userNameNotUnique {
	{
	
	OrderProcessService service = new OrderProcessService();
	
	AccountInformation accntInfo = new AccountInformation();

	accntInfo.setAccountName("mbp");
	accntInfo.setEmail("mbp@gmail.com");
	accntInfo.setPassword1("password");
	accntInfo.setFName("Michele");
	accntInfo.setLName("Belanger");
	
		
	accntInfo.setShippingAddressStreet("2564 Maple drive");
	accntInfo.setShippingAddressCity("Ottawa");
	accntInfo.setShippingAddressProvince("ON");
	accntInfo.setShippingAddressCountry("Canada");
	accntInfo.setShippingAddressPostalCode("K4R6T5");
	accntInfo.setShippingAddressPhone("613 856-7458");
	
	
	
	accntInfo.setBillingAddressStreet("235 Oak av");
	accntInfo.setBillingAddressCity("Perth");
	accntInfo.setBillingAddressProvince("ON");
	accntInfo.setBillingAddressCountry("Canada");
	accntInfo.setBillingAddressPostalCode("K0A 8F9");
	accntInfo.setBillingAddressPhone("613 235-4875");
	
	
	try {
		 
		
		Gson gson = new Gson();
		String accountInfo = gson.toJson(accntInfo);
		
		//creating input json string
		JSONObject jsonObj = new JSONObject("{\"accountName\":\"mbp\"}");
		String accountName = jsonObj.toString();
		
		
		//mbp3	    
		String result = service.creatAccount(accountName, accountInfo);
		
		
			System.out.println(result);
			MessageUtil messageUtil = new MessageUtil();
    	    messageUtil.printMessage("_16TestOrderProcessService_userNameNotUnique Test Result: " + result);
		
	
	
		
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

	  	   


