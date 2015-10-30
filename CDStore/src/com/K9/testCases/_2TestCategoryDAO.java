package com.K9.testCases;

/**
 * This is a unit test that tests the CategoryDAO class.
 */
import org.junit.Test;

import com.K9.hibernate.dao.CategoryDAO;
import com.K9.util.*;

public class _2TestCategoryDAO {
	{
	  try {

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

	  


