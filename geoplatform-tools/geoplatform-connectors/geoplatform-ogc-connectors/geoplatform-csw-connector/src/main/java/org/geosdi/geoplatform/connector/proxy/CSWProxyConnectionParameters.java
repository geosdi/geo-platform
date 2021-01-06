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

import org.geosdi.geoplatform.support.httpclient.proxy.GPProxyConnectionParamaters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Component(value = "cswProxyConnectionParameters")
public class CSWProxyConnectionParameters implements GPProxyConnectionParamaters {

    private @Value("configurator{csw_use_proxy:@null}")
    Boolean cswUseProxy;
    private @Value("configurator{csw_proxy_url:@null}")
    String cswProxyUrl;
    private @Value("configurator{csw_proxy_port:@null}")
    Integer cswProxyPort;

    @Override
    public Boolean isUseProxy() {
        return this.cswUseProxy = (cswUseProxy != null) ? cswUseProxy
                : Boolean.FALSE;
    }

    @Override
    public String getProxyUrl() {
        return this.cswProxyUrl = (cswProxyUrl != null) ? cswProxyUrl : null;
    }

    @Override
    public Integer getProxyPort() {
        return this.cswProxyPort = (cswProxyPort != null) ? cswProxyPort : null;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (this.cswProxyUrl != null
                ? this.cswProxyUrl.hashCode()
                : 0);
        hash = 37 * hash + (this.cswProxyPort != null
                ? this.cswProxyPort.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CSWProxyConnectionParameters other = (CSWProxyConnectionParameters) obj;
        if ((this.cswProxyUrl == null) ? (other.cswProxyUrl != null)
                : !this.cswProxyUrl.equals(other.cswProxyUrl)) {
            return false;
        }
        if (this.cswProxyPort != other.cswProxyPort && (this.cswProxyPort == null || !this.cswProxyPort.equals(
                other.cswProxyPort))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CSWProxyConnectionParameters{ " + "cswUseProxy = "
                + cswUseProxy + ", cswProxyUrl = " + cswProxyUrl
                + ", cswProxyPort = " + cswProxyPort + '}';
    }

}
