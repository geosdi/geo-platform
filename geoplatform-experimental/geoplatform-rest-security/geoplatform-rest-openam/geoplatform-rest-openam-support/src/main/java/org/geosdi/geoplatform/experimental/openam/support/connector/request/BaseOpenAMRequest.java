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
package org.geosdi.geoplatform.experimental.openam.support.connector.request;

import org.apache.http.client.utils.URIBuilder;
import org.geosdi.geoplatform.experimental.openam.api.connector.request.OpenAMConnectorRequest;
import org.geosdi.geoplatform.experimental.openam.api.connector.request.parameter.RequestParameter;
import org.geosdi.geoplatform.experimental.rs.security.connector.request.GPConnectorRequest;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Arrays.stream;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface BaseOpenAMRequest<Request extends BaseOpenAMRequest> extends GPConnectorRequest<Request> {

    /**
     * @return {@link OpenAMRequestType}
     */
    OpenAMRequestType getRequestType();

    /**
     * @param uriBuilder
     * @return {@link URIBuilder}
     * @throws Exception
     */
    default URIBuilder addRequestParameter(URIBuilder uriBuilder, RequestParameter... value) throws Exception {
        checkNotNull(uriBuilder, "The Parameter URIBuilder must not be null.");
        checkArgument(((value != null) && (value.length > 0)), "The Parameter Value must not be null");
        stream(value).forEach(p -> uriBuilder.addParameter(p.getkey(), p.getValue()));
        return uriBuilder;
    }

    /**
     * @param params
     * @return {@link OpenAMConnectorRequest}
     */
    default Request setExtraPathParam(String... params) {
        return self();
    }

    /**
     *
     */
    enum OpenAMRequestType {
        VALIDATE_TOKEN,
        AUTHENTICATE,
        SERVER_INFO,
        CREATE_USER,
        UPDATE_USER,
        SEARCH_USERS,
        DELETE_USER,
        UPDATE_GROUP_ADD_USER,
        LOGOUT;
    }
}
