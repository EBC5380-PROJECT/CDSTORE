/**
 * ProductCatalogServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.K9.WSClient.ProductCatalogService;

public class ProductCatalogServiceServiceLocator extends org.apache.axis.client.Service implements com.K9.WSClient.ProductCatalogService.ProductCatalogServiceService {

    public ProductCatalogServiceServiceLocator() {
    }


    public ProductCatalogServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ProductCatalogServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ProductCatalogService
    private java.lang.String ProductCatalogService_address = "http://localhost:8080/CDStore/services/ProductCatalogService";

    public java.lang.String getProductCatalogServiceAddress() {
        return ProductCatalogService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ProductCatalogServiceWSDDServiceName = "ProductCatalogService";

    public java.lang.String getProductCatalogServiceWSDDServiceName() {
        return ProductCatalogServiceWSDDServiceName;
    }

    public void setProductCatalogServiceWSDDServiceName(java.lang.String name) {
        ProductCatalogServiceWSDDServiceName = name;
    }

    public com.K9.WSClient.ProductCatalogService.ProductCatalogService getProductCatalogService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ProductCatalogService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getProductCatalogService(endpoint);
    }

    public com.K9.WSClient.ProductCatalogService.ProductCatalogService getProductCatalogService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.K9.WSClient.ProductCatalogService.ProductCatalogServiceSoapBindingStub _stub = new com.K9.WSClient.ProductCatalogService.ProductCatalogServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getProductCatalogServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setProductCatalogServiceEndpointAddress(java.lang.String address) {
        ProductCatalogService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.K9.WSClient.ProductCatalogService.ProductCatalogService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.K9.WSClient.ProductCatalogService.ProductCatalogServiceSoapBindingStub _stub = new com.K9.WSClient.ProductCatalogService.ProductCatalogServiceSoapBindingStub(new java.net.URL(ProductCatalogService_address), this);
                _stub.setPortName(getProductCatalogServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ProductCatalogService".equals(inputPortName)) {
            return getProductCatalogService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ProductCatalogService.WebServices.K9.com", "ProductCatalogServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ProductCatalogService.WebServices.K9.com", "ProductCatalogService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ProductCatalogService".equals(portName)) {
            setProductCatalogServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
