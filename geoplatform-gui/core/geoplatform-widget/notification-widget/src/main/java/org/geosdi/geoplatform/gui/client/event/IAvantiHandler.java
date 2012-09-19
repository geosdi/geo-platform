package org.geosdi.geoplatform.gui.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent.Type;

public interface IAvantiHandler extends EventHandler{
	
	Type<IAvantiHandler> TYPE = new Type<IAvantiHandler>();

	public void onClickAvanti(int index);

}
