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
package org.geosdi.geoplatform.gui.client.widget.wfs;

import org.geosdi.geoplatform.gui.client.puregwt.wfs.handler.GWFSPMapToolsHandler;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformContentPanel;
import org.geosdi.geoplatform.gui.client.widget.wfs.initializer.IFeatureMapInitializer;
import org.geosdi.geoplatform.gui.client.widget.wfs.viewport.WFSViewportUtility;
import org.geosdi.geoplatform.gui.configuration.map.client.geometry.BBoxClientInfo;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.gwtopenmaps.openlayers.client.Bounds;
import org.gwtopenmaps.openlayers.client.MapWidget;
import org.gwtopenmaps.openlayers.client.Projection;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.TRUE;
import static org.geosdi.geoplatform.gui.configuration.map.client.GPCoordinateReferenceSystem.EPSG_GOOGLE;
import static org.geosdi.geoplatform.gui.configuration.map.client.GPCoordinateReferenceSystem.GOOGLE_MERCATOR;

/**
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class FeatureMapWidget extends GeoPlatformContentPanel implements IFeatureMapWidget, GWFSPMapToolsHandler {

    private final GPEventBus bus;
    @Inject
    private MapWidget mapWidget;
    @Inject
    private IFeatureMapInitializer featureMapInitializer;

    /**
     * @param theBus
     */
    @Inject
    public FeatureMapWidget(GPEventBus theBus) {
        super(TRUE);
        checkArgument(theBus != null, "The Parameter bus must not be null.");
        this.bus = theBus;
        this.bus.addHandler(IFeatureMapWidget.TYPE, this);
        this.bus.addHandler(GWFSPMapToolsHandler.TYPE, this);
    }

    @Override
    public void addComponent() {
        this.featureMapInitializer.initMapWidget();
        super.add(this.mapWidget);
    }

    @Override
    public void initSize() {
    }

    @Override
    public void setPanelProperties() {
        super.setHeaderVisible(false);
    }

    @Override
    public void reset() {
        this.featureMapInitializer.resetMapWidget();
    }

    @Override
    public void bindLayerSchema() {
        this.featureMapInitializer.bindLayerSchema();
    }

    @Override
    public void updateSize() {
        this.mapWidget.getMap().updateSize();
    }

    @Override
    public void increaseWidth(int width) {
        super.setWidth(getWidth() + width);
        this.mapWidget.setWidth(String.valueOf(super.getWidth()));
        updateSize();
        super.layout();
    }

    @Override
    public void decreaseWidth() {
        super.setWidth(getWidth());
        this.mapWidget.setWidth(String.valueOf(super.getWidth()));
        updateSize();
        super.layout();
    }

    @Override
    public void increaseHeight(int height) {
        super.setHeight(getHeight() + height);
        this.mapWidget.setHeight(String.valueOf(super.getHeight()));
        updateSize();
        super.layout();
    }

    @Override
    public void decreaseHeight() {
        super.setHeight(getHeight());
        this.mapWidget.setHeight(String.valueOf(super.getHeight()));
        updateSize();
        super.layout();
    }

    @Override
    public void onZoomToMaxExtend(BBoxClientInfo bbox, String crs) {
        Bounds bounds = WFSViewportUtility.generateBoundsFromBBOX(bbox);
        if (!this.mapWidget.getMap().getProjection().equals(crs)) {
            if (this.mapWidget.getMap().getProjection().equals(GOOGLE_MERCATOR.getCode())) {
                bounds.transform(new Projection(crs), new Projection(EPSG_GOOGLE.getCode()));
            }
        }
        this.mapWidget.getMap().zoomToExtent(bounds);
    }

    @Override
    public void onZoom() {
        this.featureMapInitializer.zoomOnBounds();
    }

    protected void manageMapSize() {
        this.mapWidget.setHeight(String.valueOf(super.getHeight()));
        updateSize();
        super.layout();
    }
}