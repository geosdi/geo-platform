package org.geosdi.geoplatform.gui.puregwt;

import com.google.gwt.event.shared.HandlerRegistration;
import org.geosdi.geoplatform.gui.puregwt.baselayer.GPBaseLayerStoreHandler;

import java.util.logging.Logger;

import static org.geosdi.geoplatform.gui.puregwt.baselayer.GPBaseLayerStoreHandler.TYPE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public final class GPBaseLayerStoreHandlerSupport {

    private static final Logger logger = Logger.getLogger("GPBaseLayerStoreHandlerSupport");
    //
    private static final GPEventBus bus = new GPEventBusImpl();

    public GPBaseLayerStoreHandlerSupport(GPBaseLayerStoreHandler baseLayerStoreHandler) {
        addBaseLayerStoreHandler(baseLayerStoreHandler);
    }

    protected HandlerRegistration addBaseLayerStoreHandler(GPBaseLayerStoreHandler baseLayerStoreHandler) {
        return bus.addHandler(TYPE, baseLayerStoreHandler);
    }

    /**
     * @param baseLayerEvent
     */
    public static void fireBaseLayerEvent(GPBaseLayerStoreHandler.GPBaseLayerEvent baseLayerEvent) {
        bus.fireEvent(baseLayerEvent);
    }
}
