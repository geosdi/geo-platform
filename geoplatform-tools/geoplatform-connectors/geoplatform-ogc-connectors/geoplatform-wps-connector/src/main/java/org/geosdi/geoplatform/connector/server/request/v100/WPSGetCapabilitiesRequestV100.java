package org.geosdi.geoplatform.connector.server.request.v100;

import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.WPSBaseRequest;
import org.geosdi.geoplatform.connector.server.request.WPSGetCapabilitiesRequest;
import org.geosdi.geoplatform.xml.wps.v100.GetCapabilities;
import org.geosdi.geoplatform.xml.wps.v100.WPSCapabilitiesType;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WPSGetCapabilitiesRequestV100 extends WPSBaseRequest<WPSCapabilitiesType, GetCapabilities>
        implements WPSGetCapabilitiesRequest<WPSCapabilitiesType> {

    /**
     * @param server
     */
    public WPSGetCapabilitiesRequestV100(GPServerConnector server) {
        super(server);
    }

    /**
     * @return {@link Object}
     * @throws Exception
     */
    @Override
    protected GetCapabilities createRequest() throws Exception {
        GetCapabilities request = new GetCapabilities();
        if (this.isLanguageSet())
            request.setLanguage(this.language);
        return request;
    }
}
