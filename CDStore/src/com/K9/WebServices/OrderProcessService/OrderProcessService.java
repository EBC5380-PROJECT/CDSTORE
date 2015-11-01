package com.K9.WebServices.OrderProcessService;
 

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import java.util.ResourceBundle;

import com.K9.hibernate.dao.*;
import com.K9.hibernate.bean.Orders;
import com.google.gson.Gson;
import com.K9.session.bean.*;
import com.K9.util.AvailableCreditValidationUtil;
import com.K9.util.CallStatus;
import com.K9.util.CreateOrderFactory;
import com.K9.util.GetAccountIdUtil;
import com.K9.util.OrderId;
import com.K9.util.PasswordHash;
import com.K9.util.ResponseFactory;

/** 
 * 
 * The OrderProcessService Webservice contains several methods which serve to process order requests from the CD Store.
 * 
 * createAccount method servers to create a new user account.
 * 
 * getAccount method serves to get the account information of an existing user.
 * 
 * createOrder method serves to create a new order entry in the database.
 * 
 * confirmOrder method serves to confirm the order after credit card information is validated.
 * 
 * 
 * @author Michele
 *
 */

public class OrderProcessService {
	
	/** 
	 * 
	 * The createAccount method creates a new account provided that the account name used is unique.
	 * 
	 * @param accountName
	 * @param accountInfo
	 * @return Call status 
	 * 
	 */
	@SuppressWarnings("rawtypes")		
	public String creatAccount(String accountName, String accountInfo) {
	       
		{
		 
		 try {
			 	 
			 //Create a new instance of the AccountInfo class         
	         AccountInformation accntInfo = new AccountInformation();
	         
	         //Convert the Json string received from the calling servlet into the accntInfo instance.
	         Gson gson = new Gson();
	         accntInfo = gson.fromJson(accountInfo, AccountInformation.class);	
	         
	         //Extract the accountName from the Json string received from the calling servlet
	         Map jsonJavaRootObject = new Gson().fromJson(accountName, Map.class);
	         String accountName1=(String) jsonJavaRootObject.get("accountName");
			 
			 //Create an new instance of the AccountDAO in order to validate if the accountName being used to register to the site is unique.
	         
	         AccountDAO accntDAO = new AccountDAO();
	         
			 //determine whether the account name is unique
			 String validAccountName = accntDAO.isUserNameUnique(accountName1);
			 
			 			 
			 if (validAccountName.equals("true")) {
				 //if the account name is unique, insert the billingAddress, ShippingAddress and account information into the database.
				 
				 String success = "";
				 // Need to store both the billing address and shipping address
				 AddressDAO addressDAO = new AddressDAO();
				 //billingAddressId will be used when inserting a new account entry.
				 String billingAddressId = addressDAO.addAddressDetails(accntInfo.getBillingAddressStreet(), accntInfo.getBillingAddressCity(), accntInfo.getBillingAddressProvince(), accntInfo.getBillingAddressCountry(), accntInfo.getBillingAddressPostalCode(), accntInfo.getBillingAddressPhone());
				 
				 
				 if (Integer.valueOf(billingAddressId) != (int)Integer.valueOf(billingAddressId))
				 	return billingAddressId;  
					 
					 
				 AddressDAO addressDAO1 = new AddressDAO();
				//shippingAddressId will be used when inserting a new account entry.
				 String shippingAddressId = addressDAO1.addAddressDetails(accntInfo.getShippingAddressStreet(), accntInfo.getShippingAddressCity(), accntInfo.getShippingAddressProvince(), accntInfo.getShippingAddressCountry(), accntInfo.getShippingAddressPostalCode(), accntInfo.getShippingAddressPhone());
					
				 if (Integer.valueOf(shippingAddressId) != (int)Integer.valueOf(shippingAddressId))
					 	return shippingAddressId;  
				 
				 //Calling the utility to hash the password so that it is not stored in clear text in the database.
				 String password = PasswordHash.createHash(accntInfo.getPassword1()); 
				 
				 //Calling the method addAccountDetails to insert a new record in the account table
				 success = accntDAO.addAccountDetails(accntInfo.getAccountName(), password, accntInfo.getFName(), accntInfo.getLName(), Integer.valueOf(billingAddressId), Integer.valueOf(shippingAddressId), accntInfo.getEmail());
				 
				 //Returning the appropriate status back to the calling servlet
				 if (success.equals(""))
					 return ResponseFactory.create(0);  //No processing errors encountered
				// else if(success.equals("false"))
					 //return ResponseFactory.create(3);  //Error returned while trying to add accountDetails
				 else
					 return success;  //returning system level error alert
				 
			 } else if(validAccountName.equals("false")) {
				  return ResponseFactory.create(2);  //Error returned since the user name is not unique
				 
			 } else
				 return validAccountName;  //returning system level error alert
			 		 
			 
		   } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
	            System.out.println(e.getMessage());
	            e.printStackTrace();
	            return ResponseFactory.create(1000);  //returning system level error alert
	        }
		 
		}
		
	}        	

/**
 * 	 
 * This method is called in order to retrieve the account information for a given account name and password.
 * 
 * @param accountName
 * @param password
 * @return account information or call status
 * 
 * 
 */
@SuppressWarnings("rawtypes") 	 
public String getAccount(String accountName, String password){
    
	 //Declaring some local variables
	 String accountName1;
	 String acctInfo;
	 String  password1;	
	 
	 try {
			
		 Gson gson = new Gson();
		 
		//Extracting the accountName from the Json string received from the calling servlet.		 
         Map jsonJavaRootObject = gson.fromJson(accountName, Map.class);
         accountName1=(String) jsonJavaRootObject.get("accountName");
         
         //Extracting the password from the Json string received from the calling servlet.
         Map jsonJavaRootObject2 = gson.fromJson(password, Map.class);
         password1=(String) jsonJavaRootObject2.get("password");
         
         //Creating a new instance of AccountDAO so the the userName and password can be validated before returning the account information. 
		 AccountDAO accntDAO = new AccountDAO();
		 //determine whether the user credentials are valid
		 String validUserCredentials = accntDAO.areCredentialsValid(accountName1, password1);
		 
		 
		 if (validUserCredentials.equals("true")) {
			 //If credentials are valid, the account information is retrieved for the given accountName
			acctInfo = accntDAO.getAccountInfo(accountName1);
			//do not return password to calling servlet
			 AccountInformation ai = gson.fromJson(acctInfo, AccountInformation.class);	
			 ai.setPassword1("");
			acctInfo = gson.toJson(ai);
		 }
		 else if (validUserCredentials.equals("false")) {
			 return ResponseFactory.create(1);  //Invalid userName or password message			 
		 } else 
			 return validUserCredentials;  //returning system level error alert
		 
		 	
			return acctInfo;  //return the account information to the calling servlet.
		 
	 	} catch (Exception e) {
           System.out.println(e.getMessage());
           e.printStackTrace();
           return ResponseFactory.create(1000);  //returning system level error alert
       } 
       
       }
     	


/**
 * 
 * This method is called in order to create a new order.  
 * 
 * @param shoppingCartInfo
 * @param shippingInfo
 * @return orderId or error code
 */
public String createOrder(String shoppingCartInfo, String shippingInfo)  {
	
	
	try {
		//Create a new instance of CreateOrderFactory in order to process the new order
		
		CreateOrderFactory orderFactory = new CreateOrderFactory();
		
		//Call orderFactory to create the order and save it to the database
		return orderFactory.createOrder(shippingInfo, shoppingCartInfo);
		
		
	} catch (Exception e) {
		System.out.println(e.getMessage());
		e.printStackTrace();
        return ResponseFactory.create(1000);  //returning system level error alert
	}
	
	
 }        	


/**
 * 
 * This method is called in order to confirm an order once it has been entered in the database.
 * 
 * @param purchaseOrder
 * @param shippingInfo
 * @param paymentInfo
 * @return call status
 */
public String confirmOrder(String purchaseOrder, String shippingInfo, String paymentInfo) {
	
	//Creating a reference to the enums resource bundle in order to extract order status
	ResourceBundle rb = ResourceBundle.getBundle("com.K9.resources.enums"); 
	 
	 try {
	 
		 //declaring local variables
		 String status="";
		
		 //Converting Json string received by calling servlet into an instance of Orders class.
		 Gson gson = new Gson();
         Orders orders = gson.fromJson(purchaseOrder, Orders.class);        
         
                  
         if ((orders.getOrderId() % 5) == 0) 
        	 status=rb.getString("DENIED");	 
         else
        	 status= rb.getString("PROCESSED");	
		 
         
         //Need to extract accountId from shippingInfo
		 
		 GetAccountIdUtil getAcctId = new GetAccountIdUtil();
		 String accntIdString = getAcctId.getId(shippingInfo);
		 int accntId = Integer.valueOf(accntIdString);
		 
          //Create a new instance of OrdersDAO in order to update the order information with the order status, shipping address and update the timestamp (automatically done when row is updated).
		 
		 OrdersDAO ordersDAO = new OrdersDAO(); 		 
		 String result = ordersDAO.updateOrderStatus(orders.getOrderId(), status, accntId);
		 
		 if (result.equals(""))
				return ResponseFactory.create(0);  //no errors encountered.  Order was successfully created.
			else
				return result;  //returning system level error alert
		 		
		 
	   } catch (Exception e) {
		   System.out.println(e.getMessage());
		   e.printStackTrace();
	       return ResponseFactory.create(1000);  //returning system level error alert
      }
 }        	


}