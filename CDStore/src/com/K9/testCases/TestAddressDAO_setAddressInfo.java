package com.K9.testCases;

/**
 * This is a unit test that tests the CategoryDAO class.
 */
import org.junit.Test;


import com.K9.hibernate.dao.AddressDAO;

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


public class TestAddressDAO_setAddressInfo {
	{
	  try{
			    
		AddressDAO addressDAO=new AddressDAO();

		
		String accountName="mbp";

		String password1="password";

		int billingAddressId=1;

		int shippingAddressId=2;

		String email="mbp@gmail.com";


		int accountId=1;

		String street="Castle Hill";


		String city="Ottawa";


		String province="ON";


		String country="Canada";


		 String postalCode="K4D5G6";


		String phone="613 552-6598";
		
		       
        addressDAO.addAddressDetails(street, city, province, country, postalCode, phone);
        
         MessageUtil messageUtil = new MessageUtil("success");
        String msg = messageUtil.printMessage("TestCategoryDAO:");
        
      //  String categoryArray = new Gson.fromJson([{"categoryId":1,"categoryName":"COUNTRY"},{"categoryId":2,"categoryName":"ROCK"},{"categoryId":3,"categoryName":"POP"}]);
	  } catch (Exception e) {
		  
		  throw e;
	  }
         
	}
   
        @Test
  	   public void testResult() {	  

        	//JSONAssert.assertEquals("ff", categoryString, success);
        	//assertEquals("{"categoryId":1,"categoryName":"COUNTRY"},{"categoryId":2,"categoryName":"ROCK"},{"categoryId":3,"categoryName":"POP"}",categoryString);
  	   }
      
        

}
	  	   

	  


