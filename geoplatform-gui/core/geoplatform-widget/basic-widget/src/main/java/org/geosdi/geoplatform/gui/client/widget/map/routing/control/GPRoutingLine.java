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

import org.geosdi.geoplatform.gui.configuration.map.client.GPCoordinateReferenceSystem;
import org.geosdi.geoplatform.gui.impl.map.GeoPlatformMap;
import org.geosdi.geoplatform.gui.impl.map.control.GPRoutingControl;
import org.geosdi.geoplatform.gui.puregwt.routing.RoutingHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.routing.event.TraceRoutingLineEventHandler;
import org.gwtopenmaps.openlayers.client.Projection;
import org.gwtopenmaps.openlayers.client.feature.VectorFeature;
import org.gwtopenmaps.openlayers.client.geometry.Geometry;
import org.gwtopenmaps.openlayers.client.geometry.MultiLineString;
import org.gwtopenmaps.openlayers.client.layer.Vector;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 */
public class GPRoutingLine extends GPRoutingControl implements
        TraceRoutingLineEventHandler {

    /**
     * @param theLayer
     */
    public GPRoutingLine(Vector theLayer, GeoPlatformMap geoPlatformMap) {
        super(theLayer, geoPlatformMap);

        RoutingHandlerManager.addHandler(TraceRoutingLineEventHandler.TYPE,
                this);
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * org.geosdi.geoplatform.gui.impl.map.control.GPRoutingControl#createStyle()
     */
    @Override
    public void createStyle() {
        style.setStrokeColor("#2c2d99");
        style.setStrokeWidth(6);
        style.setStrokeOpacity(0.7);
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * org.geosdi.geoplatform.gui.puregwt.routing.event.TraceRoutingLineEventHandler#drawLine(java.lang.String)
     */
    @Override
    public void drawLine(String wkt) {
        if (this.feature != null) {
            layer.removeFeature(feature);
        }

        MultiLineString geometry = MultiLineString.narrowToMultiLineString(Geometry.fromWKT(wkt).getJSObject());
        if (geoPlatformMap.getMap().getProjection().equals(GPCoordinateReferenceSystem.GOOGLE_MERCATOR.getCode())) {
            geometry.transform(new Projection(GPCoordinateReferenceSystem.WGS_84.getCode()),
                    new Projection(GPCoordinateReferenceSystem.EPSG_GOOGLE.getCode()));
        }
        this.feature = new VectorFeature(geometry);
        this.feature.setStyle(style);
        this.layer.addFeature(feature);

        this.geoPlatformMap.getMap().zoomToExtent(geometry.getBounds());
    }

    public void removeLine() {
        if (feature != null) {
            layer.removeFeature(feature);
            feature = null;
        }
    }
}
