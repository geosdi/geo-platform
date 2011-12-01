/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.configurator.cxf.server;

import org.geosdi.geoplatform.configurator.cxf.EnumWebserviceSecurity;
import java.util.HashMap;
import java.util.Map;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.handler.WSHandlerConstants;
import org.geosdi.geoplatform.configurator.cxf.GPDummyLoggingInInterceptor;
import org.geosdi.geoplatform.configurator.cxf.GPDummyLoggingOutInterceptor;

/**
 * @author Michele Santomauro - CNR IMAA geoSDI Group
 * @email michele.santomauro@geosdi.org
 */
public class GPServerWebServiceInterceptorStrategyFactory {
    
    private String loggingStrategy;
    private String securityStrategy;
    //
    private String usernameTokenUser;
    //
    private String serverPrivateKeyPropertiesFile;
    private String serverKeystoreUser;
    private String clientPublicKeyPropertiesFile;
    private String clientKeystoreUser;
    

    public LoggingInInterceptor getLoggingInInterceptor() throws IllegalArgumentException {
        if (this.loggingStrategy.equals(EnumWebserviceSecurity.LOGGING_IN.getValue())) {
            return new LoggingInInterceptor();
        } else if (this.loggingStrategy.equals(EnumWebserviceSecurity.LOGGING_IN_OUT.getValue())) {
            return new LoggingInInterceptor();
        } else if (this.loggingStrategy.equals(EnumWebserviceSecurity.LOGGING_OUT.getValue())) {
            return new GPDummyLoggingInInterceptor();
        }
        throw new IllegalArgumentException("Invalid parameter " + this.loggingStrategy + " specified in properties file for IN interceptor of server");
    }

    public LoggingOutInterceptor getLoggingOutInterceptor() throws IllegalArgumentException {
        if (this.loggingStrategy.equals(EnumWebserviceSecurity.LOGGING_OUT.getValue())) {
            return new LoggingOutInterceptor();
        } else if (this.loggingStrategy.equals(EnumWebserviceSecurity.LOGGING_IN_OUT.getValue())) {
            return new LoggingOutInterceptor();
        } else if (this.loggingStrategy.equals(EnumWebserviceSecurity.LOGGING_IN.getValue())) {
            return new GPDummyLoggingOutInterceptor();
        }
        throw new IllegalArgumentException("Invalid parameter " + this.loggingStrategy + " specified in properties file for OUT interceptor of server");
    }

    public WSS4JInInterceptor getSecurityInInterceptor() throws IllegalArgumentException {
        if (this.securityStrategy.equals(EnumWebserviceSecurity.USERNAME_TOKEN.getValue())) {
            return createUsernameTokenInInterceptor();
        } else if (this.securityStrategy.equals(EnumWebserviceSecurity.ENCRYPTION.getValue())) {
            return createEncryptionInInterceptor();
        } else if (this.securityStrategy.equals(EnumWebserviceSecurity.SIGNATURE.getValue())) {
            return createSignatureInInterceptor();
        } else if (this.securityStrategy.equals(EnumWebserviceSecurity.TIMESTAMP_SIGNATURE_ENCRYPTION.getValue())) {
            return createTimestampSignatureEncryptionInInterceptor();
        }
        throw new IllegalArgumentException("Invalid parameter " + this.securityStrategy + " specified in properties file");
    }

    public WSS4JOutInterceptor getSecurityOutInterceptor() throws IllegalArgumentException {
        if (this.securityStrategy.equals(EnumWebserviceSecurity.USERNAME_TOKEN.getValue())) {
            return createUsernameTokenOutInterceptor();
        } else if (this.securityStrategy.equals(EnumWebserviceSecurity.ENCRYPTION.getValue())) {
            return createEncryptionOutInterceptor();
        } else if (this.securityStrategy.equals(EnumWebserviceSecurity.SIGNATURE.getValue())) {
            return createSignatureOutInterceptor();
        } else if (this.securityStrategy.equals(EnumWebserviceSecurity.TIMESTAMP_SIGNATURE_ENCRYPTION.getValue())) {
            return createTimestampSignatureEncryptionOutInterceptor();
        }
        throw new IllegalArgumentException("Invalid parameter " + this.securityStrategy + " specified in properties file");
    }

    private WSS4JInInterceptor createUsernameTokenInInterceptor() {
        return new WSS4JInInterceptor(createUsernameTokenInterceptor());
    }

    private WSS4JOutInterceptor createUsernameTokenOutInterceptor() {
        return new WSS4JOutInterceptor(createUsernameTokenInterceptor());
    }

    private Map<String, Object> createUsernameTokenInterceptor() {
        Map<String, Object> props = new HashMap<String, Object>();
        
        props.put(WSHandlerConstants.USER, this.usernameTokenUser);
        props.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_DIGEST);
        props.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
        props.put(WSHandlerConstants.PW_CALLBACK_CLASS, ServerKeystorePasswordCallback.class.getName());
        
        return props;
    }

    private WSS4JInInterceptor createEncryptionInInterceptor() {
        Map<String, Object> inProps = new HashMap<String, Object>();
        
        inProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.ENCRYPT);
//        inProps.put(WSHandlerConstants.DEC_PROP_FILE, "Server_Decrypt.properties");
        inProps.put(WSHandlerConstants.DEC_PROP_FILE, this.serverPrivateKeyPropertiesFile);
        inProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, ServerKeystorePasswordCallback.class.getName());
        
        return new WSS4JInInterceptor(inProps);
    }

    private WSS4JOutInterceptor createEncryptionOutInterceptor() {
        Map<String, Object> outProps = new HashMap<String, Object>();
        
        outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.ENCRYPT);
        outProps.put(WSHandlerConstants.ENC_PROP_FILE, this.clientPublicKeyPropertiesFile);
        outProps.put(WSHandlerConstants.ENCRYPTION_USER, this.clientKeystoreUser);
        
        return new WSS4JOutInterceptor(outProps);
    }

    private WSS4JInInterceptor createSignatureInInterceptor() {
        Map<String, Object> inProps = new HashMap<String, Object>();
        
        inProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.SIGNATURE);
        inProps.put(WSHandlerConstants.SIG_PROP_FILE, this.clientPublicKeyPropertiesFile);

        return new WSS4JInInterceptor(inProps);
    }

    private WSS4JOutInterceptor createSignatureOutInterceptor() {
        Map<String, Object> outProps = new HashMap<String, Object>();
        
        outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.SIGNATURE);
        outProps.put(WSHandlerConstants.USER, this.serverKeystoreUser);
        outProps.put(WSHandlerConstants.SIG_PROP_FILE, this.serverPrivateKeyPropertiesFile);
        outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, ServerKeystorePasswordCallback.class.getName());
        
        return new WSS4JOutInterceptor(outProps);
    }

    private WSS4JInInterceptor createTimestampSignatureEncryptionInInterceptor() {
        Map<String, Object> inProps = new HashMap<String, Object>();
        
        StringBuilder sb = new StringBuilder();
        sb.append(WSHandlerConstants.TIMESTAMP + " ");
        sb.append(WSHandlerConstants.SIGNATURE + " ");
        sb.append(WSHandlerConstants.ENCRYPT);
        inProps.put(WSHandlerConstants.ACTION, sb.toString());
        inProps.put(WSHandlerConstants.SIG_PROP_FILE, this.clientPublicKeyPropertiesFile);
        inProps.put(WSHandlerConstants.DEC_PROP_FILE, this.serverPrivateKeyPropertiesFile);

        inProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, ServerKeystorePasswordCallback.class.getName());

        return new WSS4JInInterceptor(inProps);
    }

    private WSS4JOutInterceptor createTimestampSignatureEncryptionOutInterceptor() {
        Map<String, Object> outProps = new HashMap<String, Object>();
        
        StringBuilder sb = new StringBuilder();
        sb.append(WSHandlerConstants.TIMESTAMP + " ");
        sb.append(WSHandlerConstants.SIGNATURE + " ");
        sb.append(WSHandlerConstants.ENCRYPT);
        outProps.put(WSHandlerConstants.ACTION, sb.toString());
        outProps.put(WSHandlerConstants.USER, this.serverKeystoreUser);
        outProps.put(WSHandlerConstants.SIG_PROP_FILE, this.serverPrivateKeyPropertiesFile);
        outProps.put(WSHandlerConstants.ENC_PROP_FILE, this.clientPublicKeyPropertiesFile);
        outProps.put(WSHandlerConstants.ENCRYPTION_USER, this.clientKeystoreUser);

//        outProps.put("signatureKeyIdentifier", "DirectReference");

        outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, ServerKeystorePasswordCallback.class.getName());

//        outProps.put("signatureParts", "{Element}{http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd}Timestamp;{Element}{http://schemas.xmlsoap.org/soap/envelope/}Body");
//        outProps.put("encryptionParts", "{Element}{http://www.w3.org/2000/09/xmldsig#}Signature;{Content}{http://schemas.xmlsoap.org/soap/envelope/}Body");
//        outProps.put("encryptionParts", "{Content}{http://schemas.xmlsoap.org/soap/envelope/}Body");
//        outProps.put("encryptionSymAlgorithm", "http://www.w3.org/2001/04/xmlenc#tripledes-cbc");
        return new WSS4JOutInterceptor(outProps);
    }

    public void setLoggingStrategy(String loggingStrategy) {
        this.loggingStrategy = loggingStrategy;
    }

    public void setSecurityStrategy(String securityStrategy) {
        this.securityStrategy = securityStrategy;
    }

    public void setUsernameTokenUser(String usernameTokenUser) {
        this.usernameTokenUser = usernameTokenUser;
    }

    public void setClientPublicKeyPropertiesFile(String clientPublicKeyPropertiesFile) {
        this.clientPublicKeyPropertiesFile = clientPublicKeyPropertiesFile;
    }

    public void setClientKeystoreUser(String clientKeystoreUser) {
        this.clientKeystoreUser = clientKeystoreUser;
    }

    public void setServerPrivateKeyPropertiesFile(String serverPrivateKeyPropertiesFile) {
        this.serverPrivateKeyPropertiesFile = serverPrivateKeyPropertiesFile;
    }

    public void setServerKeystoreUser(String serverKeystoreUser) {
        this.serverKeystoreUser = serverKeystoreUser;
    }
    
}
