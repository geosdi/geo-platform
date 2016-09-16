package org.geosdi.geoplatform.experimental.el.query.index.settings.spi;

import org.geosdi.geoplatform.experimental.el.index.GPBaseIndexCreator;

import static org.geosdi.geoplatform.experimental.el.query.index.settings.GPElasticSearchQueryIndexSettings.GP_ELASTIC_SEARCH_QUERY_INDEX_SETTINGS;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPDefaultQueryIndexSPISettings implements IGPQueryIndexSPISettings {

    /**
     * @return {@link IS}
     */
    @Override
    public <IS extends GPBaseIndexCreator.GPIndexSettings> IS createQueryIndexSettings() {
        return (IS) GP_ELASTIC_SEARCH_QUERY_INDEX_SETTINGS.getValue();
    }
}
