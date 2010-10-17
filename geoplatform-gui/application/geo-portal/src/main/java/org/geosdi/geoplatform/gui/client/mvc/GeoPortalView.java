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
package org.geosdi.geoplatform.gui.client.mvc;

import org.geosdi.geoplatform.gui.client.GeoPortalEvents;
import org.geosdi.geoplatform.gui.client.MapWidgetEvents;
import org.geosdi.geoplatform.gui.configuration.mvc.GeoPlatformView;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * @author giuseppe
 * 
 */
public class GeoPortalView extends GeoPlatformView {

	private Viewport viewport;
	private ContentPanel center;
	private ContentPanel north;

	public GeoPortalView(Controller controller) {
		super(controller);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.geosdi.geoplatform.gui.configuration.mvc.GeoPlatformView#handleEvent
	 * (com.extjs.gxt.ui.client.mvc.AppEvent)
	 */
	@Override
	protected void handleEvent(AppEvent event) {
		// TODO Auto-generated method stub
		if (event.getType() == GeoPortalEvents.INIT_GEO_PORTAL)
			initUI();
	}

	private void initUI() {
		viewport = new Viewport();
		viewport.setLayout(new BorderLayout());

		createNorth();
		createCenter();

		RootPanel.get().add(viewport);
	}

	private void createNorth() {
		north = new ContentPanel();
		north.setHeaderVisible(false);

		BorderLayoutData data = new BorderLayoutData(LayoutRegion.NORTH, 30);
		data.setMargins(new Margins(0, 5, 0, 5));
		viewport.add(north, data);
	}

	private void createCenter() {
		center = new ContentPanel();
		center.setLayout(new FitLayout());
		center.setHeaderVisible(false);
		BorderLayoutData data = new BorderLayoutData(LayoutRegion.CENTER);
		data.setMargins(new Margins(5, 5, 5, 5));

		viewport.add(center, data);

		Dispatcher.forwardEvent(MapWidgetEvents.ATTACH_MAP_WIDGET, this.center);
		Dispatcher.forwardEvent(MapWidgetEvents.ATTACH_TOOLBAR, this.north);
	}
}
