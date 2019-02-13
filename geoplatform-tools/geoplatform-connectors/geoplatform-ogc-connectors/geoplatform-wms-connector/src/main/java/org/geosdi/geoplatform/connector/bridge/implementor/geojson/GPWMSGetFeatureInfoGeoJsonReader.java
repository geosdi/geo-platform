package org.geosdi.geoplatform.connector.bridge.implementor.geojson;

import org.geojson.FeatureCollection;
import org.geosdi.geoplatform.connector.bridge.implementor.GPWMSGetFeatureInfoReader;
import org.geosdi.geoplatform.connector.server.request.WMSFeatureInfoFormat;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.InputStream;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.TRUE;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.server.request.WMSFeatureInfoFormat.JSON;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature.NON_NULL;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWMSGetFeatureInfoGeoJsonReader implements GPWMSGetFeatureInfoReader<FeatureCollection> {

    private static final Logger logger = LoggerFactory.getLogger(GPWMSGetFeatureInfoGeoJsonReader.class);
    //
    private static final JacksonSupport jacksonSupport = new GPJacksonSupport(UNWRAP_ROOT_VALUE_DISABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE, ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE, WRAP_ROOT_VALUE_DISABLE,
            INDENT_OUTPUT_ENABLE, NON_NULL);

    /**
     * @param inputStream
     * @return {@link Object}
     * @throws Exception
     */
    @Override
    public FeatureCollection read(@Nonnull(when = NEVER) InputStream inputStream) throws Exception {
        checkArgument(inputStream != null, "The Parameter inputStream must not be null.");
        logger.debug("##############################Executing {}#read.", this);
        return jacksonSupport.getDefaultMapper().readValue(inputStream, FeatureCollection.class);
    }

    /**
     * @return {@link WMSFeatureInfoFormat}
     */
    @Override
    public WMSFeatureInfoFormat getKey() {
        return JSON;
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean isValid() {
        return TRUE;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " { key = " + getKey() +
                "}";
    }
}