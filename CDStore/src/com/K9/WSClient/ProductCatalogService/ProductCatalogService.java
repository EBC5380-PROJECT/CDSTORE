/**
 * ProductCatalogService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.K9.WSClient.ProductCatalogService;

public interface ProductCatalogService extends java.rmi.Remote {
    public java.lang.String getProductInfo(int productId) throws java.rmi.RemoteException;
    public java.lang.String getProductListByCategory(int categoryId) throws java.rmi.RemoteException;
    public java.lang.String getCategoryList() throws java.rmi.RemoteException;
    public java.lang.String getProductList() throws java.rmi.RemoteException;
}
