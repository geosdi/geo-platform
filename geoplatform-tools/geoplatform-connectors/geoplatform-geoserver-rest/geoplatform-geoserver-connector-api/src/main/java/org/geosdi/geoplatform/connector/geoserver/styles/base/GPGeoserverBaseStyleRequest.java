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

import org.geosdi.geoplatform.connector.geoserver.exsist.GPGeoserverExsistRequest;
import org.geosdi.geoplatform.connector.geoserver.request.styles.base.GeoserverBaseStyleRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.TRUE;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPGeoserverBaseStyleRequest<T, R extends GeoserverBaseStyleRequest> extends GPGeoserverExsistRequest<T, R> implements GeoserverBaseStyleRequest<T, R> {

    private final ThreadLocal<String> styleName;
    private final ThreadLocal<Boolean> quietOnNotFound;

    /**
     * @param server
     * @param theJacksonSupport
     */
    protected GPGeoserverBaseStyleRequest(@Nonnull(when = NEVER) GPServerConnector server, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport);
        this.styleName = withInitial(() -> null);
        this.quietOnNotFound = withInitial(() -> TRUE);
    }

    /**
     * @param theStyleName
     * @return {@link R}
     */
    @Override
    public R withStyleName(@Nonnull(when = NEVER) String theStyleName) {
        this.styleName.set(theStyleName);
        return self();
    }

    /**
     * @param theQuietOnNotFound
     * @return {@link R}
     */
    @Override
    public R withQuietOnNotFound(@Nullable Boolean theQuietOnNotFound) {
        this.quietOnNotFound.set(theQuietOnNotFound);
        return self();
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String styleName = this.styleName.get();
        checkArgument(((styleName != null) && !(styleName.trim().isEmpty())), "The Parameter Style Name must not be null or an Empty String.");
        styleName = styleName.replaceAll("\\s", "+");
        String baseURI = this.serverURI.toString();
        String quietOnNotFound = this.quietOnNotFound.get().toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("styles/").concat(styleName).concat(".json").concat("?quietOnNotFound=").concat(quietOnNotFound)
                : baseURI.concat("/styles/").concat(styleName).concat(".json").concat("?quietOnNotFound=").concat(quietOnNotFound)));
    }
}