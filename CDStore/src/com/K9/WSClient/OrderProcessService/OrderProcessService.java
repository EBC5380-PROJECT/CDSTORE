/**
 * OrderProcessService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.K9.WSClient.OrderProcessService;

public interface OrderProcessService extends java.rmi.Remote {
    public java.lang.String getAccount(java.lang.String accountName, java.lang.String password) throws java.rmi.RemoteException;
    public java.lang.String createOrder(java.lang.String shoppingCartInfo, java.lang.String shippingInfo) throws java.rmi.RemoteException;
    public java.lang.String creatAccount(java.lang.String accountName, java.lang.String accountInfo) throws java.rmi.RemoteException;
    public java.lang.String confirmOrder(java.lang.String purchaseOrder, java.lang.String shippingInfo, java.lang.String paymentInfo) throws java.rmi.RemoteException;
}
