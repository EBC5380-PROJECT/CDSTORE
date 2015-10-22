package com.K9.testCases;



/**
 * This is a unit test that tests the CategoryDAO class.
 */
import org.junit.Test;

import com.K9.WebServices.OrderProcessService.*;
import com.K9.session.bean.PaymentInfo;
import com.K9.session.bean.ShippingInfo;
import com.K9.hibernate.bean.Orders;

//import static org.junit.Assert.assertEquals;


//import org.skyscreamer.jsonassert.*;

import com.google.gson.*;

public class TestOrderProcessService_confirmOrder {
	{
	
	OrderProcessService service = new OrderProcessService();
	
	ShippingInfo shippingInfo = new ShippingInfo();

	shippingInfo.setAccountId(3);
	shippingInfo.setShippingCharge(5.25);
	shippingInfo.setTaxes(4.25);
	shippingInfo.setTotalCost(20.36);
	
	Orders order = new Orders();
	order.setOrderId(6);
	order.setAccountId(3);
	order.setShippingCharge(5.2);
	order.setStatus("ORDERED");
	order.setTaxes(6.2);
	order.setTotalCost(20.36);
	
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
		
		
		
		Orders orders2 = gson.fromJson(order1, Orders.class);	
		 
         System.out.println("accountID - beginning"+orders2.getAccountId());
		
		//System.out.println("beginning - orderID"+ );
		
		service.confirmOrder(order1, shippingInfo1, paymentInfo1);
		
	
		
			
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

	  	   


