package org.geosdi.geoplatform.connector.geoserver.model.featuretypes.configured;

import lombok.ToString;
import org.geosdi.geoplatform.connector.geoserver.model.GPGeoserverEmptyResponse;

import javax.xml.bind.annotation.XmlAccessorType;

import static java.util.Collections.EMPTY_LIST;
import static javax.xml.bind.annotation.XmlAccessType.FIELD;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ToString
@XmlAccessorType(FIELD)
public class GPGeoserverEmptyFeatureTypes implements GPGeoserverEmptyResponse<IGPGeoserverFeatureTypes> {

    private String featureTypes;

    /**
     * @return {@link IGPGeoserverFeatureTypes}
     */
    @Override
    public IGPGeoserverFeatureTypes toModel() {
        return new GPGeoserverFeatureTypes(EMPTY_LIST);
    }
}