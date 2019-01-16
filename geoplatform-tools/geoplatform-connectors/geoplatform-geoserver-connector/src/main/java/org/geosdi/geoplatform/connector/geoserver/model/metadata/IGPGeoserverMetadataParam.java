package org.geosdi.geoplatform.connector.geoserver.model.metadata;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.geosdi.geoplatform.response.collection.GPGenericEntryType;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPGeoserverMetadataParam.class)
public interface IGPGeoserverMetadataParam extends GPGenericEntryType<String, String> {

    /**
     * @return {@link String}
     */
    String getKey();

    /**
     * @return {@link String}
     */
    String getValue();
}