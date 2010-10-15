package org.geosdi.geoplatform.gui.client;

import org.geosdi.geoplatform.gui.action.ToolbarActionFactory;
import org.geosdi.geoplatform.gui.client.action.toolbar.ZoomInAction;

import com.google.gwt.core.client.EntryPoint;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Application implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		ZoomInAction action = (ZoomInAction) ToolbarActionFactory.get("zoomIn",
				null);

		System.out.println("TEST ******************************** "
				+ action.getTooltip());
	}
}
