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
package org.geosdi.geoplatform.gui.client.widget.map.responsibility;

import org.geosdi.geoplatform.gui.client.widget.map.control.ModifyFeatureControl;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.gwtopenmaps.openlayers.client.feature.VectorFeature;
import org.gwtopenmaps.openlayers.client.geometry.Geometry;
import org.gwtopenmaps.openlayers.client.geometry.LineString;
import org.gwtopenmaps.openlayers.client.layer.Vector;

import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;

/**
 * @author giuseppe
 * 
 */
public class LineRequestHandler extends GeometryRequestHandler {

	public LineRequestHandler(ModifyFeatureControl theControl) {
		super(theControl);
		// TODO Auto-generated constructor stub
	}

	public void geometryRequest(VectorFeature feature, Vector vector) {
		// TODO Auto-generated method stub
		if (feature.getGeometry().getClassName()
				.equals(Geometry.LINESTRING_CLASS_NAME)) {

			if (!checkModifications(feature))
				showConfirmMessage(feature, vector);

		} else
			forwardGeometryRequest(feature, vector);
	}

	@Override
	public boolean checkModifications(VectorFeature feature) {
		// TODO Auto-generated method stub
		LineString oldLine = LineString.narrowToLineString(control
				.getSelectedFeature().getGeometry().getJSObject());

		LineString li = LineString.narrowToLineString(feature.getGeometry()
				.getJSObject());

		return li.equals(oldLine);
	}

	@Override
	public void showConfirmMessage(final VectorFeature feature,
			final Vector vector) {
		// TODO Auto-generated method stub
		final VectorFeature selectedFeature = getSelectedFeaure();

		GeoPlatformMessage
				.confirmMessage(
						"Line Feature Status",
						"The Geometry Line Feature is changed. Do you want to apply the changes?",
						new Listener<MessageBoxEvent>() {

							@Override
							public void handleEvent(MessageBoxEvent be) {
								// TODO Auto-generated method stub
								if (be.getButtonClicked().getText()
										.equalsIgnoreCase("yes")
										|| be.getButtonClicked().getText()
												.equalsIgnoreCase("si")) {
									/**
									 * HERE THE CODE TO DISPATCH THAT THE
									 * GEOMETRY FEATURE MUST BE UPDATE IN DB ON
									 * THE SERVICES
									 **/
									System.out.println("YES **********");
								} else {
									vector.removeFeature(feature);
									vector.addFeature(selectedFeature);
								}
							}
						});
	}

}
