package org.geosdi.geoplatform.experimental.openam.support.connector.request;

import com.google.common.base.Preconditions;
import org.apache.http.client.utils.URIBuilder;
import org.geosdi.geoplatform.experimental.openam.api.connector.request.OpenAMConnectorRequest;
import org.geosdi.geoplatform.experimental.openam.api.connector.request.parameter.RequestParameter;
import org.geosdi.geoplatform.experimental.rs.security.connector.request.GPConnectorRequest;

import java.util.Arrays;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface BaseOpenAMRequest<Request extends BaseOpenAMRequest> extends GPConnectorRequest<Request> {

    /**
     * @return {@link OpenAMRequestType}
     */
    OpenAMRequestType getRequestType();

    /**
     * @param uriBuilder
     * @return {@link URIBuilder}
     * @throws Exception
     */
    default URIBuilder addRequestParameter(URIBuilder uriBuilder, RequestParameter... value) throws Exception {
        Preconditions.checkNotNull(uriBuilder, "The Parameter URIBuilder must not be null.");
        Preconditions.checkArgument(((value != null) && (value.length > 0)),
                "The Parameter Value must not be null");
        Arrays.stream(value).forEach(p -> uriBuilder.addParameter(p.getkey(), p.getValue()));
        return uriBuilder;
    }

    /**
     * @param params
     * @return {@link OpenAMConnectorRequest}
     */
    default Request setExtraPathParam(String... params) {
        return self();
    }

    /**
     *
     */
    enum OpenAMRequestType {
        VALIDATE_TOKEN,
        AUTHENTICATE,
        SERVER_INFO,
        CREATE_USER,
        UPDATE_USER,
        SEARCH_USERS,
        DELETE_USER,
        UPDATE_GROUP_ADD_USER,
        LOGOUT;
    }
}
