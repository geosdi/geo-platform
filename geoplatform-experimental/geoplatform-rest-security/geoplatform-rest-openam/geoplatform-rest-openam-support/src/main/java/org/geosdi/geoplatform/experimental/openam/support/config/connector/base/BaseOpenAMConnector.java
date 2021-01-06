/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.experimental.openam.support.config.connector.base;

import com.google.common.base.Preconditions;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import net.jcip.annotations.Immutable;
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
