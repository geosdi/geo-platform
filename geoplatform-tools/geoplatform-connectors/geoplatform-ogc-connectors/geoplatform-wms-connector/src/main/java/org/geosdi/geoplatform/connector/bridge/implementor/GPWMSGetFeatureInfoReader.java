package org.geosdi.geoplatform.connector.bridge.implementor;

import org.geosdi.geoplatform.connector.server.request.WMSFeatureInfoFormat;
import org.geosdi.geoplatform.support.bridge.implementor.GPImplementor;

import javax.annotation.Nonnull;
import java.io.InputStream;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWMSGetFeatureInfoReader<P extends Object> extends GPImplementor<WMSFeatureInfoFormat> {

    /**
     * @param inputStream
     * @return {@link Object}
     * @throws Exception
     */
    P read(@Nonnull(when = NEVER) InputStream inputStream) throws Exception;
}