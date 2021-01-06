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
package org.geosdi.geoplatform.support.jackson;

import org.geosdi.geoplatform.core.model.GPAccountProject;
import org.geosdi.geoplatform.request.InsertAccountRequest;
import org.geosdi.geoplatform.response.SearchUsersResponseWS;
import org.geosdi.geoplatform.response.ShortAccountDTOContainer;
import org.geosdi.geoplatform.response.authority.GetAuthoritiesResponseWS;
import org.geosdi.geoplatform.response.authority.GetAuthorityResponse;
import org.junit.Test;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPJacksonAccountSupportTest extends GPBaseJacksonSupportTest {
    
    @Test
    public void accountsDataMapperTest() throws Exception {
        ShortAccountDTOContainer accountContainer = jacksonSupport.getDefaultMapper().readValue(
                Thread.currentThread().getContextClassLoader().getResourceAsStream(
                        ACCOUNTS_DATA_JSON), ShortAccountDTOContainer.class);
        
        logger.info("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ACCOUNTS_DATA_MAPPING"
                + " : {}\n\n", accountContainer);
        
        super.marshall(accountContainer);
    }
    
    @Test
    public void insertAccountRequestMapperTest() throws Exception {
        InsertAccountRequest insertAccountRequest = jacksonSupport.getDefaultMapper().readValue(
                Thread.currentThread().getContextClassLoader().getResourceAsStream(
                        INSERT_ACCOUNT_REQUEST_DATA_JSON),
                InsertAccountRequest.class);
        
        logger.info("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@INSERT_ACCOUNT_REQUEST"
                + " : {}\n\n", insertAccountRequest);
        
        super.marshall(insertAccountRequest);
    }
    
    @Test
    public void accountProjectDataMapperTest() throws Exception {
        GPAccountProject accountProject = jacksonSupport.getDefaultMapper().readValue(
                Thread.currentThread().getContextClassLoader().getResourceAsStream(
                        ACCOUNT_PROJECT_DATA_JSON), GPAccountProject.class);
        
        logger.info("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@ACCOUNT_PROJECT_DATA_MAPPING"
                + " : {}\n\n", accountProject);
        
        super.marshall(accountProject);
    }
    
    @Test
    public void getAuthoritiesResponseDataMapperTest() throws Exception {
        GetAuthoritiesResponseWS getAuthoritiesResponse = jacksonSupport.getDefaultMapper().readValue(
                Thread.currentThread().getContextClassLoader().getResourceAsStream(
                        GET_AUTHORITIES_RESPONSE_DATA_JSON),
                GetAuthoritiesResponseWS.class);
        
        logger.info("\n\n@@@@@@@@@@@@@@@@@GET_AUTHORITIES_RESPONSE_DATA_MAPPING"
                + " : {}\n\n", getAuthoritiesResponse);
        
        super.marshall(getAuthoritiesResponse);
    }
    
    @Test
    public void searchUsersResponseDataMapperTest() throws Exception {
        SearchUsersResponseWS searchUsersResponse = jacksonSupport.getDefaultMapper().readValue(
                Thread.currentThread().getContextClassLoader().getResourceAsStream(
                        SEARCH_USERS_RESPONSE_DATA_JSON),
                SearchUsersResponseWS.class);
        
        logger.info("\n\n@@@@@@@@@@@@@@@@@@@@SEARCH_USERS_RESPONSE_DATA_MAPPING"
                + " : {}\n\n", searchUsersResponse);
        
        super.marshall(searchUsersResponse);
    }
    
    @Test
    public void getAuthorityResponseDataMapperTest() throws Exception {
        GetAuthorityResponse getAuthorityResponse = jacksonSupport.getDefaultMapper().readValue(
                Thread.currentThread().getContextClassLoader().getResourceAsStream(
                        GET_AUTHORITY_RESPONSE_DATA_JSON),
                GetAuthorityResponse.class);
        
        logger.info("\n\n@@@@@@@@@@@@@@@@@GET_AUTHORITY_RESPONSE_DATA_MAPPING"
                + " : {}\n\n", getAuthorityResponse);
        
        super.marshall(getAuthorityResponse);
    }
    
}
