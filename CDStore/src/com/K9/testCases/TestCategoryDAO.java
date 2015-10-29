package com.K9.testCases;

/**
 * This is a unit test that tests the CategoryDAO class.
 */
import org.junit.Test;

import org.skyscreamer.jsonassert.*;

import com.K9.hibernate.dao.CategoryDAO;
import com.K9.util.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.json.*;
import com.google.gson.*;
import java.io.File;




public class TestCategoryDAO {
	{
	  try {
			    //CustomerInfoDAO customerInfoDAO = new CustomerInfoDAO();
		CategoryDAO categoryDAO=new CategoryDAO();
        
        String categoryString = categoryDAO.getCategoryList();
        MessageUtil messageUtil = new MessageUtil();
        messageUtil.printMessage("TestCategoryDAO Test Result: " + categoryString);
        
	  } catch (Exception e) {
		  
	  }
        
	}  
              
          
        @Test
  	   public void testResult() {	 
        	
        	        }	  


}

	  


