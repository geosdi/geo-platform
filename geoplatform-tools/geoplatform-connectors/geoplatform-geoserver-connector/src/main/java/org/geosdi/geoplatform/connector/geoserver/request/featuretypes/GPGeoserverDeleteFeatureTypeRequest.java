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
package org.geosdi.geoplatform.connector.geoserver.request.featuretypes;

import com.google.common.io.CharStreams;
import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
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
@ThreadSafe
public class GPGeoserverDeleteFeatureTypeRequest extends GPJsonDeleteConnectorRequest<Boolean> implements GeoserverDeleteFeatureTypeRequest {

    private final ThreadLocal<String> workspace;
    private final ThreadLocal<String> store;
    private final ThreadLocal<String> featureTypeName;
    private final ThreadLocal<Boolean> recurse;

    /**
     * @param theServerConnector
     * @param theJacksonSupport
     */
    public GPGeoserverDeleteFeatureTypeRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(theServerConnector, theJacksonSupport);
        this.workspace = withInitial(() -> null);
        this.store = withInitial(() -> null);
        this.featureTypeName = withInitial(() -> null);
        this.recurse = withInitial(() -> FALSE);
    }

    /**
     * @param theWorkspace
     * @return {@link GeoserverDeleteFeatureTypeRequest}
     */
    @Override
    public GeoserverDeleteFeatureTypeRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace) {
        this.workspace.set(theWorkspace);
        return this;
    }

    /**
     * @param theStore
     * @return {@link GeoserverDeleteFeatureTypeRequest}
     */
    @Override
    public GeoserverDeleteFeatureTypeRequest withStore(@Nonnull(when = NEVER) String theStore) {
        this.store.set(theStore);
        return this;
    }

    /**
     * @param theFeatureTypeName
     * @return {@link GeoserverDeleteFeatureTypeRequest}
     */
    @Override
    public GeoserverDeleteFeatureTypeRequest withFeatureTypeName(@Nonnull(when = NEVER) String theFeatureTypeName) {
        this.featureTypeName.set(theFeatureTypeName);
        return this;
    }

    /**
     * <p>Default value is {@link Boolean#FALSE}</p>
     *
     * @param theRecurse
     * @return {@link GeoserverDeleteFeatureTypeRequest}
     */
    @Override
    public GeoserverDeleteFeatureTypeRequest withRecurse(@Nullable Boolean theRecurse) {
        this.recurse.set((theRecurse != null) ? theRecurse : FALSE);
        return this;
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String workspaceName = this.workspace.get();
        checkArgument((workspaceName != null) && !(workspaceName.trim().isEmpty()), "The Parameter workspace mut not be null or an Empty String.");
        String datastoreName = this.store.get();
        checkArgument((datastoreName != null) && !(datastoreName.trim().isEmpty()), "The Parameter store mut not be null or an Empty String.");
        String featureTypeName = this.featureTypeName.get();
        checkArgument((featureTypeName != null) && !(featureTypeName.trim().isEmpty()), "The Parameter featureTypeName mut not be null or an Empty String.");
        String recurse = this.recurse.get().toString();
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspaceName).concat("/datastores/").concat(datastoreName).concat("/featuretypes/").concat(featureTypeName).concat("?recurse=").concat(recurse)
                : baseURI.concat("/workspaces/").concat(workspaceName).concat("/datastores/").concat(datastoreName).concat("/featuretypes/").concat(featureTypeName).concat("?recurse=").concat(recurse)));
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