package com.K9.WSClient.ProductCatalogService;

public class ProductCatalogServiceProxy implements com.K9.WSClient.ProductCatalogService.ProductCatalogService {
  private String _endpoint = null;
  private com.K9.WSClient.ProductCatalogService.ProductCatalogService productCatalogService = null;
  
  public ProductCatalogServiceProxy() {
    _initProductCatalogServiceProxy();
  }
  
  public ProductCatalogServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initProductCatalogServiceProxy();
  }
  
  private void _initProductCatalogServiceProxy() {
    try {
      productCatalogService = (new com.K9.WSClient.ProductCatalogService.ProductCatalogServiceServiceLocator()).getProductCatalogService();
      if (productCatalogService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)productCatalogService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)productCatalogService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (productCatalogService != null)
      ((javax.xml.rpc.Stub)productCatalogService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.K9.WSClient.ProductCatalogService.ProductCatalogService getProductCatalogService() {
    if (productCatalogService == null)
      _initProductCatalogServiceProxy();
    return productCatalogService;
  }
  
  public java.lang.String getProductInfo(int productId) throws java.rmi.RemoteException{
    if (productCatalogService == null)
      _initProductCatalogServiceProxy();
    return productCatalogService.getProductInfo(productId);
  }
  
  public java.lang.String getProductListByCategory(int categoryId) throws java.rmi.RemoteException{
    if (productCatalogService == null)
      _initProductCatalogServiceProxy();
    return productCatalogService.getProductListByCategory(categoryId);
  }
  
  public java.lang.String getCategoryList() throws java.rmi.RemoteException{
    if (productCatalogService == null)
      _initProductCatalogServiceProxy();
    return productCatalogService.getCategoryList();
  }
  
  public java.lang.String getProductList() throws java.rmi.RemoteException{
    if (productCatalogService == null)
      _initProductCatalogServiceProxy();
    return productCatalogService.getProductList();
  }
  
  
}