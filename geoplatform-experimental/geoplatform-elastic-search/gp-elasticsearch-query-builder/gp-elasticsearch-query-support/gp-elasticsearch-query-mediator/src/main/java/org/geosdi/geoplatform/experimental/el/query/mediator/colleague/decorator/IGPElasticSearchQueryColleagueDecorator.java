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
     * @param <QUERY_REPLACER>
     * @return {@link QUERY_REPLACER}
     */
    <QUERY_REPLACER extends IGPElasticSearchQueryTemplateReplacer> IGPElasticSearchQueryTemplateReplacer getQueryReplacer();

    /**
     * @param <V>
     * @return {@link Map<String, V>}
     */
    <V> Map<String, V> getQueryTemplateParameters();

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
