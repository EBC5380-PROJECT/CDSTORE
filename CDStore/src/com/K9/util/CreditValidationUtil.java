package com.K9.util;


public class CreditValidationUtil {
	
	/**
	 * The isCreditAvailable method is just a simulation of a service checking to see if there is enough available credit to cover the order amount.
	 * 
	 * @param totalOrderAmount
	 * @return
	 */
	
	  public static boolean isCreditAvailable(double totalOrderAmount) {
	    try {
	    	//Set the threshold for available credit
	    	double creditLimit = 100.00;
	    	
	    	//Check if there is enough credit to purchase the order
	    	if (totalOrderAmount > creditLimit) {
	    		return false;  //not enough credit to cover the purchase
	    	} else {
	    		return true;  //sufficient credit to cover the purchase
	    	}
				   
	    } catch (Exception e) {
	    	System.out.println(e.getMessage());
	    	e.printStackTrace();
	    	throw e;
	    }
	  }
	}
