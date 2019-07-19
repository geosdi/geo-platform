package org.geosdi.geoplatform.connector.geoserver.model.store;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@Setter
@ToString
public class GPGeoserverStore implements IGPGeoserverStore {

    private static final long serialVersionUID = 9036435031185496179L;
    //
    private String name;
    private String href;
}