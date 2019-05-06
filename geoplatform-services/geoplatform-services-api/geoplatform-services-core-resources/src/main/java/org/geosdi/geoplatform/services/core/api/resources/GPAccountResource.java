/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2014 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 *
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 *
 */
package org.geosdi.geoplatform.services.core.api.resources;

import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.request.InsertAccountRequest;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.response.SearchUsersResponseWS;
import org.geosdi.geoplatform.response.ShortAccountDTOContainer;
import org.geosdi.geoplatform.response.UserDTOResponse;
import org.geosdi.geoplatform.response.authority.GetAuthoritiesResponseWS;
import org.geosdi.geoplatform.response.authority.GetAuthorityResponse;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPAccountResource {

    /**
     * Insert an Account (User or Application).
     *
     * @param insertAccountRequest
     * @return the Account ID
     * @throws Exception if the account not have an Organization or there is a
     *                   duplicate Account
     */
    Long insertAccount(InsertAccountRequest insertAccountRequest)
            throws Exception;

    /**
     * Update a User and his Authorities.
     *
     * @param user the User to update
     * @return the User ID
     * @throws Exception if User not found or if User ID is null or update a
     *                   standard User to a temporary User
     */
    Long updateUser(GPUser user) throws Exception;

    /**
     * Delete an Account by ID. Delete his Authorities, the owner Project and
     * the reference to shared Project.
     *
     * @param accountID the Account ID
     * @return true if the Account was deleted
     * @throws Exception if Account not found
     */
    Boolean deleteAccount(Long accountID) throws Exception;

    /**
     * Retrieve a User by ID.
     *
     * @param userID the User ID
     * @return the User to retrieve
     * @throws Exception if User not found
     */
    GPUser getUserDetail(Long userID) throws Exception;

    /**
     * Retrieve a User by username.
     *
     * @param request the request that wrap the username
     * @return the User to retrieve
     * @throws Exception if User not found
     */
    GPUser getUserDetailByUsername(SearchRequest request) throws Exception;

    /**
     * Retrieve a User by username (or email) and password to authenticate it.
     *
     * @param username      the username or the email of the User
     * @param plainPassword the plaintext password of the User
     * @return the User to retrieve
     * @throws Exception if User not found or not have Authorities or if the
     *                   password is wrong or if User is disabled or expired
     */
    GPUser getUserDetailByUsernameAndPassword(String username,
            String plainPassword) throws Exception;

    /**
     * Retrieve a User by ID.
     *
     * @param userID the User ID
     * @return the User to retrieve
     * @throws Exception if User not found
     */
    UserDTOResponse getShortUser(Long userID) throws Exception;

    /**
     * Retrieve a User by username.
     *
     * @param request
     * @return the User to retrieve
     * @throws Exception if User not found
     */
    UserDTOResponse getShortUserByUsername(SearchRequest request) throws Exception;

    /**
     * Search Users and their Authorities in paginated way. The Users are
     * searched by name like of username. Note: the Organization of the User is
     * retrieved by User ID.
     *
     * @param userID  the User ID that will exclude from the search (logged user)
     * @param request the request that wrap the search parameters
     * @return the paginate list of Users found
     * @throws Exception if User not found or a searched User not have
     *                   Authorities
     */
    SearchUsersResponseWS searchUsers(Long userID, PaginatedSearchRequest request) throws Exception;

    /**
     * @param userID
     * @param request
     * @return {@link SearchUsersResponseWS}
     * @throws Exception
     */
    SearchUsersResponseWS searchEnabledUsers(Long userID, PaginatedSearchRequest request) throws Exception;

    /**
     * Retrieve all Accounts.
     *
     * @return the list of Accounts
     */
    ShortAccountDTOContainer getAllAccounts();

    /**
     * Retrieve all Accounts within an Organization.
     *
     * @param organization the Organization name
     * @return the list of Accounts
     * @throws Exception if Organization not found
     */
    ShortAccountDTOContainer getAccounts(String organization) throws Exception;

    /**
     * Retrieve the number of Accounts founded by search request.
     *
     * @param request the request that wrap the username (for User) or the
     *                string ID (for Application)
     * @return the number of Accounts found
     */
    Long getAccountsCount(SearchRequest request);

    /**
     * Retrieve the number of Users of an Organization founded by search
     * request.
     *
     * @param organization the Organization name
     * @param request      the request that wrap the username
     * @return the number of Users found
     */
    Long getUsersCount(String organization, SearchRequest request);

    /**
     * Retrieve the Authorities of an Account.
     *
     * @param accountID the Account ID
     * @return the list of string Authorities
     * @throws Exception if Account not found or Account not have Authorities
     */
    GetAuthorityResponse getAuthorities(Long accountID) throws Exception;

    /**
     * Retrieve the Authorities of an Account.
     *
     * @param accountNaturalID the username (for User) or the appID (for
     *                         Application)
     * @return the list of Authorities
     * @throws Exception if Account not found or Account not have Authorities
     */
    GetAuthoritiesResponseWS getAuthoritiesDetail(String accountNaturalID) throws
            Exception;

    /**
     * Set an Account as temporary.
     *
     * @param accountID the Account ID
     * @throws Exception if Account not found
     */
    void forceTemporaryAccount(Long accountID) throws Exception;

    /**
     * Set a temporary Account as expired.
     *
     * @param accountID the Account ID
     * @throws Exception if Account not found or if Account is not temporary
     */
    void forceExpiredTemporaryAccount(Long accountID) throws Exception;
    // </editor-fold>
}
