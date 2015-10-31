package com.K9.WSClient.OrderProcessService;

public class OrderProcessServiceProxy implements com.K9.WSClient.OrderProcessService.OrderProcessService {
  private String _endpoint = null;
  private com.K9.WSClient.OrderProcessService.OrderProcessService orderProcessService = null;
  
  public OrderProcessServiceProxy() {
    _initOrderProcessServiceProxy();
  }
  
  public OrderProcessServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initOrderProcessServiceProxy();
  }
  
  private void _initOrderProcessServiceProxy() {
    try {
      orderProcessService = (new com.K9.WSClient.OrderProcessService.OrderProcessServiceServiceLocator()).getOrderProcessService();
      if (orderProcessService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)orderProcessService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)orderProcessService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (orderProcessService != null)
      ((javax.xml.rpc.Stub)orderProcessService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.K9.WSClient.OrderProcessService.OrderProcessService getOrderProcessService() {
    if (orderProcessService == null)
      _initOrderProcessServiceProxy();
    return orderProcessService;
  }
  
  public java.lang.String getAccount(java.lang.String accountName, java.lang.String password) throws java.rmi.RemoteException{
    if (orderProcessService == null)
      _initOrderProcessServiceProxy();
    return orderProcessService.getAccount(accountName, password);
  }
  
  public java.lang.String createOrder(java.lang.String shoppingCartInfo, java.lang.String shippingInfo) throws java.rmi.RemoteException{
    if (orderProcessService == null)
      _initOrderProcessServiceProxy();
    return orderProcessService.createOrder(shoppingCartInfo, shippingInfo);
  }
  
  public java.lang.String creatAccount(java.lang.String accountName, java.lang.String accountInfo) throws java.rmi.RemoteException{
    if (orderProcessService == null)
      _initOrderProcessServiceProxy();
    return orderProcessService.creatAccount(accountName, accountInfo);
  }
  
  public java.lang.String confirmOrder(java.lang.String purchaseOrder, java.lang.String shippingInfo, java.lang.String paymentInfo) throws java.rmi.RemoteException{
    if (orderProcessService == null)
      _initOrderProcessServiceProxy();
    return orderProcessService.confirmOrder(purchaseOrder, shippingInfo, paymentInfo);
  }
  
  
}