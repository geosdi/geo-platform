package org.geosdi.geoplatform.experimental.openam.support.config.connector;

import com.google.common.base.Preconditions;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.geosdi.geoplatform.experimental.openam.api.model.serverinfo.IOpenAMServerInfo;
import org.geosdi.geoplatform.experimental.openam.api.model.serverinfo.OpenAMServerInfo;
import org.geosdi.geoplatform.experimental.openam.api.model.validate.IOpenAMValidateToken;
import org.geosdi.geoplatform.experimental.openam.api.model.validate.OpenAMValidateToken;
import org.geosdi.geoplatform.experimental.openam.support.config.connector.search.OpenAMSearchConnector;
import org.geosdi.geoplatform.experimental.openam.support.connector.request.serverinfo.OpenAMServerInfoRequest;
import org.geosdi.geoplatform.experimental.openam.support.connector.request.validate.ValidateTokenRequest;
import org.geosdi.geoplatform.experimental.rs.security.connector.settings.GPConnectorSettings;

import java.net.URI;
import java.net.URLDecoder;
import static org.geosdi.geoplatform.experimental.openam.api.connector.request.parameter.RequestParameter.RequestParameterType.ACTION_VALIDATE;
import static org.geosdi.geoplatform.experimental.openam.support.connector.request.BaseOpenAMRequest.OpenAMRequestType.SERVER_INFO;
import static org.geosdi.geoplatform.experimental.openam.support.connector.request.BaseOpenAMRequest.OpenAMRequestType.VALIDATE_TOKEN;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
class OpenAMConnector extends OpenAMSearchConnector {

    public OpenAMConnector(GPConnectorSettings theOpenAMConnectorSettings) {
        super(theOpenAMConnectorSettings);
    }

    /**
     * @param token
     * @return {@link Boolean}
     * @throws Exception
     */
    @Override
    public IOpenAMValidateToken validateToken(String token) throws Exception {
        Preconditions.checkArgument((token != null) && !(token.isEmpty()), "The Token to validate " +
                "must not be null or an Empty String.");
        logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@TRYING TO VALIDATE_TOKEN WITH " + "OPENAM_CONNECTOR_SETTINGS : {}\n",
                this.openAMConnectorSettings);
        ValidateTokenRequest validateTokenRequest = this.openAMRequestMediator.getRequest(VALIDATE_TOKEN);
        URIBuilder uriBuilder = this.buildURI(this.openAMConnectorSettings, validateTokenRequest.setExtraPathParam(token));
        validateTokenRequest.addRequestParameter(uriBuilder, this.requestParameterMediator.getRequest(ACTION_VALIDATE));

        logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OPENAM_VALIDATE_TOKEN_CONNECTOR_URI : {}\n",
                URLDecoder.decode(uriBuilder.toString(), "UTF-8"));

        HttpPost httpPost = new HttpPost(uriBuilder.build());
        httpPost.addHeader("Content-Type", "application/json");
        CloseableHttpResponse response = this.httpClient.execute(httpPost);

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new IllegalStateException("OpenAMValidateToken Error Code : "
                    + response.getStatusLine().getStatusCode());
        }

        return this.openAMReader.readValue(response.getEntity().getContent(), OpenAMValidateToken.class);
    }

    /**
     * @return {@link IOpenAMServerInfo}
     * @throws Exception
     */
    @Override
    public IOpenAMServerInfo serverInfo() throws Exception {
        logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@TRYING TO HAVE SERVER_INFO WITH " + "OPENAM_CONNECTOR_SETTINGS : {}\n",
                this.openAMConnectorSettings);
        OpenAMServerInfoRequest serverInfoRequest = this.openAMRequestMediator.getRequest(SERVER_INFO);
        URI serverInfoURI = this.buildURI(this.openAMConnectorSettings, serverInfoRequest).build();

        logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OPENAM_SERVER_INFO_CONNECTOR_URI : {}\n",
                URLDecoder.decode(serverInfoURI.toString(), "UTF-8"));

        HttpGet httpGet = new HttpGet(serverInfoURI);
        httpGet.addHeader("Content-Type", "application/json");
        CloseableHttpResponse response = this.httpClient.execute(httpGet);

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new IllegalStateException("OpenAMServerInfo Error Code : "
                    + response.getStatusLine().getStatusCode());
        }

        return this.openAMReader.readValue(response.getEntity().getContent(), OpenAMServerInfo.class);
    }
}
