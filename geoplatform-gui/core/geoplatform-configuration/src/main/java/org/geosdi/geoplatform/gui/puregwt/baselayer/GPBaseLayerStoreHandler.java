package org.geosdi.geoplatform.gui.puregwt.baselayer;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import org.geosdi.geoplatform.gui.factory.baselayer.GPBaseLayerCreator;
import org.geosdi.geoplatform.gui.global.baselayer.GPBaseLayerValue;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPBaseLayerStoreHandler extends EventHandler {

    GwtEvent.Type<GPBaseLayerStoreHandler> TYPE = new GwtEvent.Type<GPBaseLayerStoreHandler>();

    /**
     * @param value
     * @param layerCreator
     */
    void registerBaseLayer(GPBaseLayerValue value, GPBaseLayerCreator layerCreator);

    /**
     * @param value
     */
    void removeBaseLayer(GPBaseLayerValue value);

    abstract class GPBaseLayerEvent extends GwtEvent<GPBaseLayerStoreHandler> {

        @Override
        public Type<GPBaseLayerStoreHandler> getAssociatedType() {
            return TYPE;
        }
    }
}
