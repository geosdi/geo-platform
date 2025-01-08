/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.geoserver.namespaces;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.geoserver.model.namespace.GPGeoserverSingleNamespace;
import org.geosdi.geoplatform.connector.geoserver.request.namespaces.GeoserverNamespaceRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonGetConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class GPGeoserverNamespaceRequest extends GPJsonGetConnectorRequest<GPGeoserverSingleNamespace, GeoserverNamespaceRequest> implements GeoserverNamespaceRequest {

    private final ThreadLocal<String> prefix;

    /**
     * @param server
     * @param theJacksonSupport
     */
    public GPGeoserverNamespaceRequest(@Nonnull(when = NEVER) GPServerConnector server, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport);
        this.prefix = withInitial(() -> null);
    }

    /**
     * @param thePrefix
     * @return {@link GeoserverNamespaceRequest}
     */
    public GeoserverNamespaceRequest withPrefix(@Nonnull(when = NEVER) String thePrefix) {
        this.prefix.set(thePrefix);
        return self();
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String prefix = this.prefix.get();
        checkArgument(((prefix != null) && !(prefix.isEmpty())), "The Parameter prefix must not be null or an Empty String.");
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("namespaces/").concat(prefix).concat(".json")
                : baseURI.concat("/namespaces/").concat(prefix).concat(".json")));
    }

    /**
     * @return {@link Class<GPGeoserverSingleNamespace>}
     */
    @Override
    protected Class<GPGeoserverSingleNamespace> forClass() {
        return GPGeoserverSingleNamespace.class;
    }
}