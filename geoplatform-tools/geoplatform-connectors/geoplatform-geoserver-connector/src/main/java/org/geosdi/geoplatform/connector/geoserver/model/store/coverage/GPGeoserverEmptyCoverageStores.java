package org.geosdi.geoplatform.connector.geoserver.model.store.coverage;

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
public class GPGeoserverEmptyCoverageStores implements GPGeoserverEmptyResponse<GPGeoserverCoverageStores> {

    private String coverageStores;

    /**
     * @return {@link GPGeoserverCoverageStores}
     */
    @Override
    public GPGeoserverCoverageStores toModel() {
        return new GPGeoserverCoverageStores();
    }
}