package org.geosdi.geoplatform.connector.geoserver.model.metadata.adapter;

import org.geosdi.geoplatform.connector.geoserver.model.metadata.GPGeoserverMetadataMapType;
import org.geosdi.geoplatform.connector.geoserver.model.metadata.IGPGeoserverMetadataParam;
import org.geosdi.geoplatform.response.collection.GPGenericMapAdapter;

import javax.xml.bind.ValidationEventHandler;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPGeoserverMetadataMapAdapter extends GPGenericMapAdapter<String, String, IGPGeoserverMetadataParam, GPGeoserverMetadataMapType> {

    /**
     * Convert a bound type to a value type.
     *
     * @param map The value to be convereted. Can be null.
     * @throws Exception if there's an error during the conversion. The caller is responsible for
     *                   reporting the error to the user through {@link ValidationEventHandler}.
     */
    @Override
    public GPGeoserverMetadataMapType marshal(Map<String, String> map) throws Exception {
        return new GPGeoserverMetadataMapType(map);
    }

    /**
     * @param mapType
     * @return {@link Map<String, String>}
     * @throws Exception
     */
    @Override
    public Map<String, String> unmarshal(GPGeoserverMetadataMapType mapType) throws Exception {
        return super.unmarshal(mapType);
    }
}