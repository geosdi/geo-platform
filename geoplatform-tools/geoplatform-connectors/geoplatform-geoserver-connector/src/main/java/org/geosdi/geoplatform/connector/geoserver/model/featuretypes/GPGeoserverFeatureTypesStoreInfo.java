package org.geosdi.geoplatform.connector.geoserver.model.featuretypes;

import lombok.Getter;
import lombok.ToString;
import org.geosdi.geoplatform.connector.geoserver.model.store.GPGeoserverStore;
import org.geosdi.geoplatform.connector.geoserver.model.store.GPGeoserverStoreInfo;
import org.geosdi.geoplatform.connector.geoserver.model.store.GeoserverStoreInfoType;

import javax.xml.bind.annotation.XmlElement;

import static org.geosdi.geoplatform.connector.geoserver.model.store.GeoserverStoreInfoType.FEATURE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString(callSuper = true)
public class GPGeoserverFeatureTypesStoreInfo extends GPGeoserverStore implements GPGeoserverStoreInfo {

    private static final long serialVersionUID = -8675326812103402976L;
    //
    @XmlElement(name = "@class")
    private final GeoserverStoreInfoType storeType = FEATURE;
}