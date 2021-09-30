package org.geosdi.geoplatform.connector.geoserver.request.datastores;

import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.GPGeoserverFeatureTypeInfo;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonConnectorRequest;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface GeoserverCreateDatastoreResourceRequest extends GPJsonConnectorRequest<Boolean, GeoserverCreateDatastoreResourceRequest> {

    /**
     * @param theWorkspace
     * @return {@link GeoserverCreateDatastoreResourceRequest}
     */
    GeoserverCreateDatastoreResourceRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace);

    /**
     * @param theStore
     * @return {@link GeoserverCreateDatastoreResourceRequest}
     */
    GeoserverCreateDatastoreResourceRequest withDataStore(@Nonnull(when = NEVER) String theStore);

    /**
     * @param theDataStoreBody
     * @return {@link GeoserverCreateDatastoreResourceRequest}
     */
    GeoserverCreateDatastoreResourceRequest withDataStoreBody(@Nonnull(when = NEVER) GPGeoserverFeatureTypeInfo theDataStoreBody);
}