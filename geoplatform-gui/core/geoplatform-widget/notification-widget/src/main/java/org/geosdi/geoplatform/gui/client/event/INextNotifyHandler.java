package org.geosdi.geoplatform.gui.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent.Type;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public interface INextNotifyHandler extends EventHandler {

    Type<INextNotifyHandler> TYPE = new Type<INextNotifyHandler>();

    public void onClickNext(int index);
}
