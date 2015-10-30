package com.K9.testCases;

/**
 * This is a unit test that tests the CategoryDAO class.
 */
import org.junit.Test;

import com.K9.hibernate.dao.AccountDAO;



public class _6TestAccountDAO_setAccountInfo {
	
	  
			    
		AccountDAO accountDAO=new AccountDAO();

		
		private String accountName="mbp";

		private String password1="password";

		private int billingAddressId=1;

		private int shippingAddressId=2;

		private String email="mbp@gmail.com";
		
		private String fName = "Michele";
		
		private String lName = "Belanger";
		
		        
        boolean success = accountDAO.addAccountDetails(accountName, password1, fName, lName, billingAddressId, shippingAddressId, email);
        
              
   
        @Test
  	   public void testResult() {	  

        	
  	   }
      
        

}
	  	   

	  


