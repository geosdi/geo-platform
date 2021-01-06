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
package org.geosdi.geoplatform.gui.client.widget.components.filters.spatial;

import javax.inject.Inject;
import org.geosdi.geoplatform.gui.client.config.CatalogSpatialFilter;
import org.geosdi.geoplatform.gui.client.config.provider.CatalogMapMoveListenerProvider.CatalogMapExtentReprojector;
import org.geosdi.geoplatform.gui.client.puregwt.event.CatalogBBoxChangeEvent;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformContentPanel;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.gwtopenmaps.openlayers.client.Bounds;
import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.MapWidget;
import org.gwtopenmaps.openlayers.client.Projection;
import org.gwtopenmaps.openlayers.client.event.MapMoveEndListener;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@CatalogSpatialFilter
public class CatalogMapWidget extends GeoPlatformContentPanel {

    private MapWidget mapWidget;
    private LonLat center;
    private MapMoveEndListener listener;
    private GPEventBus bus;
    private CatalogBBoxChangeEvent event = new CatalogBBoxChangeEvent();

    @Inject
    public CatalogMapWidget(MapWidget theMapWidget, LonLat theCenter,
            MapMoveEndListener theListener, GPEventBus theBus) {

        super(true);
        this.mapWidget = theMapWidget;
        this.center = theCenter;
        this.listener = theListener;
        this.bus = theBus;
    }

    @Override
    public void addComponent() {
        this.initMapWidget();

        super.add(this.mapWidget);
    }

    @Override
    public void initSize() {
    }

    @Override
    public void setPanelProperties() {
        setHeaderVisible(false);
    }

    private void initMapWidget() {
        this.mapWidget.getMap().setCenter(this.center, 4);
    }

    @Override
    public void reset() {
        this.initMapWidget();
    }

    /**
     * Add {@link MapMoveEndListener} Listener to the Map
     */
    protected void addMapMoveListener() {
        this.mapWidget.getMap().addMapMoveEndListener(this.listener);
        this.fireCatalogBBoxChangeEvent(CatalogMapExtentReprojector.reprojects(
                new Projection(this.mapWidget.getMap().getProjection()),
                this.mapWidget.getMap().getExtent()));
    }

    /**
     * Remove {@link MapMoveEndListener} Listener from the Map
     */
    protected void removeMapMoveListener() {
        this.mapWidget.getMap().removeListener(this.listener);
        this.fireCatalogBBoxChangeEvent(null);
    }

    protected void fireCatalogBBoxChangeEvent(Bounds extent) {
        this.event.bind(extent);
        this.bus.fireEvent(event);
    }
}
