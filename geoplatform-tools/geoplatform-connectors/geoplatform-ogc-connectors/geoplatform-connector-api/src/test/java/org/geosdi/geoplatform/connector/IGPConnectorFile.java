package org.geosdi.geoplatform.connector;

import java.io.File;
import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPConnectorFile extends Serializable {

    /**
     * @return {@link File}
     */
    File getFile();

    /**
     * @return {@link String}
     */
    String getKey();
}