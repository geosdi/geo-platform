package org.geosdi.geoplatform.gui.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent.Type;
import java.util.List;
import org.geosdi.geoplatform.gui.client.widget.SingleNotify;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public interface IViewNewsHandler extends EventHandler {

    Type<IViewNewsHandler> TYPE = new Type<IViewNewsHandler>();

    public void onClickListComponent(List<SingleNotify> listComponent);
}
