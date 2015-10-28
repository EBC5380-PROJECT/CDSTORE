package com.K9.util;

import com.google.gson.Gson;

/**
 * Used to generate a Json string from the integer responseStatus
 * 
 * @author Michele
 *
 */
public class ResponseFactory {
	
	/**
	 * The create method receives an integer responseStatus from the calling class and transforms that integer into a Json string which is returned to the calling class.
	 * 
	 * @param responseStatus
	 * @return
	 */
	  public static String create(int responseStatus) {
	    try {
	    	
	    	//Instantiate and set the response status in the class CallStatus
	    	CallStatus callStatus = new CallStatus();
	    	callStatus.setCallStatus(responseStatus);
	    	
	    	Gson gson = new Gson();
			
	    	//return the Json String to be returned to the calling servlet
	    	return gson.toJson(callStatus);
	   
	    } catch (Exception e) {
	    	System.out.println(e.getMessage());
	    	e.printStackTrace();
	    	throw e;
	    }
	  }
	}
