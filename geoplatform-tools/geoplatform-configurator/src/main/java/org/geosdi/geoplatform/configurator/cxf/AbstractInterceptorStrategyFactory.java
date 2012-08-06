/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.configurator.cxf;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public abstract class AbstractInterceptorStrategyFactory {

    private String loggingStrategy;
    private String securityStrategy;
    //
    protected String usernameTokenUser;
    protected String clientKeystoreUser;
    protected String serverKeystoreUser;

    /**
     * Logging
     */
    public LoggingInInterceptor getLoggingInInterceptor() throws IllegalArgumentException {
        LoggingWebServiceType loggingType = LoggingWebServiceType.fromValue(loggingStrategy);
        if (loggingType == LoggingWebServiceType.LOGGING_IN
                || loggingType == LoggingWebServiceType.LOGGING_IN_OUT) {
            return new LoggingInInterceptor();
        } else {
            return new DummyLoggingInInterceptor();
        }
    }

    public LoggingOutInterceptor getLoggingOutInterceptor() throws IllegalArgumentException {
        LoggingWebServiceType loggingType = LoggingWebServiceType.fromValue(loggingStrategy);
        if (loggingType == LoggingWebServiceType.LOGGING_OUT
                || loggingType == LoggingWebServiceType.LOGGING_IN_OUT) {
            return new LoggingOutInterceptor();
        } else {
            return new DummyLoggingOutInterceptor();
        }
    }

    /**
     * Security
     */
    public WSS4JInInterceptor getSecurityInInterceptor() throws IllegalArgumentException {
        SecurityWebServiceType securityType = SecurityWebServiceType.fromValue(securityStrategy);
        switch (securityType) {
            case USERNAME_TOKEN:
                return createUsernameTokenInInterceptor();
            case ENCRYPTION:
                return createEncryptionInInterceptor();
            case SIGNATURE:
                return createSignatureInInterceptor();
            case TIMESTAMP_SIGNATURE_ENCRYPTION:
                return createTimestampSignatureEncryptionInInterceptor();
            case NONE:
                return new WSS4JInInterceptor(); // TODO Try to return null
            default:
                return null;
        }
    }

    protected abstract WSS4JInInterceptor createUsernameTokenInInterceptor();

    protected abstract WSS4JInInterceptor createEncryptionInInterceptor();

    protected abstract WSS4JInInterceptor createSignatureInInterceptor();

    protected abstract WSS4JInInterceptor createTimestampSignatureEncryptionInInterceptor();

    public WSS4JOutInterceptor getSecurityOutInterceptor() throws IllegalArgumentException {
        SecurityWebServiceType securityType = SecurityWebServiceType.fromValue(securityStrategy);
        switch (securityType) {
            case USERNAME_TOKEN:
                return createUsernameTokenOutInterceptor();
            case ENCRYPTION:
                return createEncryptionOutInterceptor();
            case SIGNATURE:
                return createSignatureOutInterceptor();
            case TIMESTAMP_SIGNATURE_ENCRYPTION:
                return createTimestampSignatureEncryptionOutInterceptor();
            case NONE: // TODO Try to return null
                return new WSS4JOutInterceptor();
            default:
                return null;
        }
    }

    protected abstract WSS4JOutInterceptor createUsernameTokenOutInterceptor();

    protected abstract WSS4JOutInterceptor createEncryptionOutInterceptor();

    protected abstract WSS4JOutInterceptor createSignatureOutInterceptor();

    protected abstract WSS4JOutInterceptor createTimestampSignatureEncryptionOutInterceptor();

    /**
     * Setter
     */
    public void setLoggingStrategy(String loggingStrategy) {
        this.loggingStrategy = loggingStrategy;
    }

    public void setSecurityStrategy(String securityStrategy) {
        this.securityStrategy = securityStrategy;
    }

    public void setUsernameTokenUser(String usernameTokenUser) {
        this.usernameTokenUser = usernameTokenUser;
    }

    public void setClientKeystoreUser(String clientKeystoreUser) {
        this.clientKeystoreUser = clientKeystoreUser;
    }

    public void setServerKeystoreUser(String serverKeystoreUser) {
        this.serverKeystoreUser = serverKeystoreUser;
    }
}
