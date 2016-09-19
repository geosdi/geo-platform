package org.geosdi.geoplatform.experimental.el.query.mediator;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import org.geosdi.geoplatform.experimental.el.index.GPBaseIndexCreator;
import org.geosdi.geoplatform.experimental.el.query.mediator.colleague.GPElasticSearchQueryColleague;
import org.geosdi.geoplatform.experimental.el.query.mediator.colleague.decorator.IGPElasticSearchQueryColleagueDecorator;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class AbstractElasticSearchQueryMediator implements GPElasticSearchQueryMediator {

    @Resource(name = "elasticSearchQueryColleagueRegistry")
    protected Map<GPBaseIndexCreator.GPIndexSettings, IGPElasticSearchQueryColleagueDecorator> queryColleagueRegistry;

    /**
     * @param queryColleagueKey
     * @return {@link GPElasticSearchQueryColleague}
     * @throws Exception
     */
    @Override
    public IGPElasticSearchQueryColleagueDecorator getQueryColleague(GPBaseIndexCreator.GPIndexSettings queryColleagueKey)
            throws Exception {
        Preconditions.checkArgument((queryColleagueKey != null), "The Parameter Query Colleague Key must " +
                "not be null.");
        return (isQueryColleagueRegistered(queryColleagueKey) ? this.queryColleagueRegistry.get(queryColleagueKey)
                : null);
    }

    /**
     * @param queryColleagueKey
     * @return {@link Boolean}
     */
    protected final Boolean isQueryColleagueRegistered(GPBaseIndexCreator.GPIndexSettings queryColleagueKey) {
        return this.queryColleagueRegistry.containsKey(queryColleagueKey);
    }

    /**
     * @return {@link ImmutableMap<org.geosdi.geoplatform.experimental.el.index.GPBaseIndexCreator.GPIndexSettings, GPElasticSearchQueryColleague>}
     */
    @Override
    public ImmutableMap<GPBaseIndexCreator.GPIndexSettings, IGPElasticSearchQueryColleagueDecorator> getAllQueryColleagues() {
        return new ImmutableMap.Builder<GPBaseIndexCreator.GPIndexSettings, IGPElasticSearchQueryColleagueDecorator>()
                .putAll(this.queryColleagueRegistry).build();
    }

    /**
     * Invoked by a BeanFactory after it has set all bean properties supplied
     * (and satisfied BeanFactoryAware and ApplicationContextAware).
     * <p>This method allows the bean instance to perform initialization only
     * possible when all bean properties have been set and to throw an
     * exception in the event of misconfiguration.
     *
     * @throws Exception in the event of misconfiguration (such
     *                   as failure to set an essential property) or if initialization fails.
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        Preconditions.checkArgument((this.queryColleagueRegistry != null)
                && !(this.queryColleagueRegistry.isEmpty()), "The Query Colleague Registry must not be " +
                "null or Emty Map.");
    }
}
