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
package org.geosdi.geoplatform.connector.store.about;

import org.geosdi.geoplatform.connector.api.GPConnectorStore;
import org.geosdi.geoplatform.connector.geoserver.GPGeoserverConnector;
import org.geosdi.geoplatform.connector.geoserver.request.about.manifest.GeoserverAboutManifestRequest;
import org.geosdi.geoplatform.connector.geoserver.request.about.status.GeoserverAboutStatusRequest;
import org.geosdi.geoplatform.connector.geoserver.request.about.system.GeoserverAboutSystemStatusRequest;
import org.geosdi.geoplatform.connector.geoserver.request.about.version.GeoserverAboutVersionRequest;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GeoserverAboutConnectorStore extends GPConnectorStore<GPGeoserverConnector> implements GPGeoserverAboutConnectorStore {

    /**
     * @param theServer
     */
    protected GeoserverAboutConnectorStore(GPGeoserverConnector theServer) {
        super(theServer);
    }

    /**
     * @return {@link GeoserverAboutVersionRequest}
     */
    @Override
    public GeoserverAboutVersionRequest createAboutVersionRequest() {
        return this.server.createAboutVersionRequest();
    }

    /**
     * @return {@link GeoserverAboutStatusRequest}
     */
    @Override
    public GeoserverAboutStatusRequest createAboutStatusRequest() {
        return this.server.createAboutStatusRequest();
    }

    /**
     * @return {@link GeoserverAboutManifestRequest}
     */
    @Override
    public GeoserverAboutManifestRequest createAboutManifestRequest() {
        return this.server.createAboutManifestRequest();
    }

    /**
     * @return {@link GeoserverAboutSystemStatusRequest}
     */
    @Override
    public GeoserverAboutSystemStatusRequest createAboutSystemStatusRequest() {
        return this.server.createAboutSystemStatusRequest();
    }
}