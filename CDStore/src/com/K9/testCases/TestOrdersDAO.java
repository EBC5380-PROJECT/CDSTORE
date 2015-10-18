package com.K9.testCases;

/**
 * This is a unit test that tests the CategoryDAO class.
 */
import org.junit.Test;

import com.K9.hibernate.dao.OrdersDAO;

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


public class TestOrdersDAO {
	
	  
			    
	OrdersDAO ordersDAO=new OrdersDAO();

		
		private int accountId=1;

		private String status="ordered";

		private Double shippingCharge=5.99;

		private Double taxes=8.50;

		private Double totalCost=62.25;
        
        int orderId = ordersDAO.addOrder(accountId, status, shippingCharge, taxes, totalCost);
        
         MessageUtil messageUtil = new MessageUtil("success");
        String msg = messageUtil.printMessage("TestCategoryDAO:");
        
      //  String categoryArray = new Gson.fromJson([{"categoryId":1,"categoryName":"COUNTRY"},{"categoryId":2,"categoryName":"ROCK"},{"categoryId":3,"categoryName":"POP"}]);
              
        
      
   
        @Test
  	   public void testResult() {	  

        	//JSONAssert.assertEquals("ff", categoryString, success);
        	//assertEquals("{"categoryId":1,"categoryName":"COUNTRY"},{"categoryId":2,"categoryName":"ROCK"},{"categoryId":3,"categoryName":"POP"}",categoryString);
  	   }
      
        

}
	  	   

	  


