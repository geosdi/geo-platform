package org.geosdi.geoplatform.connector.server.request;

import org.geosdi.geoplatform.support.bridge.implementor.GPImplementor;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWMSFeatureInfoFormat extends GPImplementor.GPImplementorKey<WMSFeatureInfoFormat> {

    /**
     * @return {@link String}
     */
    String getFormat();
}