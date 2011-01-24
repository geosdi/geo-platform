/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-plartform.org
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
package org.geosdi.geoplatform.gui.client.widget.map.control.crud;

import org.geosdi.geoplatform.gui.client.MapWidgetEvents;
import org.geosdi.geoplatform.gui.client.widget.map.control.MapControl;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.gwtopenmaps.openlayers.client.control.SelectFeature;
import org.gwtopenmaps.openlayers.client.control.SelectFeature.ClickFeatureListener;
import org.gwtopenmaps.openlayers.client.control.SelectFeatureOptions;
import org.gwtopenmaps.openlayers.client.feature.VectorFeature;
import org.gwtopenmaps.openlayers.client.layer.Vector;

import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;

/**
 * @author giuseppe
 * 
 */
public class GenericFeatureOperation extends MapControl {

	private SelectFeature control;
	private OperationType operation;

	public GenericFeatureOperation(Vector vector) {
		super(vector);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.geosdi.geoplatform.gui.impl.map.control.GeoPlatformMapControl#
	 * createControl()
	 */
	@Override
	public void createControl() {
		// TODO Auto-generated method stub
		SelectFeatureOptions selectFeatureOptions = new SelectFeatureOptions();

		selectFeatureOptions.clickFeature(new ClickFeatureListener() {

			@Override
			public void onFeatureClicked(final VectorFeature vectorFeature) {
				// TODO Auto-generated method stub
				GeoPlatformMessage
						.confirmMessage(
								"Delete Feature",
								"Are you sure you want to delete the selected feature ?",
								new Listener<MessageBoxEvent>() {

									@Override
									public void handleEvent(MessageBoxEvent be) {
										// TODO Auto-generated method stub
										if (be.getButtonClicked().getText()
												.equalsIgnoreCase("yes")
												|| be.getButtonClicked()
														.getText()
														.equalsIgnoreCase("si"))

											Dispatcher
													.forwardEvent(
															MapWidgetEvents.DELETE_FEATURE,
															vectorFeature);

									}
								});
			}
		});

		control = new SelectFeature(vector, selectFeatureOptions);
	}

	@Override
	public void activateControl() {
		// TODO Auto-generated method stub
		this.control.activate();
		this.enabled = true;
	}

	@Override
	public void deactivateControl() {
		// TODO Auto-generated method stub
		this.control.deactivate();
		this.enabled = false;
	}

	/**
	 * @return the control
	 */
	public SelectFeature getControl() {
		return control;
	}

	/**
	 * @param operation
	 *            the operation to set
	 */
	public void setOperation(OperationType operation) {
		this.operation = operation;
	}

	/**
	 * @return the operation
	 */
	public OperationType getOperation() {
		return operation;
	}

}
