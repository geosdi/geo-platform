package org.geosdi.geoplatform.gui.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class EventShowNotification extends GwtEvent<IShowNotificationHandler>{
	
	private boolean show;
	private int AbsoluteLeft;
	private int AbsoluteTop; 
	private int OffsetHeight;
	private int OffsetWidth;
	

	public int getAbsoluteLeft() {
		return AbsoluteLeft;
	}

	public void setAbsoluteLeft(int absoluteLeft) {
		AbsoluteLeft = absoluteLeft;
	}

	public int getAbsoluteTop() {
		return AbsoluteTop;
	}

	public void setAbsoluteTop(int absoluteTop) {
		AbsoluteTop = absoluteTop;
	}

	public int getOffsetHeight() {
		return OffsetHeight;
	}

	public void setOffsetHeight(int offsetHeight) {
		OffsetHeight = offsetHeight;
	}

	public int getOffsetWidth() {
		return OffsetWidth;
	}

	public void setOffsetWidth(int offsetWidth) {
		OffsetWidth = offsetWidth;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<IShowNotificationHandler> getAssociatedType() {
		return IShowNotificationHandler.TYPE;
	}

	@Override
	protected void dispatch(IShowNotificationHandler handler) {
		handler.onClickShowPopUp(AbsoluteLeft, AbsoluteTop, OffsetHeight, OffsetWidth, show);
	}

	public void setBooleanShow(boolean show){
		this.show = show;
	}
}
