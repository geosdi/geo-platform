package org.geosdi.geoplatform.experimental.el.query.mediator;

import com.google.common.collect.ImmutableMap;
import org.geosdi.geoplatform.experimental.el.index.GPBaseIndexCreator.GPIndexSettings;
import org.geosdi.geoplatform.experimental.el.query.mediator.colleague.GPElasticSearchQueryColleague;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPElasticSearchQueryMediator extends InitializingBean, DisposableBean {

    /**
     * @param queryColleague
     */
    void registerQueryColleague(GPElasticSearchQueryColleague queryColleague) throws Exception;

    /**
     * @param queryColleagueKey
     * @return {@link GPElasticSearchQueryColleague}
     * @throws Exception
     */
    GPElasticSearchQueryColleague getQueryColleague(GPIndexSettings queryColleagueKey) throws Exception;

    /**
     * @param queryColleagueKey
     * @param queryTemplate
     * @param <R>
     * @return {@link R}
     * @throws Exception
     */
    <R extends Object> R executeQueryColleague(GPIndexSettings queryColleagueKey, String queryTemplate)
            throws Exception;

    /**
     * @return {@link ImmutableMap<GPIndexSettings, GPElasticSearchQueryColleague>}
     */
    ImmutableMap<GPIndexSettings, GPElasticSearchQueryColleague> getAllQueryColleagues();
}
