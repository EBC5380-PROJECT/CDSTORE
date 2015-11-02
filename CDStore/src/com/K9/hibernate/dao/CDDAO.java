package com.K9.hibernate.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.K9.hibernate.bean.CD;
import com.K9.util.HibernateUtil;
import com.K9.util.ResponseFactory;
import com.google.gson.Gson;
import java.util.ArrayList;

/**
 * This Data Access Object class is used to access the cd table in the database.  The hibernate framework is used to manage the interaction with the database.

 * 
 * @author MBP
 */
 


public class CDDAO {
	
	/**
	 * The getProductList method retrieves all products from the CD database table.
	 * 
	 * @return
	 */
	 @SuppressWarnings("rawtypes")
	public String getProductList() {
	        try {
	  
	        	// The following steps are specific to Hibernate and are used to establish connectivity and a session with the database
	        		        	
	        	//Configure Hibernate and get the sessionFactory and get a session object
	        	
	        	Session session = HibernateUtil.getSessionFactory().openSession();
	        	
	        	            
	        	//Starting Transaction
	        	 Transaction transaction=session.beginTransaction();
	        	 
	        	             
	            
	            //The following method session.getNamedQuery calls a stored procedure which is defined in the CD bean.
	                   
	            Query query = session.getNamedQuery("callCDProcedure");
	            
	            
	            //The result of the call to the stored procedure is returned and stored in the listCDs ArrayList.
	             
	            
	            ArrayList listCDs = (ArrayList) query.list();
	            
	            
	            //The listCDs is transformed into a Json string.
	                  
	            String json = new Gson().toJson(listCDs);
	           
	                     
	            
	            //The transaction is finalised by calling the commit method.
	            

	            transaction.commit();
	            session.close();
	            
	           
	            //The json string is returned to the caller.
	            
	         
	            return json;
	 
	        } catch (HibernateException e) {
	            System.out.println(e.getMessage());
	            e.printStackTrace();
	            return ResponseFactory.create(1000);  //returning system level error alert
	            
	        }
	 
	    }
	 
	 	
	 
	 /**
	  * The getProductListByCategory method retrieves all products from the CD database table that are of the categoryId type.
	  * 
	  * @param categoryId
	  * @return
	  */
	 @SuppressWarnings("rawtypes")
		public String getProductListByCategory(int categoryId) {
		        try {
		        	
		        	//The following steps are specific to Hibernate and are used to establish connectivity and a session with the database
		        	 
		        	
		        	//Configure Hibernate and get the sessionFactory and get a session object
		        	
		        	Session session = HibernateUtil.getSessionFactory().openSession();
		        	
		        	            
		        	//Starting Transaction
		        	 Transaction transaction=session.beginTransaction();           
		            		            
		            
		            //The following method session.getNamedQuery calls a stored procedure which is defined in the CD bean.
		             
		            
		            Query query = session.getNamedQuery("callProductListByCategoryProcedure")
		            		.setParameter("categoryId", categoryId);
		            
		            
		            //The result of the call to the stored procedure is returned and stored in the listCDs ArrayList.  The call to the stored proc
		            //requires an input parameter called categoryId.  This call retrieves all the CDs within the requested category.
		            
		            
		            ArrayList listCDs = (ArrayList) query.list();
		            
		            
		            // The listCDs is transformed into a Json string
		        	
		            String json = new Gson().toJson(listCDs);
		            
		            
		            
		            //The transaction is finalised by calling the commit method.
		             
		            transaction.commit();
		            session.close();
		            
		            
		             //The json string is returned to the caller.
		             
		         
		            return json;
		 
		        } catch (HibernateException e) {
		            System.out.println(e.getMessage());
		            e.printStackTrace();
		            return ResponseFactory.create(1000);  //returning system level error alert
		        }
		 
		    }
	 
	
	 /**
	  * 
	  *  The getProductInfo method retrieves detailed information for a specific product (productId) in the CD database table.
	  * 
	  * @param productId
	  * @return
	  */
		public String getProductInfo(int productId) {
		        try {
		        	
		        	CD CDQueryResult = new CD();
		        	
		        	
		        	//The following steps are specific to Hibernate and are used to establish connectivity and a session with the database
		        	 
		        	
		        	//Configure Hibernate and get the sessionFactory and get a session object
		        	
		        	Session session = HibernateUtil.getSessionFactory().openSession();
		        	
		        	            
		        	//Starting Transaction
		        	 Transaction transaction=session.beginTransaction();
		        	 
		            
		            //The following method session.getNamedQuery calls a stored procedure which is defined in the CD bean.
		             
		           		           
		            Query query = session.getNamedQuery("callDetailedProductInfoProcedure")
		            		.setParameter("productId", productId);
		            
		            
		             //The result of the call to the stored procedure is returned and stored in the CD bean.  The call to the stored proc
		             //requires an input parameter called productId.  This call retrieves the detailed information of a specific CD.
		            
		            
		            if (query.list().isEmpty() != true) {
			            CDQueryResult = (CD) query.uniqueResult();
			            
			         } 
		            
		            	            
		            
		            //CD is transformed into a Json string.
		             
		            	
		            String json = new Gson().toJson(CDQueryResult);
		            
		                     
		            
		            //The transaction is finalised by calling the commit method.
		              

		            transaction.commit();
		            session.close();
		            
		            
		            //The json string is returned to the caller.
		            
		         
		         
		            return json;
		 
		        } catch (HibernateException e) {
		            System.out.println(e.getMessage());
		            e.printStackTrace();
		            return ResponseFactory.create(1000);  //returning system level error alert
		        }
		 
		    }
 
	 	
}