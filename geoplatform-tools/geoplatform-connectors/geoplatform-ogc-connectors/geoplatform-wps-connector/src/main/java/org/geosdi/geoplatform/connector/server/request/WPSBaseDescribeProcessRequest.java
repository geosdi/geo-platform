package org.geosdi.geoplatform.connector.server.request;

import lombok.Getter;
import lombok.Setter;
import org.geosdi.geoplatform.connector.server.GPServerConnector;

import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Setter
@Getter
public abstract class WPSBaseDescribeProcessRequest<T, Request> extends WPSBaseRequest<T, Request>
        implements WPSDescribeProcessRequest<T> {

    protected List<String> processIdentifiers;

    /**
     * @param server
     */
    protected WPSBaseDescribeProcessRequest(GPServerConnector server) {
        super(server);
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean isSetProcessIdentifiers() {
        return ((this.processIdentifiers != null) && !(this.processIdentifiers.isEmpty()));
    }
}
