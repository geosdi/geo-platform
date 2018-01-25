/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2018 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.store;

import org.geosdi.geoplatform.connector.GeoserverVersion;
import org.geosdi.geoplatform.connector.api.GPConnectorStore;
import org.geosdi.geoplatform.connector.geoserver.GPGeoserverConnector;
import org.geosdi.geoplatform.connector.geoserver.request.about.GPGeoserverAboutStatusRequest;
import org.geosdi.geoplatform.connector.geoserver.request.about.GPGeoserverAboutVersionRequest;
import org.geosdi.geoplatform.connector.geoserver.request.layers.GPGeoserverLayerRequest;
import org.geosdi.geoplatform.connector.geoserver.request.layers.GPGeoserverLayersRequest;
import org.geosdi.geoplatform.connector.geoserver.request.namespaces.GPGeoserverNamespaceRequest;
import org.geosdi.geoplatform.connector.geoserver.request.namespaces.GPGeoserverNamespacesRequest;
import org.geosdi.geoplatform.connector.geoserver.request.styles.GPGeoserverStyleRequest;
import org.geosdi.geoplatform.connector.geoserver.request.styles.GPGeoserverStylesRequest;
import org.geosdi.geoplatform.connector.geoserver.request.workspaces.GPGeoserverWorkspacesRequest;
import org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfig;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import java.net.URL;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPGeoserverConnectorStore extends GPConnectorStore<GPGeoserverConnector> implements IGPGeoserverConnectorStore {

    /**
     * @param server
     * @param securityConnector
     * @param theJacksonSupport
     * @param theVersion
     */
    public GPGeoserverConnectorStore(URL server, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport,
            GeoserverVersion theVersion) {
        this(server, null, securityConnector, theJacksonSupport, theVersion);
    }

    /**
     * @param server
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param theJacksonSupport
     * @param theVersion
     */
    public GPGeoserverConnectorStore(URL server, GPPooledConnectorConfig pooledConnectorConfig,
            GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, GeoserverVersion theVersion) {
        super(new GPGeoserverConnector(server, pooledConnectorConfig, securityConnector, theJacksonSupport, theVersion));
    }

    /**
     * @return {@link GeoserverVersion}
     */
    @Override
    public GeoserverVersion getVersion() {
        return this.server.getVersion();
    }

    /**
     * @return {@link GPGeoserverAboutVersionRequest}
     */
    @Override
    public GPGeoserverAboutVersionRequest createAboutVersionRequest() {
        return this.server.createAboutVersionRequest();
    }

    /**
     * @return {@link GPGeoserverAboutStatusRequest}
     */
    @Override
    public GPGeoserverAboutStatusRequest createAboutStatusRequest() {
        return this.server.createAboutStatusRequest();
    }

    /**
     * @return {@link GPGeoserverWorkspacesRequest}
     */
    @Override
    public GPGeoserverWorkspacesRequest createWorkspacesRequest() {
        return this.server.createWorkspacesRequest();
    }

    /**
     * @return {@link GPGeoserverNamespacesRequest}
     */
    @Override
    public GPGeoserverNamespacesRequest createNamespacesRequest() {
        return this.server.createNamespacesRequest();
    }

    /**
     * @return {@link GPGeoserverNamespaceRequest}
     */
    @Override
    public GPGeoserverNamespaceRequest createNamespaceRequest() {
        return this.server.createNamespaceRequest();
    }

    /**
     * @return {@link GPGeoserverLayersRequest}
     */
    @Override
    public GPGeoserverLayersRequest createLayersRequest() {
        return this.server.createLayersRequest();
    }

    /**
     * @return {@link GPGeoserverLayerRequest}
     */
    @Override
    public GPGeoserverLayerRequest createLayerRequest() {
        return this.server.createLayerRequest();
    }

    /**
     * @return {@link GPGeoserverStylesRequest}
     */
    @Override
    public GPGeoserverStylesRequest createStylesRequest() {
        return this.server.createStylesRequest();
    }

    /**
     * @return {@link GPGeoserverStyleRequest}
     */
    @Override
    public GPGeoserverStyleRequest createStyleRequest() {
        return this.server.createStyleRequest();
    }
}