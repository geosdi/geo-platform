package org.geosdi.geoplatform.support.jackson.jts.bridge.implementor;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface Implementor<Key extends Object> {

    /**
     * @return {@link Key}
     */
    Key getKey();

    /**
     * <p>
     * Specify if {@link Implementor} is valid or not
     * </p>
     *
     * @return {@link Boolean}
     */
    Boolean isImplementorValid();
}
