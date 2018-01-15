package org.geosdi.geoplatform.connector.geoserver.request.styles;

import net.jcip.annotations.ThreadSafe;
import org.apache.http.client.methods.HttpGet;
import org.geosdi.geoplatform.connector.geoserver.request.model.styles.GPGeoserverSingleStyle;
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
public class GPGeoserverStyleRequest extends GPJsonGetConnectorRequest<GPGeoserverSingleStyle> {

    private final ThreadLocal<String> name;

    /**
     * @param server
     * @param theJacksonSupport
     */
    public GPGeoserverStyleRequest(GPServerConnector server, JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport, GPGeoserverSingleStyle.class);
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
        String styleName = this.name.get();
        checkArgument(((styleName != null) && !(styleName.isEmpty())),
                "The Parameter Style Name must not be null or an Empty String.");
        String baseURI = this.serverURI.toString();
        HttpGet httpGet = ((baseURI.endsWith("/") ? new HttpGet(baseURI.concat("styles/").concat(styleName).concat(".json"))
                : new HttpGet(baseURI.concat("/styles/").concat(styleName).concat(".json"))));
        httpGet.setConfig(super.prepareRequestConfig());
        return httpGet;
    }
}