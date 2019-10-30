package org.geosdi.geoplatform.experimental.el.rest.api.info;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPElasticSearchRestInfo extends Serializable {

    /**
     * @return {@link String}
     */
    String getNodeName();

    /**
     * @return {@link String}
     */
    String getClusterName();

    /**
     * @return {@link String}
     */
    String getClusterUUID();
}