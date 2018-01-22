package org.geosdi.geoplatform.connector.geoserver.request.layers;

import net.jcip.annotations.ThreadSafe;
import org.apache.http.client.methods.HttpGet;
import org.geosdi.geoplatform.connector.geoserver.request.model.layers.GeoserverLayer;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonGetConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.ThreadLocal.withInitial;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class GPGeoserverLayerRequest extends GPJsonGetConnectorRequest<GeoserverLayer> {

    private final ThreadLocal<String> name;

    /**
     * @param server
     * @param theJacksonSupport
     */
    public GPGeoserverLayerRequest(GPServerConnector server, JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport, GeoserverLayer.class);
        this.name = withInitial(() -> null);
    }

    /**
     * @return {@link String}
     */
    public String getName() {
        return this.name.get();
    }

    /**
     * @param theName
     */
    public void setName(String theName) {
        this.name.set(theName);
    }

    /**
     * @return {@link HttpGet}
     */
    @Override
    protected HttpGet prepareHttpMethod() {
        String layerName = this.name.get();
        checkArgument(((layerName != null) && !(layerName.isEmpty())),
                "The Parameter Name must not be null or an Empty String.");
        String baseURI = this.serverURI.toString();
        HttpGet httpGet = ((baseURI.endsWith("/") ? new HttpGet(baseURI.concat("layers/").concat(layerName).concat(".json"))
                : new HttpGet(baseURI.concat("/layers/").concat(layerName).concat(".json"))));
        httpGet.setConfig(super.prepareRequestConfig());
        return httpGet;
    }
}