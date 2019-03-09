package org.geosdi.geoplatform.gui.shared.wfs;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface WFSOperatorType extends Serializable {

    /**
     * @return {@link String}
     */
    String getSymbol();
}