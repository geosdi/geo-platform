package org.geosdi.geoplatform.experimental.el.query.index.settings.spi;

import org.geosdi.geoplatform.experimental.el.index.GPBaseIndexCreator;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPQueryIndexSPISettings {

    /**
     * @param <IS>
     * @return {@link IS}
     */
    <IS extends GPBaseIndexCreator.GPIndexSettings> IS createQueryIndexSettings();
}
