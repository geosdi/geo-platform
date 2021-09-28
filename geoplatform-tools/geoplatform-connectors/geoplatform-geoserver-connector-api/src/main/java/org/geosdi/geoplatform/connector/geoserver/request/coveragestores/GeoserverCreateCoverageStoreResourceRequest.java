package org.geosdi.geoplatform.connector.geoserver.request.coveragestores;

import org.geosdi.geoplatform.connector.geoserver.model.format.GPFormatExtension;
import org.geosdi.geoplatform.connector.geoserver.model.store.GPStoreType;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.GPGeoserverCoverageInfo;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonConnectorRequest;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface GeoserverCreateCoverageStoreResourceRequest extends GPJsonConnectorRequest<Boolean, GeoserverCreateCoverageStoreResourceRequest> {

    /**
     * @param theWorkspace
     * @return {@link GeoserverCreateCoverageStoreResourceRequest}
     */
    GeoserverCreateCoverageStoreResourceRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace);

    /**
     * @param theStore
     * @return {@link GeoserverCreateCoverageStoreResourceRequest}
     */
    GeoserverCreateCoverageStoreResourceRequest withCoverageStore(@Nonnull(when = NEVER) String theStore);

    /**
     * @param theMethod
     * @return {@link GeoserverCreateCoverageStoreResourceRequest}
     */
    GeoserverCreateCoverageStoreResourceRequest withMethod(@Nonnull(when = NEVER) GPStoreType theMethod);

    /**
     * @param theFormat
     * @return {@link GeoserverCreateCoverageStoreResourceRequest}
     */
    GeoserverCreateCoverageStoreResourceRequest withFormat(@Nonnull(when = NEVER) GPFormatExtension theFormat);

    /**
     * @param theGPGeoserverCoverageInfo
     * @return {@link GeoserverCreateCoverageStoreResourceRequest}
     */
    GeoserverCreateCoverageStoreResourceRequest withCoverageBody(GPGeoserverCoverageInfo theGPGeoserverCoverageInfo);

    /**
     * @param theFileName
     * @return {@link GeoserverCreateCoverageStoreResourceRequest}
     */
    GeoserverCreateCoverageStoreResourceRequest withFileName(String theFileName);

    /**
     * @param theUpdateBbox
     * @return {@link GeoserverCreateCoverageStoreResourceRequest}
     */
    GeoserverCreateCoverageStoreResourceRequest withUpdateBbox(Boolean theUpdateBbox);
}