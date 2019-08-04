package org.geosdi.geoplatform.connector.geoserver.request.featuretypes;

import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.IGPGeoserverFeatureTypeInfo;
import org.geosdi.geoplatform.connector.server.request.GPConnectorRequest;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GeoserverCreateFeatureTypeRequest extends GPConnectorRequest<String> {

    JacksonSupport JACKSON_SUPPORT = new GPJacksonSupport(UNWRAP_ROOT_VALUE_DISABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
            WRAP_ROOT_VALUE_DISABLE,
            INDENT_OUTPUT_ENABLE);

    /**
     * @param theWorkspace
     * @return {@link GeoserverCreateFeatureTypeRequest}
     */
    GeoserverCreateFeatureTypeRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace);

    /**
     * @param theStore
     * @return {@link GeoserverCreateFeatureTypeRequest}
     */
    GeoserverCreateFeatureTypeRequest withStore(@Nonnull(when = NEVER) String theStore);

    /**
     * @param theFeatureTypeBody
     * @return {@link GeoserverCreateFeatureTypeRequest}
     */
    <FeatureTypeBody extends IGPGeoserverFeatureTypeInfo> GeoserverCreateFeatureTypeRequest withFeatureTypeBody(@Nonnull(when = NEVER) FeatureTypeBody theFeatureTypeBody);
}