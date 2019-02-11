package org.geosdi.geoplatform.connector.server.request;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.WMSVersion;
import org.geosdi.geoplatform.connector.server.GPWMSServerConnector;
import org.geosdi.geoplatform.jaxb.GPBaseJAXBContext;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public abstract class GPBaseWMSDescribeLayerRequest<T> extends GPWMSBaseGetRequest<T> implements GPWMSDescribeLayerRequest<T> {

    private final WMSVersion version;
    private final ThreadLocal<String[]> layers;

    /**
     * @param server
     * @param theWmsJAXBContext
     */
    protected GPBaseWMSDescribeLayerRequest(@Nonnull(when = NEVER) GPWMSServerConnector server, @Nonnull(when = NEVER) GPBaseJAXBContext theWmsJAXBContext) {
        super(server, theWmsJAXBContext);
        this.version = server.getVersion();
        this.layers = ThreadLocal.withInitial(() -> null);
    }

    /**
     * @param theLayers
     * @return {@link GPWMSDescribeLayerRequest<T>}
     */
    @Override
    public GPWMSDescribeLayerRequest<T> withLayers(@Nonnull(when = When.NEVER) String... theLayers) {
        this.layers.set(theLayers);
        return this;
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String[] layers = this.layers.get();
        checkArgument(layers != null, "The Parameter layers must not be null.");
        String layersKVP = stream(layers)
                .filter(Objects::nonNull)
                .filter(layer -> !(layer.trim().isEmpty()))
                .collect(joining(","));
        checkArgument((layersKVP != null) && !(layersKVP.trim().isEmpty()), "The Parameter layersKVP must not be null");
        String baseURI = this.serverURI.toString();
        return (baseURI.contains("?") ?
                baseURI.concat(WMS_DESCRIBE_LAYER_BASE_REQUEST.replace("${start}", "&").replace("${version}", this.version.getVersion())
                        .replace("${layers}", layersKVP))
                : baseURI.concat(WMS_DESCRIBE_LAYER_BASE_REQUEST.replace("${start}", "?").replace("${version}", this.version.getVersion())
                .replace("${layers}", layersKVP)));
    }
}