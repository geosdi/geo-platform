package org.geosdi.geoplatform.gui.client.event;

import com.google.gwt.event.shared.GwtEvent;
import java.util.List;
import org.geosdi.geoplatform.gui.client.widget.SingleNotify;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class EventViewNews extends GwtEvent<IViewNewsHandler> {

    private List<SingleNotify> listComponents;

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<IViewNewsHandler> getAssociatedType() {
        return IViewNewsHandler.TYPE;
    }

    @Override
    protected void dispatch(IViewNewsHandler handler) {
        handler.onClickListComponent(listComponents);
    }

    public void setListComponent(List<SingleNotify> listComponents) {
        this.listComponents = listComponents;
    }
}
