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

import org.geosdi.geoplatform.gui.client.widget.map.control.DrawPolygonControl;
import org.geosdi.geoplatform.gui.client.widget.map.control.ModifyFeatureControl;
import org.gwtopenmaps.openlayers.client.Map;
import org.gwtopenmaps.openlayers.client.Projection;
import org.gwtopenmaps.openlayers.client.Style;
import org.gwtopenmaps.openlayers.client.control.DrawFeature;
import org.gwtopenmaps.openlayers.client.control.ModifyFeature;
import org.gwtopenmaps.openlayers.client.feature.VectorFeature;
import org.gwtopenmaps.openlayers.client.geometry.Geometry;
import org.gwtopenmaps.openlayers.client.geometry.MultiPolygon;
import org.gwtopenmaps.openlayers.client.layer.Vector;
import org.gwtopenmaps.openlayers.client.layer.VectorOptions;

/**
 * @author giuseppe
 * 
 */
public class MapControlManager {

	private Map map;
	private Vector vector;

	private DrawPolygonControl drawFeature;
	private ModifyFeatureControl modifyFeature;

	public MapControlManager(Map map) {
		this.map = map;
		this.initVectorLayer();
	}

	/**
	 * Create a vector layer to add to the map which defines a set of controls
	 * 
	 */
	private void initVectorLayer() {
		VectorOptions vectorOption = new VectorOptions();
		vectorOption.setStyle(this.createStyle());
		vectorOption.setDisplayInLayerSwitcher(false);
		this.vector = new Vector("GeoPlatform Vector Layer", vectorOption);
		this.map.addLayer(vector);

		this.initControl();

		this.addMapControl();
	}

	/**
	 * Initialize Control on Vector Layer
	 * 
	 */
	private void initControl() {
		this.drawFeature = new DrawPolygonControl(vector);
		this.modifyFeature = new ModifyFeatureControl(vector);
	}

	/**
	 * Add Control to the Map
	 * 
	 */
	private void addMapControl() {
		this.map.addControl(this.drawFeature.getControl());
		this.map.addControl(this.modifyFeature.getControl());
		this.modifyFeature.activateControl();
	}

	/**
	 * 
	 * @return Style
	 */
	private Style createStyle() {
		Style style = new Style();
		style.setStrokeColor("#000000");
		style.setStrokeWidth(1);
		style.setFillColor("#FF0000");
		style.setFillOpacity(0.5);
		style.setPointRadius(5);
		style.setStrokeOpacity(1.0);
		return style;
	}

	/**
	 * Draw AOE on the Map
	 * 
	 * @param wkt
	 */
	public void drawAoeOnMap(String wkt) {
		this.eraseFeatures();
		MultiPolygon geom = MultiPolygon.narrowToMultiPolygon(Geometry.fromWKT(
				wkt).getJSObject());
		geom.transform(new Projection("EPSG:4326"), new Projection(
				"EPSG:900913"));
		VectorFeature vectorFeature = new VectorFeature(geom);
		this.vector.addFeature(vectorFeature);
		this.map.zoomToExtent(geom.getBounds());
	}

	/**
	 * Erase all Features added to Vector Layer
	 */
	public void eraseFeatures() {
		this.vector.destroyFeatures();
	}

	/**
	 * Erase Single Feature from the Map
	 * 
	 * @param vf
	 */
	public void eraseFeature(VectorFeature vf) {
		this.vector.removeFeature(vf);
	}

	/**
	 * activate draw feature control on the map
	 */
	public void activateDrawFeature() {
		this.drawFeature.activateControl();
	}

	/**
	 * deactivate draw feature control on the map
	 */
	public void deactivateDrawFeature() {
		this.drawFeature.deactivateControl();
	}

	/**
	 * 
	 * @return DrawFeature
	 */
	public DrawFeature getDrawFeatureControl() {
		return this.drawFeature.getControl();
	}

	/**
	 * 
	 * @return ModifyFeature
	 */
	public ModifyFeature getModifyFeatureControl() {
		return this.modifyFeature.getControl();
	}

	/**
	 * Redraw the Vector Layer
	 */
	public void redrawVectorLayer() {
		this.vector.redraw();
	}
}
