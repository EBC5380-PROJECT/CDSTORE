package com.K9.testCases;

/**
 * This is a unit test that tests the CategoryDAO class.
 */
import org.junit.Test;

import org.skyscreamer.jsonassert.*;

import com.K9.hibernate.dao.CategoryDAO;
import com.K9.util.*;
import org.json.*;
import org.json.*;
import com.google.gson.*;



public class TestCategoryDAO {
	
	  
			    //CustomerInfoDAO customerInfoDAO = new CustomerInfoDAO();
		CategoryDAO categoryDAO=new CategoryDAO();
        
        String categoryString = categoryDAO.getCategoryList();
        MessageUtil messageUtil = new MessageUtil(categoryString);
        String msg = messageUtil.printMessage("TestCategoryDAO:");
        
      //  String categoryArray = new Gson.fromJson([{"categoryId":1,"categoryName":"COUNTRY"},{"categoryId":2,"categoryName":"ROCK"},{"categoryId":3,"categoryName":"POP"}]);
              
        
      
   
        @Test
  	   public void testResult() throws JSONException {	 
        	
        	//creating expected result json string
    		//JSONObject jsonObj = new JSONObject("{\"accountName\":\"mbp3\"}");
    		//String accountName = jsonObj.toString();
       /* 	try {
        	boolean pass = false;
        
    		JSONArray jsonObj = new JSONArray("[{\"categoryId\":\''1\'',\"categoryName\":\"COUNTRY\"},{\"categoryId\":\'2\',\"categoryName\":\"ROCK\"},{\"categoryId\":\'3\',\"categoryName\":\"POP\"}]");
    		String expectedResult = jsonObj.toString();
        	JSONAssert.assertEquals(expectedResult, categoryString, pass);
        	//assertEquals("{"categoryId":1,"categoryName":"COUNTRY"},{"categoryId":2,"categoryName":"ROCK"},{"categoryId":3,"categoryName":"POP"}",categoryString);
  	   } catch (Exception e){
  		   System.out.println("error:" + e);
  	   }
      */
        //String jsonData="[{\"accountName\":\"mbp2\",\"cdid\":\"1\",\"quantity\":\"3\"},{\"accountName\":\"mbp2\",\"cdid\":\"2\",\"quantity\":\"2\"}]";
        }	  

}
	  	   

	  


