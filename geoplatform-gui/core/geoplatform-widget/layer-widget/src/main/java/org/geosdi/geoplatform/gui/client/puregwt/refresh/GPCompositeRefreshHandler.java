package org.geosdi.geoplatform.gui.client.puregwt.refresh;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import org.geosdi.geoplatform.gui.client.puregwt.refresh.event.LoadRootElementsEvent;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPCompositeRefreshHandler extends EventHandler {

    LoadRootElementsEvent LOAD_ROOT_ELEMENTS_EVENT = new LoadRootElementsEvent();

    GwtEvent.Type<GPCompositeRefreshHandler> TYPE = new GwtEvent.Type<GPCompositeRefreshHandler>();

    void refreshRootElements();

    abstract class RefreshCompositeEvent extends GwtEvent<GPCompositeRefreshHandler> {

        @Override
        public Type<GPCompositeRefreshHandler> getAssociatedType() {
            return TYPE;
        }
    }
}
