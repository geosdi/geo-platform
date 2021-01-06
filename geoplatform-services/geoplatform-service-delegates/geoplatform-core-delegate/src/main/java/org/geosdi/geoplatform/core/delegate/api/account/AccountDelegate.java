/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface AccountDelegate extends GPAccountResource {

    /**
     * @param insertAccountRequest
     * @return {@link Long}
     * @throws IllegalParameterFault
     */
    @Override
    Long insertAccount(InsertAccountRequest insertAccountRequest) throws IllegalParameterFault;

    /**
     * @param user
     * @return {@link Long}
     * @throws ResourceNotFoundFault
     * @throws IllegalParameterFault
     */
    @Override
    Long updateUser(GPUser user) throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * @param user
     * @param currentPlainPassword
     * @param newPlainPassword
     * @return {@link Long}
     * @throws ResourceNotFoundFault
     * @throws IllegalParameterFault
     */
    Long updateOwnUser(UserDTO user, String currentPlainPassword, String newPlainPassword) throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * @param application
     * @return {@link Long}
     * @throws ResourceNotFoundFault
     * @throws IllegalParameterFault
     */
    Long updateApplication(GPAccount application) throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * @param accountID
     * @return {@link Boolean}
     * @throws ResourceNotFoundFault
     */
    @Override
    Boolean deleteAccount(Long accountID) throws ResourceNotFoundFault;

    /**
     * @param userID
     * @return {@link GPUser}
     * @throws ResourceNotFoundFault
     */
    @Override
    GPUser getUserDetail(Long userID) throws ResourceNotFoundFault;

    /**
     * @param applicationID
     * @return {@link GPApplication}
     * @throws ResourceNotFoundFault
     */
    GPApplication getApplicationDetail(Long applicationID) throws ResourceNotFoundFault;

    /**
     * @param userID
     * @return {@link UserDTOResponse}
     * @throws ResourceNotFoundFault
     */
    @Override
    UserDTOResponse getShortUser(Long userID) throws ResourceNotFoundFault;

    /**
     * @param applicationID
     * @return {@link ApplicationDTO}
     * @throws ResourceNotFoundFault
     */
    ApplicationDTO getShortApplication(Long applicationID) throws ResourceNotFoundFault;

    /**
     * @param request
     * @return {@link UserDTOResponse}
     * @throws ResourceNotFoundFault
     */
    @Override
    UserDTOResponse getShortUserByUsername(SearchRequest request) throws ResourceNotFoundFault;

    /**
     * @param request
     * @return {@link GPUser}
     * @throws ResourceNotFoundFault
     */
    @Override
    GPUser getUserDetailByUsername(SearchRequest request) throws ResourceNotFoundFault;

    /**
     * @param username
     * @param plainPassword
     * @return {@link GPUser}
     * @throws ResourceNotFoundFault
     * @throws IllegalParameterFault
     * @throws AccountLoginFault
     */
    @Override
    GPUser getUserDetailByUsernameAndPassword(String username, String plainPassword) throws ResourceNotFoundFault, IllegalParameterFault, AccountLoginFault;

    /**
     * @param appID
     * @return {@link GPApplication}
     * @throws ResourceNotFoundFault
     * @throws AccountLoginFault
     */
    GPApplication getApplication(String appID) throws ResourceNotFoundFault, AccountLoginFault;

    /**
     * @param request
     * @return {@link ApplicationDTO}
     * @throws ResourceNotFoundFault
     */
    ApplicationDTO getShortApplicationByAppID(SearchRequest request) throws ResourceNotFoundFault;

    /**
     * @param userID
     * @param request
     * @return {@link SearchUsersResponseWS}
     * @throws ResourceNotFoundFault
     */
    @Override
    SearchUsersResponseWS searchUsers(Long userID, PaginatedSearchRequest request) throws ResourceNotFoundFault;

    /**
     * @param userID
     * @param request
     * @return {@link SearchUsersResponseWS}
     * @throws Exception
     */
    @Override
    SearchUsersResponseWS searchEnabledUsers(Long userID, PaginatedSearchRequest request) throws ResourceNotFoundFault;

    /**
     * @return {@link ShortAccountDTOContainer}
     */
    @Override
    ShortAccountDTOContainer getAllAccounts();

    /**
     * @param organization
     * @return {@link ShortAccountDTOContainer}
     * @throws ResourceNotFoundFault
     */
    @Override
    ShortAccountDTOContainer getAccounts(String organization) throws ResourceNotFoundFault;

    /**
     * @param request
     * @return {@link Long}
     */
    @Override
    Long getAccountsCount(SearchRequest request);

    /**
     * @param organization
     * @param request
     * @return {@link Long}
     */
    @Override
    Long getUsersCount(String organization, SearchRequest request);

    /**
     * @param accountNaturalID
     * @return {@link GetAuthorityResponse}
     * @throws ResourceNotFoundFault
     */
    @Override
    GetAuthorityResponse getAuthorities(Long accountNaturalID) throws ResourceNotFoundFault;

    /**
     * @param accountNaturalID
     * @return {@link GetAuthoritiesResponseWS}
     * @throws ResourceNotFoundFault
     */
    @Override
    GetAuthoritiesResponseWS getAuthoritiesDetail(String accountNaturalID) throws ResourceNotFoundFault;

    /**
     * @param accountID
     * @throws ResourceNotFoundFault
     */
    @Override
    void forceTemporaryAccount(Long accountID) throws ResourceNotFoundFault;

    /**
     * @param accountID
     * @throws ResourceNotFoundFault
     * @throws IllegalParameterFault
     */
    @Override
    void forceExpiredTemporaryAccount(Long accountID) throws ResourceNotFoundFault, IllegalParameterFault;
}