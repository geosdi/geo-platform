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

import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.category.GPGeoserverFeatureTypeCategory;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.wrapper.GPGeoserverEmptyFeatureTypeWrapper;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.wrapper.GPGeoserverFeatureTypeWrapper;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.wrapper.IGPGeoserverEmptyFeatureTypeWrapper;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.wrapper.IGPGeoserverFeatureTypeWrapper;
import org.geosdi.geoplatform.connector.geoserver.request.GPGeoserverGetConnectorRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.InputStream;

import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.geoserver.model.featuretypes.category.GPGeoserverFeatureTypeCategory.all;
import static org.geosdi.geoplatform.connector.geoserver.model.featuretypes.wrapper.IGPGeoserverFeatureTypeWrapper.of;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
abstract class GPGeoserverLoadFeatureTypesRequest<Request extends GeoserverLoadFeatureTypesRequest> extends GPGeoserverGetConnectorRequest<GPGeoserverFeatureTypeWrapper, GPGeoserverEmptyFeatureTypeWrapper> implements GeoserverLoadFeatureTypesRequest<Request> {

    protected final ThreadLocal<String> workspace;
    protected final ThreadLocal<GPGeoserverFeatureTypeCategory> featureTypeCategory;

    /**
     * @param server
     * @param theJacksonSupport
     */
    GPGeoserverLoadFeatureTypesRequest(GPServerConnector server, JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport);
        this.workspace = withInitial(() -> null);
        this.featureTypeCategory = withInitial(() -> all);
    }

    /**
     * @param theWorkspace the name of the Workspace
     * @return {@link Request}
     */
    @Override
    public Request withWorkspace(@Nonnull(when = NEVER) String theWorkspace) {
        this.workspace.set(theWorkspace);
        return self();
    }

    /**
     * @param theFeatureTypeCategory
     * @return {@link Request}
     */
    @Override
    public Request withFeatureTypeCategory(@Nullable GPGeoserverFeatureTypeCategory theFeatureTypeCategory) {
        this.featureTypeCategory.set((theFeatureTypeCategory != null) ? theFeatureTypeCategory : all);
        return self();
    }

    /**
     * @param reader
     * @return {@link GPGeoserverFeatureTypeWrapper}
     * @throws Exception
     */
    @Override
    protected GPGeoserverFeatureTypeWrapper readInternal(BufferedReader reader) throws Exception {
        GPGeoserverFeatureTypeCategory featureTypeCategory = this.featureTypeCategory.get();
        return of(this.jacksonSupport.getDefaultMapper().readValue(reader, featureTypeCategory.toModel()));
    }

    /**
     * @param inputStream
     * @return {@link GPGeoserverEmptyFeatureTypeWrapper}
     * @throws Exception
     */
    @Override
    protected <IS extends InputStream> GPGeoserverEmptyFeatureTypeWrapper internalReadResponse(IS inputStream) throws Exception {
        GPGeoserverFeatureTypeCategory featureTypeCategory = this.featureTypeCategory.get();
        return IGPGeoserverEmptyFeatureTypeWrapper.of(of(emptyJacksonSupport.getDefaultMapper().readValue(inputStream, featureTypeCategory.toEmptyModel())));
    }

    /**
     * @return {@link Request}
     */
    protected Request self() {
        return (Request) this;
    }

    /**
     * @return {@link Class<GPGeoserverEmptyFeatureTypeWrapper>}
     */
    @Override
    protected Class<GPGeoserverEmptyFeatureTypeWrapper> forEmptyResponse() {
        return GPGeoserverEmptyFeatureTypeWrapper.class;
    }

    /**
     * @return {@link Class<IGPGeoserverFeatureTypeWrapper>}
     */
    @Override
    protected Class<GPGeoserverFeatureTypeWrapper> forClass() {
        return GPGeoserverFeatureTypeWrapper.class;
    }
}