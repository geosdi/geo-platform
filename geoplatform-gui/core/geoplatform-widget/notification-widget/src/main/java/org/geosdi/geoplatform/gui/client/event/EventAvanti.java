package org.geosdi.geoplatform.gui.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EventAvanti extends GwtEvent<IAvantiHandler>{
	
	private int index;
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<IAvantiHandler> getAssociatedType() {
		return IAvantiHandler.TYPE;
	}

	@Override
	protected void dispatch(IAvantiHandler handler) {
		handler.onClickAvanti(index);
	}
	
	public void setIndex(int index){
		this.index = index;
	}

}
