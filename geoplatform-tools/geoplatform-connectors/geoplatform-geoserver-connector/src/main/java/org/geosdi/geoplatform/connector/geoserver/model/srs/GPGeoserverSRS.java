package org.geosdi.geoplatform.connector.geoserver.model.srs;

import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPGeoserverSRS extends Serializable {

    /**
     * @return {@link List<String>}
     */
    List<String> getValues();

    /**
     * @param theValues
     */
    void setValues(List<String> theValues);
}