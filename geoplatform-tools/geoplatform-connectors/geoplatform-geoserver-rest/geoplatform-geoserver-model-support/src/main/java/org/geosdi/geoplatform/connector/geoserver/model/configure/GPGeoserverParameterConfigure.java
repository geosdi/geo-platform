package org.geosdi.geoplatform.connector.geoserver.model.configure;

import org.geosdi.geoplatform.connector.geoserver.model.uri.GPGeoserverQueryParam;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public enum GPGeoserverParameterConfigure implements GPGeoserverQueryParam<String> {

    FIRST, NONE, ALL;

    /**
     * Returns the name of this enum constant, as contained in the
     * declaration.  This method may be overridden, though it typically
     * isn't necessary or desirable.  An enum type should override this
     * method when a more "programmer-friendly" string form exists.
     *
     * @return the name of this enum constant
     */
    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getKey() {
        return "configure";
    }

    @Override
    public String getValue() {
        return super.toString().toLowerCase();
    }

    /**
     * @return {@link String}
     */
    @Override
    public String formatValue() {
        return this.getValue();
    }
}