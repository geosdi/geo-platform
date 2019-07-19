package org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages;

import lombok.ToString;
import org.geosdi.geoplatform.connector.geoserver.model.GPGeoserverEmptyResponse;

import javax.xml.bind.annotation.XmlAccessorType;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ToString
@XmlAccessorType(FIELD)
public class GPGeoserverEmptyCoverages implements GPGeoserverEmptyResponse<GPGeoserverCoverages> {

    private String coverage;

    /**
     * @return {@link GPGeoserverCoverages}
     */
    @Override
    public GPGeoserverCoverages toModel() {
        return new GPGeoserverCoverages();
    }
}