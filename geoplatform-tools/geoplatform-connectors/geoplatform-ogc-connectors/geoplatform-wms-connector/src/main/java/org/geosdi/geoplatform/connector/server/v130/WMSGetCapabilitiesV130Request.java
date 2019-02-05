package org.geosdi.geoplatform.connector.server.v130;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.server.GPWMSServerConnector;
import org.geosdi.geoplatform.connector.server.request.GPBaseWMSGetCapabilitiesRequest;
import org.geosdi.geoplatform.wms.v130.WMSCapabilities;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class WMSGetCapabilitiesV130Request extends GPBaseWMSGetCapabilitiesRequest<WMSCapabilities> implements GPWMSGetCapabilitiesV130Request {

    /**
     * @param server
     */
    WMSGetCapabilitiesV130Request(@Nonnull(when = NEVER) GPWMSServerConnector server) {
        super(server, WMS_JAXB_CONTEXT_V130);
    }
}