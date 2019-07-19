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