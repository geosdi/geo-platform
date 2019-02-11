package org.geosdi.geoplatform.connector.server.v111;

import org.geosdi.geoplatform.connector.server.request.GPWMSGetFeatureInfoRequest;
import org.geosdi.geoplatform.connector.server.request.GPWMSGetMapBaseRequest;
import org.geosdi.geoplatform.connector.server.request.WMSFeatureInfoFormat;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWMSGetFeatureInfoV111Request<T> extends GPWMSGetFeatureInfoRequest<T, GPWMSGetFeatureInfoV111Request<T>> {

    /**
     * @param theWMSGetMapRequest
     * @return {@link GPWMSGetFeatureInfoRequest<T>}
     */
    @Override
    <WMSGetMapRequest extends GPWMSGetMapBaseRequest> GPWMSGetFeatureInfoV111Request<T> withWMSGetMapRequest(@Nonnull(when = NEVER) WMSGetMapRequest theWMSGetMapRequest);

    /**
     * @param theQueryLayers
     * @return {@link GPWMSGetFeatureInfoRequest<T>}
     */
    @Override
    GPWMSGetFeatureInfoV111Request<T> withQueryLayers(@Nonnull(when = NEVER) String... theQueryLayers);

    /**
     * @param theInfoFormat
     * @return {@link GPWMSGetFeatureInfoRequest<T>}
     */
    @Override
    GPWMSGetFeatureInfoV111Request<T> withInfoFormat(@Nonnull(when = NEVER) WMSFeatureInfoFormat theInfoFormat);

    /**
     * @param theX
     * @return {@link GPWMSGetFeatureInfoRequest<T>}
     */
    @Override
    GPWMSGetFeatureInfoV111Request<T> withX(@Nonnull(when = NEVER) Integer theX);

    /**
     * @param theY
     * @return {@link GPWMSGetFeatureInfoRequest<T>}
     */
    @Override
    GPWMSGetFeatureInfoV111Request<T> withY(@Nonnull(when = NEVER) Integer theY);

    /**
     * @param theFeatureCount if null default value is 1.
     * @return {@link GPWMSGetFeatureInfoRequest<T>}
     */
    @Override
    GPWMSGetFeatureInfoV111Request<T> withFeatureCount(@Nullable Integer theFeatureCount);
}