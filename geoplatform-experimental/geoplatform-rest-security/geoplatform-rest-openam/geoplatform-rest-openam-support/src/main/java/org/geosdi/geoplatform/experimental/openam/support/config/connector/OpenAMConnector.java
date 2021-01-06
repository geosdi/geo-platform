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
package org.geosdi.geoplatform.experimental.openam.support.config.connector;

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

import javax.annotation.Nonnull;
import java.net.URI;
import java.net.URLDecoder;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
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
    public IOpenAMValidateToken validateToken(@Nonnull(when = NEVER) String token) throws Exception {
        checkArgument((token != null) && !(token.trim().isEmpty()), "The Token to validate must not be null or an Empty String.");
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
            throw new IllegalStateException("OpenAMValidateToken Error Code : " + response.getStatusLine().getStatusCode());
        }
        return this.openAMReader.readValue(response.getEntity().getContent(), OpenAMValidateToken.class);
    }

    /**
     * @return {@link IOpenAMServerInfo}
     * @throws Exception
     */
    @Override
    public IOpenAMServerInfo serverInfo() throws Exception {
        logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@TRYING TO HAVE SERVER_INFO WITH " + "OPENAM_CONNECTOR_SETTINGS : {}\n", this.openAMConnectorSettings);
        OpenAMServerInfoRequest serverInfoRequest = this.openAMRequestMediator.getRequest(SERVER_INFO);
        URI serverInfoURI = this.buildURI(this.openAMConnectorSettings, serverInfoRequest).build();

        logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OPENAM_SERVER_INFO_CONNECTOR_URI : {}\n", URLDecoder.decode(serverInfoURI.toString(), "UTF-8"));

        HttpGet httpGet = new HttpGet(serverInfoURI);
        httpGet.addHeader("Content-Type", "application/json");
        CloseableHttpResponse response = this.httpClient.execute(httpGet);

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new IllegalStateException("OpenAMServerInfo Error Code : " + response.getStatusLine().getStatusCode());
        }
        return this.openAMReader.readValue(response.getEntity().getContent(), OpenAMServerInfo.class);
    }
}