package com.K9.testCases;

/**
 * This is a unit test that tests the CategoryDAO class.
 */
import org.junit.Test;

import com.K9.hibernate.dao.AccountDAO;
import com.K9.util.MessageUtil;



public class _6TestAccountDAO_setAccountInfo {
	{
		 String accountName="mbp2";

		 String password1="password";

		 int billingAddressId=1;

		 int shippingAddressId=2;

		 String email="mbp@gmail.com";
		
		 String fName = "Michele";
		
		 String lName = "Belanger";
			try{    
		AccountDAO accountDAO=new AccountDAO();

		
		
		
		        
        String success = accountDAO.addAccountDetails(accountName, password1, fName, lName, billingAddressId, shippingAddressId, email);
        MessageUtil messageUtil = new MessageUtil();
	    messageUtil.printMessage("TestCDDAO_getProductListWithCategory Test Result: " + success);
	    
			} catch (Exception e) {
				
			}
			
	}
              
   
        @Test
  	   public void testResult() {	  

        	
  	   }
      
        

}
	  	   

	  


