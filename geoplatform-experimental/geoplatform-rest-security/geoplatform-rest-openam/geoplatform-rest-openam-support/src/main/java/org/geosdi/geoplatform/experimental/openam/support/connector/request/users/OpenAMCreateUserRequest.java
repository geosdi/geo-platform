package org.geosdi.geoplatform.experimental.openam.support.connector.request.users;

import net.jcip.annotations.Immutable;
import org.springframework.stereotype.Component;

import static org.geosdi.geoplatform.experimental.openam.support.connector.request.BaseOpenAMRequest.OpenAMRequestType.CREATE_USER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
@Component(value = "openAMCreateUserRequest")
public class OpenAMCreateUserRequest extends OpenAMBaseUsersRequest<OpenAMCreateUserRequest> {

    private static final long serialVersionUID = 2553216179764977892L;


    /**
     * @return {@link OpenAMRequestType}
     */
    @Override
    public OpenAMRequestType getRequestType() {
        return CREATE_USER;
    }
}
