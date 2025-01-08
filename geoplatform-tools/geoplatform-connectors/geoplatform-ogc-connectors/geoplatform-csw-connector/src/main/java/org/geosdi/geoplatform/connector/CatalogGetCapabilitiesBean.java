/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector;

import org.geosdi.geoplatform.GPGenericMarshaller;
import org.geosdi.geoplatform.connector.api.capabilities.model.csw.CatalogCapabilities;
import org.geosdi.geoplatform.connector.security.CatalogSecurityConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Nonnull;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.GPCatalogVersion.V202;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class CatalogGetCapabilitiesBean implements GPCatalogGetCapabilitiesBean {

    private static final String CSW_CABABILITIES_REQUEST = "?SERVICE=CSW&REQUEST=GetCapabilities";
    /**
     * SNIPC Catalog.
     */
    private @Value("configurator{snipc_catalog_username}")
    String snipcUsername;
    private @Value("configurator{snipc_catalog_password}")
    String snipcPassword;
    //
    @Autowired
    private GPGenericMarshaller xStreamCatalog;
    //
    private CatalogSecurityConnection securityConnection;

    /**
     * <p>
     *      Bind CSW_202 Server URL with Control for Server Version. The standard CSW_202 Server version must be 2.0.2.
     * </p>
     *
     * @param urlServer
     * @return CatalogCapabilities
     * @throws Exception
     */
    @Override
    public CatalogCapabilities bindUrl(@Nonnull(when = NEVER) String urlServer) throws Exception {
        checkArgument((urlServer != null) && !(urlServer.trim().isEmpty()), "The Parameter urlServer must not be null or an empty string.");
        CatalogCapabilities catalogGetCapabilities = this.connect(urlServer);
        this.checkCSWServerVersion(catalogGetCapabilities);
        return catalogGetCapabilities;
    }

    /**
     * @param urlServer
     * @return {@link CatalogGetCapabilitiesBean}
     * @throws Exception
     */
    @Override
    public CatalogCapabilities bindUrlWithoutVersionControl(String urlServer) throws Exception {
        checkArgument((urlServer != null) && !(urlServer.trim().isEmpty()), "The Parameter urlServer must not be null or an empty string.");
        return this.connect(urlServer);

    }

    /**
     * Invoked by the containing {@code BeanFactory} after it has set all bean properties
     * and satisfied {@link org.springframework.beans.factory.BeanFactoryAware}, {@code ApplicationContextAware} etc.
     * <p>This method allows the bean instance to perform validation of its overall
     * configuration and final initialization when all bean properties have been set.
     *
     * @throws Exception in the event of misconfiguration (such as failure to set an
     *                   essential property) or if initialization fails for any other reason
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        this.securityConnection = new CatalogSecurityConnection();
    }

    private CatalogCapabilities connect(String urlServer) throws Exception {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(this.convertUrl(urlServer) + CSW_CABABILITIES_REQUEST);
            if (urlServer.startsWith("https")) {
                /**
                 * @@@@@@@@@@@@@@@ TODO FIX ME
                 * @@@@@@@@@@@@@@@@@@@@ *
                 */
                if (urlServer.contains("snipc.protezionecivile.it")) {
                    conn = this.securityConnection.getSecureConnectionWithAuth(url, snipcUsername, snipcPassword);
                } else {
                    conn = this.securityConnection.getSecureConnection(url);
                }
            } else {
                conn = (HttpURLConnection) url.openConnection();
            }
            return  (CatalogCapabilities) this.xStreamCatalog.unmarshal(conn.getInputStream());
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    private void checkCSWServerVersion(CatalogCapabilities capabilitiesBean) throws CatalogVersionException {
        if (!capabilitiesBean.getServiceIdentification().getServiceTypeVersion().equals(V202.toString())) {
            throw new CatalogVersionException("The version of CSW_202 Service must be 2.0.2");
        }
    }

    private String convertUrl(String urlServer) {
        int index = urlServer.indexOf("?");
        if (index != -1) {
            return urlServer.substring(0, index);
        }
        return urlServer;
    }
}