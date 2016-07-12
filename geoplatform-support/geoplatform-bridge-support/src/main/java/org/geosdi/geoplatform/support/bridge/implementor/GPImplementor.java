package org.geosdi.geoplatform.support.bridge.implementor;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPImplementor<K extends Object> extends Serializable {

    /**
     * @param <Key>
     * @return {@link GPImplementorKey<K>}
     */
    <Key extends GPImplementorKey<K>> Key getKey();

    /**
     * <p>Specify if {@link GPImplementor} is Valid or Not.</p>
     *
     * @return {@link Boolean}
     */
    Boolean isValid();

    /**
     * @param <K>
     */
    @FunctionalInterface
    interface GPImplementorKey<K extends Object> extends Serializable {

        /**
         * @return {@link K}
         */
        K getImplementorKey();
    }
}
