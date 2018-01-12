package org.geosdi.geoplatform.connector.geoserver.request.namespaces;

import net.jcip.annotations.ThreadSafe;
import org.apache.http.client.methods.HttpGet;
import org.geosdi.geoplatform.connector.geoserver.request.model.namespace.GPGeoserverSingleNamespace;
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
public class GPGeoserverNamespaceRequest extends GPJsonGetConnectorRequest<GPGeoserverSingleNamespace> {

    private final ThreadLocal<String> prefix;

    /**
     * @param server
     * @param theJacksonSupport
     */
    public GPGeoserverNamespaceRequest(GPServerConnector server, JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport, GPGeoserverSingleNamespace.class);
        this.prefix = withInitial(() -> null);
    }

    /**
     * @param thePrefix
     */
    public void setPrefix(String thePrefix) {
        this.prefix.set(thePrefix);
    }

    /**
     * @return {@link String}
     */
    public String getPrefix() {
        return this.prefix.get();
    }

    /**
     * @return {@link HttpGet}
     */
    @Override
    protected HttpGet prepareHttpMethod() {
        String prefix = this.prefix.get();
        checkArgument(((prefix != null) && !(prefix.isEmpty())),
                "The Parameter prefix must not be null or an Empty String.");
        String baseURI = this.serverURI.toString();
        HttpGet httpGet = ((baseURI.endsWith("/") ? new HttpGet(baseURI.concat("namespaces/").concat(prefix).concat(".json"))
                : new HttpGet(baseURI.concat("/namespaces/").concat(prefix).concat(".json"))));
        httpGet.setConfig(super.prepareRequestConfig());
        return httpGet;
    }
}