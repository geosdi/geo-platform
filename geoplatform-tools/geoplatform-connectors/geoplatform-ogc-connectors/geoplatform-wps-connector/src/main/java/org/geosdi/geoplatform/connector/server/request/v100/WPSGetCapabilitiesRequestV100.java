package org.geosdi.geoplatform.connector.server.request.v100;

import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.WPSGetCapabilitiesRequest;
import org.geosdi.geoplatform.connector.server.request.WPSRequest;
import org.geosdi.geoplatform.xml.wps.v100.GetCapabilities;
import org.geosdi.geoplatform.xml.wps.v100.WPSCapabilitiesType;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WPSGetCapabilitiesRequestV100 extends WPSRequest<WPSCapabilitiesType> implements WPSGetCapabilitiesRequest<WPSCapabilitiesType> {

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
    protected Object createRequest() throws Exception {
        return new GetCapabilities();
    }
}
