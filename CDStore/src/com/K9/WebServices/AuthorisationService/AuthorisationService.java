package com.K9.WebServices.AuthorisationService;
 

import com.K9.hibernate.bean.Orders;
import com.google.gson.Gson;


/**
 * 
 * @author MBP
 * 
 * This service simulates a call to credit card validation service.  For the sake of this project, it will be assumed that the payment information
 * received through the call to this service contains a valid credit card.  What will be checked is the credit amount available.  If there are insufficient funds
 * available to cover the purchase, the service will not authorise the purchase.  If there are sufficient funds, the purchase will be authorised.
 *
 */

public class AuthorisationService {
	
	
	public boolean authorisePurchase(String paymentInfo, String order) {
	       
		{
		 
		 
		 try {
			 	 
			 int totalCreditAmount = 50;
			 Orders order1 = new Orders();
			 
			 Gson gson = new Gson();
			 order1 = gson.fromJson(order, Orders.class);	
	         
	         if(order1.getTotalCost() > totalCreditAmount)
	        	 return false;
	         else
	        	 return true;
			 
			 	 
			 
		   } catch (Exception e) {
	            System.out.println(e.getMessage());
	            System.out.println("error");
	            //return e.getMessage();
	            throw e;
	        }
		 
		}
		
	}        	


}