/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2024 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
import org.geosdi.geoplatform.connector.geoserver.GPGeoserverConnector;
import org.geosdi.geoplatform.connector.geoserver.request.reload.GeoserverReloadCatalogRequest;
import org.geosdi.geoplatform.connector.geoserver.request.reset.GeoserverResetRequest;
import org.geosdi.geoplatform.connector.geoserver.request.running.GeoserverRestRunningRequest;
import org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfig;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;
import org.geosdi.geoplatform.connector.store.logging.GeoserverLoggingStore;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import java.net.URL;

import static java.lang.Boolean.FALSE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPGeoserverConnectorStore extends GeoserverLoggingStore implements IGPGeoserverConnectorStore {

    private final GeoserverRestRunningRequest restRunningRequest;
    private final GeoserverReloadCatalogRequest reloadCatalogRequest;
    private final GeoserverResetRequest resetRequest;
    private final GeoserverRestRunningRequest importerRunningRequest;

    /**
     * @param server
     * @param securityConnector
     * @param theJacksonSupport
     * @param theVersion
     */
    GPGeoserverConnectorStore(URL server, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, GeoserverVersion theVersion) {
        this(server, null, securityConnector, theJacksonSupport, theVersion);
    }

    /**
     * @param server
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param theJacksonSupport
     * @param theVersion
     */
    GPGeoserverConnectorStore(URL server, GPPooledConnectorConfig pooledConnectorConfig, GPSecurityConnector securityConnector, JacksonSupport theJacksonSupport, GeoserverVersion theVersion) {
        super(new GPGeoserverConnector(server, pooledConnectorConfig, securityConnector, theJacksonSupport, theVersion));
        this.restRunningRequest = this.server.createGeoserverRestRunningRequest();
        this.reloadCatalogRequest = this.server.reloadGeoserverCatalogRequest();
        this.resetRequest = this.server.resetGeoserverRequest();
        this.importerRunningRequest = this.server.createImportsGeoserverRunningRequest();
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean isGeoserverRestRunning() {
       return this.restRunningRequest.isUp();
    }

    /**
     * <p>Reload the configuration from disk, and reset all caches.</p>
     *
     * @return {@link Boolean}
     */
    @Override
    public Boolean reloadCatalog() {
        try {
            return this.reloadCatalogRequest.getResponse();
        } catch (Exception ex) {
            return FALSE;
        }
    }

    /**
     * <p>Reset all store, raster, and schema caches.</p>
     *
     * @return {@link Boolean}
     */
    @Override
    public Boolean reset() {
        try {
            return this.resetRequest.getResponse();
        } catch (Exception ex) {
            return FALSE;
        }
    }

    /**
     * @return {@link GeoserverVersion}
     */
    @Override
    public GeoserverVersion getVersion() {
        return this.server.getVersion();
    }

    /**
     * @return
     */
    @Override
    public Boolean isImportsRunning() {
        return this.importerRunningRequest.isUp();
    }
}