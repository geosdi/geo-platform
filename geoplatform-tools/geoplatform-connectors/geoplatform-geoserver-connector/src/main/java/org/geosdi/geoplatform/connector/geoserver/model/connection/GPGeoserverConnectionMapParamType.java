package org.geosdi.geoplatform.connector.geoserver.model.connection;

import org.geosdi.geoplatform.response.collection.GPGenericMapType;

import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPGeoserverConnectionMapParamType extends GPGenericMapType<String, String, IGPGeoserverConnectionParam> {

    public GPGeoserverConnectionMapParamType() {
    }

    /**
     * @param map
     */
    public GPGeoserverConnectionMapParamType(Map<String, String> map) {
        super(map);
    }

    /**
     * @param entry
     * @return {@link IGPGeoserverConnectionParam}
     */
    @Override
    protected IGPGeoserverConnectionParam toEntry(Map.Entry<String, String> entry) {
        return new GPGeoserverConnectionParam(entry.getKey(), entry.getValue());
    }
}