package org.geosdi.geoplatform.experimental.openam.support.config.connector.secure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.geosdi.geoplatform.experimental.openam.api.connector.cookie.IOpenAMCookieInfo;
import org.geosdi.geoplatform.experimental.openam.api.connector.request.parameter.mediator.IRequestParameterMediator;
import org.geosdi.geoplatform.experimental.openam.api.model.authenticate.IOpenAMAuthenticate;
import org.geosdi.geoplatform.experimental.openam.api.model.authenticate.IOpenAMLogout;
import org.geosdi.geoplatform.experimental.openam.api.model.authenticate.OpenAMAuthenticate;
import org.geosdi.geoplatform.experimental.openam.api.model.authenticate.OpenAMLogout;
import org.geosdi.geoplatform.experimental.openam.api.model.error.IOpenAMErrorResponse;
import org.geosdi.geoplatform.experimental.openam.api.model.error.OpenAMErrorResponse;
import org.geosdi.geoplatform.experimental.openam.support.config.connector.base.BaseOpenAMConnector;
import org.geosdi.geoplatform.experimental.openam.support.connector.request.authenticate.IOpenAMAuthenticateRequest;
import org.geosdi.geoplatform.experimental.openam.support.connector.request.authenticate.OpenAMLogoutRequest;
import org.geosdi.geoplatform.experimental.openam.support.connector.request.mediator.IOpenAMRequestMediator;
import org.geosdi.geoplatform.experimental.rs.security.connector.settings.GPConnectorSettings;
import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum;
import org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature;
import org.slf4j.Logger;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URLDecoder;

import static org.geosdi.geoplatform.experimental.openam.api.connector.request.parameter.RequestParameter.RequestParameterType.ACTION_LOGOUT;
import static org.geosdi.geoplatform.experimental.openam.support.connector.request.BaseOpenAMRequest.OpenAMRequestType.AUTHENTICATE;
import static org.geosdi.geoplatform.experimental.openam.support.connector.request.BaseOpenAMRequest.OpenAMRequestType.LOGOUT;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class OpenAMAuthorizedConnector implements BaseOpenAMConnector {

    @GeoPlatformLog
    protected static Logger logger;
    //
    protected final GPConnectorSettings openAMConnectorSettings;
    protected final CloseableHttpClient httpClient;
    @Resource(name = "openAMRequestMediator")
    protected IOpenAMRequestMediator openAMRequestMediator;
    @Resource(name = "requestParameterMediator")
    protected IRequestParameterMediator requestParameterMediator;
    protected IOpenAMCookieInfo openAMCookieInfo;
    protected final ObjectMapper openAMReader = new GPJacksonSupport(GPJacksonSupportEnum.UNWRAP_ROOT_VALUE_DISABLE,
            GPJacksonSupportEnum.FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            GPJacksonSupportEnum.ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
            GPJacksonSupportEnum.WRAP_ROOT_VALUE_DISABLE, GPJacksonSupportEnum.INDENT_OUTPUT_ENABLE)
            .configure(GPJsonIncludeFeature.NON_NULL).getDefaultMapper();

    protected OpenAMAuthorizedConnector(GPConnectorSettings theOpenAMConnectorSettings) {
        Preconditions.checkNotNull(theOpenAMConnectorSettings, "The OpenAMConnectorSettings must not be null.");
        this.openAMConnectorSettings = theOpenAMConnectorSettings;
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(this.openAMConnectorSettings.getMaxTotalConnections());
        cm.setDefaultMaxPerRoute(this.openAMConnectorSettings.getDefaultMaxPerRoute());
        this.httpClient = HttpClients
                .custom()
                .setConnectionManager(cm)
                .setRetryHandler(new OpenAMHttpRequestRetryHandler(5))
                .build();
    }

    /**
     * @return {@link String}
     * @throws Exception
     */
    @Override
    public IOpenAMAuthenticate authenticate() throws Exception {
        logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@TRYING TO AUTHENTICATE WITH " + "OPENAM_CONNECTOR_SETTINGS : {}\n",
                this.openAMConnectorSettings);
        IOpenAMAuthenticateRequest authenticateRequest = this.openAMRequestMediator.getRequest(AUTHENTICATE);
        URI authenticateURI = this.buildURI(openAMConnectorSettings, authenticateRequest).build();

        logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OPENAM_AUTHENTICATE_TOKEN_CONNECTOR_URI : {}\n",
                URLDecoder.decode(authenticateURI.toString(), "UTF-8"));

        HttpPost httpPost = new HttpPost(authenticateURI);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader(authenticateRequest.getUserNameKey(), authenticateRequest.getUserName());
        httpPost.addHeader(authenticateRequest.getPasswordKey(), authenticateRequest.getPassword());

        CloseableHttpResponse response = this.httpClient.execute(httpPost);

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new IllegalStateException("OpenAMValidateToken Error Code : "
                    + response.getStatusLine().getStatusCode());
        }
        return this.openAMReader.readValue(response.getEntity().getContent(), OpenAMAuthenticate.class);
    }

    /**
     * @param userName
     * @param password
     * @return {@link IOpenAMAuthenticate}
     * @throws Exception
     */
    @Override
    public IOpenAMAuthenticate authenticate(String userName, String password) throws Exception {
        Preconditions.checkArgument((userName != null) && !(userName.isEmpty()), "The Parameter UserName must " +
                "not be null or an Empty String.");
        Preconditions.checkArgument((password != null) && !(password.isEmpty()), "The Parameter Password must " +
                "not be null or an Empty String.");

        logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@TRYING TO AUTHENTICATE WITH " + "OPENAM_CONNECTOR_SETTINGS : {}\n",
                this.openAMConnectorSettings);
        IOpenAMAuthenticateRequest authenticateRequest = this.openAMRequestMediator.getRequest(AUTHENTICATE);
        URI authenticateURI = this.buildURI(openAMConnectorSettings, authenticateRequest).build();

        logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OPENAM_AUTHENTICATE_TOKEN_CONNECTOR_URI : {}\n",
                URLDecoder.decode(authenticateURI.toString(), "UTF-8"));

        HttpPost httpPost = new HttpPost(authenticateURI);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader(authenticateRequest.getUserNameKey(), userName);
        httpPost.addHeader(authenticateRequest.getPasswordKey(), password);

        CloseableHttpResponse response = this.httpClient.execute(httpPost);

        if (response.getStatusLine().getStatusCode() != 200) {
            IOpenAMErrorResponse openAMErrorResponse = this.openAMReader
                    .readValue(response.getEntity().getContent(), OpenAMErrorResponse.class);
            throw new IllegalStateException("OpenAMAuthentication Error Code : " + openAMErrorResponse.getCode()
                    + " - Reason : " + openAMErrorResponse.getReason() + " - Message : " + openAMErrorResponse.getMessage());
        }
        return this.openAMReader.readValue(response.getEntity().getContent(), OpenAMAuthenticate.class);
    }

    /**
     * @param tokenId
     * @return {@link IOpenAMLogout}
     * @throws Exception
     */
    @Override
    public IOpenAMLogout logout(String tokenId) throws Exception {
        logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@TRYING TO LOGOUT WITH " + "OPENAM_CONNECTOR_SETTINGS : {} " +
                " and tokenId : {}\n", this.openAMConnectorSettings, tokenId);
        OpenAMLogoutRequest openAMLogoutRequest = this.openAMRequestMediator.getRequest(LOGOUT);
        URI logoutURI = openAMLogoutRequest.addRequestParameter(this.buildURI(openAMConnectorSettings,
                openAMLogoutRequest), this.requestParameterMediator.getRequest(ACTION_LOGOUT)).build();

        logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OPENAM_LOGOUT_CONNECTOR_URI : {}\n",
                URLDecoder.decode(logoutURI.toString(), "UTF-8"));

        HttpPost httpPost = new HttpPost(logoutURI);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader(this.openAMCookieInfo.getOpenAMCookie(), tokenId);
        CloseableHttpResponse response = this.httpClient.execute(httpPost);

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new IllegalStateException("OpenAMLogout Error Code : "
                    + response.getStatusLine().getStatusCode());
        }
        return this.openAMReader.readValue(response.getEntity().getContent(), OpenAMLogout.class);
    }

    @Override
    public void destroy() throws Exception {
        logger.debug("::::::::::::::::::::::::::::CALLED Destroy on : {}\n", getClass().getSimpleName());
        if (this.httpClient != null) {
            this.httpClient.close();
        }
    }

    /**
     * @param openAMCookieInfo
     */
    @Override
    public void registerOpenAMCookieInfo(IOpenAMCookieInfo openAMCookieInfo) {
        this.openAMCookieInfo = openAMCookieInfo;
    }
}
