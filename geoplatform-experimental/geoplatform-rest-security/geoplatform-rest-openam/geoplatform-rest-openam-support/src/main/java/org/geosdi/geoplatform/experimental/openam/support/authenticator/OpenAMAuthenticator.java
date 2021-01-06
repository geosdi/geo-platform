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
package org.geosdi.geoplatform.experimental.openam.support.authenticator;

import org.geosdi.geoplatform.exception.NotAuthorizedFault;
import org.geosdi.geoplatform.exception.ServerInternalFault;
import org.geosdi.geoplatform.experimental.openam.api.annotation.GPOpenAMSupport;
import org.geosdi.geoplatform.experimental.openam.api.authenticator.filter.BaseOpenAmAuthenticator;
import org.geosdi.geoplatform.experimental.openam.api.connector.GPOpenAMConnector;
import org.geosdi.geoplatform.experimental.openam.api.model.validate.IOpenAMValidateToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import static javax.ws.rs.core.Response.status;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@GPOpenAMSupport
@Provider
@Priority(Priorities.AUTHENTICATION)
public class OpenAMAuthenticator extends BaseOpenAmAuthenticator {

    private static final Logger logger = LoggerFactory.getLogger(OpenAMAuthenticator.class);
    //
    private final GPOpenAMConnector openAMConnector;

    /**
     * @param theOpenAMConnector
     */
    public OpenAMAuthenticator(@Nonnull(when = NEVER) GPOpenAMConnector theOpenAMConnector) {
        checkArgument(theOpenAMConnector != null, "The Parameter openAmConnector must not be null.");
        this.openAMConnector = theOpenAMConnector;
    }

    /**
     * Filter method called before a request has been dispatched to a resource.
     * <p/>
     * <p>
     * Filters in the filter chain are ordered according to their {@code javax.annotation.Priority}
     * class-level annotation value.
     * If a request filter produces a response by calling {@link ContainerRequestContext#abortWith}
     * method, the execution of the (either pre-match or post-match) request filter
     * chain is stopped and the response is passed to the corresponding response
     * filter chain (either pre-match or post-match). For example, a pre-match
     * caching filter may produce a response in this way, which would effectively
     * skip any post-match request filters as well as post-match response filters.
     * Note however that a responses produced in this manner would still be processed
     * by the pre-match response filter chain.
     * </p>
     *
     * @param requestContext request context.
     * @throws IOException if an I/O exception occurs.
     * @see javax.ws.rs.container.PreMatching
     */
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        logger.debug("::::::::::::::::::::::::{} is validating --------------> Path : {}\n", getAuthenticatorType(),
                requestContext.getUriInfo().getAbsolutePath());
        // Get the HTTP Authorization header from the request
        String authorizationHeader = requestContext.getHeaderString(AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("OpenAM ")) {
            throw new NotAuthorizedFault("Authorization header must be provided");
        }
        String token = authorizationHeader.substring("OpenAM".length()).trim();
        try {
            validateToken(token);
        } catch (Exception ex) {
            requestContext.abortWith(status(UNAUTHORIZED).build());
        }
    }

    /**
     * @param token
     * @throws Exception
     */
    @Override
    protected void validateToken(@Nonnull(when = NEVER) String token) throws Exception {
        IOpenAMValidateToken validateTokenResponse = this.openAMConnector.validateToken(token);
        if (!validateTokenResponse.isValid()) {
            throw new ServerInternalFault("Token is not valid");
        } else {
            logger.debug("::::::::::::::::::::::::Token : {} is valid.\n", token);
        }
    }
}
