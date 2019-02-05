package org.geosdi.geoplatform.connector.server.request;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWMSDescribeLayerRequest<T> extends GPConnectorRequest<T> {

    String WMS_DESCRIBE_LAYER_BASE_REQUEST = "?SERVICE=WMS&REQUEST=DescribeLayer&VERSION=${version}&layers=${layers}";

    /**
     * @param theLayers
     * @return {@link GPWMSDescribeLayerRequest<T>}
     */
    GPWMSDescribeLayerRequest<T> withLayers(@Nonnull(when = When.NEVER) String... theLayers);
}