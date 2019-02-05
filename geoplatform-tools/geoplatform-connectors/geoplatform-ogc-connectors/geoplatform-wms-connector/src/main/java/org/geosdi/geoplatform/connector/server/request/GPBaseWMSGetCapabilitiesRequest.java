package org.geosdi.geoplatform.connector.server.request;

import org.geosdi.geoplatform.connector.WMSVersion;
import org.geosdi.geoplatform.connector.server.GPWMSServerConnector;
import org.geosdi.geoplatform.jaxb.GPBaseJAXBContext;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPBaseWMSGetCapabilitiesRequest<T> extends GPWMSBaseGetRequest<T> implements GPWMSGetCapabilitiesRequest<T> {

    private final WMSVersion version;

    /**
     * @param server
     */
    protected GPBaseWMSGetCapabilitiesRequest(@Nonnull(when = NEVER) GPWMSServerConnector server, @Nonnull(when = NEVER) GPBaseJAXBContext theWMSJAXBContext) {
        super(server, theWMSJAXBContext);
        this.version = server.getVersion();
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String baseURI = this.serverURI.toString();
        return baseURI.concat(WMS_GET_CAPABILITIES_BASE_REQUEST).concat(this.version.getVersion());
    }
}