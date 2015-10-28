package com.K9.WebServices.ProductCatalogService;
 
import com.K9.hibernate.dao.CDDAO;
import com.K9.hibernate.dao.CategoryDAO;
import com.K9.util.ResponseFactory;

/**
 * 
 * The web service serves to retrieve CD information from the database in order to be displayed to the user.
 * 
 * getProductList method retrieves all available CDs from the database.
 * 
 * getProductInfo method retrieves detail information about a specific CD.
 * 
 * getCategoryList method retrieves all of the different CD categories available.
 * 
 * getProductListByCategory method retrieves all of the CDs of the specified category.
 * 
 * 
 * @author Michele
 *
 */
public class ProductCatalogService {
	
	/**
	 * This method is called by a controller servlet when the complete list of available CDs is requested by the user.
	 * 
	 * @return CD list or call status if system error occurs
	 */
	public String getProductList() {
	       
		 
		 try {
			 //A new instance of CDDAO is created in order to fetch the complete list of available CDs.
			 CDDAO cdDAO=new CDDAO();
			 //The list of CDs is returned as a Json string and the string is returned to the calling servlet
			 return cdDAO.getProductList();
			 
		   } catch (Exception e) {
			   System.out.println(e.getMessage());
			   e.printStackTrace();
		       return ResponseFactory.create(1000);  //returning system level error alert
	        }
	        	
	      }
	 
 	 
	/**
	 * 
	 * This method is called by a controller servlet when the detailed information of a particular CD is requested by the user.
	 * 
	 * @param productId
	 * @return detailed product information or call status if system error occurs
	 */
		public String getProductInfo(int productId) {
		        	 try {
		    			 //A new instance of CDDAO is created in order to fetch the detailed product information
		    			 CDDAO cdDAO=new CDDAO();
		    			//The detailed product information is returned as a Json string and the string is returned to the calling servlet
		    			 return cdDAO.getProductInfo(productId);
		    			 
		    		   } catch (Exception e) {
		    			   System.out.println(e.getMessage());
		    			   e.printStackTrace();
		    		       return ResponseFactory.create(1000);  //returning system level error alert
		    	        }
		        
	 }
		          	 
	 /**
	  * 
	  * This method is called by a controller servlet when the available CD category information is requested by the user.
	  * 
	  * @return available CD categories or call status if system error occurs
	  */
		public String getCategoryList() {
		 try {
			 //A new instance of CategoryDAO is created in order to fetch the CD category information
			 CategoryDAO categoryDAO=new CategoryDAO();
			//The category information is returned as a Json string and the string is returned to the calling servlet
			 return categoryDAO.getCategoryList();
			 
		   } catch (Exception e) {
			   System.out.println(e.getMessage());
			   e.printStackTrace();
		       return ResponseFactory.create(1000);  //returning system level error alert
	        }
    
   }	 
	
		/**
		 * 
		 * This method is called by a controller servlet when the user wishes to see all of the CDs available within a given category.
		 * 
		 * @param categoryId
		 * @return available CD of the specified category or call status if system error occurs
		 */
		public String getProductListByCategory(int categoryId) {
		 try {
			 //Create a new instance of CDDAO in order to fetch the list of CDs of a given category
			 CDDAO cdDAO=new CDDAO();
			//The CDs of a specified category are returned as a Json string and the string is returned to the calling servlet
			 return cdDAO.getProductListByCategory(categoryId);

		   } catch (Exception e) {
			   System.out.println(e.getMessage());
			   e.printStackTrace();
		       return ResponseFactory.create(1000);  //returning system level error alert
	        }
    
  }
	 
}