package org.geosdi.geoplatform.gui.client.event;

import java.util.ArrayList;

import org.geosdi.geoplatform.gui.client.SingleComponent;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent.Type;

public interface IViewNewsHandler extends EventHandler{
	
	Type<IViewNewsHandler> TYPE = new Type<IViewNewsHandler>();

	public void onClickListComponent(ArrayList<SingleComponent> listComponent);

}
