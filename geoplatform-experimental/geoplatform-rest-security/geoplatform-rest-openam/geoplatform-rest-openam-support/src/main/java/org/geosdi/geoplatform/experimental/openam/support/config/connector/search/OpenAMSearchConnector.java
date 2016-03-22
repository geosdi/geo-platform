package org.geosdi.geoplatform.experimental.openam.support.config.connector.search;

import com.google.common.base.Preconditions;
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
        Preconditions.checkArgument((uid != null) && !(uid.isEmpty()), "The UID Parameter must not be " +
                "null or an empty String.");
        logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@TRYING TO Search USER by UID : {}  WITH " +
                "OPENAM_CONNECTOR_SETTINGS : {} \n", uid, this.openAMConnectorSettings);

        OpenAMSearchUsersRequest openAMSearchUsersRequest = this.openAMRequestMediator.getRequest(SEARCH_USERS);
        URIBuilder uriBuilder = super.buildURI(this.openAMConnectorSettings, openAMSearchUsersRequest);
        SearchUserByUidParameter parameter = new SearchUserByUidParameter(uid);
        uriBuilder.setParameter(parameter.getkey(), URLDecoder.decode(parameter.getValue(), "UTF-8"));

        URI searchURI = uriBuilder.build();
        logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OPENAM_SEARCH_USER_CONNECTOR_URI : {}\n",
                URLDecoder.decode(searchURI.toString(), "UTF-8"));

        IOpenAMAuthenticate openAMAuthenticate = this.authenticate();
        logger.debug("::::::::::::::::::::::::::::::AUTHENTICATE_FOR_SEARCH_USER : {}\n", openAMAuthenticate);

        HttpGet httpGet = new HttpGet(searchURI);
        httpGet.addHeader("Content-Type", "application/json");
        httpGet.addHeader(this.openAMCookieInfo.getOpenAMCookie(), openAMAuthenticate.getTokenId());

        CloseableHttpResponse response = this.httpClient.execute(httpGet);

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new IllegalStateException("OpenAMSearchUser Error Code : "
                    + response.getStatusLine().getStatusCode());
        }
        this.logout(openAMAuthenticate.getTokenId());
        OpenAMSearchUsersResult result = this.openAMReader.readValue(response.getEntity().getContent(),
                OpenAMSearchUsersResult.class);
        logger.trace(":::::::::::::::::::::::::::OPENAM_SEARCH_USERS_RESULT_AS_STRING : \n{}\n",
                this.openAMReader.writeValueAsString(result));
        return result;
    }
}
