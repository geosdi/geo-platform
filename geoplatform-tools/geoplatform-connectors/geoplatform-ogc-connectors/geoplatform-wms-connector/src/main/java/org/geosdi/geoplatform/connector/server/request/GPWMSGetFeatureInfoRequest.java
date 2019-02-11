package org.geosdi.geoplatform.connector.server.request;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.meta.When;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWMSGetFeatureInfoRequest<T, R extends GPWMSGetFeatureInfoRequest> extends GPConnectorRequest<T> {

    String WMS_GET_FEATURE_INFO_BASE_REQUEST = "${start}SERVICE=WMS&REQUEST=GetFeatureInfo&VERSION=${version}&${MAP_REQUEST_COPY}&QUERY_LAYERS=${query_layers}&INFO_FORMAT=${info_format}&X=${x}&Y=${y}&FEATURE_COUNT=${feature_count}";

    /**
     * @param theWMSGetMapRequest
     * @param <WMSGetMapRequest>
     * @return {@link R}
     */
    <WMSGetMapRequest extends GPWMSGetMapBaseRequest> R withWMSGetMapRequest(@Nonnull(when = When.NEVER) WMSGetMapRequest theWMSGetMapRequest);

    /**
     * @param theQueryLayers
     * @return {@link R}
     */
    R withQueryLayers(@Nonnull(when = When.NEVER) String... theQueryLayers);

    /**
     * @param theInfoFormat
     * @return {@link R}
     */
    R withInfoFormat(@Nonnull(when = When.NEVER) WMSFeatureInfoFormat theInfoFormat);

    /**
     * @param theX
     * @return {@link R}
     */
    R withX(@Nonnull(when = When.NEVER) Integer theX);

    /**
     * @param theY
     * @return {@link R}
     */
    R withY(@Nonnull(when = When.NEVER) Integer theY);

    /**
     * @param theFeatureCount if null default value is 1.
     * @return {@link R}
     */
    R withFeatureCount(@Nullable Integer theFeatureCount);
}