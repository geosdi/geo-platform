/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.support.cxf.client;

import java.util.HashMap;
import java.util.Map;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.geosdi.geoplatform.support.cxf.api.interceptor.factory.strategy.AbstractInterceptorStrategyFactory;

/**
 * @author Michele Santomauro - CNR IMAA geoSDI Group
 * @email michele.santomauro@geosdi.org
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class ClientInterceptorStrategyFactory extends AbstractInterceptorStrategyFactory<GPClientStrategyBean> {

    public ClientInterceptorStrategyFactory(GPClientStrategyBean strategyBean) {
        super(strategyBean);
    }

    @Override
    protected Map<String, Object> createUsernameTokenInterceptor() {
        Map<String, Object> props = new HashMap<String, Object>();

        props.put(WSHandlerConstants.USER, strategyBean.getUsernameTokenUser());
        props.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_DIGEST);
        props.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
        props.put(WSHandlerConstants.PW_CALLBACK_CLASS,
                ClientKeystorePasswordCallback.class.getName());

        return props;
    }

    @Override
    protected WSS4JInInterceptor createEncryptionInInterceptor() {
        Map<String, Object> inProps = new HashMap<String, Object>();

        inProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.ENCRYPT);
        inProps.put(WSHandlerConstants.DEC_PROP_FILE,
                strategyBean.getClientPrivateKeyPropertiesFile());
        inProps.put(WSHandlerConstants.PW_CALLBACK_CLASS,
                ClientKeystorePasswordCallback.class.getName());

        return new WSS4JInInterceptor(inProps);
    }

    @Override
    protected WSS4JOutInterceptor createEncryptionOutInterceptor() {
        Map<String, Object> outProps = new HashMap<String, Object>();

        outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.ENCRYPT);
        outProps.put(WSHandlerConstants.ENC_PROP_FILE,
                strategyBean.getServerPublicKeyPropertiesFile());
        outProps.put(WSHandlerConstants.ENCRYPTION_USER,
                strategyBean.getServerKeystoreUser());

        return new WSS4JOutInterceptor(outProps);
    }

    @Override
    protected WSS4JInInterceptor createSignatureInInterceptor() {
        Map<String, Object> inProps = new HashMap<String, Object>();

        inProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.SIGNATURE);
        inProps.put(WSHandlerConstants.SIG_PROP_FILE,
                strategyBean.getServerPublicKeyPropertiesFile());

        return new WSS4JInInterceptor(inProps);
    }

    @Override
    protected WSS4JOutInterceptor createSignatureOutInterceptor() {
        Map<String, Object> outProps = new HashMap<String, Object>();

        outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.SIGNATURE);
        outProps.put(WSHandlerConstants.USER, strategyBean.getClientKeystoreUser());
        outProps.put(WSHandlerConstants.SIG_PROP_FILE,
                strategyBean.getClientPrivateKeyPropertiesFile());
        outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS,
                ClientKeystorePasswordCallback.class.getName());

        return new WSS4JOutInterceptor(outProps);
    }

    @Override
    protected WSS4JInInterceptor createTimestampSignatureEncryptionInInterceptor() {
        Map<String, Object> inProps = new HashMap<String, Object>();

        StringBuilder sb = new StringBuilder();
        sb.append(WSHandlerConstants.TIMESTAMP + " ");
        sb.append(WSHandlerConstants.SIGNATURE + " ");
        sb.append(WSHandlerConstants.ENCRYPT);
        inProps.put(WSHandlerConstants.ACTION, sb.toString());
        inProps.put(WSHandlerConstants.SIG_PROP_FILE,
                strategyBean.getServerPublicKeyPropertiesFile());
        inProps.put(WSHandlerConstants.DEC_PROP_FILE,
                strategyBean.getClientPrivateKeyPropertiesFile());

        inProps.put(WSHandlerConstants.PW_CALLBACK_CLASS,
                ClientKeystorePasswordCallback.class.getName());

        return new WSS4JInInterceptor(inProps);
    }

    @Override
    protected WSS4JOutInterceptor createTimestampSignatureEncryptionOutInterceptor() {
        Map<String, Object> outProps = new HashMap<String, Object>();

        StringBuilder sb = new StringBuilder();
        sb.append(WSHandlerConstants.TIMESTAMP + " ");
        sb.append(WSHandlerConstants.SIGNATURE + " ");
        sb.append(WSHandlerConstants.ENCRYPT);
        outProps.put(WSHandlerConstants.ACTION, sb.toString());
        outProps.put(WSHandlerConstants.USER, strategyBean.getClientKeystoreUser());
        outProps.put(WSHandlerConstants.SIG_PROP_FILE,
                strategyBean.getClientPrivateKeyPropertiesFile());
        outProps.put(WSHandlerConstants.ENC_PROP_FILE,
                strategyBean.getServerPublicKeyPropertiesFile());
        outProps.put(WSHandlerConstants.ENCRYPTION_USER,
                strategyBean.getServerKeystoreUser());

//        outProps.put("signatureKeyIdentifier", "DirectReference");
        outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS,
                ClientKeystorePasswordCallback.class.getName());

//        outProps.put("signatureParts", "{Element}{http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd}Timestamp;{Element}{http://schemas.xmlsoap.org/soap/envelope/}Body");
//        outProps.put("encryptionParts", "{Element}{http://www.w3.org/2000/09/xmldsig#}Signature;{Content}{http://schemas.xmlsoap.org/soap/envelope/}Body");
//        outProps.put("encryptionParts", "{Content}{http://schemas.xmlsoap.org/soap/envelope/}Body");
//        outProps.put("encryptionSymAlgorithm", "http://www.w3.org/2001/04/xmlenc#tripledes-cbc");
        return new WSS4JOutInterceptor(outProps);
    }

}
