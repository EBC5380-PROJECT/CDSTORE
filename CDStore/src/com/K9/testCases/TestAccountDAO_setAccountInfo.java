package com.K9.testCases;

/**
 * This is a unit test that tests the CategoryDAO class.
 */
import org.junit.Test;

import com.K9.hibernate.dao.AccountDAO;

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


public class TestAccountDAO_setAccountInfo {
	
	  
			    
		AccountDAO accountDAO=new AccountDAO();

		
		private String accountName="mbp";

		private String password1="password";

		private int billingAddressId=1;

		private int shippingAddressId=2;

		private String email="mbp@gmail.com";
        
        boolean success = accountDAO.addAccountDetails(accountName, password1, billingAddressId, shippingAddressId, email);
        
         MessageUtil messageUtil = new MessageUtil("success");
        String msg = messageUtil.printMessage("TestCategoryDAO:");
        
      //  String categoryArray = new Gson.fromJson([{"categoryId":1,"categoryName":"COUNTRY"},{"categoryId":2,"categoryName":"ROCK"},{"categoryId":3,"categoryName":"POP"}]);
              
        
      
   
        @Test
  	   public void testResult() {	  

        	//JSONAssert.assertEquals("ff", categoryString, success);
        	//assertEquals("{"categoryId":1,"categoryName":"COUNTRY"},{"categoryId":2,"categoryName":"ROCK"},{"categoryId":3,"categoryName":"POP"}",categoryString);
  	   }
      
        

}
	  	   

	  


