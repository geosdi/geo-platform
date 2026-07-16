/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.server.request;

import com.google.common.io.CharStreams;
import org.apache.commons.io.IOUtils;
import org.apache.hc.client5.http.auth.CredentialsStore;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.exception.GPConnectorHttpStatusException;
import org.geosdi.geoplatform.connector.server.exception.ResourceNotFoundException;
import org.geosdi.geoplatform.connector.server.exception.UnauthorizedException;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

import static com.google.common.base.Charsets.UTF_8;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.net.UrlEscapers.urlPathSegmentEscaper;
import static javax.annotation.meta.When.NEVER;
import static org.apache.hc.client5.http.config.RequestConfig.custom;
import static org.geosdi.geoplatform.connector.server.security.GPSecurityConnector.MOCK_SECURITY;

/**
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public abstract class GPAbstractConnectorRequest<T> implements GPConnectorRequest<T> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private final GPServerConnector serverConnector;
    protected final URI serverURI;
    protected final GPSecurityConnector securityConnector;
    protected final CloseableHttpClient clientConnection;
    private final CredentialsStore credentialStore;
    private final RequestConfig requestConfig;

    /**
     * @param theServerConnector
     */
    protected GPAbstractConnectorRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector) {
        checkArgument(theServerConnector != null, "The Parameter GPServerConnector must not be null.");
        checkArgument(theServerConnector.getCredentialsStore() != null, "The Parameter CredentialProvider must not be null.");
        checkArgument(theServerConnector.getURI() != null, "The Parameter Server URI must not be null.");
        checkArgument(theServerConnector.getClientConnection() != null, "The Parameter Client Connection  must not be null.");
        this.serverConnector = theServerConnector;
        this.clientConnection = this.serverConnector.getClientConnection();
        this.serverURI = this.serverConnector.getURI();
        this.credentialStore = this.serverConnector.getCredentialsStore();
        this.securityConnector = (this.serverConnector.getSecurityConnector() == null ? MOCK_SECURITY : this.serverConnector.getSecurityConnector());
        this.requestConfig = this.createRequestConfig();
    }

    /**
     * <p>
     *   Setting basic configuration for HttpParams
     * </p>
     *
     * @return RequestConfig
     */
    protected RequestConfig prepareRequestConfig() {
        return this.requestConfig;
    }

    /**
     * @param statusCode
     * @throws Exception
     */
    protected void checkHttpResponseStatus(int statusCode) throws Exception {
        switch (statusCode) {
            case 401 -> throw new UnauthorizedException();
            case 404 -> throw new ResourceNotFoundException();
            case 405 -> throw new IllegalStateException("Method not allowed");
        }
    }

    /**
     * Validates the HTTP status of the response before its body is consumed. Request-specific status
     * mappings are applied first via {@link #checkHttpResponseStatus(int)}; any remaining status
     * outside the {@code 2xx} success family is reported as a {@link GPConnectorHttpStatusException}
     * carrying the status code and the raw error body, instead of letting the body be parsed as a
     * valid payload (which would surface as a misleading generic parsing error). Successful ({@code
     * 2xx}) responses pass through untouched, leaving the entity available for reading.
     *
     * @param httpResponse
     */
    protected void verifyHttpResponseStatus(ClassicHttpResponse httpResponse) {
        int statusCode = httpResponse.getCode();
        try {
            this.checkHttpResponseStatus(statusCode);
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        if ((statusCode < 200) || (statusCode > 299)) {
            throw new GPConnectorHttpStatusException(statusCode, this.readErrorBody(httpResponse));
        }
    }

    /**
     * @param httpResponse
     * @return the response body as a {@link String}, or an empty String if it cannot be read.
     */
    protected String readErrorBody(ClassicHttpResponse httpResponse) {
        try {
            HttpEntity responseEntity = httpResponse.getEntity();
            return ((responseEntity != null) ? new String(IOUtils.toByteArray(responseEntity.getContent()), UTF_8) : "");
        } catch (Exception ex) {
            logger.warn("###############################Unable to read error response body for Request : {} cause : {}\n", this.getClass().getSimpleName(), ex.getMessage());
            return "";
        }
    }

    /**
     * Builds a URI by appending the given path segments to the server base URI, joining them with
     * {@code '/'} and URL-encoding each segment (so that names containing spaces or reserved
     * characters produce a valid, unambiguous URI). Trailing/leading slashes on the base are
     * normalized. Query parameters, if any, must be appended by the caller to the returned value.
     *
     * @param segments the raw (unencoded) path segments; none may be {@code null}
     * @return the resolved URI as a {@link String}
     */
    protected String resolvePath(@Nonnull(when = NEVER) String... segments) {
        checkArgument(segments != null, "The Parameter segments must not be null.");
        String base = this.serverURI.toString();
        StringBuilder builder = new StringBuilder(base.endsWith("/") ? base.substring(0, base.length() - 1) : base);
        for (String segment : segments) {
            checkArgument(segment != null, "A path segment must not be null.");
            builder.append('/').append(urlPathSegmentEscaper().escape(segment));
        }
        return builder.toString();
    }

    /**
     * URL-encodes a single path segment (e.g. a user-supplied resource name) so it can be safely
     * embedded in a URI. Use it when a fixed path fragment cannot be expressed as discrete segments
     * for {@link #resolvePath(String...)}.
     *
     * @param segment the raw (unencoded) path segment
     * @return the URL-encoded segment
     */
    protected String escapePathSegment(@Nonnull(when = NEVER) String segment) {
        checkArgument(segment != null, "The Parameter segment must not be null.");
        return urlPathSegmentEscaper().escape(segment);
    }

    /**
     * @return {@link RequestConfig}
     */
    protected RequestConfig createRequestConfig() {
        return custom()
                .setCookieSpec(this.serverConnector.getPooledConnectorConfig().getCookieSpec().toCookieSpec())
                .setConnectionRequestTimeout(this.serverConnector.getPooledConnectorConfig().getRequestConnectionTimeout())
                .setResponseTimeout(this.serverConnector.getPooledConnectorConfig().getResponseConnectionTimeout())
                .setRedirectsEnabled(this.serverConnector.getPooledConnectorConfig().isRedirectsEnabled())
                .setConnectionKeepAlive(this.serverConnector.getPooledConnectorConfig().getConnectionKeepAlive())
                .setMaxRedirects(this.serverConnector.getPooledConnectorConfig().getMaxRedirect())
                .build();
    }

    /**
     * @return {@link URI}
     */
    @Override
    public URI getURI() {
        return this.serverURI;
    }

    /**
     * @return {@link CloseableHttpClient}
     */
    @Override
    public CloseableHttpClient getClientConnection() {
        return this.clientConnection;
    }

    /**
     * @return {@link CredentialsStore}
     */
    @Override
    public CredentialsStore getCredentialsStore() {
        return this.credentialStore;
    }

    /**
     * @param httpResponse
     * @return {@link T}
     */
    protected T internalResponseAsEntity(ClassicHttpResponse httpResponse) {
        int statusCode = httpResponse.getCode();
        logger.debug("###############################STATUS_CODE : {} for Request : {}\n", statusCode, this.getClass().getSimpleName());
        this.verifyHttpResponseStatus(httpResponse);
        try {
            HttpEntity responseEntity = httpResponse.getEntity();
            return ((statusCode == 204) || (responseEntity == null)) ? null : this.readInternal(responseEntity.getContent());
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @param httpResponse
     * @return {@link String}
     */
    protected String internalResponseAsString(ClassicHttpResponse httpResponse) {
        int statusCode = httpResponse.getCode();
        logger.debug("###############################STATUS_CODE : {} for Request : {}\n", statusCode, this.getClass().getSimpleName());
        this.verifyHttpResponseStatus(httpResponse);
        try {
            HttpEntity responseEntity = httpResponse.getEntity();
            return statusCode == 204 || responseEntity == null ? "" : CharStreams.toString(new InputStreamReader(responseEntity.getContent(), UTF_8));
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @param httpResponse
     * @return {@link InputStream}
     */
    protected InputStream internalResponseAsStream(ClassicHttpResponse httpResponse) {
        int statusCode = httpResponse.getCode();
        logger.debug("###############################STATUS_CODE : {} for Request : {}\n", statusCode, this.getClass().getSimpleName());
        this.verifyHttpResponseStatus(httpResponse);
        try {
            HttpEntity responseEntity = httpResponse.getEntity();
            return statusCode == 204 || responseEntity == null ? null : new ByteArrayInputStream(IOUtils.toByteArray(responseEntity.getContent()));
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @param inputStream
     * @return {@link T}
     * @throws Exception
     */
    protected abstract T readInternal(@Nonnull(when = NEVER) InputStream inputStream) throws Exception;
}