package org.geosdi.geoplatform.gui.puregwt.baselayer.event;

import org.geosdi.geoplatform.gui.factory.baselayer.GPBaseLayerCreator;
import org.geosdi.geoplatform.gui.global.baselayer.GPBaseLayerValue;
import org.geosdi.geoplatform.gui.puregwt.baselayer.GPBaseLayerStoreHandler;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class AddBaseLayerEvent extends GPBaseLayerStoreHandler.GPBaseLayerEvent {

    private final GPBaseLayerValue value;
    private final GPBaseLayerCreator layerCreator;

    public AddBaseLayerEvent(GPBaseLayerValue theValue, GPBaseLayerCreator theLayerCreator) {
        this.value = theValue;
        this.layerCreator = theLayerCreator;
    }

    @Override
    protected void dispatch(GPBaseLayerStoreHandler baseLayerStoreHandler) {
        baseLayerStoreHandler.registerBaseLayer(this.value, this.layerCreator);
    }
}
