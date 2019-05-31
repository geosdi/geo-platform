package org.geosdi.geoplatform.connector.bridge.implementor.gml;

import org.geosdi.geoplatform.connector.bridge.implementor.GPWMSGetFeatureInfoReader;
import org.geosdi.geoplatform.connector.reader.stax.GPWMSGetFeatureInfoStaxReader;
import org.geosdi.geoplatform.connector.reader.stax.WMSGetFeatureInfoStaxReader;
import org.geosdi.geoplatform.connector.server.request.WMSFeatureInfoFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.google.common.base.Charsets.UTF_8;
import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.TRUE;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.server.request.WMSFeatureInfoFormat.GML;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWMSGetFeatureInfoGmlReader implements GPWMSGetFeatureInfoReader<Object> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    protected static final WMSGetFeatureInfoStaxReader wmsGetFeatureInfoStaxReader = new GPWMSGetFeatureInfoStaxReader();

    /**
     * @param inputStream
     * @return {@link Object}
     * @throws Exception
     */
    @Override
    public Object read(@Nonnull(when = NEVER) InputStream inputStream) throws Exception {
        checkArgument(inputStream != null, "The Parameter inputStream must not be null.");
        logger.debug("##########################Executing {}#read.", this);
        return wmsGetFeatureInfoStaxReader.read(new InputStreamReader(inputStream, UTF_8));
    }

    /**
     * @return {@link WMSFeatureInfoFormat}
     */
    @Override
    public WMSFeatureInfoFormat getKey() {
        return GML;
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
        return this.getClass().getSimpleName();
    }
}