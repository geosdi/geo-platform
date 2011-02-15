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
package org.geosdi.geoplatform.gui.client.widget;

import java.util.ArrayList;

import org.geosdi.geoplatform.gui.client.model.GeocodingBean;
import org.geosdi.geoplatform.gui.client.model.GeocodingKeyValue;
import org.geosdi.geoplatform.gui.client.mvc.RoutingController;
import org.geosdi.geoplatform.gui.client.widget.search.ComboSearchWidget;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.widget.tips.ToolTipConfig;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 * 
 */
public class StartPointSearchRouting extends
		ComboSearchWidget<GeocodingBean, RoutingController> {

	/**
	 * 
	 * @param controller
	 * 
	 */
	public StartPointSearchRouting(RoutingController controller) {
		super(controller);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.geosdi.geoplatform.gui.client.widget.search.ComboSearchWidget#
	 * setComboProperties()
	 */
	@Override
	public void setWidgetProperties() {
		// TODO Auto-generated method stub
		this.combo.setDisplayField(GeocodingKeyValue.DESCRIPTION.getValue());
		this.combo.setHideTrigger(true);

		this.combo.setUseQueryCache(false);

		this.combo.setWidth(200);

		this.combo.addKeyListener(new KeyListener() {

			@Override
			public void componentKeyUp(ComponentEvent event) {
				// TODO Auto-generated method stub
				if ((event.getKeyCode() == 8)
						&& (combo.getRawValue().equals("")))
					clearStatus();
			}

			@Override
			public void componentKeyPress(ComponentEvent event) {
				// TODO Auto-generated method stub
				if ((event.getKeyCode() == 13)
						&& (combo.getRawValue().length() >= 4)
						&& (combo.getSelection().size() == 0))
					dispatchRequest();
			}

		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.geosdi.geoplatform.gui.client.widget.search.ComboSearchWidget#
	 * setComboToolTip()
	 */
	@Override
	public void setComboToolTip() {
		// TODO Auto-generated method stub
		ToolTipConfig config = new ToolTipConfig();
		config.setTitle("Usage");
		config.setText("Enter Address - click INVIO to set Start Point.");
		config.setTrackMouse(true);
		this.combo.setToolTip(config);
	}

	/**
	 * Clear Component Status for Widget :
	 * <ul>
	 * <li>Clear Store</li>
	 * <li>Collapse ComboBox</li>
	 * <li>Remove Marker on the Map</li>
	 * </ul>
	 */
	public void clearStatus() {
		super.clearWidget();
		/********************************************************************/
		/********************************************************************/
		/************ HERE THE CHECK TO REMOVE ALL ON THE MAP ***************/
		/********************************************************************/
		/********************************************************************/
	}

	/**
	 * Send Request to Geocoding Module to fill the Combo with Results
	 * 
	 */
	public void dispatchRequest() {
		loadImage(TypeImage.IMAGE_LOADING, true);
		this.clearStore();

		controller.getGeocodingService().findLocations(combo.getRawValue(),
				new AsyncCallback<ArrayList<GeocodingBean>>() {

					@Override
					public void onSuccess(ArrayList<GeocodingBean> result) {
						// TODO Auto-generated method stub

						if (result.size() > 0) {
							GeoPlatformMessage.infoMessage("Geocoding Service",
									"Results loaded with success.");
							loadImage(TypeImage.IMAGE_RESULT_FOUND, true);
							fillStore(result);
							expand();
						} else {
							GeoPlatformMessage.alertMessage(
									"Geocoding Service", "No Results found!");
							loadImage(TypeImage.IMAGE_RESULT_NOT_FOUND, true);
							clearStore();
						}

					}

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						GeoPlatformMessage.errorMessage("Geocoding Service",
								"An Error occurred while dispatching request.");
						loadImage(TypeImage.IMAGE_SERVICE_ERROR, true);
						clearStore();
					}
				});
	}

	/* (non-Javadoc)
	 * @see org.geosdi.geoplatform.gui.client.widget.search.ComboSearchWidget#selectionChanged(com.extjs.gxt.ui.client.event.SelectionChangedEvent)
	 */
	@Override
	public void selectionChanged(SelectionChangedEvent<GeocodingBean> se) {
		// TODO Auto-generated method stub
		
	}
}
