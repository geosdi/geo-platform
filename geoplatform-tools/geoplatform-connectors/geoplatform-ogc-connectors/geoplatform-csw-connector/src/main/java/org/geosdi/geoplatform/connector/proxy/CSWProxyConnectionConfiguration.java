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
package org.geosdi.geoplatform.connector.proxy;

import javax.annotation.Resource;
import org.geosdi.geoplatform.support.httpclient.proxy.GPNoProxyHostsMatcher;
import org.geosdi.geoplatform.support.httpclient.proxy.GPProxyConnectionParamaters;
import org.geosdi.geoplatform.support.httpclient.proxy.GPProxyCredentialProvider;
import org.geosdi.geoplatform.support.httpclient.proxy.HttpClientProxyConfiguration;
import org.springframework.stereotype.Component;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Component(value = "cswProxyConnectionConfiguration")
public class CSWProxyConnectionConfiguration implements
        HttpClientProxyConfiguration {

    @Resource(name = "cswProxyConnectionParameters")
    private GPProxyConnectionParamaters cswProxyConnectionParameters;
    @Resource(name = "cswProxyCredentialProvider")
    private GPProxyCredentialProvider cswProxyCredentialProvider;
    @Resource(name = "cswNoProxyHostsMatcher")
    private GPNoProxyHostsMatcher cswNoProxyHostsMatcher;

    /**
     * @return the cswUseProxy
     */
    @Override
    public Boolean isUseProxy() {
        return cswProxyConnectionParameters.isUseProxy();
    }

    /**
     * @return the cswProxyUrl
     */
    @Override
    public String getProxyUrl() {
        String proxyURL = cswProxyConnectionParameters.getProxyUrl();

        if ((proxyURL == null) || (proxyURL.equals(""))) {
            throw new IllegalStateException("The Proxy URL Parameter must not"
                    + "be NULL Value or Empty String.");
        }

        return cswProxyConnectionParameters.getProxyUrl();
    }

    /**
     * @return the csw_proxy_port
     */
    @Override
    public Integer getProxyPort() {
        Integer proxyPort = cswProxyConnectionParameters.getProxyPort();

        if ((proxyPort == null) || (proxyPort == 0)) {
            throw new IllegalStateException("The Proxy PORT Parameter must not "
                    + "be NULL Value or 0.");
        }

        return cswProxyConnectionParameters.getProxyPort();
    }

    @Override
    public boolean isUseCredentialProvider() {
        return cswProxyCredentialProvider.isUseCredentialProvider();
    }

    @Override
    public String getUserName() {
        return cswProxyCredentialProvider.getUserName();
    }

    @Override
    public String getPassword() {
        return cswProxyCredentialProvider.getPassword();
    }

    @Override
    public boolean matchServerURL(String serverURL) {
        return this.cswNoProxyHostsMatcher.matchServerURL(serverURL);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{ cswProxyConnectionParameters = "
                + cswProxyConnectionParameters + ", cswProxyCredentialProvider = "
                + cswProxyCredentialProvider + ", cswNoProxyHostsMatcher = "
                + cswNoProxyHostsMatcher + '}';
    }

}
