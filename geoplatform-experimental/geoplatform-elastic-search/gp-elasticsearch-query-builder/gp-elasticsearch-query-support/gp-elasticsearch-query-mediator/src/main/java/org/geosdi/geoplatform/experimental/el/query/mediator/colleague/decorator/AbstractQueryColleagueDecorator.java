package org.geosdi.geoplatform.experimental.el.query.mediator.colleague.decorator;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.geosdi.geoplatform.experimental.el.index.GPBaseIndexCreator;
import org.geosdi.geoplatform.experimental.el.query.mediator.colleague.GPElasticSearchQueryColleague;
import org.geosdi.geoplatform.experimental.el.query.template.IGPElasticSearchQueryTemplate;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
abstract class AbstractQueryColleagueDecorator implements IGPElasticSearchQueryColleagueDecorator {

    protected final GPElasticSearchQueryColleague queryColleague;
    protected final IGPElasticSearchQueryTemplate queryTemplate;

    protected AbstractQueryColleagueDecorator(GPElasticSearchQueryColleague theColleague) {
        this.queryColleague = theColleague;
        this.queryTemplate = ((this.queryColleague.getQueryTemplate() != null) ? this.queryColleague.getQueryTemplate()
                : this.getQueryTemplate());
    }

    /**
     * @return {@link QUERY_COLLEAGUE}
     */
    @Override
    public <QUERY_COLLEAGUE extends GPElasticSearchQueryColleague> QUERY_COLLEAGUE getQueryColleague() {
        return (QUERY_COLLEAGUE) this.queryColleague;
    }

    /**
     * @return {@link QUERY_COLLEAGUE_KEY}
     */
    @Override
    public <QUERY_COLLEAGUE_KEY extends GPBaseIndexCreator.GPIndexSettings> QUERY_COLLEAGUE_KEY getQueryColleagueKey() {
        return this.queryColleague.getQueryColleagueKey();
    }

    /**
     * @param queryTemplate
     * @param queryTemplateParameters
     * @param <V>
     * @return {@link String}
     */
    protected final <V> String decoreQueryTemplate(String queryTemplate,
            @Nullable Map<String, V> queryTemplateParameters) {
        return ((queryTemplateParameters != null) && !(queryTemplateParameters.isEmpty()) ?
                new StrSubstitutor(queryTemplateParameters, this.queryTemplate.getPrefix(),
                        this.queryTemplate.getSuffix(),
                        this.queryTemplate.getEscape().charValue()).replace(queryTemplate) : queryTemplate);
    }
}
