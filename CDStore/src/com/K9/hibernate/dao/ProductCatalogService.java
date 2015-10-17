package com.K9.hibernate.dao;
 
import org.hibernate.HibernateException;

import com.K9.hibernate.dao.CDDAO;
import com.K9.hibernate.dao.CategoryDAO;


public class ProductCatalogService {
	
		
	public String getProductList() {
	       
		 
		 try {
			 
			 CDDAO cdDAO=new CDDAO();
			 String json = cdDAO.getProductList();
			 return json;
		   } catch (HibernateException e) {
	            System.out.println(e.getMessage());
	            System.out.println("error");
	            return null;
	        }
	        	
	        		    }
	 
 	 
	
		public String getProductInfo(int productId) {
		          	
		        	
		        	 try {
		    			 
		    			 CDDAO cdDAO=new CDDAO();
		    			 String json = cdDAO.getProductInfo(productId);
		    			 return json;
		    		   } catch (HibernateException e) {
		    	            System.out.println(e.getMessage());
		    	            System.out.println("error");
		    	            return null;
		    	        }
		        
	 }
		          	 
	 
		public String getCategoryList() {
		 try {
			 
			 CategoryDAO categoryDAO=new CategoryDAO();
			 String j = categoryDAO.getCategoryList();
			 return j;
		   } catch (HibernateException e) {
	            System.out.println(e.getMessage());
	            System.out.println("error");
	            return null;
	        }
    
   }	 
	
		public String getProductListByCategory(int categoryId) {
		 try {
			 
			 CDDAO cdDAO=new CDDAO();
			 String json = cdDAO.getProductListByCategory(categoryId);
			 return json;
		   } catch (HibernateException e) {
	            System.out.println(e.getMessage());
	            System.out.println("error");
	            return null;
	        }
    
  }
	 
}