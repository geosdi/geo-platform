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
package org.geosdi.geoplatform.gui.client.widget.map.routing.control;

import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.map.GeoPlatformBoxesWidget;
import org.geosdi.geoplatform.gui.impl.map.GeoPlatformMap;
import org.geosdi.geoplatform.gui.model.IGeoPlatformLocation;
import org.geosdi.geoplatform.gui.puregwt.routing.RoutingHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.routing.event.RemoveStartRoutingPointEventHandler;
import org.geosdi.geoplatform.gui.puregwt.routing.event.StartRoutingPointEventHandler;
import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.feature.VectorFeature;
import org.gwtopenmaps.openlayers.client.geometry.Point;
import org.gwtopenmaps.openlayers.client.layer.Vector;

import com.google.gwt.core.client.GWT;
import org.geosdi.geoplatform.gui.client.i18n.BasicWidgetConstants;
import org.geosdi.geoplatform.gui.configuration.map.client.GPCoordinateReferenceSystem;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 */
public class GPRoutingStartPoint extends GenericRoutingPoint implements
        StartRoutingPointEventHandler, RemoveStartRoutingPointEventHandler {

    /**
     * @param theLayer
     */
    public GPRoutingStartPoint(Vector theLayer,
            GeoPlatformBoxesWidget boxesWidget, GeoPlatformMap geoPlatformMap) {
        super(theLayer, boxesWidget, geoPlatformMap);
        
        RoutingHandlerManager.addHandler(StartRoutingPointEventHandler.TYPE,
                this);
        RoutingHandlerManager.addHandler(
                RemoveStartRoutingPointEventHandler.TYPE, this);
    }

    /**
     *
     */
    @Override
    public void setIconStyle() {
        style.setExternalGraphic(GWT.getModuleBaseURL()
                + "/gp-images/start.png");
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * org.geosdi.geoplatform.gui.puregwt.routing.event.StartRoutingPointEventHandler#drawFeature(org.geosdi.geoplatform.gui.model.IGeoPlatformLocation)
     */
    @Override
    public void drawFeature(IGeoPlatformLocation location) {
        LonLat ll = new LonLat(location.getLon(), location.getLat());
        
        if (geoPlatformMap.getMap().getProjection().equals(GPCoordinateReferenceSystem.GOOGLE_MERCATOR.getCode())) {
            ll.transform(GPCoordinateReferenceSystem.WGS_84.getCode(), GPCoordinateReferenceSystem.EPSG_GOOGLE.getCode());
        }
        if (!this.boxesWidget.containsLonLat(ll)) {
            GeoPlatformMessage.errorMessage(BasicWidgetConstants.INSTANCE.GPRoutingStartPoint_errorMessageTitleText(),
                    BasicWidgetConstants.INSTANCE.GPRoutingStartPoint_errorMessageBodyText());
            return;
        }
        
        if (feature != null) {
            layer.removeFeature(feature);
        }
        
        Point p = new Point(ll.lon(), ll.lat());
        feature = new VectorFeature(p);
        feature.setStyle(style);
        layer.addFeature(feature);
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * org.geosdi.geoplatform.gui.puregwt.routing.event.RemoveStartRoutingPointEventHandler#removePoint()
     */
    @Override
    public void removePoint() {
        if (feature != null) {
            layer.removeFeature(feature);
            feature = null;
        }
        this.line.removeLine();
    }
}
