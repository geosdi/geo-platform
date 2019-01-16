package org.geosdi.geoplatform.connector.geoserver.request.workspaces.coverages;

import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.GPGeoserverCoverages;
import org.geosdi.geoplatform.connector.server.request.GPConnectorRequest;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GeoserverLoadCoveragesRequest extends GPConnectorRequest<GPGeoserverCoverages> {

    /**
     * @param theWorkspace
     * @return {@link GeoserverLoadCoveragesRequest}
     */
    GeoserverLoadCoveragesRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace);

    /**
     * <p>If the list parameter value is equal to “all” all the coverages available in the data source
     * (even the non published ones) will be returned. The Class returned is an istance
     * of {@link org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.GPGeoserverAllCoverages}  class.
     * </p>
     *
     * @param theQueryList
     * @return {@link GeoserverLoadCoveragesRequest}
     */
    GeoserverLoadCoveragesRequest withQueryList(@Nullable String theQueryList);
}