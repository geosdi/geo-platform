/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.gui.factory.map;

import org.gwtopenmaps.openlayers.client.*;
import org.gwtopenmaps.openlayers.client.layer.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 * 
 */
public class DefaultMapFactory implements GeoPlatformMapFactory {

    public static final String bingKey = "Apd8EWF9Ls5tXmyHr22OuL1ay4HRJtI4JG4jgluTDVaJdUXZV6lpSBpX-TwnoRDG";

    /**
     * (non-Javadoc)
     * 
     * @see org.geosdi.geoplatform.gui.factory.GeoPlatformMapFactory#createMap(java.lang.String, java.lang.String)
     */
    @Override
    public MapWidget createMap(String width, String height) {
        return new MapWidget(width, height);
    }

    /**
     * (non-Javadoc)
     * 
     * @see org.geosdi.geoplatform.gui.factory.GeoPlatformMapFactory#createMap(java.lang.String, java.lang.String, org.gwtopenmaps.openlayers.client.MapOptions)
     */
    @Override
    public MapWidget createMap(String width, String height, MapOptions options) {
        return new MapWidget(width, height, options);
    }

    @Override
    public MapWidget createMap(String width, String height, GPBaseLayer baseLayer) {
        MapOptions defaultMapOptions = new MapOptions();

        defaultMapOptions.setNumZoomLevels(18);

        defaultMapOptions.setProjection("EPSG:900913");
        defaultMapOptions.setDisplayProjection(new Projection("EPSG:4326"));
        defaultMapOptions.setUnits(MapUnits.METERS);

        defaultMapOptions.setMaxExtent(new Bounds(-20037508, -20037508,
                20037508, 20037508.34));
        defaultMapOptions.setMaxResolution(
                new Double(156543.0339).floatValue());

        MapWidget mapWidget = new MapWidget(width, height, defaultMapOptions);

        Layer layer = null;

        switch (baseLayer) {
            case OPENSTREET_MAP:
                layer = createOSM();
                break;
            case GOOGLE_NORMAL:
                layer = createGoogleNormal();
                break;
            case BING_ROAD:
                layer = createBingRoad();
                break;
            default:
                layer = createOSM();
        }

        mapWidget.getMap().addLayer(layer);

        return mapWidget;
    }

    private Layer createOSM() {
        OSMOptions osmOption = new OSMOptions();

        Layer osm = OSM.Mapnik("OpenStreetMap", osmOption);
        osm.setIsBaseLayer(true);

        return osm;
    }

    private Layer createGoogleNormal() {
        GoogleV3Options option = new GoogleV3Options();
        option.setType(GoogleV3MapType.G_NORMAL_MAP);
        option.setSphericalMercator(true);
        option.setTransitionEffect(TransitionEffect.RESIZE);

        Layer google = new GoogleV3("Google Normal", option);
        google.setIsBaseLayer(true);

        return google;
    }

    private Layer createBingRoad() {
        Bing road = new Bing(new BingOptions("Bing Road Layer",
                bingKey, BingType.ROAD));
        road.setIsBaseLayer(true);

        return road;
    }
}
