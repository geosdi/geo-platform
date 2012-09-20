package org.geosdi.geoplatform.gui.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent.Type;

public interface IIndietroHandler extends EventHandler{
	
	Type<IIndietroHandler> TYPE = new Type<IIndietroHandler>();

	public void onClickIndietro(int index);

}
