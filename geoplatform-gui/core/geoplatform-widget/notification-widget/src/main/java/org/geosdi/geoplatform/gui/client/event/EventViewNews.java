package org.geosdi.geoplatform.gui.client.event;

import java.util.ArrayList;
import org.geosdi.geoplatform.gui.client.SingleComponent;

import com.google.gwt.event.shared.GwtEvent;

public class EventViewNews extends GwtEvent<IViewNewsHandler>{
	
private ArrayList<SingleComponent> listComponents;
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<IViewNewsHandler> getAssociatedType() {
		return IViewNewsHandler.TYPE;
	}

	@Override
	protected void dispatch(IViewNewsHandler handler) {
		handler.onClickListComponent(listComponents);
	}
	
	public void setListComponent(ArrayList<SingleComponent> liComponents){
		this.listComponents = listComponents;	
		}

}
