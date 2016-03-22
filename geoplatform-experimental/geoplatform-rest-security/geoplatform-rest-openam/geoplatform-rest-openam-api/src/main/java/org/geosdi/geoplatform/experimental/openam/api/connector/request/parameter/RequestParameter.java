package org.geosdi.geoplatform.experimental.openam.api.connector.request.parameter;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface RequestParameter extends Serializable {

    /**
     * @return {@link String}
     */
    String getkey();

    /**
     * @return {@link String}
     */
    String getValue();

    /**
     * @return {@link RequestParameterType}
     */
    RequestParameterType getRequestParameterType();

    /**
     *
     */
    enum RequestParameterType {
        ACTION_VALIDATE,
        ACTION_CREATE_USER,
        ACTION_LOGOUT,
        SEARCH_USER_BY_UID;
    }
}
