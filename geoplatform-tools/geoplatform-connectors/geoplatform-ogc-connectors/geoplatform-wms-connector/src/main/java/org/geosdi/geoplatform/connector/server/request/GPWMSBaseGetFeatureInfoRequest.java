package org.geosdi.geoplatform.connector.server.request;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.WMSVersion;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.jaxb.GPBaseJAXBContext;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.ThreadLocal.withInitial;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public abstract class GPWMSBaseGetFeatureInfoRequest<T, R extends GPWMSGetFeatureInfoRequest> extends GPWMSBaseGetRequest<T> implements GPWMSGetFeatureInfoRequest<T, R> {

    private final WMSVersion version;
    private final ThreadLocal<GPWMSGetMapBaseRequest> wmsGetMapBaseRequest;
    private final ThreadLocal<String[]> queryLayers;
    private final ThreadLocal<WMSFeatureInfoFormat> infoFormat;
    private final ThreadLocal<Integer> x;
    private final ThreadLocal<Integer> y;
    private final ThreadLocal<Integer> featureCount;

    /**
     * @param server
     * @param theWmsJAXBContext
     */
    protected GPWMSBaseGetFeatureInfoRequest(@Nonnull(when = NEVER) GPServerConnector server, @Nonnull(when = NEVER) GPBaseJAXBContext theWmsJAXBContext) {
        super(server, theWmsJAXBContext);
        this.version = server.getVersion();
        this.wmsGetMapBaseRequest = withInitial(() -> null);
        this.queryLayers = withInitial(() -> null);
        this.infoFormat = withInitial(() -> null);
        this.x = withInitial(() -> null);
        this.y = withInitial(() -> null);
        this.featureCount = withInitial(() -> 1);
    }

    /**
     * @param theWMSGetMapRequest
     * @return {@link R}
     */
    @Override
    public <WMSGetMapRequest extends GPWMSGetMapBaseRequest> R withWMSGetMapRequest(@Nonnull(when = NEVER) WMSGetMapRequest theWMSGetMapRequest) {
        this.wmsGetMapBaseRequest.set(theWMSGetMapRequest);
        return self();
    }

    /**
     * @param theQueryLayers
     * @return {@link R}
     */
    @Override
    public R withQueryLayers(@Nonnull(when = NEVER) String... theQueryLayers) {
        this.queryLayers.set(theQueryLayers);
        return self();
    }

    /**
     * @param theInfoFormat
     * @return {@link R}
     */
    @Override
    public R withInfoFormat(@Nonnull(when = NEVER) WMSFeatureInfoFormat theInfoFormat) {
        this.infoFormat.set(theInfoFormat);
        return self();
    }

    /**
     * @param theX
     * @return {@link R}
     */
    @Override
    public R withX(@Nonnull(when = NEVER) Integer theX) {
        this.x.set(theX);
        return self();
    }

    /**
     * @param theY
     * @return {@link R}
     */
    @Override
    public R withY(@Nonnull(when = NEVER) Integer theY) {
        this.y.set(theY);
        return self();
    }

    /**
     * @param theFeatureCount if null default value is 1.
     * @return {@link R}
     */
    @Override
    public R withFeatureCount(@Nullable Integer theFeatureCount) {
        this.featureCount.set(((theFeatureCount != null) && (theFeatureCount != 0)) ? theFeatureCount : 1);
        return self();
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        GPWMSGetMapBaseRequest wmsGetMapBaseRequest = this.wmsGetMapBaseRequest.get();
        checkArgument(wmsGetMapBaseRequest != null, "The Parameter wmsGetMapBaseRequest must not be null.");
        String[] queryLayers = this.queryLayers.get();
        checkArgument(queryLayers != null, "The Parameter queryLayers must not be null.");
        String querylayersKVP = stream(queryLayers)
                .filter(Objects::nonNull)
                .filter(layer -> !(layer.trim().isEmpty()))
                .collect(joining(","));
        checkArgument((querylayersKVP != null) && !(querylayersKVP.trim().isEmpty()), "The Parameter querylayersKVP must not be null");
        WMSFeatureInfoFormat infoFormat = this.infoFormat.get();
        checkArgument(infoFormat != null, "The Parameter infoFormat must not be null.");
        Integer x = this.x.get();
        checkArgument(x != null, "The Parameter x must not be null.");
        Integer y = this.y.get();
        checkArgument(y != null, "The Parameter y must not be null.");
        Integer featureCount = this.featureCount.get();
        String baseURI = this.serverURI.toString();
        return (baseURI.contains("?") ?
                baseURI.concat(WMS_GET_FEATURE_INFO_BASE_REQUEST.replace("${start}", "&").replace("${version}", this.version.getVersion())
                        .replace("${MAP_REQUEST_COPY}", wmsGetMapBaseRequest.toWMSKeyValuePair())
                        .replace("${query_layers}", querylayersKVP)
                        .replace("${info_format}", infoFormat.getFormat()).replace("${x}", x.toString())
                        .replace("${y}", y.toString()).replace("${feature_count}", featureCount.toString()))
                : baseURI.concat(WMS_GET_FEATURE_INFO_BASE_REQUEST.replace("${start}", "?").replace("${version}", this.version.getVersion())
                .replace("${version}", this.version.getVersion())
                .replace("${MAP_REQUEST_COPY}", wmsGetMapBaseRequest.toWMSKeyValuePair())
                .replace("${query_layers}", querylayersKVP)
                .replace("${info_format}", infoFormat.getFormat()).replace("${x}", x.toString())
                .replace("${y}", y.toString()).replace("${feature_count}", featureCount.toString())));
    }

    /**
     * @return {@link R}
     */
    protected abstract R self();
}