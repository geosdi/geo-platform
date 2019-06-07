package org.geosdi.geoplatform.services.builder;

import org.geosdi.geoplatform.services.request.GPWMSGetFeatureInfoRequest;
import org.geosdi.geoplatform.services.response.WMSGetFeatureInfoResponse;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWMSGetFeatureInfoResponseBuilder {

    /**
     * @param theRequest
     * @return {@link GPWMSGetFeatureInfoResponseBuilder}
     */
    GPWMSGetFeatureInfoResponseBuilder withRequest(@Nonnull(when = NEVER) GPWMSGetFeatureInfoRequest theRequest);

    /**
     * @return {@link WMSGetFeatureInfoResponse}
     * @throws Exception
     */
    WMSGetFeatureInfoResponse build() throws Exception;
}