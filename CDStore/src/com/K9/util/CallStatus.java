package com.K9.util;

/**
 * CallStatus class - contains the status of the request made to the webservice by the servlet.  
 * A status of '0' represents a successful call
 * A status other than '0' represents an error.  The corresponding error message is stored in the messageBundle.properties file.
 * 
 * @author MBP
 *
 */

public class CallStatus {
	
	private int callStatus;
	
	// Getters and Setters are defined below
	
	    
	 public int getCallStatus() {
	        return callStatus;
    }
 
    public void setCallStatus(int callStatus) {
        this.callStatus = callStatus;
    }
    
      
}