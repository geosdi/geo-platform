package org.geosdi.geoplatform.experimental.el.query.mediator.colleague.decorator;

import org.geosdi.geoplatform.experimental.el.index.GPBaseIndexCreator;
import org.geosdi.geoplatform.experimental.el.query.mediator.colleague.GPElasticSearchQueryColleague;
import org.geosdi.geoplatform.experimental.el.query.template.IGPElasticSearchQueryTemplate;

import javax.annotation.Nullable;
import java.util.Map;

import static org.geosdi.geoplatform.experimental.el.query.template.IGPElasticSearchQueryTemplate.DEFAULT_QUERY_TEMPLATE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPElasticSearchQueryColleagueDecorator {

    /**
     * @param <QUERY_COLLEAGUE>
     * @return {@link QUERY_COLLEAGUE}
     */
    <QUERY_COLLEAGUE extends GPElasticSearchQueryColleague> QUERY_COLLEAGUE getQueryColleague();

    /**
     * @param queryTemplate
     * @param queryTemplateParameters
     * @param <R>
     * @param <V>
     * @return {@link R}
     * @throws Exception
     */
    <R, V> R executeQueryColleague(String queryTemplate, @Nullable Map<String, V> queryTemplateParameters)
            throws Exception;

    /**
     * @param <QUERY_COLLEAGUE_KEY>
     * @return {@link QUERY_COLLEAGUE_KEY}
     */
    <QUERY_COLLEAGUE_KEY extends GPBaseIndexCreator.GPIndexSettings> QUERY_COLLEAGUE_KEY getQueryColleagueKey();

    /**
     * @param <QUERY_TEMPLATE>
     * @return {@link QUERY_TEMPLATE}
     */
    default <QUERY_TEMPLATE extends IGPElasticSearchQueryTemplate> QUERY_TEMPLATE getQueryTemplate() {
        return (QUERY_TEMPLATE) DEFAULT_QUERY_TEMPLATE;
    }
}
