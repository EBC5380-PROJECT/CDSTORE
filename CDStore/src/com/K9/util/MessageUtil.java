/**
 * This class prints the given message on console.
 * 
 */
package com.K9.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Util used to print out messages
 * 
 * @author MBP
 *
 */
public class MessageUtil {
	         
	   // prints the message
	   public void printMessage(String testCase){
	          
	      
	      try{
	    	System.out.println(testCase);
      		File file =new File("K9TestResults.txt");
      		
      		//if file doesnt exists, then create it
      		if(!file.exists()){
      			file.createNewFile();
      		}
      		
      		//true = append file
      		FileWriter fileWritter = new FileWriter(file.getName(),true);
      	        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
      	        bufferWritter.write(testCase);
      	        bufferWritter.newLine();
      	        bufferWritter.newLine();
      	        bufferWritter.close();
  	        
      	}catch(IOException e){
      		e.printStackTrace();
      	}
	}      
}   

