package org.geosdi.geoplatform.experimental.openam.support.config.connector.crud;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.io.CharStreams;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.geosdi.geoplatform.experimental.openam.api.model.authenticate.IOpenAMAuthenticate;
import org.geosdi.geoplatform.experimental.openam.api.model.delete.IOpenAMDeleteResponse;
import org.geosdi.geoplatform.experimental.openam.api.model.delete.OpenAMDeleteResponse;
import org.geosdi.geoplatform.experimental.openam.api.model.groups.IOpenAMGroup;
import org.geosdi.geoplatform.experimental.openam.api.model.groups.IOpenAMGroupResponse;
import org.geosdi.geoplatform.experimental.openam.api.model.groups.OpenAMGroupResponse;
import org.geosdi.geoplatform.experimental.openam.api.model.users.IOpenAMUser;
import org.geosdi.geoplatform.experimental.openam.api.model.users.IOpenAMUserResponse;
import org.geosdi.geoplatform.experimental.openam.api.model.users.OpenAMUserResponse;
import org.geosdi.geoplatform.experimental.openam.support.config.connector.secure.OpenAMAuthorizedConnector;
import org.geosdi.geoplatform.experimental.openam.support.connector.request.group.OpenAMUpdateGroupAddUserRequest;
import org.geosdi.geoplatform.experimental.openam.support.connector.request.users.OpenAMCreateUserRequest;
import org.geosdi.geoplatform.experimental.openam.support.connector.request.users.OpenAMDeleteUserRequest;
import org.geosdi.geoplatform.experimental.openam.support.connector.request.users.OpenAMUpdateUserRequest;
import org.geosdi.geoplatform.experimental.rs.security.connector.settings.GPConnectorSettings;

import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLDecoder;

import static org.geosdi.geoplatform.experimental.openam.api.connector.request.parameter.RequestParameter.RequestParameterType.ACTION_CREATE_USER;
import static org.geosdi.geoplatform.experimental.openam.support.connector.request.BaseOpenAMRequest.OpenAMRequestType.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class OpenAMCrudConnector extends OpenAMAuthorizedConnector {

    protected OpenAMCrudConnector(GPConnectorSettings theOpenAMConnectorSettings) {
        super(theOpenAMConnectorSettings);
    }

    /**
     * @param openAMUser
     * @return {@link IOpenAMUserResponse}
     * @throws Exception
     */
    @Override
    public IOpenAMUserResponse createUser(IOpenAMUser openAMUser) throws Exception {
        Preconditions.checkNotNull(openAMUser, "The OpenAMUser must not be null");
        logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@TRYING TO CREATE USER {}  WITH " +
                "OPENAM_CONNECTOR_SETTINGS : {} \n", openAMUser, this.openAMConnectorSettings);

        OpenAMCreateUserRequest openAMCreateUserRequest = this.openAMRequestMediator.getRequest(CREATE_USER);
        URI createUserURI = openAMCreateUserRequest.addRequestParameter(this.buildURI(openAMConnectorSettings,
                openAMCreateUserRequest), this.requestParameterMediator.getRequest(ACTION_CREATE_USER)).build();

        logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OPENAM_CREATE_USER_CONNECTOR_URI : {}\n",
                URLDecoder.decode(createUserURI.toString(), "UTF-8"));

        IOpenAMAuthenticate openAMAuthenticate = this.authenticate();
        logger.debug("::::::::::::::::::::::::::::::AUTHENTICATE_FOR_CREATION_USER : {}\n", openAMAuthenticate);

        String openAMUserAsString = this.openAMReader.writeValueAsString(openAMUser);
        logger.debug("::::::::::::::::::::::::::OPENAM_USER_AS_STRING : {}\n", openAMUserAsString);

        HttpPost httpPost = new HttpPost(createUserURI);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader(this.openAMCookieInfo.getOpenAMCookie(), openAMAuthenticate.getTokenId());
        httpPost.setEntity(new StringEntity(openAMUserAsString, ContentType.APPLICATION_JSON));

        CloseableHttpResponse response = this.httpClient.execute(httpPost);

//        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@{}\n", CharStreams
//                .toString(new InputStreamReader(response.getEntity().getContent(), Charsets.UTF_8)));

        if (response.getStatusLine().getStatusCode() != 201) {
            throw new IllegalStateException("OpenAMCreateUser Error : "
                    + CharStreams.toString(new InputStreamReader(response.getEntity()
                    .getContent(), Charsets.UTF_8)));
        }
        this.logout(openAMAuthenticate.getTokenId());
        return this.openAMReader.readValue(response.getEntity().getContent(), OpenAMUserResponse.class);
    }

    /**
     * @param openAMUser
     * @return {@link IOpenAMUserResponse}
     * @throws Exception
     */
    @Override
    public IOpenAMUserResponse updateUser(IOpenAMUser openAMUser) throws Exception {
        Preconditions.checkNotNull(openAMUser, "The OpenAMUser must not be null");
        Preconditions.checkArgument((openAMUser.getUserName() != null) && !(openAMUser.getUserName().isEmpty()),
                "The OpenAm UserName must not be null or an Empty String.");
        logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@TRYING TO Update USER {}  WITH " +
                "OPENAM_CONNECTOR_SETTINGS : {} \n", openAMUser.getUserName(), this.openAMConnectorSettings);

        OpenAMUpdateUserRequest updateUserRequest = this.openAMRequestMediator.getRequest(UPDATE_USER);
        URIBuilder updateURIBuilder = this.buildURI(this.openAMConnectorSettings, updateUserRequest.setExtraPathParam(openAMUser.getUserName()));

        logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OPENAM_UPDATE_USER_CONNECTOR_URI : {}\n",
                URLDecoder.decode(updateURIBuilder.toString(), "UTF-8"));

        IOpenAMAuthenticate openAMAuthenticate = super.authenticate();
        logger.debug("::::::::::::::::::::::::::::::AUTHENTICATE_FOR_UPDATE_USER : {}\n", openAMAuthenticate);

        String openAMUserAsString = this.openAMReader.writeValueAsString(openAMUser);
        logger.debug("::::::::::::::::::::::::::OPENAM_UPDATE_USER_AS_STRING : {}\n", openAMUserAsString);

        HttpPut httpPut = new HttpPut(updateURIBuilder.build());
        httpPut.addHeader("Content-Type", "application/json");
        httpPut.addHeader(this.openAMCookieInfo.getOpenAMCookie(), openAMAuthenticate.getTokenId());
        httpPut.setEntity(new StringEntity(openAMUserAsString, ContentType.APPLICATION_JSON));

        CloseableHttpResponse response = this.httpClient.execute(httpPut);

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new IllegalStateException("OpenAMUpdateUser Error Code : "
                    + response.getStatusLine().getStatusCode());
        }
        this.logout(openAMAuthenticate.getTokenId());
        return this.openAMReader.readValue(response.getEntity().getContent(), OpenAMUserResponse.class);
    }

    /**
     * @param userName
     * @return {@link IOpenAMDeleteResponse}
     * @throws Exception
     */
    @Override
    public IOpenAMDeleteResponse deleteUser(String userName) throws Exception {
        IOpenAMAuthenticate openAMAuthenticate = null;
        try {
            Preconditions.checkNotNull(userName, "The UserName must not be null");
            logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@TRYING TO Delete USER with UserName: {}  WITH " +
                    "OPENAM_CONNECTOR_SETTINGS : {} \n", userName, this.openAMConnectorSettings);

            OpenAMDeleteUserRequest deleteUserRequest = this.openAMRequestMediator.getRequest(DELETE_USER);
            URIBuilder uriBuilder = this.buildURI(this.openAMConnectorSettings, deleteUserRequest.setExtraPathParam(userName));

            logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OPENAM_DELETE_USER_CONNECTOR_URI : {}\n",
                    URLDecoder.decode(uriBuilder.toString(), "UTF-8"));

            openAMAuthenticate = super.authenticate();
            logger.debug("::::::::::::::::::::::::::::::AUTHENTICATE_FOR_DELETE_USER : {}\n", openAMAuthenticate);

            HttpDelete httpDelete = new HttpDelete(uriBuilder.build());
            httpDelete.addHeader(this.openAMCookieInfo.getOpenAMCookie(), openAMAuthenticate.getTokenId());

            CloseableHttpResponse response = this.httpClient.execute(httpDelete);
            if (response.getStatusLine().getStatusCode() == 404) {
                logger.debug("##############################OPENAM_USER with USER_NAME : {} - not exist\n", userName);
                return new OpenAMDeleteResponse() {
                    {
                        super.setSuccess(Boolean.TRUE);
                    }
                };
            }

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new IllegalStateException("OpenAMDeleteeUser Error Code : "
                        + response.getStatusLine().getStatusCode());
            }
            return this.openAMReader.readValue(response.getEntity().getContent(), OpenAMDeleteResponse.class);
        } finally {
            if (openAMAuthenticate != null)
                super.logout(openAMAuthenticate.getTokenId());
        }
    }

    /**
     * @param group
     * @return {@link IOpenAMGroupResponse}
     * @throws Exception
     */
    @Override
    public IOpenAMGroupResponse updateGroupAddingUser(IOpenAMGroup group)
            throws Exception {
        Preconditions.checkNotNull(group, "The group must not be null");

        logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@TRYING TO UPDATE GROUP : {} adding USER WITH " +
                "OPENAM_CONNECTOR_SETTINGS : {} \n", group, this.openAMConnectorSettings);

        OpenAMUpdateGroupAddUserRequest updateGroupAddUserRequest = this.openAMRequestMediator.getRequest(UPDATE_GROUP_ADD_USER);
        URI uri = this.buildURI(this.openAMConnectorSettings, updateGroupAddUserRequest.setExtraPathParam(group.getGroupName())).build();

        logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OPENAM_UPDATE_GROUP_ADDING_USER_CONNECTOR_URI : {}\n",
                URLDecoder.decode(uri.toString(), "UTF-8"));

        logger.debug(":::::::::::::::::::::::::::::::UPDATE_GROUP_REQUEST_AS_STRING : {}\n",
                this.openAMReader.writeValueAsString(group));

        IOpenAMAuthenticate openAMAuthenticate = super.authenticate();
        logger.debug("::::::::::::::::::::::::::::::AUTHENTICATE_FOR_UPDATE_GROUP : {}\n", openAMAuthenticate);

        HttpPut httpPut = new HttpPut(uri);
        httpPut.addHeader("Content-Type", "application/json");
        httpPut.addHeader(this.openAMCookieInfo.getOpenAMCookie(), openAMAuthenticate.getTokenId());
        httpPut.setEntity(new StringEntity(this.openAMReader.writeValueAsString(group), ContentType.APPLICATION_JSON));

        CloseableHttpResponse response = this.httpClient.execute(httpPut);

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new IllegalStateException("OpenAMUpdateGroupAddingUser Error Code : "
                    + response.getStatusLine().getStatusCode());
        }

        super.logout(openAMAuthenticate.getTokenId());
        return this.openAMReader.readValue(response.getEntity().getContent(), OpenAMGroupResponse.class);
    }
}
