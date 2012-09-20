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
package org.geosdi.geoplatform.gui.factory.baselayer;

import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import org.geosdi.geoplatform.gui.global.enumeration.BaseLayerValue;
import org.gwtopenmaps.openlayers.client.Size;
import org.gwtopenmaps.openlayers.client.layer.Bing;
import org.gwtopenmaps.openlayers.client.layer.BingOptions;
import org.gwtopenmaps.openlayers.client.layer.BingType;
import org.gwtopenmaps.openlayers.client.layer.GoogleV3;
import org.gwtopenmaps.openlayers.client.layer.GoogleV3MapType;
import org.gwtopenmaps.openlayers.client.layer.GoogleV3Options;
import org.gwtopenmaps.openlayers.client.layer.Layer;
import org.gwtopenmaps.openlayers.client.layer.OSM;
import org.gwtopenmaps.openlayers.client.layer.OSMOptions;
import org.gwtopenmaps.openlayers.client.layer.TransitionEffect;
import org.gwtopenmaps.openlayers.client.layer.WMS;
import org.gwtopenmaps.openlayers.client.layer.WMSOptions;
import org.gwtopenmaps.openlayers.client.layer.WMSParams;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
class GPBaseLayerRepository {

    private final String bingKey = "Apd8EWF9Ls5tXmyHr22O"
            + "uL1ay4HRJtI4JG4jgluTDVaJdUXZV6lpSBpX-TwnoRDG";
    private EnumMap<BaseLayerValue, Layer> baseLayerMap = Maps.newEnumMap(
            BaseLayerValue.class);
    private static GPBaseLayerRepository instance;

    private GPBaseLayerRepository() {
        lookupBaseLayers();
    }

    public static GPBaseLayerRepository getInstance() {
        if (instance == null) {
            instance = new GPBaseLayerRepository();
        }

        return instance;
    }

    public Layer findBaseLayer(BaseLayerValue enumLayer) {
        return baseLayerMap.get(enumLayer);
    }

    /**
     * Return all Base Layers registered in the Repository
     *
     * @return Map<BaseLayerEnum, Layer>
     */
    public Map<BaseLayerValue, Layer> getAllBaseLayers() {
        return Collections.unmodifiableMap(baseLayerMap);
    }

    private void lookupBaseLayers() {
        baseLayerMap.put(BaseLayerValue.OPEN_STREET_MAP, createOSMBaseLayer());
        baseLayerMap.put(BaseLayerValue.GOOGLE_NORMAL,
                createGoogleNormalBaseLayer());
        baseLayerMap.put(BaseLayerValue.GOOGLE_SATELLITE,
                createGoogleSatelliteBaseLayer());
        baseLayerMap.put(BaseLayerValue.GOOGLE_HYBRID,
                createGoogleHybridBaseLayer());
        baseLayerMap.put(BaseLayerValue.BING_ROAD_LAYER,
                createBingRoadBaseLayer());
        baseLayerMap.put(BaseLayerValue.BING_HYBRID, createBingHybridBaseLayer());
        baseLayerMap.put(BaseLayerValue.BING_AERIAL, createBingAerialBaseLayer());
        baseLayerMap.put(BaseLayerValue.METACARTA, createMetacartaBaseLayer());
        baseLayerMap.put(BaseLayerValue.GEOSDI_BASE, createGeoSdiBaseLayer());
        baseLayerMap.put(BaseLayerValue.GEOSDI_NULL_BASE,
                createGeoSdiNullMapBaseLayer());
    }

    private Layer createOSMBaseLayer() {
        OSMOptions osmOption = new OSMOptions();
        Layer osm = OSM.Mapnik("OpenStreetMap", osmOption);
        osm.setIsBaseLayer(Boolean.TRUE);
        return osm;
    }

    private Layer createGoogleNormalBaseLayer() {
        GoogleV3Options option = new GoogleV3Options();
        option.setType(GoogleV3MapType.G_NORMAL_MAP);
        option.setSphericalMercator(Boolean.TRUE);
        option.setTransitionEffect(TransitionEffect.RESIZE);
        Layer google = new GoogleV3("Google Normal", option);
        google.setIsBaseLayer(Boolean.TRUE);

        return google;
    }

    private Layer createGeoSdiBaseLayer() {
        WMSParams wmsParams = new WMSParams();
        wmsParams.setFormat("image/png");
        wmsParams.setLayers("Mappa_di_Base");
        wmsParams.setStyles("");
        WMSOptions wmsLayerParams = new WMSOptions();

        wmsLayerParams.setTileSize(new Size(256, 256));
        Layer geoSdi = new WMS("geoSdi", "http://dpc.geosdi.org/geoserver/wms",
                wmsParams, wmsLayerParams);
        geoSdi.setIsBaseLayer(Boolean.TRUE);

        return geoSdi;
    }

    private Layer createGeoSdiNullMapBaseLayer() {
        WMSParams wmsParams = new WMSParams();
        wmsParams.setFormat("image/png");
        wmsParams.setLayers("StratiDiBase:nullMap");
        wmsParams.setStyles("");
        WMSOptions wmsLayerParams = new WMSOptions();
        wmsLayerParams.setTileSize(new Size(256, 256));
        WMS geoSdi = new WMS("geoSdi No Map",
                "http://dpc.geosdi.org/geoserver/wms",
                wmsParams, wmsLayerParams);
        geoSdi.setIsBaseLayer(Boolean.TRUE);

        return geoSdi;
    }

    private Layer createMetacartaBaseLayer() {
        WMSParams wmsParams = new WMSParams();
        wmsParams.setFormat("image/png");
        wmsParams.setLayers("basic");
        wmsParams.setStyles("");
        WMSOptions wmsLayerParams = new WMSOptions();
        wmsLayerParams.setTileSize(new Size(256, 256));
        WMS metacarta = new WMS("Metacarta",
                "http://labs.metacarta.com/wms/vmap0",
                wmsParams, wmsLayerParams);
        metacarta.setIsBaseLayer(Boolean.TRUE);

        return metacarta;
    }

    private Layer createBingRoadBaseLayer() {
        Bing road = new Bing(new BingOptions("Bing Road Layer", bingKey,
                BingType.ROAD));
        road.setIsBaseLayer(Boolean.TRUE);

        return road;
    }

    private Layer createGoogleSatelliteBaseLayer() {
        GoogleV3Options opSatellite = new GoogleV3Options();
        opSatellite.setType(GoogleV3MapType.G_SATELLITE_MAP);
        opSatellite.setSphericalMercator(Boolean.TRUE);
        opSatellite.setTransitionEffect(TransitionEffect.RESIZE);
        Layer satellite = new GoogleV3("Google Satellite", opSatellite);
        satellite.setIsBaseLayer(Boolean.TRUE);

        return satellite;
    }

    private Layer createGoogleHybridBaseLayer() {
        GoogleV3Options opHybrid = new GoogleV3Options();
        opHybrid.setType(GoogleV3MapType.G_HYBRID_MAP);
        opHybrid.setSphericalMercator(Boolean.TRUE);
        opHybrid.setTransitionEffect(TransitionEffect.RESIZE);
        Layer hybrid = new GoogleV3("Google Hybrid", opHybrid);
        hybrid.setIsBaseLayer(Boolean.TRUE);

        return hybrid;
    }

    private Layer createBingHybridBaseLayer() {
        Bing hybrid = new Bing(new BingOptions("Bing Hybrid Layer", bingKey,
                BingType.HYBRID));
        hybrid.setIsBaseLayer(Boolean.TRUE);

        return hybrid;
    }

    private Layer createBingAerialBaseLayer() {
        Bing aerial = new Bing(new BingOptions("Bing Aerial Layer", bingKey,
                BingType.AERIAL));
        aerial.setIsBaseLayer(Boolean.TRUE);

        return aerial;
    }
}
