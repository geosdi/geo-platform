package org.geosdi.geoplatform.experimental.el.query.mediator.colleague.decorator;

import com.google.common.base.Preconditions;
import org.geosdi.geoplatform.experimental.el.query.mediator.colleague.GPElasticSearchQueryColleague;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPElasticSearchQueryColleagueDecorator extends AbstractQueryColleagueDecorator {

    private static final Logger logger = LoggerFactory.getLogger(GPElasticSearchQueryColleagueDecorator.class);

    protected GPElasticSearchQueryColleagueDecorator(GPElasticSearchQueryColleague theColleague) {
        super(theColleague);
    }

    /**
     * @param queryColleague
     * @return {@link GPElasticSearchQueryColleague}
     */
    public static GPElasticSearchQueryColleagueDecorator of(GPElasticSearchQueryColleague queryColleague) {
        Preconditions.checkArgument((queryColleague != null), "The Parameter Query Colleague must not be null.");
        return new GPElasticSearchQueryColleagueDecorator(queryColleague);
    }

    /**
     * @param queryTemplate
     * @param queryTemplateParameters
     * @return {@link R}
     * @throws Exception
     */
    @Override
    public <R, V> R executeQueryColleague(String queryTemplate, @Nullable Map<String, V> queryTemplateParameters)
            throws Exception {
        logger.trace("################################{} executing QueryTemplate : \n\n{}\n\n with Template Parameters" +
                " : {}\n", super.getQueryColleague(), queryTemplate, queryTemplateParameters);
        return this.queryColleague.executeQueryColleague(super.decoreQueryTemplate(queryTemplate,
                queryTemplateParameters));
    }
}
