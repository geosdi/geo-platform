package org.geosdi.geoplatform.connector.geoserver.request.security;

import org.geosdi.geoplatform.connector.geoserver.model.security.catalog.IGPGeoserverCatalogMode;
import org.geosdi.geoplatform.connector.server.request.GPConnectorRequest;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GeoserverUpdateCatalogRequest extends GPConnectorRequest<Boolean> {

    /**
     * @param theCatalogMode
     * @param <CatalogMode>
     * @return {@link GeoserverUpdateCatalogRequest}
     */
    <CatalogMode extends IGPGeoserverCatalogMode> GeoserverUpdateCatalogRequest withCatalogMode(@Nonnull(when = When.NEVER) CatalogMode theCatalogMode);
}