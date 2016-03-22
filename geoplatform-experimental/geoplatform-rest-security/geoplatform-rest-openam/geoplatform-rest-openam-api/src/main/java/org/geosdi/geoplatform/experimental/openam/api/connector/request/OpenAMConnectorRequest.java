package org.geosdi.geoplatform.experimental.openam.api.connector.request;

import org.springframework.beans.factory.InitializingBean;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface OpenAMConnectorRequest extends InitializingBean, Serializable {

    /**
     * @return {@link String}
     */
    String getPath();

    /**
     * @param params
     * @param <Request>
     * @return {@link Request}
     */
    <Request extends OpenAMConnectorRequest> Request setExtraPathParam(String... params);

    /**
     * @param <Request>
     * @return
     */
    default <Request extends OpenAMConnectorRequest> Request self() {
        return (Request) this;
    }
}
