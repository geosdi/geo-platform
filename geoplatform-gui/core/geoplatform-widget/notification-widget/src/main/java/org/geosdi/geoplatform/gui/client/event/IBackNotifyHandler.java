package org.geosdi.geoplatform.gui.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent.Type;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public interface IBackNotifyHandler extends EventHandler {

    Type<IBackNotifyHandler> TYPE = new Type<IBackNotifyHandler>();

    public void onClickBack(int index);
}
