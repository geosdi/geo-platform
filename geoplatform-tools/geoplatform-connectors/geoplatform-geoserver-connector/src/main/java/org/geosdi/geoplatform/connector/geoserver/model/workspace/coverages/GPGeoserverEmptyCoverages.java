package org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages;

import lombok.ToString;
import org.geosdi.geoplatform.connector.geoserver.model.GPGeoserverEmptyResponse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
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
