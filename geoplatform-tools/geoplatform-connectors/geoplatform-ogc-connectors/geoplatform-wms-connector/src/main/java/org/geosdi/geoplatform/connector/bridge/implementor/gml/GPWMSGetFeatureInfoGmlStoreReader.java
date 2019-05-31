package org.geosdi.geoplatform.connector.bridge.implementor.gml;

import org.geosdi.geoplatform.connector.server.request.WMSFeatureInfoFormat;

import javax.annotation.Nonnull;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.google.common.base.Charsets.UTF_8;
import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.server.request.WMSFeatureInfoFormat.GML_AS_STORE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWMSGetFeatureInfoGmlStoreReader extends GPWMSGetFeatureInfoGmlReader {

    /**
     * @param inputStream
     * @return {@link Object}
     * @throws Exception
     */
    @Override
    public Object read(@Nonnull(when = NEVER) InputStream inputStream) throws Exception {
        checkArgument(inputStream != null, "The Parameter inputStream must not be null.");
        logger.debug("##########################Executing {}#read.", this);
        return wmsGetFeatureInfoStaxReader.readAsStore(new InputStreamReader(inputStream, UTF_8));
    }

    /**
     * @return {@link WMSFeatureInfoFormat}
     */
    @Override
    public WMSFeatureInfoFormat getKey() {
        return GML_AS_STORE;
    }
}