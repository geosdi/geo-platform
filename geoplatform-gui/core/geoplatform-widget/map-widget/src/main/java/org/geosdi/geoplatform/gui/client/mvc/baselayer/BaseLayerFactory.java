/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.client.mvc.baselayer;

import org.geosdi.geoplatform.gui.global.enumeration.BaseLayerEnum;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.EnumMap;
import java.util.List;
import org.geosdi.geoplatform.gui.client.Resources;
import org.gwtopenmaps.openlayers.client.Projection;
import org.gwtopenmaps.openlayers.client.Size;
import org.gwtopenmaps.openlayers.client.layer.*;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class BaseLayerFactory {

    public static final String bingKey = "Apd8EWF9Ls5tXmyHr22OuL1ay4HRJtI4JG4jgluTDVaJdUXZV6lpSBpX-TwnoRDG";
    private static EnumMap<BaseLayerEnum, GPBaseLayer> baseLayerMap = Maps.newEnumMap(BaseLayerEnum.class);

    static {
        initializeBaseLayerMap();
    }

    public static List<GPBaseLayer> getBaseLayerList() {
        return Lists.newArrayList(baseLayerMap.values());
    }

    private static void initializeBaseLayerMap() {
        baseLayerMap.put(BaseLayerEnum.OPEN_STREET_MAP, createOSMBaseLayer());
        baseLayerMap.put(BaseLayerEnum.GOOGLE_NORMAL, createGoogleNormalBaseLayer());
        baseLayerMap.put(BaseLayerEnum.GOOGLE_SATELLITE, createGoogleSatelliteBaseLayer());
        baseLayerMap.put(BaseLayerEnum.GOOGLE_HYBRID, createGoogleHybridBaseLayer());
        baseLayerMap.put(BaseLayerEnum.BING_ROAD_LAYER, createBingRoadBaseLayer());
        baseLayerMap.put(BaseLayerEnum.BING_HYBRID, createBingHybridBaseLayer());
        baseLayerMap.put(BaseLayerEnum.BING_AERIAL, createBingAerialBaseLayer());
        baseLayerMap.put(BaseLayerEnum.METACARTA, createMetacartaBaseLayer());
        baseLayerMap.put(BaseLayerEnum.GEOSDI_BASE, createGeoSdiBaseLayer());
        baseLayerMap.put(BaseLayerEnum.GEOSDI_NULL_BASE, createGeoSdiNullMapBaseLayer());
    }

    private static GPBaseLayer createOSMBaseLayer() {
        OSMOptions osmOption = new OSMOptions();
        Layer osm = OSM.Mapnik("OpenStreetMap", osmOption);
        osm.setIsBaseLayer(Boolean.TRUE);
        GPBaseLayer osmBaseLayer = new GPBaseLayer(osm, Resources.IMAGES.osm(),
                new Projection("EPSG:3857"), BaseLayerEnum.OPEN_STREET_MAP);
        return osmBaseLayer;
    }

    private static GPBaseLayer createGoogleNormalBaseLayer() {
        GoogleV3Options option = new GoogleV3Options();
        option.setType(GoogleV3MapType.G_NORMAL_MAP);
        option.setSphericalMercator(Boolean.TRUE);
        option.setTransitionEffect(TransitionEffect.RESIZE);
        Layer google = new GoogleV3("Google Normal", option);
        google.setIsBaseLayer(Boolean.TRUE);
        GPBaseLayer googleNormalBaseLayer = new GPBaseLayer(google, 
                Resources.IMAGES.googleNormal(), new Projection("EPSG:3857"),
                BaseLayerEnum.GOOGLE_NORMAL);
        return googleNormalBaseLayer;
    }

    private static GPBaseLayer createGeoSdiBaseLayer() {
        WMSParams wmsParams = new WMSParams();
        wmsParams.setFormat("image/png");
        wmsParams.setLayers("Mappa_di_Base");
        wmsParams.setStyles("");
        WMSOptions wmsLayerParams = new WMSOptions();
        wmsLayerParams.setIsBaseLayer(Boolean.TRUE);
        wmsLayerParams.setTileSize(new Size(256, 256));
        Layer geoSdi = new WMS("geoSdi", "http://dpc.geosdi.org/geoserver/wms",
                wmsParams, wmsLayerParams);
        geoSdi.setIsBaseLayer(Boolean.TRUE);
        GPBaseLayer geoSdiBaseLayer = new GPBaseLayer(geoSdi, Resources.IMAGES.DPC(),
                new Projection("EPSG:4326"), BaseLayerEnum.GEOSDI_BASE);
        return geoSdiBaseLayer;
    }

    private static GPBaseLayer createGeoSdiNullMapBaseLayer() {
        WMSParams wmsParams = new WMSParams();
        wmsParams.setFormat("image/png");
        wmsParams.setLayers("StratiDiBase:nullMap");
        wmsParams.setStyles("");
        WMSOptions wmsLayerParams = new WMSOptions();
        wmsLayerParams.setTileSize(new Size(256, 256));
        WMS geoSdi = new WMS("geoSdi No Map", "http://dpc.geosdi.org/geoserver/wms",
                wmsParams, wmsLayerParams);
        geoSdi.setIsBaseLayer(Boolean.TRUE);
        GPBaseLayer geoSdiNullMapBaseLayer = new GPBaseLayer(geoSdi, Resources.IMAGES.blank(),
                new Projection("EPSG:4326"), BaseLayerEnum.GEOSDI_NULL_BASE);
        return geoSdiNullMapBaseLayer;
    }

    private static GPBaseLayer createMetacartaBaseLayer() {
        WMSParams wmsParams = new WMSParams();
        wmsParams.setFormat("image/png");
        wmsParams.setLayers("basic");
        wmsParams.setStyles("");
        WMSOptions wmsLayerParams = new WMSOptions();
        wmsLayerParams.setTileSize(new Size(256, 256));
        WMS metacarta = new WMS("Metacarta", "http://labs.metacarta.com/wms/vmap0",
                wmsParams, wmsLayerParams);
        metacarta.setIsBaseLayer(Boolean.TRUE);
        GPBaseLayer metacartaBaseLayer = new GPBaseLayer(metacarta, Resources.IMAGES.metacartaVmap(),
                new Projection("EPSG:4326"), BaseLayerEnum.METACARTA);
        return metacartaBaseLayer;
    }

    private static GPBaseLayer createBingRoadBaseLayer() {
        Bing road = new Bing(new BingOptions("Bing Road Layer", bingKey,
                BingType.ROAD));
        road.setIsBaseLayer(Boolean.TRUE);
        GPBaseLayer bingRoadBaseLayer = new GPBaseLayer(road, Resources.IMAGES.bingRoad(),
                new Projection("EPSG:3857"), BaseLayerEnum.BING_ROAD_LAYER);
        return bingRoadBaseLayer;
    }

    private static GPBaseLayer createGoogleSatelliteBaseLayer() {
        GoogleV3Options opSatellite = new GoogleV3Options();
        opSatellite.setType(GoogleV3MapType.G_SATELLITE_MAP);
        opSatellite.setSphericalMercator(Boolean.TRUE);
        opSatellite.setTransitionEffect(TransitionEffect.RESIZE);
        Layer satellite = new GoogleV3("Google Satellite", opSatellite);
        satellite.setIsBaseLayer(Boolean.TRUE);
        GPBaseLayer googleSatelliteBaseLayer = new GPBaseLayer(satellite, Resources.IMAGES.googleSatellite(),
                new Projection("EPSG:3857"), BaseLayerEnum.GOOGLE_SATELLITE);
        return googleSatelliteBaseLayer;
    }

    private static GPBaseLayer createGoogleHybridBaseLayer() {
        GoogleV3Options opHybrid = new GoogleV3Options();
        opHybrid.setType(GoogleV3MapType.G_HYBRID_MAP);
        opHybrid.setSphericalMercator(Boolean.TRUE);
        opHybrid.setTransitionEffect(TransitionEffect.RESIZE);
        Layer hybrid = new GoogleV3("Google Hybrid", opHybrid);
        hybrid.setIsBaseLayer(Boolean.TRUE);
        GPBaseLayer googleHybridBaseLayer = new GPBaseLayer(hybrid, Resources.IMAGES.googleHybrid(),
                new Projection("EPSG:3857"), BaseLayerEnum.GOOGLE_HYBRID);
        return googleHybridBaseLayer;
    }

    private static GPBaseLayer createBingHybridBaseLayer() {
        Bing hybrid = new Bing(new BingOptions("Bing Hybrid Layer", bingKey,
                BingType.HYBRID));
        hybrid.setIsBaseLayer(Boolean.TRUE);
        GPBaseLayer bingHybridBaseLayer = new GPBaseLayer(hybrid, Resources.IMAGES.bingHybrid(),
                new Projection("EPSG:3857"), BaseLayerEnum.BING_HYBRID);
        return bingHybridBaseLayer;
    }

    private static GPBaseLayer createBingAerialBaseLayer() {
        Bing aerial = new Bing(new BingOptions("Bing Aerial Layer", bingKey,
                BingType.AERIAL));
        aerial.setIsBaseLayer(Boolean.TRUE);
        GPBaseLayer bingAerialBaseLayer = new GPBaseLayer(aerial, Resources.IMAGES.bingAerial(),
                new Projection("EPSG:3857"), BaseLayerEnum.BING_AERIAL);
        return bingAerialBaseLayer;
    }

    public static GPBaseLayer getGPBaseLayer(BaseLayerEnum baseLayerEnum) {
        return baseLayerMap.get(baseLayerEnum);
    }
}
