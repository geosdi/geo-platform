package org.geosdi.geoplatform.connector.geoserver.request.datastores;

import org.geosdi.geoplatform.connector.geoserver.model.configure.GPParameterConfigure;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.GPGeoserverFeatureTypeInfo;
import org.geosdi.geoplatform.connector.geoserver.model.format.GPFormatExtension;
import org.geosdi.geoplatform.connector.geoserver.model.store.GeoserverStoreInfoType;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonConnectorRequest;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface GeoserverUpdateDatastoreResourceRequest extends GPJsonConnectorRequest<Boolean, GeoserverUpdateDatastoreResourceRequest> {

    /**
     * @param theWorkspace
     * @return {@link GeoserverUpdateDatastoreResourceRequest}
     */
    GeoserverUpdateDatastoreResourceRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace);

    /**
     * @param theStore
     * @return {@link GeoserverUpdateDatastoreResourceRequest}
     */
    GeoserverUpdateDatastoreResourceRequest withDataStore(@Nonnull(when = NEVER) String theStore);

    /**
     * @param theMethod
     * @return {@link GeoserverUpdateDatastoreResourceRequest}
     */
    GeoserverUpdateDatastoreResourceRequest withMethod(@Nonnull(when = NEVER) GeoserverStoreInfoType theMethod);

    /**
     * @param theFormat
     * @return {@link GeoserverUpdateDatastoreResourceRequest}
     */
    GeoserverUpdateDatastoreResourceRequest withFormat(@Nonnull(when = NEVER) GPFormatExtension theFormat);

    /**
     * @param theCharset
     * @return {@link GeoserverUpdateDatastoreResourceRequest}
     */
    GeoserverUpdateDatastoreResourceRequest withCharset(@Nonnull(when = NEVER) String theCharset);

    /**
     * @param theUpdate
     * @return {@link GeoserverUpdateDatastoreResourceRequest}
     */
    GeoserverUpdateDatastoreResourceRequest withUpdate(@Nonnull(when = NEVER) String theUpdate);

    /**
     * @param theParameterConfigure
     * @return {@link GeoserverUpdateDatastoreResourceRequest}
     */
    GeoserverUpdateDatastoreResourceRequest withConfigure(@Nonnull(when = NEVER) GPParameterConfigure theParameterConfigure);

    /**
     * @param theTarget
     * @return {@link GeoserverUpdateDatastoreResourceRequest}
     */
    GeoserverUpdateDatastoreResourceRequest withTarget(@Nonnull(when = NEVER) String theTarget);

    /**
     * @param theFilename
     * @return {@link GeoserverUpdateDatastoreResourceRequest}
     */
    GeoserverUpdateDatastoreResourceRequest withFileName(@Nonnull(when = NEVER) String theFilename);

    /**
     * @param theDataStoreBody
     * @return {@link GeoserverUpdateDatastoreResourceRequest}
     */
    GeoserverUpdateDatastoreResourceRequest withDataStoreBody(@Nonnull(when = NEVER) GPGeoserverFeatureTypeInfo theDataStoreBody);
}