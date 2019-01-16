package org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.store;

import lombok.Getter;
import lombok.ToString;
import org.geosdi.geoplatform.connector.geoserver.model.store.GeoserverStoreInfo;
import org.geosdi.geoplatform.connector.geoserver.model.store.GeoserverStoreInfoType;

import javax.xml.bind.annotation.XmlElement;

import static org.geosdi.geoplatform.connector.geoserver.model.store.GeoserverStoreInfoType.COVERAGE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString(callSuper = true)
public class GPGeoserverCoverageStoreInfo extends GeoserverStoreInfo {

    private static final long serialVersionUID = 2243349093423272966L;
    //
    @XmlElement(name = "@class")
    private final GeoserverStoreInfoType storeType = COVERAGE;
}