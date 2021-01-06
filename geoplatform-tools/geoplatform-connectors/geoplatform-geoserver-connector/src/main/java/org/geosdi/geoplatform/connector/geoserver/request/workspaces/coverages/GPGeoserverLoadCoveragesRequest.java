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
package org.geosdi.geoplatform.connector.geoserver.request.workspaces.coverages;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.GPGeoserverAllCoverages;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.GPGeoserverCoverages;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.GPGeoserverEmptyCoverages;
import org.geosdi.geoplatform.connector.geoserver.request.GPGeoserverGetConnectorRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.BufferedReader;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class GPGeoserverLoadCoveragesRequest extends GPGeoserverGetConnectorRequest<GPGeoserverCoverages, GPGeoserverEmptyCoverages> implements GeoserverLoadCoveragesRequest {

    private final ThreadLocal<String> workspace;
    private final ThreadLocal<String> queryList;

    /**
     * @param server
     * @param theJacksonSupport
     */
    public GPGeoserverLoadCoveragesRequest(GPServerConnector server, JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport);
        this.workspace = withInitial(() -> null);
        this.queryList = withInitial(() -> "");
    }

    /**
     * @param theWorkspace
     * @return {@link GeoserverLoadCoveragesRequest}
     */
    @Override
    public GeoserverLoadCoveragesRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace) {
        this.workspace.set(theWorkspace);
        return this;
    }

    /**
     * <p>If the list parameter value is equal to “all” all the coverages available in the data source
     * (even the non published ones) will be returned. The Class returned is an istance
     * of {@link org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.GPGeoserverAllCoverages}  class.
     * </p>
     *
     * @param theQueryList
     * @return {@link GeoserverLoadCoveragesRequest}
     */
    @Override
    public GeoserverLoadCoveragesRequest withQueryList(@Nullable String theQueryList) {
        this.queryList.set(((theQueryList != null) && !(theQueryList.trim().isEmpty())) ? theQueryList : "");
        return this;
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String workspace = this.workspace.get();
        checkArgument((workspace != null) && !(workspace.trim().isEmpty()), "The Parameter workspace must not ne null or an empty string.");
        String queryList = this.queryList.get();
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspace).concat("/coverages.json").concat("?list=").concat(queryList)
                : baseURI.concat("/workspaces/").concat(workspace).concat("/coverages.json").concat("?list=").concat(queryList)));
    }

    /**
     * @param reader
     * @return {@link org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.GPGeoserverCoverages}
     * @throws Exception
     */
    @Override
    protected GPGeoserverCoverages readInternal(BufferedReader reader) throws Exception {
        String queryList = this.queryList.get();
        return ((queryList != null) && (queryList.equalsIgnoreCase("all"))
                ? this.jacksonSupport.getDefaultMapper().readValue(reader, GPGeoserverAllCoverages.class) : super.readInternal(reader));
    }

    /**
     * @return {@link Class<GPGeoserverEmptyCoverages>}
     */
    @Override
    protected Class<GPGeoserverEmptyCoverages> forEmptyResponse() {
        return GPGeoserverEmptyCoverages.class;
    }

    /**
     * @return {@link Class<GPGeoserverCoverages>}
     */
    @Override
    protected Class<GPGeoserverCoverages> forClass() {
        return GPGeoserverCoverages.class;
    }
}