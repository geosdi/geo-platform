/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2024 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.geoserver.styles.base;

import com.google.common.io.CharStreams;
import org.apache.hc.core5.net.URIBuilder;
import org.geosdi.geoplatform.connector.geoserver.request.styles.base.GeoserverBaseDeleteStyleRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.exception.UnauthorizedException;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonDeleteConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.BufferedReader;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPGeoserverBaseDeleteStyleRequest<R extends GeoserverBaseDeleteStyleRequest> extends GPJsonDeleteConnectorRequest<Boolean, R> implements GeoserverBaseDeleteStyleRequest<R> {

    protected final ThreadLocal<String> style;
    protected final ThreadLocal<Boolean> purge;
    protected final ThreadLocal<Boolean> recurse;

    /**
     * @param theServerConnector
     * @param theJacksonSupport
     */
    protected GPGeoserverBaseDeleteStyleRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(theServerConnector, theJacksonSupport);
        this.style = withInitial(() -> null);
        this.purge = withInitial(() -> TRUE);
        this.recurse = withInitial(() -> TRUE);
    }

    /**
     * <p>Name of the style to delete.</p>
     *
     * @param theStyle
     * @return {@link R}
     */
    @Override
    public R withStyle(@Nonnull(when = NEVER) String theStyle) {
        this.style.set(theStyle);
        return this.self();
    }

    /**
     * <p>Specifies whether the underlying file containing the style should be deleted on disk. Default Value is {@link Boolean#TRUE}</p>
     *
     * @param thePurge
     * @return {@link R}
     */
    @Override
    public R withPurge(@Nullable Boolean thePurge) {
        this.purge.set(thePurge != null ? thePurge : TRUE);
        return this.self();
    }

    /**
     * <p>Removes references to the specified style in existing layers. Default Value is {@link Boolean#TRUE}</p>
     *
     * @param theRecurse
     * @return {@link R}
     */
    @Override
    public R withRecurse(@Nullable Boolean theRecurse) {
        this.recurse.set(theRecurse != null ? theRecurse : TRUE);
        return this.self();
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String styleName = this.style.get();
        checkArgument((styleName != null) && !(styleName.trim().isEmpty()), "The Parameter styleName mut not be null or an Empty String.");
        //        styleName = REPLACEMENT.replace(styleName);
        String recurse = this.recurse.get().toString();
        String purge = this.purge.get().toString();
        String baseURI = this.serverURI.toString();
        return new URIBuilder((baseURI.endsWith("/") ? baseURI.concat("styles/").concat(styleName) : baseURI.concat("/styles/").concat(styleName)))
                .addParameter("recurse", recurse)
                .addParameter("purge", purge)
                .build().toString();
    }

    /**
     * @param statusCode
     * @throws Exception
     */
    @Override
    protected void checkHttpResponseStatus(int statusCode) throws Exception {
        switch (statusCode) {
            case 401:
                throw new UnauthorizedException();
            case 405:
                throw new IllegalStateException("Method not allowed");
        }
    }

    /**
     * @param reader
     * @return {@link Boolean}
     * @throws Exception
     */
    @Override
    protected Boolean readInternal(BufferedReader reader) throws Exception {
        String value = CharStreams.toString(reader);
        return ((value != null) && (value.trim().isEmpty()) ? TRUE : FALSE);
    }

    /**
     * @return {@link Class<Boolean>}
     */
    @Override
    protected Class<Boolean> forClass() {
        return Boolean.class;
    }
}