package com.K9.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import com.K9.WSClient.ProductCatalogService.*;
import com.google.gson.Gson;

/**
 * Servlet implementation class ItemListAction
 */

public class ProductListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ProductListAction() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int categoryId = 0;
		String jsonProductList = "";
		
		String category = request.getParameter("category");
		categoryId = (category==null||category.trim().equals(""))?0:Integer.parseInt(category);		

		
		try {
			ProductCatalogServiceSoapBindingStub pcService = (ProductCatalogServiceSoapBindingStub) new ProductCatalogServiceServiceLocator().getProductCatalogService();
			if(categoryId != 0){
				jsonProductList = pcService.getProductListByCategory(categoryId);
			}else{
				jsonProductList = pcService.getProductList();
			}
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		out.print(jsonProductList);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
