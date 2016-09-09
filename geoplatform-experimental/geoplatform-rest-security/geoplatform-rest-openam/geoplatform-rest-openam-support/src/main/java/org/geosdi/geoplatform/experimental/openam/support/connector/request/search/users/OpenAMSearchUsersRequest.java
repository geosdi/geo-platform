package org.geosdi.geoplatform.experimental.openam.support.connector.request.search.users;

import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.experimental.openam.support.connector.request.users.OpenAMBaseUsersRequest;
import org.springframework.stereotype.Component;

import static org.geosdi.geoplatform.experimental.openam.support.connector.request.BaseOpenAMRequest.OpenAMRequestType.SEARCH_USERS;


/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
@Component(value = "openAMSearchUsersRequest")
public class OpenAMSearchUsersRequest extends OpenAMBaseUsersRequest<OpenAMSearchUsersRequest> {

    private static final long serialVersionUID = 4999422039256595995L;

    /**
     * @return {@link OpenAMRequestType}
     */
    @Override
    public OpenAMRequestType getRequestType() {
        return SEARCH_USERS;
    }
}
