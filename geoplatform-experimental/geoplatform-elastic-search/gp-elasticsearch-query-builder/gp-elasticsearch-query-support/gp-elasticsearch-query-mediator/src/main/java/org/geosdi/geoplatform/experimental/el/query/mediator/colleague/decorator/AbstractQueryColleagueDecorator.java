package org.geosdi.geoplatform.experimental.el.query.mediator.colleague.decorator;

import org.apache.commons.lang3.text.StrLookup;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.geosdi.geoplatform.experimental.el.index.GPBaseIndexCreator;
import org.geosdi.geoplatform.experimental.el.query.mediator.colleague.GPElasticSearchQueryColleague;
import org.geosdi.geoplatform.experimental.el.query.template.IGPElasticSearchQueryTemplate;

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
    private final ThreadLocal<StrSubstitutor> querySubstitutor;

    protected AbstractQueryColleagueDecorator(GPElasticSearchQueryColleague theColleague) {
        this.queryColleague = theColleague;
        this.queryTemplate = ((this.queryColleague.getQueryTemplate() != null) ? this.queryColleague.getQueryTemplate()
                : this.getQueryTemplate());
        this.querySubstitutor = ThreadLocal.withInitial(() -> new StrSubstitutor(this.queryTemplateParameters.get(),
                this.queryTemplate.getPrefix(), this.queryTemplate.getSuffix(), this.queryTemplate.getEscape().charValue()));
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
    @Override
    public <QUERY_SUBSTITUTOR extends StrSubstitutor> StrSubstitutor getQuerySubstitutor() {
        return this.querySubstitutor.get();
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
    protected final <V> String decoreQueryTemplate(String queryTemplate,
            @Nullable Map<String, V> queryTemplateParameters) {
        replaceQuerySubstituorVariableResolver(queryTemplate, queryTemplateParameters);
        return ((queryTemplateParameters != null) && !(queryTemplateParameters.isEmpty()) ?
                this.querySubstitutor.get().replace(queryTemplate) : queryTemplate);
    }

    /**
     * @param queryTemplate
     * @param queryTemplateParameters
     * @param <V>
     */
    protected <V> void replaceQuerySubstituorVariableResolver(String queryTemplate,
            Map<String, V> queryTemplateParameters) {
        if (((queryTemplateParameters != null) && !(queryTemplateParameters.isEmpty()))) {
            this.queryTemplateParameters.set((Map<String, Object>) queryTemplateParameters);
            this.querySubstitutor.get().setVariableResolver(StrLookup.mapLookup(this.queryTemplateParameters.get()));
        }
    }
}
