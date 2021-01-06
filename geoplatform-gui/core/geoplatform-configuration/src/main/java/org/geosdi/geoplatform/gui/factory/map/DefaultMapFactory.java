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
package org.geosdi.geoplatform.gui.factory.map;

import org.gwtopenmaps.openlayers.client.*;
import org.gwtopenmaps.openlayers.client.layer.Layer;

import static org.geosdi.geoplatform.gui.configuration.map.client.GPCoordinateReferenceSystem.GOOGLE_MERCATOR;
import static org.geosdi.geoplatform.gui.configuration.map.client.GPCoordinateReferenceSystem.WGS_84;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class DefaultMapFactory implements GeoPlatformMapFactory {

    /**
     * (non-Javadoc)
     *
     * @param width
     * @param height
     * @return
     * @see {@link GeoPlatformMapFactory#createMap(java.lang.String, java.lang.String)}
     */
    @Override
    public MapWidget createMap(String width, String height) {
        return new MapWidget(width, height);
    }

    /**
     * (non-Javadoc)
     *
     * @param width
     * @param height
     * @param options
     * @return
     * @see {@link GeoPlatformMapFactory#createMap(java.lang.String, java.lang.String, org.gwtopenmaps.openlayers.client.MapOptions)}
     */
    @Override
    public MapWidget createMap(String width, String height, MapOptions options) {
        return new MapWidget(width, height, options);
    }

    /**
     * @param width
     * @param height
     * @param gwtOlBaseLayer
     * @return {@link MapWidget}
     */
    @Override
    public MapWidget createMap(String width, String height, Layer gwtOlBaseLayer) {
        MapOptions defaultMapOptions = new MapOptions();
        defaultMapOptions.setNumZoomLevels(30);
        defaultMapOptions.setProjection(GOOGLE_MERCATOR.getCode());
        defaultMapOptions.setDisplayProjection(new Projection(WGS_84.getCode()));
        defaultMapOptions.setUnits(MapUnits.METERS);
        defaultMapOptions.setMaxExtent(new Bounds(-20037508, -20037508,
                20037508, 20037508.34));
        defaultMapOptions.setMaxResolution(156543.0339F);
        MapWidget mapWidget = new MapWidget(width, height, defaultMapOptions);
        mapWidget.getMap().addLayer(gwtOlBaseLayer);
        return mapWidget;
    }

    /**
     * @param width
     * @param height
     * @param gwtOLBaseLayer
     * @param zIndex
     * @return {@link MapWidget}
     */
    @Override
    public MapWidget createMap(String width, String height, Layer gwtOLBaseLayer, int zIndex) {
        MapOptions defaultMapOptions = new MapOptions();
        defaultMapOptions.setNumZoomLevels(30);
        defaultMapOptions.setProjection(GOOGLE_MERCATOR.getCode());
        defaultMapOptions.setDisplayProjection(new Projection(WGS_84.getCode()));
        defaultMapOptions.setUnits(MapUnits.METERS);
        defaultMapOptions.setMaxExtent(new Bounds(-20037508, -20037508,
                20037508, 20037508.34));
        defaultMapOptions.setMaxResolution(156543.0339F);
        MapWidget mapWidget = new MapWidget(width, height, defaultMapOptions);
        mapWidget.getMap().addLayer(gwtOLBaseLayer);
        gwtOLBaseLayer.setZIndex(zIndex);
        return mapWidget;
    }
}