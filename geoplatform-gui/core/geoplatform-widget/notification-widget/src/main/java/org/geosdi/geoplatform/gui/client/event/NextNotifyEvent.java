package org.geosdi.geoplatform.gui.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class NextNotifyEvent extends GwtEvent<INextNotifyHandler> {

    private int index;

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<INextNotifyHandler> getAssociatedType() {
        return INextNotifyHandler.TYPE;
    }

    @Override
    protected void dispatch(INextNotifyHandler handler) {
        handler.onClickNext(index);
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
