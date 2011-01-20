/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-plartform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2010 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import java.util.Collections;
import java.util.List;

import org.geosdi.geoplatform.gui.client.widget.ButtonBar;
import org.geosdi.geoplatform.gui.client.widget.map.control.history.NavigationHistoryControl;
import org.geosdi.geoplatform.gui.configuration.GenericClientTool;
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
import org.gwtopenmaps.openlayers.client.feature.VectorFeature;
import org.gwtopenmaps.openlayers.client.layer.GMapType;
import org.gwtopenmaps.openlayers.client.layer.Google;
import org.gwtopenmaps.openlayers.client.layer.GoogleOptions;
import org.gwtopenmaps.openlayers.client.layer.Layer;
import org.gwtopenmaps.openlayers.client.layer.OSM;
import org.gwtopenmaps.openlayers.client.layer.OSMOptions;

/**
 * @author giuseppe
 * 
 */
public class MapLayoutWidget implements GeoPlatformMap {

	private MapWidget mapWidget;
	private MapOptions defaultMapOptions;
	private MapControlManager mapControl;
	private Map map;
	private Layer layer;
	private Layer osm;
	
	private MapModel mapModel;

	private ButtonBar buttonBar;

	private List<GenericClientTool> tools;

	public MapLayoutWidget() {
		super();
		this.createMapOption();
		this.mapModel = new MapModel(this);
		this.mapModel.addLayerChangedHandler();
	}

	private void createMapOption() {
		this.defaultMapOptions = new MapOptions();

		this.defaultMapOptions.setNumZoomLevels(18);

		this.defaultMapOptions.setProjection("EPSG:900913");
		this.defaultMapOptions
				.setDisplayProjection(new Projection("EPSG:4326"));
		this.defaultMapOptions.setUnits(MapUnits.METERS);
		this.defaultMapOptions.setMaxExtent(new Bounds(-20037508, -20037508,
				20037508, 20037508.34));
		this.defaultMapOptions.setMaxResolution(new Double(156543.0339)
				.floatValue());

		initMapWidget(this.defaultMapOptions);
	}

	private void initMapWidget(MapOptions defaultMapOptions) {
		mapWidget = new MapWidget("100%", "100%", defaultMapOptions);
		this.map = mapWidget.getMap();
		this.map.addControl(new LayerSwitcher());

		this.createOSM();
		this.createBaseGoogleLayer();

		this.mapControl = new MapControlManager(this.map);
	}

	private void createOSM() {
		OSMOptions osmOption = new OSMOptions();
		// osmOption.setDisplayOutsideMaxExtent(true);
		// osmOption.setWrapDateLine(true);

		this.osm = OSM.Mapnik("OpenStreetMap", osmOption);
		this.map.addLayer(osm);
	}

	private void createBaseGoogleLayer() {
		GoogleOptions option = new GoogleOptions();
		option.setType(GMapType.G_NORMAL_MAP);
		option.setSphericalMercator(true);

		layer = new Google("Google Normal", option);
		this.map.addLayer(layer);
	}

	/**
	 * Add Map to the ContentPanel passed from Dispatcher
	 * 
	 * @param center
	 */
	public void onAddToCenterPanel() {
		LayoutManager.addComponentToCenter(mapWidget);

		setMapCenter();
	}

	/**
	 * Set center of the Map on Italy
	 */
	public void setMapCenter() {
		// TODO Auto-generated method stub
		LonLat center = new LonLat(13.375, 42.329);
		center.transform("EPSG:4326", "EPSG:900913");
		this.map.setCenter(center, 5);

		this.mapControl.clearNavigationHistory();
	}

	/**
	 * Draw AOE on the Map
	 * 
	 * @param wkt
	 */
	public void drawAoeOnMap(String wkt) {
		this.mapControl.drawAoeOnMap(wkt);
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
	public Map getMap() {
		return map;
	}

	/**
	 * @return the mapWidget
	 */
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
		LayoutManager.get().getCenter().layout();
	}

	/**
	 * @return the tools
	 */
	public List<GenericClientTool> getTools() {
		return tools;
	}

	/**
	 * @param tools
	 *            the tools to set
	 */
	public void setTools(List<GenericClientTool> tools) {
		Collections.sort(tools);
		this.tools = tools;
	}

	/**
	 * activate draw feature control on the map
	 */
	public void activateDrawFeature() {
		this.mapControl.activateDrawFeature();
	}

	/**
	 * deactivate draw feature control on the map
	 */
	public void deactivateDrawFeature() {
		this.mapControl.deactivateDrawFeature();
	}

	public void activateDrawPointFeature() {
		this.mapControl.activateDrawPointFeature();
	}

	public void deactivateDrawPointFeature() {
		this.mapControl.deactivateDrawPointFeature();
	}

	public void redrawVectorLayer() {
		this.mapControl.redrawVectorLayer();
	}

	/**
	 * @return the buttonBar
	 */
	public ButtonBar getButtonBar() {
		return buttonBar;
	}

	/**
	 * @param buttonBar
	 *            the buttonBar to set
	 */
	public void setButtonBar(ButtonBar buttonBar) {
		this.buttonBar = buttonBar;
	}

	public DrawFeature getDrawPolygonFeature() {
		return this.mapControl.getDrawFeatureControl();
	}

	public DrawFeature getDrawPointFeature() {
		return this.mapControl.getDrawPointFeaureControl();
	}

	public void activateModifyFeature() {
		this.mapControl.activateModifyFeature();
	}

	public void deactivateModifyFeature() {
		this.mapControl.deactivateModifyFeature();
	}

	public void activateFeatureOperation() {
		this.mapControl.activateFeatureOperation();
	}

	public void deactivateFeatureOperation() {
		this.mapControl.deactivateFeatureOperation();
	}

	public boolean isFeatureOperationEnable() {
		return this.mapControl.isFeatureOperationEnable();
	}

	public boolean isModifyFeatureEnable() {
		return this.mapControl.isModifyFeatureEnable();
	}

	public int getFeaturesNumber() {
		return this.mapControl.getFeaturesNumber();
	}

	public void drawAOE(VectorFeature feature) {
		this.mapControl.drawAOE(feature);
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

}
