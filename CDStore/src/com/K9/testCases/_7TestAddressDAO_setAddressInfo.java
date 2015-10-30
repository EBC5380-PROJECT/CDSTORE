package com.K9.testCases;

/**
 * This is a unit test that tests the CategoryDAO class.
 */
import org.junit.Test;


import com.K9.hibernate.dao.AddressDAO;




public class _7TestAddressDAO_setAddressInfo {
	{
	  try{
			    
		AddressDAO addressDAO=new AddressDAO();

		
		String street="Castle Hill";


		String city="Ottawa";


		String province="ON";


		String country="Canada";


		 String postalCode="K4D5G6";


		String phone="613 552-6598";
		
		       
        addressDAO.addAddressDetails(street, city, province, country, postalCode, phone);
        
      } catch (Exception e) {
		  
		  throw e;
	  }
         
	}
   
        @Test
  	   public void testResult() {	  

        }
      
        

}
	  	   

	  


