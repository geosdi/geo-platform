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
package org.geosdi.geoplatform.connector.server.security;

import net.jcip.annotations.Immutable;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.geosdi.geoplatform.connector.server.request.GPConnectorRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

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
    public GPHeaderSecurityConnector(@Nonnull(when = NEVER) Map<String, String> theHeaderParams) {
        checkArgument((theHeaderParams != null) && !(theHeaderParams.isEmpty()), "The HeaderParams must not be null or Empty Map.");
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
    public <C extends GPConnectorRequest, H extends HttpUriRequest> CloseableHttpResponse secure(@Nonnull(when = NEVER) C connectorRequest,
            @Nonnull(when = NEVER) H httpRequest) throws IOException {
        checkArgument(connectorRequest != null, "The Parameter connectorRequest must not be null.");
        checkArgument(httpRequest != null, "The Parameter httpRequest must not be null.");
        logger.debug("#########################{} is injecting values {} in Header Request.\n",
                this.getClass().getSimpleName(), this.headerParams);
        this.headerParams.entrySet()
                .stream()
                .forEach(entry -> httpRequest.addHeader(entry.getKey(), entry.getValue()));
        return connectorRequest.getClientConnection().execute(httpRequest);
    }
}