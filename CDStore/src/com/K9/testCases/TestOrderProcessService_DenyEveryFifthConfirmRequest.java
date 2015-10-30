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


public class TestOrderProcessService_DenyEveryFifthConfirmRequest {
	{
	
	OrderProcessService service = new OrderProcessService();
	
	ShippingInfo shippingInfo = new ShippingInfo();

	shippingInfo.setAccountId(1);
	shippingInfo.setShippingCharge(5.25);
	shippingInfo.setTaxes(4.25);
	shippingInfo.setTotalCost(20.36);
	
	Orders order = new Orders();
	order.setOrderId(1);
	order.setAccountId(1);
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
		 //reset database then execute the following
		TestOrderProcessService_createAccount createAccount = new TestOrderProcessService_createAccount();
		
		Gson gson = new Gson();
		TestOrderProcessService_createOrder createOrder = new TestOrderProcessService_createOrder();
		order.setOrderId(1);
		
		
		
		String paymentInfo1 = gson.toJson(paymentInfo);
		
		String order1 = gson.toJson(order);
		
		String shippingInfo1 = gson.toJson(shippingInfo);
		
		
			
		service.confirmOrder(order1, shippingInfo1, paymentInfo1);
		
	
		
		TestOrderProcessService_createOrder createOrder1 = new TestOrderProcessService_createOrder();
		order.setOrderId(2);
		order1 = gson.toJson(order);
		
				
		service.confirmOrder(order1, shippingInfo1, paymentInfo1);
		
		TestOrderProcessService_createOrder createOrder2 = new TestOrderProcessService_createOrder();
		order.setOrderId(3);
		order1 = gson.toJson(order);
		
				
		service.confirmOrder(order1, shippingInfo1, paymentInfo1);
		
		TestOrderProcessService_createOrder createOrder3 = new TestOrderProcessService_createOrder();
		order.setOrderId(4);
		order1 = gson.toJson(order);
		
				
		service.confirmOrder(order1, shippingInfo1, paymentInfo1);
		
		
		TestOrderProcessService_createOrder createOrder4 = new TestOrderProcessService_createOrder();
		order.setOrderId(5);
		order1 = gson.toJson(order);
		
				
		service.confirmOrder(order1, shippingInfo1, paymentInfo1);
		
		TestOrderProcessService_createOrder createOrder5 = new TestOrderProcessService_createOrder();
		order.setOrderId(6);
		order1 = gson.toJson(order);
		
				
		service.confirmOrder(order1, shippingInfo1, paymentInfo1);
		
		TestOrderProcessService_createOrder createOrder6 = new TestOrderProcessService_createOrder();
		order.setOrderId(7);
		order1 = gson.toJson(order);
		
				
		service.confirmOrder(order1, shippingInfo1, paymentInfo1);
		
		TestOrderProcessService_createOrder createOrder8 = new TestOrderProcessService_createOrder();
		order.setOrderId(8);
		order1 = gson.toJson(order);
		
				
		service.confirmOrder(order1, shippingInfo1, paymentInfo1);
		
		
		TestOrderProcessService_createOrder createOrder9 = new TestOrderProcessService_createOrder();
		order.setOrderId(9);
		order1 = gson.toJson(order);
		
				
		service.confirmOrder(order1, shippingInfo1, paymentInfo1);
		
		
		TestOrderProcessService_createOrder createOrder10 = new TestOrderProcessService_createOrder();
		order.setOrderId(10);
		order1 = gson.toJson(order);
		
				
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

	  	   


