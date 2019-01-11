package org.geosdi.geoplatform.connector.geoserver.request.datastores;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GeoserverUpdateDatastoreRequest extends GeoserverBaseCreateDatastoreRequest<GeoserverUpdateDatastoreRequest, Boolean> {

    /**
     * @param theStoreName
     * @return {@link GeoserverUpdateDatastoreRequest}
     */
    GeoserverUpdateDatastoreRequest withStoreName(@Nonnull(when = When.NEVER) String theStoreName);
}