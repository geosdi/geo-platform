package org.geosdi.geoplatform.experimental.openam.support.authenticator;

import org.geosdi.geoplatform.exception.NotAuthorizedFault;
import org.geosdi.geoplatform.experimental.openam.api.annotation.GPOpenAMSupport;
import org.geosdi.geoplatform.experimental.openam.api.authenticator.filter.BaseOpenAmAuthenticator;
import org.geosdi.geoplatform.experimental.openam.api.connector.GPOpenAMConnector;
import org.geosdi.geoplatform.experimental.openam.api.model.validate.IOpenAMValidateToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

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

    public OpenAMAuthenticator(GPOpenAMConnector theOpenAMConnector) {
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
        logger.debug("::::::::::::::::::::::::{} is validating --------------> Path : {}\n",
                getAuthenticatorType(), requestContext.getUriInfo().getAbsolutePath());
        // Get the HTTP Authorization header from the request
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("OpenAM ")) {
            throw new NotAuthorizedFault("Authorization header must be provided");
        }
        String token = authorizationHeader.substring("OpenAM".length()).trim();
        try {
            validateToken(token);
        } catch (Exception ex) {
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    /**
     * @param token
     * @throws Exception
     */
    @Override
    protected void validateToken(String token) throws Exception {
        IOpenAMValidateToken validateTokenResponse = this.openAMConnector.validateToken(token);
        if (!validateTokenResponse.isValid()) {
            throw new IllegalStateException("Token is not valid");
        } else {
            logger.debug("::::::::::::::::::::::::Token : {} is valid.\n", token);
        }
    }
}
