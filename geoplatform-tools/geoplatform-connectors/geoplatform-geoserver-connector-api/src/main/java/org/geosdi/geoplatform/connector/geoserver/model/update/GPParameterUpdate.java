package org.geosdi.geoplatform.connector.geoserver.model.update;

import org.geosdi.geoplatform.connector.geoserver.model.uri.GPGeoserverQueryParam;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public enum GPParameterUpdate implements GPGeoserverQueryParam {

    APPEND,
    OVERWRITE;

    public String toString() {
        return this.name().toLowerCase();
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getKey() {
        return "update";
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getValue() {
        return this.name().toLowerCase();
    }
}