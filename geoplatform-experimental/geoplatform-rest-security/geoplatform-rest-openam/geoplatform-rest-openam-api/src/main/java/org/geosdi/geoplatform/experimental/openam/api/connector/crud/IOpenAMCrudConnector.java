package org.geosdi.geoplatform.experimental.openam.api.connector.crud;

import org.geosdi.geoplatform.experimental.openam.api.connector.secure.IOpenAMAuthorizedConnector;
import org.geosdi.geoplatform.experimental.openam.api.model.delete.IOpenAMDeleteResponse;
import org.geosdi.geoplatform.experimental.openam.api.model.groups.IOpenAMGroup;
import org.geosdi.geoplatform.experimental.openam.api.model.groups.IOpenAMGroupResponse;
import org.geosdi.geoplatform.experimental.openam.api.model.users.IOpenAMUser;
import org.geosdi.geoplatform.experimental.openam.api.model.users.IOpenAMUserResponse;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IOpenAMCrudConnector extends IOpenAMAuthorizedConnector {

    /**
     * @param openAMUser
     * @return {@link IOpenAMUserResponse}
     * @throws Exception
     */
    IOpenAMUserResponse createUser(IOpenAMUser openAMUser) throws Exception;

    /**
     * @param userName
     * @return {@link IOpenAMDeleteResponse}
     * @throws Exception
     */
    IOpenAMDeleteResponse deleteUser(String userName) throws Exception;

    /**
     * @param openAMUser
     * @return {@link IOpenAMUserResponse}
     * @throws Exception
     */
    IOpenAMUserResponse updateUser(IOpenAMUser openAMUser) throws Exception;

    /**
     * @param group
     * @return {@link IOpenAMGroupResponse}
     * @throws Exception
     */
    IOpenAMGroupResponse updateGroupAddingUser(IOpenAMGroup group) throws Exception;
}
