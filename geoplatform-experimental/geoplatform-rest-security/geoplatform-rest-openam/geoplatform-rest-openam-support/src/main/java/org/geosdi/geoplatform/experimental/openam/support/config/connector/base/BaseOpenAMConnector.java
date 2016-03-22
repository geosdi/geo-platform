package org.geosdi.geoplatform.experimental.openam.support.config.connector.base;

import com.google.common.base.Preconditions;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.annotation.Immutable;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.protocol.HttpContext;
import org.geosdi.geoplatform.experimental.openam.api.connector.GPOpenAMConnector;
import org.geosdi.geoplatform.experimental.openam.support.connector.request.BaseOpenAMRequest;
import org.geosdi.geoplatform.experimental.rs.security.connector.settings.GPConnectorSettings;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface BaseOpenAMConnector extends GPOpenAMConnector {

    /**
     * @param openAMConnectorSettings
     * @param <Settings>
     * @return {@link URIBuilder}
     * @throws Exception
     */
    default <Settings extends GPConnectorSettings, Request extends BaseOpenAMRequest> URIBuilder buildURI(Settings openAMConnectorSettings, Request openAMConnectorRequest) throws Exception {
        Preconditions.checkNotNull(openAMConnectorSettings, "The Ape2015 Connector Settings must not be null.");
        return new URIBuilder().setScheme(openAMConnectorSettings.getScheme())
                .setHost(openAMConnectorSettings.getHost())
                .setPath(openAMConnectorSettings.getPath().concat(openAMConnectorRequest.getPath()));
    }

    /**
     *
     */
    @Immutable
    class OpenAMHttpRequestRetryHandler implements HttpRequestRetryHandler {

        /**
         * the maximum number of retries after which the method must be blocked
         */
        private final int attemptsCount;

        /**
         * Default Constructor with a default Attempts value = 5
         */
        public OpenAMHttpRequestRetryHandler() {
            this(5);
        }

        public OpenAMHttpRequestRetryHandler(int theAttemptsCount) {
            this.attemptsCount = theAttemptsCount;
        }

        @Override
        public boolean retryRequest(IOException exception, int executionCount,
                HttpContext context) {
            if (context == null) {
                throw new IllegalArgumentException(
                        "Parameter HttpContext must not be null");
            }

            if (executionCount >= this.attemptsCount) {
                return false;
            }

            if (exception instanceof InterruptedIOException) {
                // Timeout
                return false;
            }
            if (exception instanceof UnknownHostException) {
                // Unknown host
                return false;
            }
            if (exception instanceof ConnectTimeoutException) {
                // Connection refused
                return false;
            }
            if (exception instanceof SSLException) {
                // SSL handshake exception
                return false;
            }
            HttpClientContext clientContext = HttpClientContext.adapt(context);
            HttpRequest request = clientContext.getRequest();
            boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
            return idempotent;
        }
    }
}
