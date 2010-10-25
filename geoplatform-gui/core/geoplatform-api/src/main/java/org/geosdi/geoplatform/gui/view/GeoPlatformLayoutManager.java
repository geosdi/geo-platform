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
package org.geosdi.geoplatform.gui.view;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;

/**
 * @author giuseppe
 * 
 */
public abstract class GeoPlatformLayoutManager {

	protected Viewport viewport;
	protected ContentPanel east;
	protected ContentPanel west;
	protected ContentPanel south;
	protected ContentPanel center;
	protected ContentPanel north;

	public GeoPlatformLayoutManager() {
		intiLayoutManager();
	}


	/**
	 * Build The Main GeoPlatform UI
	 */
	private void intiLayoutManager() {
		// TODO Auto-generated method stub
		viewport = new Viewport();
		viewport.setLayout(new BorderLayout());

		createNorth();
		createEast();
		createWest();
		createCenter();
	}

	/**
	 * Create North Panel in Main UI
	 */
	private void createNorth() {
		north = new ContentPanel();
		north.setHeaderVisible(false);
		BorderLayoutData data = new BorderLayoutData(LayoutRegion.NORTH, 50);
		data.setMargins(new Margins(0, 5, 0, 5));

		viewport.add(north, data);
	}

	/**
	 * Create West Panel in Main UI
	 */
	private void createWest() {
		BorderLayoutData data = new BorderLayoutData(LayoutRegion.WEST, 150);
		data.setMargins(new Margins(5, 0, 5, 5));

		west = new ContentPanel();
		west.setHeaderVisible(false);
		west.setBodyBorder(false);
		west.setLayout(new AccordionLayout());
		west.setScrollMode(Scroll.AUTO);
		
		west.hide();

		viewport.add(west, data);
	}

	/**
	 * Create East Panel in Main UI
	 */
	private void createEast() {
		BorderLayoutData data = new BorderLayoutData(LayoutRegion.EAST, 150);
		data.setMargins(new Margins(5, 0, 5, 5));

		east = new ContentPanel();
		east.setHeaderVisible(false);
		east.setBodyBorder(false);
		east.setLayout(new AccordionLayout());
		east.setScrollMode(Scroll.AUTO);
		
		east.hide();

		viewport.add(east, data);
	}

	/**
	 * Create Center Panel in Main UI
	 */
	private void createCenter() {
		center = new ContentPanel();

		center.setHeaderVisible(false);
		BorderLayoutData data = new BorderLayoutData(LayoutRegion.CENTER);
		data.setMargins(new Margins(5, 5, 5, 5));

		viewport.add(center, data);
	}

	/**
	 * @return the east
	 */
	public ContentPanel getEast() {
		return east;
	}

	/**
	 * @return the viewport
	 */
	public Viewport getViewport() {
		return viewport;
	}

	/**
	 * @return the south
	 */
	public ContentPanel getSouth() {
		return south;
	}

	/**
	 * @return the center
	 */
	public ContentPanel getCenter() {
		return center;
	}

	/**
	 * @return the north
	 */
	public ContentPanel getNorth() {
		return north;
	}

}
