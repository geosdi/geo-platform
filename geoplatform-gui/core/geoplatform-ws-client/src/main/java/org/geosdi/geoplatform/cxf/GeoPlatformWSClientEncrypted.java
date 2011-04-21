package org.geosdi.geoplatform.cxf;

import java.util.HashMap;
import java.util.Map;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.geosdi.geoplatform.services.GeoPlatformService;

public class GeoPlatformWSClientEncrypted {

    private String address;

    public GeoPlatformService create() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

        Map<String, Object> outProps = new HashMap<String, Object>();
        outProps.put("action", "Timestamp Signature Encrypt");
        outProps.put("user", "alice");
        outProps.put("signaturePropFile", "/alice.properties");
        outProps.put("encryptionPropFile", "/bob.properties");
        outProps.put("encryptionUser", "Bob");
        outProps.put("signatureKeyIdentifier", "DirectReference");
        outProps.put("passwordCallbackClass", "org.geosdi.geoplatform.cxf.KeystorePasswordCallback");
        outProps.put("signatureParts", "{Element}{http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd}Timestamp;{Element}{http://schemas.xmlsoap.org/soap/envelope/}Body");
        outProps.put("encryptionParts", "{Element}{http://www.w3.org/2000/09/xmldsig#}Signature;{Content}{http://schemas.xmlsoap.org/soap/envelope/}Body");
        outProps.put("encryptionSymAlgorithm", "http://www.w3.org/2001/04/xmlenc#tripledes-cbc");
        WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);
        factory.getOutInterceptors().add(wssOut);
        
        Map<String, Object> inProps = new HashMap<String, Object>();
        inProps.put("action", "Timestamp Signature Encrypt");
        inProps.put("signaturePropFile", "/bob.properties");
        inProps.put("decryptionPropFile", "/alice.properties");
        inProps.put("passwordCallbackClass", "org.geosdi.geoplatform.cxf.KeystorePasswordCallback");
        WSS4JInInterceptor wssIn = new WSS4JInInterceptor(inProps);
        factory.getInInterceptors().add(wssIn);
        
        factory.getInInterceptors().add(new LoggingInInterceptor());
        factory.getOutInterceptors().add(new LoggingOutInterceptor());
        factory.setServiceClass(GeoPlatformService.class);

        factory.setAddress(this.address);

        return (GeoPlatformService) factory.create();
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
