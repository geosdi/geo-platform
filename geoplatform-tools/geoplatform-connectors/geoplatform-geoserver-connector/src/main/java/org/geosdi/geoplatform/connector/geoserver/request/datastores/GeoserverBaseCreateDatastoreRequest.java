package org.geosdi.geoplatform.connector.geoserver.request.datastores;

import org.geosdi.geoplatform.connector.geoserver.model.datastores.body.IGPGeoserverCreateDatastoreBody;
import org.geosdi.geoplatform.connector.server.request.GPConnectorRequest;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GeoserverBaseCreateDatastoreRequest<Request extends GeoserverBaseCreateDatastoreRequest, Response extends Object>
        extends GPConnectorRequest<Response> {

    /**
     * @param theWorkspaceName
     */
    Request withWorkspaceName(@Nonnull(when = NEVER) String theWorkspaceName);

    /**
     * @param theDatastoreBody
     */
    Request withDatastoreBody(@Nonnull(when = NEVER) IGPGeoserverCreateDatastoreBody theDatastoreBody);
}