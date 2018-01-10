package org.geosdi.geoplatform.connector.geoserver.request;

import org.geosdi.geoplatform.connector.geoserver.request.model.GPGeoserverEmptyResponse;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonGetConnectorRequest;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import java.io.IOException;
import java.io.InputStream;

import static com.google.common.base.Preconditions.checkArgument;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPGeoserverGetConnectorRequest<T, E extends GPGeoserverEmptyResponse<T>> extends GPJsonGetConnectorRequest<T> {

    private static final JacksonSupport emptyJacksonSupport = new GPJacksonSupport(UNWRAP_ROOT_VALUE_DISABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
            WRAP_ROOT_VALUE_DISABLE,
            INDENT_OUTPUT_ENABLE);
    //
    private final Class<E> emptyResponse;

    /**
     * @param server
     * @param theJacksonSupport
     * @param theClasse
     * @param emptyResponse
     */
    public GPGeoserverGetConnectorRequest(GPServerConnector server, JacksonSupport theJacksonSupport, Class<T> theClasse,
            Class<E> emptyResponse) {
        super(server, theJacksonSupport, theClasse);
        this.emptyResponse = emptyResponse;
    }

    /**
     * @return {@link T}
     * @throws Exception
     */
    @Override
    public T getResponse() throws Exception {
        try {
            return super.getResponse();
        } catch (IOException ex) {
            return internalResponse(super.getResponseAsStream());
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
            return emptyJacksonSupport.getDefaultMapper().readValue(inputStream, this.emptyResponse).toModel();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IllegalStateException(INCORRECT_RESPONSE_MESSAGE);
        } finally {
            inputStream.close();
        }
    }
}