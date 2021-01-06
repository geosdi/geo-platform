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
package org.geosdi.geoplatform.connector.geoserver.request.datastores;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.geoserver.model.datastores.GPGeoserverLoadDatastore;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonGetConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class GPGeoserverLoadDatastoreRequest extends GPJsonGetConnectorRequest<GPGeoserverLoadDatastore> implements GeoserverLoadDatastoreRequest {

    private final ThreadLocal<String> workspaceName;
    private final ThreadLocal<String> storeName;
    private final ThreadLocal<Boolean> quietNotFound;

    /**
     * @param server
     * @param theJacksonSupport
     */
    public GPGeoserverLoadDatastoreRequest(@Nonnull(when = NEVER) GPServerConnector server, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport);
        this.workspaceName = withInitial(() -> null);
        this.storeName = withInitial(() -> null);
        this.quietNotFound = withInitial(() -> FALSE);
    }

    /**
     * @param theWorkspaceName
     */
    @Override
    public GeoserverLoadDatastoreRequest withWorkspaceName(String theWorkspaceName) {
        this.workspaceName.set(theWorkspaceName);
        return this;
    }

    /**
     * @param theStoreName
     */
    @Override
    public GeoserverLoadDatastoreRequest withStoreName(String theStoreName) {
        this.storeName.set(theStoreName);
        return this;
    }

    /**
     * <p>The quietOnNotFound parameter avoids logging an exception when the data store is not present.
     * Note that 404 status code will still be returned.
     * </p>
     *
     * @param theQuietNotFound
     */
    @Override
    public GeoserverLoadDatastoreRequest withQuietNotFound(Boolean theQuietNotFound) {
        this.quietNotFound.set((theQuietNotFound != null) ? theQuietNotFound : FALSE);
        return this;
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String workspaceName = this.workspaceName.get();
        checkArgument((workspaceName != null) && !(workspaceName.trim().isEmpty()),
                "The Parameter workspaceName must not be null or an Empty String.");
        String storeName = this.storeName.get();
        checkArgument((storeName != null) && !(storeName.trim().isEmpty()),
                "The Parameter storeName must not be null or an Empty String.");
        String baseURI = this.serverURI.toString();
        String quietNotFound = this.quietNotFound.get().toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspaceName).concat("/datastores/")
                .concat(storeName).concat("?quietOnNotFound=").concat(quietNotFound)
                : baseURI.concat("/workspaces/").concat(workspaceName).concat("/datastores/")
                .concat(storeName).concat("?quietOnNotFound=").concat(quietNotFound)));
    }

    /**
     * @return {@link Class<GPGeoserverLoadDatastore>}
     */
    @Override
    protected Class<GPGeoserverLoadDatastore> forClass() {
        return GPGeoserverLoadDatastore.class;
    }
}