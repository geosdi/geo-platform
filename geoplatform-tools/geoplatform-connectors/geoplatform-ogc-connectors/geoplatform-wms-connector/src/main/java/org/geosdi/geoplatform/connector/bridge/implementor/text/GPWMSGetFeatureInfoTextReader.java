package org.geosdi.geoplatform.connector.bridge.implementor.text;

import com.google.common.io.CharStreams;
import org.geosdi.geoplatform.connector.bridge.implementor.GPWMSGetFeatureInfoReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.google.common.base.Charsets.UTF_8;
import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
abstract class GPWMSGetFeatureInfoTextReader implements GPWMSGetFeatureInfoReader<String> {

    protected static final Logger logger = LoggerFactory.getLogger(GPWMSGetFeatureInfoTextReader.class);

    /**
     * @param inputStream
     * @return {@link Object}
     * @throws Exception
     */
    @Override
    public String read(@Nonnull(when = NEVER) InputStream inputStream) throws Exception {
        checkArgument(inputStream != null, "The Parameter inputStream must not be null.");
        logger.debug("##########################Executing {}#read.", this);
        return CharStreams.toString(new InputStreamReader(inputStream, UTF_8));
    }
}