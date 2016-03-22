package org.geosdi.geoplatform.experimental.openam.support.connector.request.search.users.parameter;

import org.apache.http.annotation.Immutable;
import org.geosdi.geoplatform.experimental.openam.api.connector.request.parameter.RequestParameter;

import static org.geosdi.geoplatform.experimental.openam.api.connector.request.parameter.RequestParameter.RequestParameterType.SEARCH_USER_BY_UID;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
public class SearchUserByUidParameter implements RequestParameter {

    private static final long serialVersionUID = -1751815106320518191L;
    //
    private final String value;

    public SearchUserByUidParameter(String theValue) {
        this.value = theValue;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getkey() {
        return "_queryFilter";
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getValue() {
        return "uid eq \"" + this.value + "\"";
    }

    /**
     * @return {@link RequestParameterType}
     */
    @Override
    public RequestParameterType getRequestParameterType() {
        return SEARCH_USER_BY_UID;
    }
}
