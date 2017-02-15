package org.geosdi.geoplatform.gui.baselayer.store;

import org.geosdi.geoplatform.gui.factory.baselayer.GPBaseLayerCreator;
import org.geosdi.geoplatform.gui.global.baselayer.GPBaseLayerValue;
import org.gwtopenmaps.openlayers.client.layer.Layer;

import java.util.Collections;
import java.util.Map;
import java.util.logging.Logger;

import static org.geosdi.geoplatform.gui.global.enumeration.BaseLayerValue.GOOGLE_SATELLITE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPBaseLayerStore extends AbstractBaseLayerStore {

    private static final Logger logger = Logger.getLogger("GPBaseLayerStore");

    public GPBaseLayerStore() {
        for (Map.Entry<GPBaseLayerValue, GPBaseLayerCreator> entry : this.baseLayerMap.entrySet()) {
            logger.info("###########################KEY : " + entry.getKey());
        }
    }

    /**
     * @return {@link Layer}
     */
    @Override
    public Layer getDefaultBaseLayer() {
        return this.findBaseLayer(GOOGLE_SATELLITE);
    }

    /**
     * @param theKey
     * @return {@link Layer}
     */
    @Override
    public Layer findBaseLayer(GPBaseLayerValue theKey) {
        return (baseLayerMap.get(theKey) != null)
                ? baseLayerMap.get(theKey).createBaseLayer() : null;
    }

    /**
     * @return {@link Map <GPBaseLayerValue,  GPBaseLayerCreator >}
     */
    @Override
    public Map<GPBaseLayerValue, GPBaseLayerCreator> lookupBaseLayers() {
        return Collections.unmodifiableMap(this.baseLayerMap);
    }

    /**
     * @param value
     * @param layerCreator
     */
    @Override
    public void registerBaseLayer(GPBaseLayerValue value, GPBaseLayerCreator layerCreator) {
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@REGISTER : " + baseLayerMap.containsKey(value));
        this.baseLayerMap.put(value, layerCreator);
    }

    /**
     * @param value
     */
    @Override
    public void removeBaseLayer(GPBaseLayerValue value) {
        if (this.baseLayerMap.containsKey(value))
            this.baseLayerMap.remove(value);
    }
}
