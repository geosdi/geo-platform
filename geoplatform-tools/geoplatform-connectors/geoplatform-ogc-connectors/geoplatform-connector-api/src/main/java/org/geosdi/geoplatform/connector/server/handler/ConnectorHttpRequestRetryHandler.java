/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.connector.server.handler;

import java.io.IOException;
import java.io.InterruptedIOException;
import javax.net.ssl.SSLException;
import net.jcip.annotations.Immutable;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
public class ConnectorHttpRequestRetryHandler implements HttpRequestRetryHandler {

    /**
     * the maximum number of retries after which the method must be blocked
     */
    private final int attemptsCount;

    /**
     * Default Constructor with a default Attempts value = 5
     */
    public ConnectorHttpRequestRetryHandler() {
        this(5);
    }

    public ConnectorHttpRequestRetryHandler(int theAttemptsCount) {
        this.attemptsCount = theAttemptsCount;
    }

    @Override
    public boolean retryRequest(IOException exception, int executionCount,
            HttpContext context) {
        if (context == null) {
            throw new IllegalArgumentException(
                    "Parameter HttpContext must not be null");
        }

        if (exception == null) {
            throw new IllegalArgumentException(
                    "Parameter Exception must not be null");
        }

        if (executionCount >= this.attemptsCount) {
            return false;
        }

        if (exception instanceof NoHttpResponseException) {
            // Retry if the server dropped connection on us
            return true;
        }
        if (exception instanceof SSLException) {
            // Do not retry on SSL handshake exception
            return false;
        }

        if (exception instanceof InterruptedIOException) {
            // Timeout
            return true;
        }

        HttpRequest request = (HttpRequest) context.getAttribute(
                ExecutionContext.HTTP_REQUEST);

        if (processIdempotent(request)) {
            return true;
        }

        return false;
    }

    private boolean processIdempotent(HttpRequest request) {
        return !(request instanceof HttpEntityEnclosingRequest);
    }
}
