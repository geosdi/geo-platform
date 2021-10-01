package org.geosdi.geoplatform.connector.geoserver.model.file;

import org.geosdi.geoplatform.connector.geoserver.model.uri.GPGeoserverQueryParam;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public enum GPGeoserverDataStoreFileExtension implements GPGeoserverQueryParam {

    SHP,
    PROPERTIES,
    H2,
    SPATIALITE;

    public String toString() {
        return this.name().toLowerCase();
    }


    /**
     * @return {@link String}
     */
    @Override
    public String getKey() {
        return "target";
    }

    /**
     * @return {@link String}
     */
    @Override
    public Object getValue() {
        return this.getValue();
    }

    /**
     * @return {@link String}
     */
    @Override
    public String formatValue() {
        return super.toString().toLowerCase();
    }


}