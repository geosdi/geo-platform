package org.geosdi.geoplatform.gui.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent.Type;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public interface IShowNotificationHandler extends EventHandler {

    Type<IShowNotificationHandler> TYPE = new Type<IShowNotificationHandler>();

    public void onClickShowPopUp(int AbsoluteLeft, int AbsoluteTop, int OffsetHeight, int OffsetWidth, boolean flag);
}
