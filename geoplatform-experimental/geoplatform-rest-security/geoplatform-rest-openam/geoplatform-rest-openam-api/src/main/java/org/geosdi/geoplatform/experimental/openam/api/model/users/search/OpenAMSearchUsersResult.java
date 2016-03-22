package org.geosdi.geoplatform.experimental.openam.api.model.users.search;

import org.geosdi.geoplatform.experimental.openam.api.model.search.OpenAMSerachResult;
import org.geosdi.geoplatform.experimental.openam.api.model.users.OpenAMUserResponse;

import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class OpenAMSearchUsersResult extends OpenAMSerachResult<OpenAMUserResponse> {

    private static final long serialVersionUID = 2404731019461988333L;
    //
    private List<OpenAMUserResponse> result;

    public OpenAMSearchUsersResult() {
    }

    /**
     * @return {@link List <OpenAMUserResponse>}
     */
    @Override
    public List<OpenAMUserResponse> getResult() {
        return this.result;
    }

    /**
     * @param theResult
     */
    @Override
    public void setResult(List<OpenAMUserResponse> theResult) {
        this.result = theResult;
    }
}
