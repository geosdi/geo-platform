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
package org.geosdi.geoplatform.connector.server.request;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.WMSVersion;
import org.geosdi.geoplatform.connector.bridge.store.GPWMSGetFeatureInfoReaderStore;
import org.geosdi.geoplatform.connector.bridge.store.WMSGetFeatureInfoReaderStore;
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

    protected static final WMSGetFeatureInfoReaderStore store = new GPWMSGetFeatureInfoReaderStore();
    //
    protected final WMSVersion version;
    protected final ThreadLocal<GPWMSGetMapBaseRequest> wmsGetMapBaseRequest;
    protected final ThreadLocal<String[]> queryLayers;
    protected final ThreadLocal<WMSFeatureInfoFormat> infoFormat;
    protected final ThreadLocal<Integer> x;
    protected final ThreadLocal<Integer> y;
    protected final ThreadLocal<Integer> featureCount;

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