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
package org.geosdi.geoplatform.experimental.openam.support.config.connector.search;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.geosdi.geoplatform.experimental.openam.api.model.authenticate.IOpenAMAuthenticate;
import org.geosdi.geoplatform.experimental.openam.api.model.users.search.OpenAMSearchUsersResult;
import org.geosdi.geoplatform.experimental.openam.support.config.connector.crud.OpenAMCrudConnector;
import org.geosdi.geoplatform.experimental.openam.support.connector.request.search.users.OpenAMSearchUsersRequest;
import org.geosdi.geoplatform.experimental.openam.support.connector.request.search.users.parameter.SearchUserByUidParameter;
import org.geosdi.geoplatform.experimental.rs.security.connector.settings.GPConnectorSettings;

import java.net.URI;
import java.net.URLDecoder;

import static com.google.common.base.Preconditions.checkArgument;
import static org.geosdi.geoplatform.experimental.openam.support.connector.request.BaseOpenAMRequest.OpenAMRequestType.SEARCH_USERS;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class OpenAMSearchConnector extends OpenAMCrudConnector {

    protected OpenAMSearchConnector(GPConnectorSettings theOpenAMConnectorSettings) {
        super(theOpenAMConnectorSettings);
    }

    /**
     * @param uid
     * @return {@link OpenAMSearchUsersResult}
     * @throws Exception
     */
    @Override
    public OpenAMSearchUsersResult searchOpenAMUserByUid(String uid) throws Exception {
        checkArgument((uid != null) && !(uid.isEmpty()), "The UID Parameter must not be null or an empty String.");
        logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@TRYING TO Search USER by UID : {}  WITH OPENAM_CONNECTOR_SETTINGS : {} \n", uid, this.openAMConnectorSettings);

        OpenAMSearchUsersRequest openAMSearchUsersRequest = this.openAMRequestMediator.getRequest(SEARCH_USERS);
        URIBuilder uriBuilder = super.buildURI(this.openAMConnectorSettings, openAMSearchUsersRequest);
        SearchUserByUidParameter parameter = new SearchUserByUidParameter(uid);
        uriBuilder.setParameter(parameter.getkey(), URLDecoder.decode(parameter.getValue(), "UTF-8"));

        URI searchURI = uriBuilder.build();
        logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OPENAM_SEARCH_USER_CONNECTOR_URI : {}\n", URLDecoder.decode(searchURI.toString(), "UTF-8"));

        IOpenAMAuthenticate openAMAuthenticate = this.authenticate();
        logger.debug("::::::::::::::::::::::::::::::AUTHENTICATE_FOR_SEARCH_USER : {}\n", openAMAuthenticate);

        HttpGet httpGet = new HttpGet(searchURI);
        httpGet.addHeader("Content-Type", "application/json");
        httpGet.addHeader(this.openAMCookieInfo.getOpenAMCookie(), openAMAuthenticate.getTokenId());

        CloseableHttpResponse response = this.httpClient.execute(httpGet);

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new IllegalStateException("OpenAMSearchUser Error Code : " + response.getStatusLine().getStatusCode());
        }
        this.logout(openAMAuthenticate.getTokenId());
        OpenAMSearchUsersResult result = this.openAMReader.readValue(response.getEntity().getContent(), OpenAMSearchUsersResult.class);
        logger.trace(":::::::::::::::::::::::::::OPENAM_SEARCH_USERS_RESULT_AS_STRING : \n{}\n", this.openAMReader.writeValueAsString(result));
        return result;
    }
}
