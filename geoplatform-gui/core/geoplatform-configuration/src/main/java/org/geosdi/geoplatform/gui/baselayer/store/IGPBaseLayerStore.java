package org.geosdi.geoplatform.gui.baselayer.store;

import org.geosdi.geoplatform.gui.factory.baselayer.GPBaseLayerCreator;
import org.geosdi.geoplatform.gui.global.baselayer.GPBaseLayerValue;
import org.geosdi.geoplatform.gui.puregwt.baselayer.GPBaseLayerStoreHandler;
import org.gwtopenmaps.openlayers.client.layer.Layer;

import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPBaseLayerStore extends GPBaseLayerStoreHandler {

    /**
     * @return {@link V}
     */
    <V extends Layer> V getDefaultBaseLayer();

    /**
     * @param theKey
     * @param <V>
     * @param <K>
     * @return {@link V}
     */
    <V extends Layer, K extends GPBaseLayerValue> V findBaseLayer(K theKey);

    /**
     * @return {@link Map<GPBaseLayerValue, GPBaseLayerCreator>}
     */
    Map<GPBaseLayerValue, GPBaseLayerCreator> lookupBaseLayers();
}
