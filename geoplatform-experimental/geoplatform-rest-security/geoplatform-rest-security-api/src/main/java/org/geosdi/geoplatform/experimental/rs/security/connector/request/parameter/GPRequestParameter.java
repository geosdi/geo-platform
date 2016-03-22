package org.geosdi.geoplatform.experimental.rs.security.connector.request.parameter;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPRequestParameter extends Serializable {

    /**
     * @return {@link String}
     */
    String getkey();

    /**
     * @return {@link String}
     */
    String getValue();

    /**
     * @param <Type>
     * @return {@link Type}
     */
    <Type extends GPRequestParamaterType> Type getRequestType();

    /**
     *
     */
    interface GPRequestParamaterType {

        /**
         * @return {@link String}
         */
        String getRequestParameterType();
    }
}
