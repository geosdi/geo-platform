package org.geosdi.geoplatform.gui.client.puregwt.refresh.support;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.inject.Inject;
import org.geosdi.geoplatform.gui.client.puregwt.refresh.GPCompositeRefreshHandler.RefreshCompositeEvent;
import org.geosdi.geoplatform.gui.client.widget.tree.refresh.GPLayerTreeRefresher;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.geosdi.geoplatform.gui.puregwt.GPEventBusImpl;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.geosdi.geoplatform.gui.client.puregwt.refresh.GPCompositeRefreshHandler.TYPE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public final class GPCompositeRefreshHandlerSupport {

    private static final Logger logger = Logger.getLogger("GPCompositeRefreshHandlerSupport");
    //
    private static final GPEventBus bus = new GPEventBusImpl();

    @Inject
    public GPCompositeRefreshHandlerSupport(GPLayerTreeRefresher gpLayerTreeRefresher) {
        logger.log(Level.FINE, "###################################GPCompositeRefreshHandlerSupport " + gpLayerTreeRefresher);
        addCompositeRefreshHandler(gpLayerTreeRefresher);
    }

    protected HandlerRegistration addCompositeRefreshHandler(GPLayerTreeRefresher gpLayerTreeRefresher) {
        return bus.addHandler(TYPE, gpLayerTreeRefresher);
    }

    public static void fireCompositeRefreshEvent(RefreshCompositeEvent event) {
        bus.fireEvent(event);
    }
}
