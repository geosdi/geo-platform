package org.geosdi.geoplatform.connector.geoserver.model.store.coverage;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeoserverCoverageStoreBody extends Serializable {

    /**
     * @return {@link String}
     */
    String getCoverageName();

    /**
     * @return {@link String}
     */
    String getDescription();

    /**
     * @param <Type>
     * @return {@link Type}
     */
    <Type extends GPCoverageStoreType> Type getType();

    /**
     * @return {@link Boolean}
     */
    boolean isEnabled();

    /**
     * @return {@link String}
     */
    String getWorkspace();

    /**
     * @return {@link String}
     */
    String getUrl();
}