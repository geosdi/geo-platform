package org.geosdi.geoplatform.connector.geoserver.model.store;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.GPGeoserverFeatureTypesStoreInfo;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.store.GPGeoserverCoverageStoreInfo;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "@class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = GPGeoserverFeatureTypesStoreInfo.class, name = "dataStore"),
        @JsonSubTypes.Type(value = GPGeoserverCoverageStoreInfo.class, name = "coverageStore")})
public interface GPGeoserverStoreInfo extends IGPGeoserverStore {

    /**
     * @return {@link GeoserverStoreInfoType}
     */
    GeoserverStoreInfoType getStoreType();
}