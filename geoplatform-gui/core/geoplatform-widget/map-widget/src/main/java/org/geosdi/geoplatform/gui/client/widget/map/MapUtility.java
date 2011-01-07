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

import org.gwtopenmaps.openlayers.client.Icon;
import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.Map;
import org.gwtopenmaps.openlayers.client.Marker;
import org.gwtopenmaps.openlayers.client.Pixel;
import org.gwtopenmaps.openlayers.client.Size;
import org.gwtopenmaps.openlayers.client.layer.Markers;
import org.gwtopenmaps.openlayers.client.layer.MarkersOptions;

import com.google.gwt.core.client.GWT;

/**
 * @author giuseppe
 * 
 */
public class MapUtility {

	private Markers markerLayer;
	private Marker marker;
	private Icon iconMarker;

	public MapUtility() {
		buildMarkerLayer();
		buildIconMarker();
	}

	private void buildMarkerLayer() {
		MarkersOptions options = new MarkersOptions();
		options.setNumZoomLevels(18);
		options.setMaxZoomLevel(18);
		options.setDisplayInLayerSwitcher(false);

		this.markerLayer = new Markers("Map-Markers-Layer", options);
	}

	private void buildIconMarker() {
		Size size = new Size(21, 25);
		Pixel offset = new Pixel(-(size.getWidth() / 2), -size.getHeight());
		this.iconMarker = new Icon(GWT.getModuleName()
				+ "/map-images/geocodmarker.png", size, offset);
	}

	/**
	 * 
	 * @param lonlat
	 *            LonLat to build the marker and add to the markerLayer
	 */
	public void addMarker(LonLat lonlat, Map map) {
		this.markerLayer.clearMarkers();
		map.setCenter(lonlat, 16);
		this.marker = new Marker(lonlat, this.iconMarker);
		this.markerLayer.addMarker(this.marker);
	}

	/**
	 * Remove Marker from Marker Layer
	 */
	public void removeMarker() {
		if (this.marker != null)
			this.markerLayer.removeMarker(this.marker);
	}

	/**
	 * @return the markerLayer
	 */
	public Markers getMarkerLayer() {
		return markerLayer;
	}

}
