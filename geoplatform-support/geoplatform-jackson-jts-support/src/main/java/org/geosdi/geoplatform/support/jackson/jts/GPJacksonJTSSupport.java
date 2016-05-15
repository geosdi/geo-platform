package org.geosdi.geoplatform.support.jackson.jts;

import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.jts.module.GPJTSModule;

import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.INDENT_OUTPUT_ENABLE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPJacksonJTSSupport extends GPJacksonSupport {

    public GPJacksonJTSSupport() {
        super(UNWRAP_ROOT_VALUE_DISABLE,
                FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
                ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
                WRAP_ROOT_VALUE_DISABLE,
                INDENT_OUTPUT_ENABLE);
        super.registerModule(new GPJTSModule());
    }

    /**
     * @return {@link String}
     */
    @Override
    public final String getProviderName() {
        return "GP_JACKSON_JTS_SUPPORT";
    }

    @Override
    public String toString() {
        return getProviderName();
    }
}
