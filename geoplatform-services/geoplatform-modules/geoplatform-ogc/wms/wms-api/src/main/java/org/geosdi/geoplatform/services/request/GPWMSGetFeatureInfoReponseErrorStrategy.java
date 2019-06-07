package org.geosdi.geoplatform.services.request;

import org.geosdi.geoplatform.services.response.WMSGetFeatureInfoResponse;

import javax.annotation.Nonnull;
import java.io.Serializable;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWMSGetFeatureInfoReponseErrorStrategy extends Serializable {

    /**
     * @param wmsGetFeatureInfoElement
     * @param ex
     * @throws Exception
     */
    void buildError(@Nonnull(when = NEVER) GPWMSGetFeatureInfoElement wmsGetFeatureInfoElement, @Nonnull(when = NEVER) Exception ex) throws Exception;

    /**
     * @param wmsGetFeatureInfoResponse
     * @return {@link WMSGetFeatureInfoResponse}
     * @throws Exception
     */
    WMSGetFeatureInfoResponse addError(@Nonnull(when = NEVER) WMSGetFeatureInfoResponse wmsGetFeatureInfoResponse) throws Exception;
}