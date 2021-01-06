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
package org.geosdi.geoplatform.gui.client.widget.map.routing;

import org.geosdi.geoplatform.gui.configuration.map.client.GPCoordinateReferenceSystem;
import org.geosdi.geoplatform.gui.impl.map.GeoPlatformBoxesWidget;
import org.geosdi.geoplatform.gui.impl.map.GeoPlatformMap;
import org.gwtopenmaps.openlayers.client.Bounds;
import org.gwtopenmaps.openlayers.client.Projection;
import org.gwtopenmaps.openlayers.client.layer.Boxes;
import org.gwtopenmaps.openlayers.client.marker.Box;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 */
public class RoutingBoxes extends GeoPlatformBoxesWidget {

    private Boxes layer;
    private Box marker;

    /**
     * @Constructor
     */
    public RoutingBoxes(GeoPlatformMap theGeoPlatformMap) {
        super(theGeoPlatformMap);
    }

    /**
     * Init Component
     */
    public void init() {
        this.layer = new Boxes("Geo-Platform Routing Boxes Layer");
    }

    /**
     * Add the layer to the Map
     *
     */
    @Override
    public void activate() {
        this.bounds = new Bounds(6.627, 35.492, 18.521, 47.092);
        if (geoPlatformMap.getMap().getProjection().equals(GPCoordinateReferenceSystem.GOOGLE_MERCATOR.getCode())) {
            this.marker = new Box(this.bounds.transform(
                    new Projection(GPCoordinateReferenceSystem.WGS_84.getCode()),
                    new Projection(GPCoordinateReferenceSystem.EPSG_GOOGLE.getCode())));
        } else {
            this.marker = new Box(this.bounds);
        }


        this.layer.addMarker(marker);

        this.marker.setZIndex(949);


        this.geoPlatformMap.getMap().addLayer(this.layer);
        this.geoPlatformMap.getMap().zoomToExtent(this.marker.getBounds());
    }

    /**
     * Remove Layer from Map
     *
     */
    @Override
    public void deactivate() {
        this.geoPlatformMap.getMap().removeLayer(this.layer);
    }
}
