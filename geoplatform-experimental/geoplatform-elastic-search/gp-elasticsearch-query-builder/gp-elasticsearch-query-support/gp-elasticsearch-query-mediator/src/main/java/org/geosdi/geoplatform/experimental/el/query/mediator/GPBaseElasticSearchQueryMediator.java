package org.geosdi.geoplatform.experimental.el.query.mediator;

import com.google.common.base.Preconditions;
import org.geosdi.geoplatform.experimental.el.index.GPBaseIndexCreator;
import org.geosdi.geoplatform.experimental.el.query.mediator.colleague.GPElasticSearchQueryColleague;
import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.slf4j.Logger;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPBaseElasticSearchQueryMediator extends AbstractElasticSearchQueryMediator {

    @GeoPlatformLog
    private static Logger logger;

    /**
     * @param queryColleague
     */
    @Override
    public void registerQueryColleague(GPElasticSearchQueryColleague queryColleague)
            throws Exception {
        Preconditions.checkArgument((queryColleague != null), "The Parameter Query Colleague must " +
                "not be null.");
        logger.trace("################################{} is registering QueryColleague : {}\n",
                this, queryColleague);
        if (!super.isQueryColleagueRegistered(queryColleague.getQueryColleagueKey())) {
            this.queryColleagueRegistry.put(queryColleague.getQueryColleagueKey(), queryColleague);
        }
    }

    /**
     * @param queryColleagueKey
     * @param queryTemplate
     * @return {@link R}
     * @throws Exception
     */
    @Override
    public <R> R executeQueryColleague(GPBaseIndexCreator.GPIndexSettings queryColleagueKey,
            String queryTemplate) throws Exception {
        Preconditions.checkArgument((queryColleagueKey != null), "The Parameter QueryColleagueKey must " +
                "not be null.");
        Preconditions.checkArgument((queryTemplate != null) && !(queryTemplate.isEmpty()),
                "The Parameter QueryTemplate must not be Null or an Empty String.");
        logger.trace("#################################{} executing  ---------------->\n\n{}\n",
                this, queryTemplate);
        return (super.isQueryColleagueRegistered(queryColleagueKey) ? super.getQueryColleague(queryColleagueKey)
                .executeQueryColleague(queryTemplate) : null);
    }

    /**
     * Invoked by a BeanFactory on destruction of a singleton.
     *
     * @throws Exception in case of shutdown errors.
     *                   Exceptions will get logged but not rethrown to allow
     *                   other beans to release their resources too.
     */
    @Override
    public void destroy() throws Exception {
        logger.debug("#############################Called Destroy on : {}\n", this);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
