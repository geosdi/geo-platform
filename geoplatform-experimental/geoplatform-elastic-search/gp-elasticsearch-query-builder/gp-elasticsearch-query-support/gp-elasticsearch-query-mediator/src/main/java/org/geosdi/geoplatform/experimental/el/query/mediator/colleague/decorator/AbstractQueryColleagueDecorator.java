package org.geosdi.geoplatform.experimental.el.query.mediator.colleague.decorator;

import org.geosdi.geoplatform.experimental.el.index.GPBaseIndexCreator;
import org.geosdi.geoplatform.experimental.el.query.mediator.colleague.GPElasticSearchQueryColleague;
import org.geosdi.geoplatform.experimental.el.query.template.IGPElasticSearchQueryTemplate;
import org.geosdi.geoplatform.experimental.el.query.template.IGPElasticSearchQueryTemplateReplacer;
import org.geosdi.geoplatform.experimental.el.query.template.IGPElasticSearchQueryTemplateReplacer.GPElasticSearchQueryTemplateReplacer;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
abstract class AbstractQueryColleagueDecorator implements IGPElasticSearchQueryColleagueDecorator {

    protected final GPElasticSearchQueryColleague queryColleague;
    protected final IGPElasticSearchQueryTemplate queryTemplate;
    private ThreadLocal<Map<String, Object>> queryTemplateParameters = ThreadLocal.withInitial(() -> new HashMap<String, Object>());
    private final ThreadLocal<IGPElasticSearchQueryTemplateReplacer> queryReplacer;

    protected AbstractQueryColleagueDecorator(GPElasticSearchQueryColleague theColleague) {
        this.queryColleague = theColleague;
        this.queryTemplate = ((this.queryColleague.getQueryTemplate() != null) ? this.queryColleague.getQueryTemplate()
                : this.getQueryTemplate());
        this.queryReplacer = ThreadLocal.withInitial(() -> new GPElasticSearchQueryTemplateReplacer(this.queryTemplate));
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
     * @return {@link QUERY_SUBSTITUTOR}
     */
    public <QUERY_SUBSTITUTOR extends IGPElasticSearchQueryTemplateReplacer> IGPElasticSearchQueryTemplateReplacer getQueryReplacer() {
        return this.queryReplacer.get();
    }

    /**
     * @return {@link Map<String, V>}
     */
    @Override
    public <V> Map<String, V> getQueryTemplateParameters() {
        return (Map<String, V>) this.queryTemplateParameters.get();
    }

    /**
     * @param queryTemplate
     * @param queryTemplateParameters
     * @param <V>
     * @return {@link String}
     */
    protected final <V> String decoreQueryTemplate(String queryTemplate, @Nullable Map<String, V> queryTemplateParameters) {
        if (((queryTemplateParameters != null) && !(queryTemplateParameters.isEmpty())))
            this.queryTemplateParameters.set((Map<String, Object>) queryTemplateParameters);
        return ((queryTemplateParameters != null) && !(queryTemplateParameters.isEmpty()) ?
                this.queryReplacer.get().replace(queryTemplate, this.queryTemplateParameters.get()) : queryTemplate);
    }
}
