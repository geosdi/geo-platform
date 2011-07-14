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
package org.geosdi.geoplatform.gui.client.widget.map;


import org.geosdi.geoplatform.gui.client.widget.MapToolbar;
import org.geosdi.geoplatform.gui.client.widget.map.control.history.NavigationHistoryControl;
import org.geosdi.geoplatform.gui.client.widget.map.routing.GPRoutingManagerWidget;
import org.geosdi.geoplatform.gui.impl.map.GeoPlatformMap;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.gwtopenmaps.openlayers.client.Bounds;
import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.Map;
import org.gwtopenmaps.openlayers.client.MapOptions;
import org.gwtopenmaps.openlayers.client.MapUnits;
import org.gwtopenmaps.openlayers.client.MapWidget;
import org.gwtopenmaps.openlayers.client.Projection;
import org.gwtopenmaps.openlayers.client.control.DrawFeature;
import org.gwtopenmaps.openlayers.client.control.LayerSwitcher;
import org.gwtopenmaps.openlayers.client.control.Measure;
import org.gwtopenmaps.openlayers.client.control.MeasureOptions;
import org.gwtopenmaps.openlayers.client.control.ScaleLine;
import org.gwtopenmaps.openlayers.client.event.MeasureEvent;
import org.gwtopenmaps.openlayers.client.event.MeasureListener;
import org.gwtopenmaps.openlayers.client.feature.VectorFeature;
import org.gwtopenmaps.openlayers.client.handler.PathHandler;
import org.gwtopenmaps.openlayers.client.handler.PolygonHandler;
import org.gwtopenmaps.openlayers.client.layer.GMapType;
import org.gwtopenmaps.openlayers.client.layer.Google;
import org.gwtopenmaps.openlayers.client.layer.GoogleOptions;
import org.gwtopenmaps.openlayers.client.layer.Layer;
import org.gwtopenmaps.openlayers.client.layer.OSM;
import org.gwtopenmaps.openlayers.client.layer.OSMOptions;

import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.Timer;
import org.geosdi.geoplatform.gui.client.widget.scale.GPScaleWidget;
import org.geosdi.geoplatform.gui.configuration.map.client.geometry.BboxClientInfo;
import org.geosdi.geoplatform.gui.configuration.map.puregwt.MapHandlerManager;
import org.geosdi.geoplatform.gui.factory.map.GPApplicationMap;
import org.geosdi.geoplatform.gui.puregwt.featureinfo.event.GPFeatureInfoEvent;
import org.gwtopenmaps.openlayers.client.control.MousePosition;
import org.gwtopenmaps.openlayers.client.layer.TransitionEffect;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 * 
 */
public class MapLayoutWidget implements GeoPlatformMap {

    private MapWidget mapWidget;
    private MapOptions defaultMapOptions;
    private MapControlManager mapControl;
    private Map map;
    private Layer layer;
    private Layer osm;
    private GPRoutingManagerWidget routingWidget;
    private MapModel mapModel;
    private MapToolbar buttonBar;
    
    private Measure measure;
    private Measure measureArea;
    private boolean infoActive;
    private boolean measureActive;
    private boolean measureAreaActive;

    public MapLayoutWidget() {
        super();
        GPApplicationMap.setApplicationMap(this);
        this.createMapOption();
        this.mapModel = new MapModel(this);
        this.routingWidget = new GPRoutingManagerWidget(this);
        this.mapModel.addLayerChangedHandler();
    }

    private void createMapOption() {

        this.defaultMapOptions = new MapOptions();

        this.defaultMapOptions.setNumZoomLevels(18);

        this.defaultMapOptions.setProjection("EPSG:900913");
        this.defaultMapOptions.setDisplayProjection(new Projection("EPSG:4326"));
        this.defaultMapOptions.setUnits(MapUnits.METERS);

        this.defaultMapOptions.setMaxExtent(new Bounds(-20037508, -20037508,
                20037508, 20037508.34));
        this.defaultMapOptions.setMaxResolution(
                new Double(156543.0339).floatValue());

        initMapWidget(this.defaultMapOptions);
    }

    private void initMapWidget(MapOptions defaultMapOptions) {
        mapWidget = new MapWidget("100%", "100%", defaultMapOptions);
        this.map = mapWidget.getMap();
        this.map.addControl(new LayerSwitcher());
        this.map.addControl(new ScaleLine());
        this.map.addControl(new MousePosition());


        this.addMeasureControl();
        this.addMeasureAreaControl();

        this.createOSM();
        this.createBaseGoogleLayer();

        this.mapControl = new MapControlManager(this.map);
    }

    public void addMeasureControl() {

        MeasureOptions measOpts = new MeasureOptions();
        measOpts.setPersist(true);
        measOpts.setGeodesic(true);

        this.measure = new Measure(new PathHandler(), measOpts);

        this.map.addControl(measure);

        this.measure.addMeasureListener(new MeasureListener() {

            @Override
            public void onMeasure(MeasureEvent eventObject) {
                Info.display("Distance is: ", eventObject.getMeasure() + " "
                        + eventObject.getUnits());
            }
        });

    }

    public void addMeasureAreaControl() {

        MeasureOptions measOpts = new MeasureOptions();
        measOpts.setPersist(true);
        measOpts.setGeodesic(true);

        this.measureArea = new Measure(new PolygonHandler(), measOpts);

        this.map.addControl(measureArea);

        this.measureArea.addMeasureListener(new MeasureListener() {

            @Override
            public void onMeasure(MeasureEvent eventObject) {
                Info.display("Area is: ", eventObject.getMeasure() + " "
                        + eventObject.getUnits());
            }
        });

    }

    @Override
    public void activateInfo() {
        this.infoActive = true;
        MapHandlerManager.fireEvent(new GPFeatureInfoEvent(infoActive));
    }

    @Override
    public void deactivateInfo() {
        this.infoActive = false;
        MapHandlerManager.fireEvent(new GPFeatureInfoEvent(infoActive));
    }

    @Override
    public void activateMeasure() {
        measure.activate();
        this.measureActive = true;
    }

    @Override
    public void deactivateMeasure() {
        measure.deactivate();
        this.measureActive = false;
    }

    @Override
    public void activateMeasureArea() {
        measureArea.activate();
        this.measureAreaActive = true;
    }

    @Override
    public void deactivateMeasureArea() {
        measureArea.deactivate();
        this.measureAreaActive = false;
    }

    private void createOSM() {
        OSMOptions osmOption = new OSMOptions();
        // osmOption.setDisplayOutsideMaxExtent(true);
        // osmOption.setWrapDateLine(true);

        this.osm = OSM.Mapnik("OpenStreetMap", osmOption);
        this.osm.setIsBaseLayer(true);
        this.map.addLayer(osm);

        this.osm.setZIndex(-1);
    }

    private void createBaseGoogleLayer() {
        GoogleOptions option = new GoogleOptions();
        option.setType(GMapType.G_NORMAL_MAP);
        option.setSphericalMercator(true);
        option.setTransitionEffect(TransitionEffect.RESIZE);

        layer = new Google("Google Normal", option);
        layer.setIsBaseLayer(true);
        this.map.addLayer(layer);

        this.layer.setZIndex(-2);
    }

    /**
     * Add Map to the ContentPanel passed from Dispatcher
     *
     * @param center
     */
    public void onAddToCenterPanel() {
        LayoutManager.addComponentToCenter(mapWidget);

        setMapCenter();

        /** OPEN SCALE WIDGET **/
        if (!GPScaleWidget.isScaleWidgetEnabled()) {
            showScaleWidget();
        }
    }

    /**
     * Set center of the Map on Italy
     */
    public void setMapCenter() {
        LonLat center = new LonLat(13.375, 42.329);
        center.transform("EPSG:4326", map.getProjection());
        this.map.setCenter(center, 5);

        this.mapControl.clearNavigationHistory();
    }

    /**
     * Draw Feature on the Map
     *
     * @param wkt
     */
    public void drawFeatureOnMap(String wkt) {
        this.mapControl.drawFeatureOnMap(wkt);
    }

    /**
     * Erase all Features added to Vector Layer
     */
    public void eraseFeatures() {
        this.mapControl.eraseFeatures();
    }

    /**
     * Erase Single Feature from the Map
     *
     * @param vf
     */
    public void eraseFeature(VectorFeature vf) {
        this.mapControl.eraseFeature(vf);
    }

    /**
     * @return the map
     */
    @Override
    public Map getMap() {
        return map;
    }

    /**
     * @return the mapWidget
     */
    @Override
    public MapWidget getMapWidget() {
        return mapWidget;
    }

    /**
     * @return the mapControl
     */
    public MapControlManager getMapControl() {
        return mapControl;
    }

    /**
     * Update Map Size
     */
    public void updateMapSize() {
        this.map.updateSize();
        LayoutManager.getInstance().getCenter().layout();
    }

    /**
     * activate draw feature control on the map
     */
    @Override
    public void activateDrawFeature() {
        this.mapControl.activateDrawFeature();
    }

    /**
     * deactivate draw feature control on the map
     */
    @Override
    public void deactivateDrawFeature() {
        this.mapControl.deactivateDrawFeature();
    }

    @Override
    public void activateDrawPointFeature() {
        this.mapControl.activateDrawPointFeature();
    }

    @Override
    public void deactivateDrawPointFeature() {
        this.mapControl.deactivateDrawPointFeature();
    }

    public void redrawVectorLayer() {
        this.mapControl.redrawVectorLayer();
    }

    /**
     * @return the buttonBar
     */
    @Override
    public MapToolbar getButtonBar() {
        return buttonBar;
    }

    /**
     * @param buttonBar
     *            the buttonBar to set
     */
    public void setButtonBar(MapToolbar buttonBar) {
        this.buttonBar = buttonBar;
    }

    public DrawFeature getDrawPolygonFeature() {
        return this.mapControl.getDrawFeatureControl();
    }

    public DrawFeature getDrawPointFeature() {
        return this.mapControl.getDrawPointFeaureControl();
    }

    public DrawFeature getDrawLineFeature() {
        return this.mapControl.getDrawLineFeature().getControl();
    }

    @Override
    public void activateModifyFeature() {
        this.mapControl.activateModifyFeature();
    }

    @Override
    public void deactivateModifyFeature() {
        this.mapControl.deactivateModifyFeature();
    }

    public void activateFeatureOperation() {
        this.mapControl.activateFeatureOperation();
    }

    @Override
    public void deactivateFeatureOperation() {
        this.mapControl.deactivateFeatureOperation();
    }

    @Override
    public boolean isFeatureOperationEnable() {
        return this.mapControl.isFeatureOperationEnable();
    }

    @Override
    public boolean isModifyFeatureEnable() {
        return this.mapControl.isModifyFeatureEnable();
    }

    public int getFeaturesNumber() {
        return this.mapControl.getFeaturesNumber();
    }

    public void drawFeature(VectorFeature feature) {
        this.mapControl.drawFeature(feature);
    }

    /**
     * @return the Navigation History Control
     */
    public NavigationHistoryControl getNavigationHistory() {
        return this.mapControl.getNavigationHistory();
    }

    /**
     * Clear Map and Remove All Features
     */
    public void clearMap() {
        this.mapControl.eraseFeatures();
    }

    /**
     * @return the measure
     */
    public Measure getMeasure() {
        return measure;
    }

    /**
     * @return the measureArea
     */
    public Measure getMeasureArea() {
        return measureArea;
    }

    /**
     * @return the infoActive
     */
    @Override
    public boolean isInfoActive() {
        return infoActive;
    }

    /**
     * @return the measureActive
     */
    @Override
    public boolean isMeasureActive() {
        return measureActive;
    }

    /**
     * @return the measureAreaActive
     */
    public boolean isMeasureAreaActive() {
        return measureAreaActive;
    }

    /* (non-Javadoc)
     * @see org.geosdi.geoplatform.gui.impl.map.GeoPlatformEditor#activateDrawLineFeature()
     */
    @Override
    public void activateDrawLineFeature() {
        this.mapControl.activateDrawLineFeature();
    }

    /* (non-Javadoc)
     * @see org.geosdi.geoplatform.gui.impl.map.GeoPlatformEditor#deactivateDrawLineFeature()
     */
    @Override
    public void deactivateDrawLineFeature() {
        this.mapControl.deactivateDrawLineFeature();
    }

    /**
     * 
     * @param bbox
     */
    public void zoomToMaxExtend(BboxClientInfo bbox) {
        Bounds b = new Bounds(bbox.getLowerLeftX(), bbox.getLowerLeftY(),
                bbox.getUpperRightX(), bbox.getUpperRightY());
        b.transform(new Projection("EPSG:4326"),
                new Projection(map.getProjection()));

        this.map.zoomToExtent(b);
    }

    private void showScaleWidget() {

        Timer t = new Timer() {

            @Override
            public void run() {
                GPScaleWidget.display("Scale");
            }
        };
        t.schedule(3000);
    }
}
