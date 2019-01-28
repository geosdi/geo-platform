package org.geosdi.geoplatform.connector.geoserver.model.store.coverage;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.IGPGeoserverWorkspace;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPGeoserverCoverageStore.class)
public interface IGPGeoserverCoverageStore extends Serializable {

    /**
     * @return {@link String}
     */
    String getName();

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
     * @param <Workspace>
     * @return {@link Workspace}
     */
    <Workspace extends IGPGeoserverWorkspace> Workspace getWorkspace();

    /**
     * @return {@link Boolean}
     */
    boolean isDefault();

    /**
     * @return {@link String}
     */
    String getUrl();

    /**
     * @return {@link String}
     */
    String getCoverages();
}