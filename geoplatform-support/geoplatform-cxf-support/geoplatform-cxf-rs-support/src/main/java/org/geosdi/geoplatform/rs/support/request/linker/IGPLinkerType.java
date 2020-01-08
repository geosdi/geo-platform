package org.geosdi.geoplatform.rs.support.request.linker;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPLinkerType extends Serializable {

    /**
     * @return {@link String}
     */
    String getType();
}