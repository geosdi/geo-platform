package org.geosdi.geoplatform.experimental.el.query.mediator;

import com.google.common.collect.ImmutableMap;
import org.geosdi.geoplatform.experimental.el.index.GPBaseIndexCreator;
import org.geosdi.geoplatform.experimental.el.index.GPBaseIndexCreator.GPIndexSettings;
import org.geosdi.geoplatform.experimental.el.query.mediator.colleague.GPElasticSearchQueryColleague;
import org.geosdi.geoplatform.experimental.el.query.mediator.colleague.decorator.IGPElasticSearchQueryColleagueDecorator;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPElasticSearchQueryMediator extends InitializingBean, DisposableBean {

    /**
     * @param queryColleague
     */
    void registerQueryColleague(IGPElasticSearchQueryColleagueDecorator queryColleague) throws Exception;

    /**
     * @param queryColleagueKey
     * @return {@link GPElasticSearchQueryColleague}
     * @throws Exception
     */
    IGPElasticSearchQueryColleagueDecorator getQueryColleague(GPIndexSettings queryColleagueKey) throws Exception;

    /**
     * @param queryColleagueKey
     * @param queryTemplate
     * @param queryTemplateParameters
     * @param <R>
     * @param <V>
     * @return {@link R}
     * @throws Exception
     */
    <R, V> R executeQueryColleague(GPBaseIndexCreator.GPIndexSettings queryColleagueKey, String queryTemplate,
            @Nullable Map<String, V> queryTemplateParameters)
            throws Exception;

    /**
     * @return {@link ImmutableMap<GPIndexSettings, GPElasticSearchQueryColleague>}
     */
    ImmutableMap<GPIndexSettings, IGPElasticSearchQueryColleagueDecorator> getAllQueryColleagues();
}
