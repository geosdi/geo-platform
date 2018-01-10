package org.geosdi.geoplatform.connector.server.security;

import net.jcip.annotations.Immutable;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.geosdi.geoplatform.connector.server.request.GPConnectorRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
public class GPHeaderSecurityConnector implements GPSecurityConnector {

    private static final Logger logger = LoggerFactory.getLogger(GPHeaderSecurityConnector.class);
    //
    private final Map<String, String> headerParams;

    /**
     * @param theHeaderParams
     */
    public GPHeaderSecurityConnector(Map<String, String> theHeaderParams) {
        checkArgument((theHeaderParams != null) && !(theHeaderParams.isEmpty()),
                "The HeaderParams must not be null or Empty Map.");
        this.headerParams = theHeaderParams;
    }

    /**
     * <p>Method to secure Connection</p>
     *
     * @param connectorRequest
     * @param httpRequest
     * @return CloseableHttpResponse
     * @throws {@link IOException}
     */
    @Override
    public <C extends GPConnectorRequest, H extends HttpUriRequest> CloseableHttpResponse secure(C connectorRequest,
            H httpRequest) throws IOException {
        logger.debug("#########################{} is injecting values {} in Header Request.\n",
                this.getClass().getSimpleName(), this.headerParams);
        this.headerParams.entrySet()
                .stream()
                .forEach(entry -> httpRequest.addHeader(entry.getKey(), entry.getValue()));
        return connectorRequest.getClientConnection().execute(httpRequest);
    }
}