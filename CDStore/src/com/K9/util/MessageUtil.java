/**
 * This class prints the given message on console.
 * 
 */
package com.K9.util;

/**
 * Util used to print out messages
 * 
 * @author MBP
 *
 */
public class MessageUtil {
	 private String message;

	   //Constructor
	   //@param message to be printed
	 
	   public MessageUtil(String message){
	      this.message = message;
	   }
	      
	   // prints the message
	   public String printMessage(String testCase){
	      System.out.println(testCase + "   " + message);
	      return message;
	   }   
}
