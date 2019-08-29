package org.geosdi.geoplatform.connector.geoserver.model.security.catalog;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeoserverCatalog extends Serializable {

    /**
     * @param <CatalogMode>
     * @return {@link CatalogMode}
     */
    <CatalogMode extends IGPGeoserverCatalogMode> CatalogMode getCatalogMode();
}