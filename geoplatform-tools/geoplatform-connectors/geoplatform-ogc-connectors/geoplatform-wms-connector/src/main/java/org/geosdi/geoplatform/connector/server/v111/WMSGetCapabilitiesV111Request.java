package org.geosdi.geoplatform.connector.server.v111;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.server.GPWMSServerConnector;
import org.geosdi.geoplatform.connector.server.request.GPBaseWMSGetCapabilitiesRequest;
import org.geosdi.geoplatform.wms.v111.WMTMSCapabilities;

import javax.annotation.Nonnull;
import java.io.InputStream;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class WMSGetCapabilitiesV111Request extends GPBaseWMSGetCapabilitiesRequest<WMTMSCapabilities> implements GPWMSGetCapabilitiesV111Request {

    /**
     * @param server
     */
    WMSGetCapabilitiesV111Request(@Nonnull(when = NEVER) GPWMSServerConnector server) {
        super(server, WMS_JAXB_CONTEXT_V111);
    }

    /**
     * @param inputStream
     * @return {@link Object}
     * @throws Exception
     */
    @Override
    protected WMTMSCapabilities readInternal(@Nonnull(when = NEVER) InputStream inputStream) throws Exception {
        return (WMTMSCapabilities) getUnmarshaller().unmarshal(WMS_SAX_SOURCE_BUILDER.buildSource(inputStream));
    }
}