/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2019 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.core.delegate.api.account;

import org.geosdi.geoplatform.core.model.GPAccount;
import org.geosdi.geoplatform.core.model.GPApplication;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.exception.AccountLoginFault;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.InsertAccountRequest;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.response.*;
import org.geosdi.geoplatform.response.authority.GetAuthoritiesResponseWS;
import org.geosdi.geoplatform.response.authority.GetAuthorityResponse;
import org.geosdi.geoplatform.services.core.api.resources.GPAccountResource;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface AccountDelegate extends GPAccountResource {

    @Override
    Long insertAccount(InsertAccountRequest insertAccountRequest)
            throws IllegalParameterFault;

    @Override
    Long updateUser(GPUser user) throws ResourceNotFoundFault,
            IllegalParameterFault;

    Long updateOwnUser(UserDTO user, String currentPlainPassword,
            String newPlainPassword) throws ResourceNotFoundFault,
            IllegalParameterFault;

    Long updateApplication(GPAccount application) throws ResourceNotFoundFault,
            IllegalParameterFault;

    @Override
    Boolean deleteAccount(Long accountID) throws ResourceNotFoundFault;

    @Override
    GPUser getUserDetail(Long userID) throws ResourceNotFoundFault;

    GPApplication getApplicationDetail(Long applicationID) throws
            ResourceNotFoundFault;

    @Override
    UserDTOResponse getShortUser(Long userID) throws ResourceNotFoundFault;

    ApplicationDTO getShortApplication(Long applicationID) throws
            ResourceNotFoundFault;

    @Override
    UserDTOResponse getShortUserByUsername(SearchRequest request)
            throws ResourceNotFoundFault;

    @Override
    GPUser getUserDetailByUsername(SearchRequest request)
            throws ResourceNotFoundFault;

    @Override
    GPUser getUserDetailByUsernameAndPassword(String username,
            String plainPassword) throws ResourceNotFoundFault,
            IllegalParameterFault, AccountLoginFault;

    GPApplication getApplication(String appID)
            throws ResourceNotFoundFault, AccountLoginFault;

    ApplicationDTO getShortApplicationByAppID(SearchRequest request)
            throws ResourceNotFoundFault;

    @Override
    SearchUsersResponseWS searchUsers(Long userID, PaginatedSearchRequest request)
            throws ResourceNotFoundFault;

    @Override
    ShortAccountDTOContainer getAllAccounts();

    @Override
    ShortAccountDTOContainer getAccounts(String organization)
            throws ResourceNotFoundFault;

    @Override
    Long getAccountsCount(SearchRequest request);

    @Override
    Long getUsersCount(String organization, SearchRequest request);

    @Override
    GetAuthorityResponse getAuthorities(Long accountNaturalID) throws
            ResourceNotFoundFault;

    @Override
    GetAuthoritiesResponseWS getAuthoritiesDetail(String accountNaturalID)
            throws ResourceNotFoundFault;

    @Override
    void forceTemporaryAccount(Long accountID)
            throws ResourceNotFoundFault;

    @Override
    void forceExpiredTemporaryAccount(Long accountID)
            throws ResourceNotFoundFault, IllegalParameterFault;

}
