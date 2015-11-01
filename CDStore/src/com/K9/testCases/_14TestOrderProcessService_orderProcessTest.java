package com.K9.testCases;



import org.json.JSONObject;
/**
 * This is a unit test that tests the CategoryDAO class.
 */
import org.junit.Test;

import com.K9.WebServices.OrderProcessService.*;
import com.K9.hibernate.bean.Orders;
import com.K9.session.bean.AccountInformation;
import com.K9.session.bean.PaymentInfo;
import com.K9.session.bean.ShippingInfo;
import com.K9.util.MessageUtil;

//import static org.junit.Assert.assertEquals;


//import org.skyscreamer.jsonassert.*;

import com.google.gson.*;

public class _14TestOrderProcessService_orderProcessTest {
	{
	
	OrderProcessService service = new OrderProcessService();
	
	ShippingInfo shippingInfo = new ShippingInfo();

	shippingInfo.setAccountName("mbp");
	shippingInfo.setShippingCharge(7.25);
	shippingInfo.setTaxes(5.25);
	shippingInfo.setTotalCost(60.32);
	
	
	Orders order = new Orders();
	order.setOrderId(1);
	order.setAccountId(1);
	order.setShippingCharge(7.25);
	order.setStatus("ORDERED");
	order.setTaxes(5.25);
	order.setTotalCost(60.32);
	
	PaymentInfo paymentInfo = new PaymentInfo();
	paymentInfo.setCreditCardHolderName("MBP");
	paymentInfo.setCreditCardNumber("4538452625981254");
	paymentInfo.setCcv(235);
	paymentInfo.setExpiryDate("12/2018");
	
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
		 
		//Reset database manually
		
		//TestOrderProcessService_createAccount createAccount = new TestOrderProcessService_createAccount();
		
		
		Gson gson = new Gson();
		MessageUtil messageUtil = new MessageUtil();
		String accountInfo = gson.toJson(accntInfo);
		
		//creating input json string
		JSONObject jsonObj = new JSONObject("{\"accountName\":\"mbp\"}");
		String accountName = jsonObj.toString();
		
		
		//mbp	    
		String result = service.creatAccount(accountName, accountInfo);
		messageUtil.printMessage("_14TestOrderProcessService_orderProcessTest_createAccount Test Result: " + result);
		
				
		String shippingInfo1 = gson.toJson(shippingInfo);
		
		
		String jsonData="[{\"accountName\":\"mbp\",\"cdid\":\"1\",\"quantity\":\"3\"},{\"accountName\":\"mbp\",\"cdid\":\"2\",\"quantity\":\"2\"}]";
		
		String createOrder = service.createOrder(jsonData, shippingInfo1);
		messageUtil.printMessage("_14TestOrderProcessService_orderProcessTest_createOrder Test Result: " + createOrder);
		
		
			
		String paymentInfo1 = gson.toJson(paymentInfo);
		
		String order1 = gson.toJson(order);
		
				
			       			
		String confOrder = service.confirmOrder(order1, shippingInfo1, paymentInfo1);
			
		
        messageUtil.printMessage("_14TestOrderProcessService_orderProcessTest_confirmOrder Test Result: " + confOrder);

		
	
		
			
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

	  	   


