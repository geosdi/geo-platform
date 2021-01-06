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
package org.geosdi.geoplatform.experimental.el.query.page;

import com.google.common.base.Preconditions;
import net.jcip.annotations.Immutable;
import org.apache.lucene.queryparser.flexible.standard.QueryParserUtil;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.geosdi.geoplatform.experimental.el.dao.GPPageableElasticSearchDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPElasticSearchQueryPage extends GPPageableElasticSearchDAO.PageBuilder {

    /**
     * @param <QueryTemplate>
     * @return {@link QueryTemplate}
     */
    <QueryTemplate extends String> QueryTemplate getQueryTemplate();

    /**
     *
     */
    @Immutable
    class GPElasticSearchQueryPage extends GPPageableElasticSearchDAO.Page implements IGPElasticSearchQueryPage {

        private static final Logger logger = LoggerFactory.getLogger(GPElasticSearchQueryPage.class);
        //
        private final String queryTemplate;

        /**
         * @param from
         * @param size
         * @param theQueryTemplate
         */
        public GPElasticSearchQueryPage(int from, int size, String theQueryTemplate) {
            super(from, size);
            Preconditions.checkArgument((theQueryTemplate != null) && !(theQueryTemplate.isEmpty()),
                    "The Parameter QueryTemplate must not be null or an Empty String.");
            this.queryTemplate = theQueryTemplate;
        }

        /**
         * @return {@link QueryTemplate}
         */
        @Override
        public <QueryTemplate extends String> QueryTemplate getQueryTemplate() {
            return (QueryTemplate) this.queryTemplate;
        }

        private <Builder extends SearchRequestBuilder> Builder internalBuildPage(Builder builder)
                throws Exception {
            logger.trace("####################Called {} #internalBuildPage with parameters " +
                    "queryTemplate : \n\n{}\n\n", getClass().getSimpleName(), this.queryTemplate);
            return (Builder) builder.setQuery(QueryBuilders.queryStringQuery(QueryParserUtil.escape(this.queryTemplate)));
        }

        /**
         * @param builder
         * @return {@link SearchRequestBuilder} Builder
         * @throws Exception
         */
        @Override
        public <Builder extends SearchRequestBuilder> Builder buildPage(Builder builder)
                throws Exception {
            return internalBuildPage(super.buildPage(builder));
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "{" +
                    " queryTemplate = '" + queryTemplate + '\'' +
                    '}';
        }
    }
}
