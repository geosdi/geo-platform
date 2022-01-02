/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.store.styles;

import org.geosdi.geoplatform.connector.geoserver.GPGeoserverConnector;
import org.geosdi.geoplatform.connector.geoserver.request.styles.*;
import org.geosdi.geoplatform.connector.geoserver.styles.sld.*;
import org.geosdi.geoplatform.connector.store.layers.GeoserverLayersConnectorStore;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GeoserverStylesConnectorStore extends GeoserverLayersConnectorStore implements GPGeoserverStylesConnectorStore {

    /**
     * @param theServer
     */
    protected GeoserverStylesConnectorStore(GPGeoserverConnector theServer) {
        super(theServer);
    }

    /**
     * @return {@link GeoserverStylesRequest}
     */
    @Override
    public GeoserverStylesRequest loadStylesRequest() {
        return this.server.loadStylesRequest();
    }

    /**
     * @return {@link GeoserverStyleRequest}
     */
    @Override
    public GeoserverStyleRequest loadStyleRequest() {
        return this.server.loadStyleRequest();
    }

    /**
     * @return {@link GeoserverStyleSLDV100Request}
     */
    @Override
    public GeoserverStyleSLDV100Request loadStyleSLDV100Request() {
        return this.server.loadStyleSLDV100Request();
    }

    /**
     * @return {@link GeoserverCreateStyleRequest}
     */
    @Override
    public GeoserverCreateStyleRequest createStyleRequest() {
        return this.server.createStyleRequest();
    }

    /**
     * @return {@link GeoserverCreateStyleSLDV100Request}
     */
    @Override
    public GeoserverCreateStyleSLDV100Request createStyleSLDV100Request() {
        return this.server.createStyleSLDV100Request();
    }

    /**
     * @return {@link GeoserverCreateWorkspaceStyleRequest}
     */
    @Override
    public GeoserverCreateWorkspaceStyleRequest createWorkspaceStyleRequest() {
        return this.server.createWorkspaceStyleRequest();
    }

    /**
     * @return {@link GeoserverDeleteStyleRequest}
     */
    @Override
    public GeoserverDeleteStyleRequest deleteStyleRequest() {
        return this.server.deleteStyleRequest();
    }

    /**
     * @return {@link GeoserverDeleteWorkspaceStyleRequest}
     */
    @Override
    public GeoserverDeleteWorkspaceStyleRequest deleteWorkspaceStyleRequest() {
        return this.server.deleteWorkspaceStyleRequest();
    }

    /**
     * @return {@link GeoserverWorkspaceStylesRequest}
     */
    @Override
    public GeoserverWorkspaceStylesRequest loadWorkspaceStyles() {
        return this.server.loadWorkspaceStylesRequest();
    }

    /**
     * @return {@link GeoserverWorkspaceStyleRequest}
     */
    @Override
    public GeoserverWorkspaceStyleRequest loadWorkspaceStyle() {
        return this.server.loadWorkspaceStyleRequest();
    }

    /**
     * @return {@link GeoserverUpdateStyleRequest}
     */
    @Override
    public GeoserverUpdateStyleRequest updateStyleRequest() {
        return this.server.updateStyleRequest();
    }

    /**
     * @return {@link GeoserverUpdateStyleSLDV100Request}
     */
    @Override
    public GeoserverUpdateStyleSLDV100Request updateStyleSLDV100Request() {
        return this.server.updateStyleSLDV100Request();
    }

    /**
     * @return {@link GeoserverCreateStyleWithFileSLDRequest}
     */
    @Override
    public GeoserverCreateStyleWithFileSLDRequest createStyleWithFileSLDRequest() {
        return this.server.createStyleWithFileSLDRequest();
    }

    /**
     * @return {@link GeoserverUpdateStyleWithFileSLDRequest}
     */
    @Override
    public GeoserverUpdateStyleWithFileSLDRequest updateStyleWithFileSLDRequest() {
        return this.server.updateStyleWithFileSLDRequest();
    }
}