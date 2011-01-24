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
package org.geosdi.geoplatform.gui.client.mvc;

import java.util.ArrayList;

import org.geosdi.geoplatform.gui.client.GeocodingEvents;
import org.geosdi.geoplatform.gui.client.model.GeocodingBean;
import org.geosdi.geoplatform.gui.client.service.GeocodingRemote;
import org.geosdi.geoplatform.gui.client.service.GeocodingRemoteAsync;
import org.geosdi.geoplatform.gui.client.widget.map.ReverseGeocodingWidget;
import org.geosdi.geoplatform.gui.configuration.grid.IGeoPlatformGrid;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.configuration.mvc.GeoPlatformController;
import org.geosdi.geoplatform.gui.view.event.GeoPlatformEvents;
import org.gwtopenmaps.openlayers.client.LonLat;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author giuseppe
 * 
 */
public class GeocodingController extends GeoPlatformController {

	private GeocodingRemoteAsync geocodingService = GeocodingRemote.Util
			.getInstance();

	public GeocodingController() {
		registerEventTypes(GeocodingEvents.INIT_GEOCODING_WIDGET,
				GeocodingEvents.SHOW_GEOCODING_WIDGET,
				GeocodingEvents.BEGIN_GEOCODING_SEARCH,
				GeocodingEvents.HIDE_GEOCODING_WIDGET,
				GeoPlatformEvents.REVERSE_GEOCODING_REQUEST);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.extjs.gxt.ui.client.mvc.Controller#initialize()
	 */
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		this.view = new GeocodingView(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.geosdi.geoplatform.gui.configuration.mvc.GeoPlatformController#
	 * handleEvent(com.extjs.gxt.ui.client.mvc.AppEvent)
	 */
	@Override
	public void handleEvent(AppEvent event) {
		// TODO Auto-generated method stub
		if (event.getType() == GeocodingEvents.BEGIN_GEOCODING_SEARCH)
			onBeginGeocodingSearch(event);

		if (event.getType() == GeoPlatformEvents.REVERSE_GEOCODING_REQUEST)
			onProcessRequest(event);

		super.handleEvent(event);
	}

	/**
	 * Process Reverse Geocoding Request
	 * 
	 * @param event
	 */
	private void onProcessRequest(AppEvent event) {
		// TODO Auto-generated method stub
		final ReverseGeocodingWidget widget = (ReverseGeocodingWidget) event
				.getData();
		LonLat lonlat = widget.getLonlat();

		this.geocodingService.findLocation(lonlat.lat(), lonlat.lon(),
				new AsyncCallback<GeocodingBean>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						widget.onRequestFailure("An error occurred in processing the request");
					}

					@Override
					public void onSuccess(GeocodingBean result) {
						// TODO Auto-generated method stub
						widget.onRequestSuccess(result.getDescription());
					}
				});
	}

	/**
	 * Invoke Geocoding Servcice for Geo-Location
	 * 
	 * @param event
	 */
	private void onBeginGeocodingSearch(AppEvent event) {
		// TODO Auto-generated method stub
		checkWidgetStatus();
		findLocations((String) event.getData());
	}

	/**
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void checkWidgetStatus() {
		// TODO Auto-generated method stub
		GeoPlatformMessage
				.checkGridWidgetStatus(
						(IGeoPlatformGrid) ((GeocodingView) this.view)
								.getGeocodingManagement()
								.getGeocodingGridWidget(),
						"Geocoding - Service",
						"Geocoding Service is demanding too much time, probably the connection problem, do you want to stop it?");
	}

	/**
	 * 
	 * @param location
	 *            to find
	 */
	public void findLocations(String location) {
		((GeocodingView) view).maskGeocodingGrid();
		((GeocodingView) view).cleanStore();
		this.geocodingService.findLocations(location,
				new AsyncCallback<ArrayList<GeocodingBean>>() {

					@Override
					public void onSuccess(ArrayList<GeocodingBean> result) {
						// TODO Auto-generated method stub
						ArrayList<GeocodingBean> beans = (ArrayList<GeocodingBean>) result;
						((GeocodingView) view).unMaskGeocodingGrid();
						if (result != null && result.size() > 0) {
							((GeocodingView) view).fillStore(beans);
						} else
							GeoPlatformMessage.alertMessage(
									"Geocoding - Service",
									"There are no results for your search.");

					}

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						((GeocodingView) view).unMaskGeocodingGrid();
						((GeocodingView) view).getGrid().getView()
								.refresh(false);
						GeoPlatformMessage.errorMessage("Geocoding - Service",
								"There is a problem with Geocoding Service");

					}
				});
	}
}
