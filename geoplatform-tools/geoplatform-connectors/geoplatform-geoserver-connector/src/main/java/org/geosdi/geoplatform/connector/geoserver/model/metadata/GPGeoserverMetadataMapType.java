package org.geosdi.geoplatform.connector.geoserver.model.metadata;

import org.geosdi.geoplatform.response.collection.GPGenericMapType;

import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPGeoserverMetadataMapType extends GPGenericMapType<String, String, IGPGeoserverMetadataParam> {

    public GPGeoserverMetadataMapType() {
    }

    /**
     * @param map
     */
    public GPGeoserverMetadataMapType(Map<String, String> map) {
        super(map);
    }

    /**
     * @param entry
     * @return {@link IGPGeoserverMetadataParam}
     */
    @Override
    protected IGPGeoserverMetadataParam toEntry(Map.Entry<String, String> entry) {
        return new GPGeoserverMetadataParam(entry.getKey(), entry.getValue());
    }
}