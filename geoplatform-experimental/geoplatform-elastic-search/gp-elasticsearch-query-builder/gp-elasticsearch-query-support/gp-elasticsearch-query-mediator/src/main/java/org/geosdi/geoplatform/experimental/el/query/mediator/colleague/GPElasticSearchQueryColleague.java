package org.geosdi.geoplatform.experimental.el.query.mediator.colleague;

import org.geosdi.geoplatform.experimental.el.index.GPBaseIndexCreator;
import org.geosdi.geoplatform.experimental.el.query.template.IGPElasticSearchQueryTemplate;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPElasticSearchQueryColleague {

    /**
     * @param queryTemplate
     * @return {@link R}
     * @throws Exception
     */
    <R extends Object> R executeQueryColleague(String queryTemplate) throws Exception;

    /**
     * @param <QUERY_COLLEAGUE_KEY>
     * @return {@link QUERY_COLLEAGUE_KEY}
     */
    <QUERY_COLLEAGUE_KEY extends GPBaseIndexCreator.GPIndexSettings> QUERY_COLLEAGUE_KEY getQueryColleagueKey();

    /**
     * @param <QUERY_TEMPLATE>
     * @return {@link QUERY_TEMPLATE}
     */
    <QUERY_TEMPLATE extends IGPElasticSearchQueryTemplate> QUERY_TEMPLATE getQueryTemplate();
}
