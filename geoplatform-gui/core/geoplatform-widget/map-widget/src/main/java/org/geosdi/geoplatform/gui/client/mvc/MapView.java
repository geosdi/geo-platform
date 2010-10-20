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

import org.geosdi.geoplatform.gui.client.MapWidgetEvents;
import org.geosdi.geoplatform.gui.client.widget.ButtonBar;
import org.geosdi.geoplatform.gui.client.widget.map.MapLayoutWidget;
import org.geosdi.geoplatform.gui.configuration.mvc.GeoPlatformView;
import org.geosdi.geoplatform.gui.utility.GeoPlatformUtils;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.widget.ContentPanel;

/**
 * @author giuseppe
 * 
 */
public class MapView extends GeoPlatformView {

	private MapLayoutWidget mapLayout;

	private ButtonBar buttonBar;

	public MapView(Controller controller) {
		super(controller);

		this.mapLayout = new MapLayoutWidget();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.extjs.gxt.ui.client.mvc.View#handleEvent(com.extjs.gxt.ui.client.
	 * mvc.AppEvent)
	 */
	@Override
	protected void handleEvent(AppEvent event) {
		if (event.getType() == MapWidgetEvents.ATTACH_MAP_WIDGET)
			this.mapLayout.onAddToCenterPanel((ContentPanel) event.getData());

		if (event.getType() == MapWidgetEvents.ATTACH_TOOLBAR)
			onAttachToolbar(event);
	}

	/**
	 * Activate Draw Control on Map
	 */
	public void deactivateDrawControl() {
		// TODO Auto-generated method stub
		this.mapLayout.activateDrawFeature();
	}

	/**
	 * Deactivate Draw Control on Map
	 */
	public void activateDrawControl() {
		// TODO Auto-generated method stub
		this.mapLayout.deactivateDrawFeature();
	}

	/**
	 * Attach GeoPlatform Toolbar to a ContentPanel inject with Dispatcher
	 * 
	 * @param event
	 */
	private void onAttachToolbar(AppEvent event) {
		mapLayout.setTools(GeoPlatformUtils.getInstance()
				.getGlobalConfiguration().getToolbarClientTool()
				.getClientTools());

		this.buttonBar = new ButtonBar(mapLayout);

		ContentPanel north = (ContentPanel) event.getData();
		north.add(buttonBar.getToolBar());

		north.layout();
	}

}
