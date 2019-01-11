package org.geosdi.geoplatform.connector.geoserver.model.connection.adapter;

import org.geosdi.geoplatform.connector.geoserver.model.connection.GPGeoserverConnectionMapParamType;
import org.geosdi.geoplatform.connector.geoserver.model.connection.IGPGeoserverConnectionParam;
import org.geosdi.geoplatform.response.collection.GPGenericMapAdapter;

import javax.xml.bind.ValidationEventHandler;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPGeoserverConnectionMapParamAdapter extends GPGenericMapAdapter<String, String, IGPGeoserverConnectionParam, GPGeoserverConnectionMapParamType> {

    /**
     * Convert a bound type to a value type.
     *
     * @param map The value to be convereted. Can be null.
     * @throws Exception if there's an error during the conversion. The caller is responsible for
     *                   reporting the error to the user through {@link ValidationEventHandler}.
     */
    @Override
    public GPGeoserverConnectionMapParamType marshal(Map<String, String> map) throws Exception {
        return new GPGeoserverConnectionMapParamType(map);
    }

    /**
     * Convert a value type to a bound type.
     *
     * @param mapType The value to be converted. Can be null.
     * @throws Exception if there's an error during the conversion. The caller is responsible for
     *                   reporting the error to the user through {@link ValidationEventHandler}.
     */
    @Override
    public Map<String, String> unmarshal(GPGeoserverConnectionMapParamType mapType) throws Exception {
        return super.unmarshal(mapType);
    }
}