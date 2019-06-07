package org.geosdi.geoplatform.services.request;

import org.geosdi.geoplatform.connector.server.request.WMSFeatureInfoFormat;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWMSGetFeatureInfoResponseFormat extends Serializable {

    /**
     * @return {@link WMSFeatureInfoFormat}
     */
    WMSFeatureInfoFormat toWMSFeatureInfoFormat();

    /**
     * @return {@link GPWMSGetFeatureInfoReponseErrorStrategy}
     */
    GPWMSGetFeatureInfoReponseErrorStrategy toWMSGetFeatureInfoResponseErrorStrategy();
}