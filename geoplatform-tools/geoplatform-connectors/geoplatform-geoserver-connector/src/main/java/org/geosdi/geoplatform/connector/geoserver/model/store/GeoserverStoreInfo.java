package org.geosdi.geoplatform.connector.geoserver.model.store;

import lombok.Getter;

import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@XmlTransient
public abstract class GeoserverStoreInfo implements GPGeoserverStoreInfo {

    private static final long serialVersionUID = 6030188371038987277L;
    //
    private String name;
    private String href;
}
