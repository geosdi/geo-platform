package org.geosdi.geoplatform.gui.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EventIndietro extends GwtEvent<IIndietroHandler>{
	
	private int index;
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<IIndietroHandler> getAssociatedType() {
		return IIndietroHandler.TYPE;
	}

	@Override
	protected void dispatch(IIndietroHandler handler) {
		handler.onClickIndietro(index);
	}
	
	public void setIndex(int index){
		this.index = index;
	}
}
