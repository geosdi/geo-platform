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
package org.geosdi.geoplatform.gui.impl.view;

import org.geosdi.geoplatform.gui.view.GeoPlatformLayoutManager;
import org.geosdi.geoplatform.gui.view.event.GeoPlatformEvents;

import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.Component;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author giuseppe
 * 
 */
public class LayoutManager extends GeoPlatformLayoutManager {

	private static LayoutManager INSTANCE;

	/**
	 * Build Singleton Instace
	 * 
	 * @return Instance Reference
	 */
	public static LayoutManager get() {
		if (INSTANCE == null)
			INSTANCE = new LayoutManager();
		return INSTANCE;
	}

	/**
	 * Add a generic Widget to Center
	 * 
	 * @param Widget
	 *            w
	 */
	public static void addComponentToCenter(Widget w) {
		get().center.add(w);
		get().center.layout();
	}

	/**
	 * Add a generic Widget to West
	 * 
	 * @param Widget
	 *            w
	 */
	public static void addComponentToWest(Widget w) {
		get().west.add(w);
		get().west.layout();
	}

	/**
	 * Remove a generic widget from West
	 * 
	 * @param WWidget
	 *            w
	 */
	public static void removeComponentFromWest(Widget w) {
		get().west.remove(w);
		get().west.layout();
	}

	/**
	 * Add a generic Widget to East
	 * 
	 * @param Widget
	 *            w
	 */
	public static void addComponentToEast(Widget w) {
		get().east.add(w);
		get().east.layout();
	}

	/**
	 * Add a generic Widget to North
	 * 
	 * @param Widget
	 *            w
	 */
	public static void addComponentToNorth(Widget w) {
		get().north.add(w);
		get().north.layout();
	}

	/**
	 * Add a generic Widget to South
	 * 
	 * @param Widget
	 *            w
	 */
	public static void addComponentToSouth(Widget w) {
		get().south.add(w);
		get().south.layout();
	}

	/**
	 * Show or Hide West panel
	 * 
	 * @param visible
	 */
	public static void manageWest(boolean visible) {
		if (visible)
			get().west.show();
		else
			get().west.hide();
		Dispatcher.forwardEvent(GeoPlatformEvents.UPDATE_CENTER);
	}

	/**
	 * Layout West Panel
	 */
	public static void layoutWest() {
		get().west.layout();
	}

	/**
	 * Check the Visibility of West Panel
	 * 
	 * @return boolean
	 */
	public static boolean isWestVisible() {
		return get().west.isVisible();
	}

	/**
	 * Check If always one Widget is visible on West
	 * 
	 * @return boolean
	 */
	public static boolean isOneWidgetVisibleAtWest() {
		for (Component c : get().west.getItems()) {
			if (c.isVisible())
				return true;
		}
		return false;
	}

}
