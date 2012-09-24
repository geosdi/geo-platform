package org.geosdi.geoplatform.gui.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class BackNotifyEvent extends GwtEvent<IBackNotifyHandler> {

    private int index;

    @Override
    public GwtEvent.Type<IBackNotifyHandler> getAssociatedType() {
        return IBackNotifyHandler.TYPE;
    }

    @Override
    protected void dispatch(IBackNotifyHandler handler) {
        handler.onClickBack(index);
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
