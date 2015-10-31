package com.K9.testCases;



/**
 * This is a unit test that tests the CategoryDAO class.
 */
import org.junit.Test;

import com.K9.WebServices.OrderProcessService.*;
import com.K9.session.bean.PaymentInfo;
import com.K9.session.bean.ShippingInfo;
import com.K9.util.MessageUtil;
import com.K9.hibernate.bean.Orders;

//import static org.junit.Assert.assertEquals;


//import org.skyscreamer.jsonassert.*;

import com.google.gson.*;

public class _13TestOrderProcessService_confirmOrder {
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
	
	
		
	try {
		 
		
		Gson gson = new Gson();
		
		String paymentInfo1 = gson.toJson(paymentInfo);
		
		String order1 = gson.toJson(order);
		
		String shippingInfo1 = gson.toJson(shippingInfo);
	
		
		String result=service.confirmOrder(order1, shippingInfo1, paymentInfo1);
		
		MessageUtil messageUtil = new MessageUtil();
	    messageUtil.printMessage("_13TestOrderProcessService_confirmOrder: " + result);	
		
	
		
			
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

	  	   


