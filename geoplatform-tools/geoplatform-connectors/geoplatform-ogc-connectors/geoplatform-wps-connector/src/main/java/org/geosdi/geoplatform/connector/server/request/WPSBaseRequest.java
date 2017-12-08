package org.geosdi.geoplatform.connector.server.request;

import lombok.Getter;
import lombok.Setter;
import org.geosdi.geoplatform.connector.server.GPServerConnector;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@Setter
public abstract class WPSBaseRequest<T, Request> extends WPSRequest<T, Request> implements GPWPSBaseRequest<T> {

    protected String language;

    /**
     * @param server
     */
    protected WPSBaseRequest(GPServerConnector server) {
        super(server);
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean isLanguageSet() {
        return ((this.language != null) && !(this.language.isEmpty()));
    }
}
