package org.geosdi.geoplatform.connector.reader.featuretype;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;
import java.io.Serializable;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWMSFeatureTypeReader extends Serializable {

    /**
     * @param value
     * @return {@link Map<String, GPWMSFeatureType>}
     * @throws Exception
     */
    Map<String, GPWMSFeatureType> read(@Nonnull(when = When.NEVER) String value) throws Exception;
}
