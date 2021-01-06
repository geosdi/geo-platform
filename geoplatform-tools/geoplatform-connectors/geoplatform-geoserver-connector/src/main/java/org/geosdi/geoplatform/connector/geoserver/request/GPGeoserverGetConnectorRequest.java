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
package org.geosdi.geoplatform.connector.geoserver.request;

import org.geosdi.geoplatform.connector.geoserver.model.GPGeoserverEmptyResponse;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.exception.IncorrectResponseException;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonGetConnectorRequest;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import java.io.*;

import static com.google.common.base.Preconditions.checkArgument;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPGeoserverGetConnectorRequest<T, E extends GPGeoserverEmptyResponse<T>> extends GPJsonGetConnectorRequest<T> {

    protected static final JacksonSupport emptyJacksonSupport = new GPJacksonSupport(UNWRAP_ROOT_VALUE_DISABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
            WRAP_ROOT_VALUE_DISABLE,
            INDENT_OUTPUT_ENABLE);
    //
    private final Class<E> emptyResponse;

    /**
     * @param server
     * @param theJacksonSupport
     */
    protected GPGeoserverGetConnectorRequest(GPServerConnector server, JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport);
        checkArgument(((this.emptyResponse = forEmptyResponse()) != null), "The emptyResponse class must not be null.");
    }

    /**
     * @return {@link T}
     * @throws Exception
     */
    @Override
    public T getResponse() throws Exception {
        String responseAsString = super.getResponseAsString();
        try {
            return readInternal(new BufferedReader(new StringReader(responseAsString)));
        } catch (Exception ex) {
            return internalResponse(new ByteArrayInputStream(responseAsString.getBytes()));
        }
    }

    /**
     * @param inputStream
     * @param <IS>
     * @return {@link T}
     * @throws Exception
     */
    protected final <IS extends InputStream> T internalResponse(IS inputStream) throws Exception {
        checkArgument(inputStream != null, "The Parameter InputStream must not be null.");
        try {
            return this.internalReadResponse(inputStream).toModel();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IncorrectResponseException(ex);
        } finally {
            inputStream.close();
        }
    }

    /**
     * @param inputStream
     * @param <IS>
     * @return {@link T}
     * @throws Exception
     */
    protected <IS extends InputStream> E internalReadResponse(IS inputStream) throws Exception {
        return emptyJacksonSupport.getDefaultMapper().readValue(inputStream, this.emptyResponse);
    }

    /**
     * @return {@link Class<E>}
     */
    protected abstract Class<E> forEmptyResponse();
}