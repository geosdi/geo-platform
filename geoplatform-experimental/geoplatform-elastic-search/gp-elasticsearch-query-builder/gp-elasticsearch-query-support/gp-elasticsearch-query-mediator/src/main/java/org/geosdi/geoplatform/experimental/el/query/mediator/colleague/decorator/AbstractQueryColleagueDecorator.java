/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
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