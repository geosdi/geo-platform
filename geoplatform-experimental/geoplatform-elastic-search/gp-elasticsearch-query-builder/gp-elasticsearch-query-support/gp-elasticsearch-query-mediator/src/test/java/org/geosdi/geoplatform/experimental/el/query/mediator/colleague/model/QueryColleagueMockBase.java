package org.geosdi.geoplatform.experimental.el.query.mediator.colleague.model;

import org.geosdi.geoplatform.experimental.el.index.GPBaseIndexCreator;
import org.geosdi.geoplatform.experimental.el.index.settings.GPBaseIndexSettings;
import org.geosdi.geoplatform.experimental.el.query.mediator.colleague.GPElasticSearchQueryColleague;
import org.geosdi.geoplatform.experimental.el.query.template.IGPElasticSearchQueryTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Component(value = "queryColleagueBase")
public class QueryColleagueMockBase implements GPElasticSearchQueryColleague {

    private final GPBaseIndexCreator.GPIndexSettings collegueKey = new GPBaseIndexSettings("QueryColleagueBaseIndex",
            "QueryColleagueBaseType");

    /**
     * @param queryTemplate
     * @return {@link R}
     * @throws Exception
     */
    @Override
    public <R> R executeQueryColleague(String queryTemplate) throws Exception {
        return (R) "####################This is QueryColleagueMockBaseExecution : ".concat(queryTemplate);
    }

    /**
     * @return {@link QUERY_COLLEAGUE_KEY}
     */
    @Override
    public <QUERY_COLLEAGUE_KEY extends GPBaseIndexCreator.GPIndexSettings> QUERY_COLLEAGUE_KEY getQueryColleagueKey() {
        return (QUERY_COLLEAGUE_KEY) this.collegueKey;
    }

    /**
     * @return {@link QUERY_TEMPLATE}
     */
    @Override
    public <QUERY_TEMPLATE extends IGPElasticSearchQueryTemplate> QUERY_TEMPLATE getQueryTemplate() {
        return null;
    }
}
