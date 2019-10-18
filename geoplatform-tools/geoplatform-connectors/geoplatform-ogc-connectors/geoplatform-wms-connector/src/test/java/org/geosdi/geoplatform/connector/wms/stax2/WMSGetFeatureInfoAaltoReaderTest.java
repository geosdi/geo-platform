package org.geosdi.geoplatform.connector.wms.stax2;

import org.geosdi.geoplatform.connector.reader.stax2.GPWMSGetFeatureInfoStax2Reader;
import org.geosdi.geoplatform.connector.wms.WMSGetFeatureInfoReaderFileLoaderTest;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.geosdi.geoplatform.connector.reader.stax2.GPWMSGetFeatureInfoStax2Reader.aaltoConfigureForSpeed;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature.NON_NULL;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WMSGetFeatureInfoAaltoReaderTest extends WMSGetFeatureInfoReaderFileLoaderTest {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    protected static final JacksonSupport JACKSON_SUPPORT = new GPJacksonSupport(UNWRAP_ROOT_VALUE_DISABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
            WRAP_ROOT_VALUE_DISABLE,
            INDENT_OUTPUT_ENABLE, NON_NULL);
    protected static final GPWMSGetFeatureInfoStax2Reader wmsGetFeatureInfoAaltoReader = aaltoConfigureForSpeed();
}