package org.geosdi.geoplatform.experimental.rs.security.connector.request;

import org.springframework.beans.factory.InitializingBean;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPConnectorRequest<Request extends GPConnectorRequest> extends InitializingBean, Serializable {

    /**
     * @return {@link String}
     */
    String getPath();

    /**
     * @param params
     * @return
     */
    Request setExtraPathParam(String... params);

    /**
     * @return {@link Request}
     */
    default Request self() {
        return (Request) this;
    }
}
