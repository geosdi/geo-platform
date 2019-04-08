package org.geosdi.geoplatform.connector.reader.featuretype;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Map;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPFeatureTypeReader extends Serializable {

    /**
     * @param value
     * @return {@link Map <String, IGPFeatureType>}
     * @throws Exception
     */
    Map<String, IGPFeatureType> read(@Nonnull(when = NEVER) String value) throws Exception;
}