package org.geosdi.geoplatform.experimental.openam.api.connector.search;

import org.geosdi.geoplatform.experimental.openam.api.connector.crud.IOpenAMCrudConnector;
import org.geosdi.geoplatform.experimental.openam.api.model.users.search.OpenAMSearchUsersResult;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IOpenAMSearchConnector extends IOpenAMCrudConnector {

    /**
     * @param uid
     * @return {@link OpenAMSearchUsersResult}
     * @throws Exception
     */
    OpenAMSearchUsersResult searchOpenAMUserByUid(String uid) throws Exception;
}
