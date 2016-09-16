package org.geosdi.geoplatform.experimental.el.query.index.settings;

import org.geosdi.geoplatform.experimental.el.index.GPBaseIndexCreator.GPIndexSettings;
import org.geosdi.geoplatform.experimental.el.index.settings.GPBaseIndexSettings;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum GPElasticSearchQueryIndexSettings {

    GP_ELASTIC_SEARCH_QUERY_INDEX_SETTINGS(new GPBaseIndexSettings("gp_elasticsearch_query_index",
            "gp_elasticsearch_query_type"));

    private final GPIndexSettings value;

    GPElasticSearchQueryIndexSettings(GPIndexSettings theValue) {
        this.value = theValue;
    }

    /**
     * @return {@link GPIndexSettings}
     */
    public GPIndexSettings getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
