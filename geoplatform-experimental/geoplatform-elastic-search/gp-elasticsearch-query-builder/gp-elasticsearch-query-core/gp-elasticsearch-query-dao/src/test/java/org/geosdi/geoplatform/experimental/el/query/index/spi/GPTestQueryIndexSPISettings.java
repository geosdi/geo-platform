package org.geosdi.geoplatform.experimental.el.query.index.spi;

import org.geosdi.geoplatform.experimental.el.index.GPBaseIndexCreator;
import org.geosdi.geoplatform.experimental.el.index.settings.GPBaseIndexSettings;
import org.geosdi.geoplatform.experimental.el.query.index.settings.spi.IGPQueryIndexSPISettings;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPTestQueryIndexSPISettings implements IGPQueryIndexSPISettings {

    /**
     * @return {@link IS}
     */
    @Override
    public <IS extends GPBaseIndexCreator.GPIndexSettings> IS createQueryIndexSettings() {
        return (IS) new GPBaseIndexSettings("GP_ELASTICSEARCH_QUERY_INDEX_NAME_TEST",
                "GP_ELASTICSEARCH_QUERY_INDEX_TYPE_TEST");
    }
}
