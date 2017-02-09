package org.geosdi.geoplatform.gui.client.puregwt.refresh.event;

import org.geosdi.geoplatform.gui.client.puregwt.refresh.GPCompositeRefreshHandler;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class LoadRootElementsEvent extends GPCompositeRefreshHandler.RefreshCompositeEvent {

    @Override
    protected void dispatch(GPCompositeRefreshHandler handler) {
        handler.refreshRootElements();
    }
}
