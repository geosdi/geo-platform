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
package org.geosdi.geoplatform.gui.factory.baselayer;

import com.google.common.collect.Maps;
import org.geosdi.geoplatform.gui.configuration.map.client.GPCoordinateReferenceSystem;
import org.geosdi.geoplatform.gui.global.enumeration.BaseLayerValue;
import org.gwtopenmaps.openlayers.client.OpenLayers;
import org.gwtopenmaps.openlayers.client.Size;
import org.gwtopenmaps.openlayers.client.layer.*;
import org.gwtopenmaps.openlayers.client.protocol.ProtocolType;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 * see {@link org.geosdi.geoplatform.gui.baselayer.store.GPBaseLayerStore}
 */
@Deprecated
class GPBaseLayerRepository {

    private final String bingKey = "ApTJzdkyN1DdFKkRAE6QIDtzihNaf6IWJsT-nQ_2eMoO4PN__0Tzhl2-WgJtXFSp";
    private EnumMap<BaseLayerValue, GPBaseLayerCreator> baseLayerMap = Maps.
            <BaseLayerValue, GPBaseLayerCreator>newEnumMap(BaseLayerValue.class);
    private static GPBaseLayerRepository instance;
    public final static double[] baseMapResolutions = {156543.03390625d, 78271.516953125d,
            39135.7584765625d, 19567.87923828125d, 9783.939619140625d,
            4891.9698095703125d, 2445.9849047851562d, 1222.9924523925781d,
            611.4962261962891d, 305.74811309814453d, 152.87405654907226d,
            76.43702827453613d, 38.218514137268066d, 19.109257068634033d,
            9.554628534317017d, 4.777314267158508d, 2.388657133579254d,
            1.194328566789627d, 0.5971642833948135d, 0.29858214169740677d,
            0.14929107084870338d, 0.07464553542435169d, 0.037322767712175846d,
            0.018661383856087923d, 0.009330691928043961d,
            0.004665345964021981d, 0.0023326729820109904d,
            0.0011663364910054952d, 5.831682455027476E-4d,
            2.915841227513738E-4d, 1.457920613756869E-4d};

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
        return (baseLayerMap.get(enumLayer) != null)
                ? baseLayerMap.get(enumLayer).createBaseLayer() : null;
    }

    /**
     * Return all Base Layers registered in the Repository
     *
     * @return Map<BaseLayerEnum, GPBaseLayerCreator>
     */
    public Map<BaseLayerValue, GPBaseLayerCreator> getAllBaseLayers() {
        return Collections.unmodifiableMap(baseLayerMap);
    }

    private void lookupBaseLayers() {
        baseLayerMap.put(BaseLayerValue.OPEN_STREET_MAP,
                new GPBaseLayerCreator() {

                    @Override
                    public Layer createBaseLayer() {
                        return createOSMBaseLayer();
                    }

                });
        baseLayerMap.put(BaseLayerValue.MAP_QUEST_OSM, new GPBaseLayerCreator() {

            @Override
            public Layer createBaseLayer() {
                return createMapQuestOSMBaseLayer();
            }

        });
        baseLayerMap.put(BaseLayerValue.GOOGLE_NORMAL,
                new GPBaseLayerCreator() {

                    @Override
                    public Layer createBaseLayer() {
                        return createGoogleNormalBaseLayer();
                    }

                });
        baseLayerMap.put(BaseLayerValue.GOOGLE_SATELLITE,
                new GPBaseLayerCreator() {

                    @Override
                    public Layer createBaseLayer() {
                        return createGoogleSatelliteBaseLayer();
                    }

                });
        baseLayerMap.put(BaseLayerValue.GOOGLE_HYBRID,
                new GPBaseLayerCreator() {

                    @Override
                    public Layer createBaseLayer() {
                        return createGoogleHybridBaseLayer();
                    }

                });
        baseLayerMap.put(BaseLayerValue.BING_ROAD_LAYER,
                new GPBaseLayerCreator() {

                    @Override
                    public Layer createBaseLayer() {
                        return createBingRoadBaseLayer();
                    }

                });
        baseLayerMap.put(BaseLayerValue.BING_HYBRID, new GPBaseLayerCreator() {

            @Override
            public Layer createBaseLayer() {
                return createBingHybridBaseLayer();
            }

        });
        baseLayerMap.put(BaseLayerValue.BING_AERIAL, new GPBaseLayerCreator() {

            @Override
            public Layer createBaseLayer() {
                return createBingAerialBaseLayer();
            }

        });
        baseLayerMap.put(BaseLayerValue.METACARTA, new GPBaseLayerCreator() {

            @Override
            public Layer createBaseLayer() {
                return createMetacartaBaseLayer();
            }

        });
        baseLayerMap.put(BaseLayerValue.GEOSDI_BASE, new GPBaseLayerCreator() {

            @Override
            public Layer createBaseLayer() {
                return createGeoSdiBaseLayer();
            }

        });
        baseLayerMap.put(BaseLayerValue.GEOSDI_NULL_BASE,
                new GPBaseLayerCreator() {

                    @Override
                    public Layer createBaseLayer() {
                        return createGeoSdiNullMapBaseLayer();
                    }

                });

        baseLayerMap.put(BaseLayerValue.EMPTY,
                new GPBaseLayerCreator() {

                    @Override
                    public Layer createBaseLayer() {
                        return createGeoSdiEmptyMapBaseLayer();
                    }

                });
    }

    private Layer createOSMBaseLayer() {
        OSMOptions osmOption = new OSMOptions();
        osmOption.setProjection(
                GPCoordinateReferenceSystem.GOOGLE_MERCATOR.getCode());
        osmOption.setResolutions(baseMapResolutions);
        Layer osm = OSM.Mapnik("OpenStreetMap", osmOption);
//        Layer osm = OSM.THIS("OpenStreetMap", OpenLayers.getProxyHost()
//                + "http://tile.openstreetmap.org/${z}/${x}/${y}.png", osmOption);
        osm.setIsBaseLayer(Boolean.TRUE);
        return osm;
    }

    private Layer createGeoSdiBaseLayer() {
        WMSParams wmsParams = new WMSParams();
        wmsParams.setFormat("image/png");
        wmsParams.setLayers("itercampania:Ortofoto Regione Campania 2014");
        wmsParams.setStyles("");
        WMSOptions wmsLayerParams = new WMSOptions();
        wmsLayerParams.setResolutions(baseMapResolutions);
        wmsLayerParams.setProjection(
                GPCoordinateReferenceSystem.GOOGLE_MERCATOR.getCode());
        wmsLayerParams.setTileSize(new Size(256, 256));
        Layer geoSdi = new WMS("iterCampania", "https://iterservice.regione.campania.it/geowebcache/service/wms",
                wmsParams, wmsLayerParams);
        geoSdi.setIsBaseLayer(Boolean.TRUE);
        return geoSdi;
    }

    private Layer createMapQuestOSMBaseLayer() {
        OSMOptions defaultMapOptions = new OSMOptions();
        defaultMapOptions.setProjection(GPCoordinateReferenceSystem.GOOGLE_MERCATOR.getCode());
        defaultMapOptions.setIsBaseLayer(true);
        defaultMapOptions.crossOriginFix();
        defaultMapOptions.setTileSize(new Size(256, 256));
        defaultMapOptions.setResolutions(baseMapResolutions);
        Layer mapQuestOSMBaseLayer = OSM.THIS("MapQuest OSM", OpenLayers.getProxyHost()
                        + "https://a.tiles.mapbox.com/v4/mapquest.streets-mb/${z}/${x}/${y}.png?access_token=pk.eyJ1IjoibWFwcXVlc3QiLCJhIjoiY2Q2N2RlMmNhY2NiZTRkMzlmZjJmZDk0NWU0ZGJlNTMifQ.mPRiEubbajc6a5y9ISgydg",
                defaultMapOptions);
        mapQuestOSMBaseLayer.setIsBaseLayer(Boolean.TRUE);
        return mapQuestOSMBaseLayer;
    }

    private Layer createGeoSdiNullMapBaseLayer() {
        WMSParams wmsParams = new WMSParams();
        wmsParams.setFormat("image/png");
        wmsParams.setLayers("StratiDiBase:nullMap");
        wmsParams.setStyles("");
        WMSOptions wmsLayerParams = new WMSOptions();
//        wmsLayerParams.setResolutions(baseMapResolutions);
        wmsLayerParams.setProjection(
                GPCoordinateReferenceSystem.WGS_84.getCode());
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
//        wmsLayerParams.setResolutions(baseMapResolutions);
        wmsLayerParams.setProjection(
                GPCoordinateReferenceSystem.WGS_84.getCode());
        wmsLayerParams.setTileSize(new Size(256, 256));
        WMS metacarta = new WMS("Metacarta",
                "http://vmap0.tiles.osgeo.org/wms/vmap0",
                wmsParams, wmsLayerParams);
        metacarta.setIsBaseLayer(Boolean.TRUE);

        return metacarta;
    }

    private Layer createGoogleNormalBaseLayer() {
        GoogleV3Options option = new GoogleV3Options();
        option.setType(GoogleV3MapType.G_NORMAL_MAP);
        option.setSphericalMercator(Boolean.TRUE);
        option.setTransitionEffect(TransitionEffect.RESIZE);
        option.setProjection(
                GPCoordinateReferenceSystem.GOOGLE_MERCATOR.getCode());
        option.setResolutions(baseMapResolutions);
        Layer google = new GoogleV3("Google Normal", option);
        google.setIsBaseLayer(Boolean.TRUE);

        return google;
    }

    private Layer createGoogleSatelliteBaseLayer() {
        GoogleV3Options opSatellite = new GoogleV3Options();
        opSatellite.setType(GoogleV3MapType.G_SATELLITE_MAP);
        opSatellite.setSphericalMercator(Boolean.TRUE);
        opSatellite.setTransitionEffect(TransitionEffect.RESIZE);
        opSatellite.setProjection(
                GPCoordinateReferenceSystem.GOOGLE_MERCATOR.getCode());
        opSatellite.setResolutions(baseMapResolutions);
        Layer satellite = new GoogleV3("Google Satellite", opSatellite);
        satellite.setIsBaseLayer(Boolean.TRUE);

        return satellite;
    }

    private Layer createGoogleHybridBaseLayer() {
        GoogleV3Options opHybrid = new GoogleV3Options();
        opHybrid.setType(GoogleV3MapType.G_HYBRID_MAP);
        opHybrid.setSphericalMercator(Boolean.TRUE);
        opHybrid.setTransitionEffect(TransitionEffect.RESIZE);
        opHybrid.setProjection(
                GPCoordinateReferenceSystem.GOOGLE_MERCATOR.getCode());
        opHybrid.setResolutions(baseMapResolutions);
        Layer hybrid = new GoogleV3("Google Hybrid", opHybrid);
        hybrid.setIsBaseLayer(Boolean.TRUE);

        return hybrid;
    }

    private Layer createBingRoadBaseLayer() {
        BingOptions bingOption = new BingOptions("Bing Road Layer", bingKey,
                BingType.ROAD);
        bingOption.setProtocol(ProtocolType.HTTPS);
        bingOption.setProjection(
                GPCoordinateReferenceSystem.GOOGLE_MERCATOR.getCode());
        bingOption.setResolutions(baseMapResolutions);
        Bing road = new Bing(bingOption);
        road.setIsBaseLayer(Boolean.TRUE);

        return road;
    }

    private Layer createBingHybridBaseLayer() {
        BingOptions bingOption = new BingOptions("Bing Hybrid Layer", bingKey,
                BingType.HYBRID);
        bingOption.setProtocol(ProtocolType.HTTPS);
        bingOption.setProjection(
                GPCoordinateReferenceSystem.GOOGLE_MERCATOR.getCode());
        bingOption.setResolutions(baseMapResolutions);
        Bing hybrid = new Bing(bingOption);
        hybrid.setIsBaseLayer(Boolean.TRUE);

        return hybrid;
    }

    private Layer createBingAerialBaseLayer() {
        BingOptions bingOption = new BingOptions("Bing Aerial Layer", bingKey,
                BingType.AERIAL);
        bingOption.setProtocol(ProtocolType.HTTPS);
        bingOption.setProjection(
                GPCoordinateReferenceSystem.GOOGLE_MERCATOR.getCode());
        bingOption.setResolutions(baseMapResolutions);
        Bing aerial = new Bing(bingOption);
        aerial.setIsBaseLayer(Boolean.TRUE);

        return aerial;
    }

    private Layer createGeoSdiEmptyMapBaseLayer() {
        //And now lets create an EmptyLayer and add it to the map.
        EmptyLayer.Options emptyLayerOptions = new EmptyLayer.Options();
        emptyLayerOptions.setAttribution("EmptyLayer (c) geoSDI"); //lets set some copyright msg as attribution
        emptyLayerOptions.setIsBaseLayer(true); //make it a baselayer.
        emptyLayerOptions.setProjection(GPCoordinateReferenceSystem.WGS_84.getCode());
//        emptyLayerOptions.setResolutions(baseMapResolutions);
        EmptyLayer emptyLayer = new EmptyLayer("Empty layer", emptyLayerOptions);
        return emptyLayer;
    }

}
