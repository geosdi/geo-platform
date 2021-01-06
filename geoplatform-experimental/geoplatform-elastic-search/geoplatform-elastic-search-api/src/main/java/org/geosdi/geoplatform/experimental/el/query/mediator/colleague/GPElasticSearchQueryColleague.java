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
package org.geosdi.geoplatform.experimental.el.query.mediator.colleague;

import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.experimental.el.index.GPBaseIndexCreator;
import org.geosdi.geoplatform.experimental.el.query.template.IGPElasticSearchQueryTemplate;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPElasticSearchQueryColleague {

    /**
     * @param colleagueParam
     * @return {@link R}
     * @throws Exception
     */
    <COLLEAGUE_PARAM extends IGPElasticSearchQueryColleagueParams, R extends Object> R executeQueryColleague(COLLEAGUE_PARAM colleagueParam)
            throws Exception;

    /**
     * @param <QUERY_COLLEAGUE_KEY>
     * @return {@link QUERY_COLLEAGUE_KEY}
     */
    <QUERY_COLLEAGUE_KEY extends GPBaseIndexCreator.GPIndexSettings> QUERY_COLLEAGUE_KEY getQueryColleagueKey();

    /**
     * @param <QUERY_TEMPLATE>
     * @return {@link QUERY_TEMPLATE}
     */
    <QUERY_TEMPLATE extends IGPElasticSearchQueryTemplate> QUERY_TEMPLATE getQueryTemplate();

    interface IGPElasticSearchQueryColleagueParams {

        /**
         * @return {@link Integer}
         */
        Integer getFrom();

        /**
         * @return {@link Integer}
         */
        Integer getSize();

        /**
         * @return {@link String}
         */
        String getQueryTemplate();
    }

    @Immutable
    class GPElasticSearchQueryColleagueParams implements IGPElasticSearchQueryColleagueParams {

        private final Integer from;
        private final Integer size;
        private final String queryTemplate;

        public GPElasticSearchQueryColleagueParams(Integer theFrom, Integer theSize,
                String theQueryTemplate) {
            this.from = theFrom;
            this.size = theSize;
            this.queryTemplate = theQueryTemplate;
        }

        /**
         * @return {@link Integer}
         */
        @Override
        public Integer getFrom() {
            return ((this.from != null) && (this.from > 0) ? this.from : 0);
        }

        /**
         * @return {@link Integer}
         */
        @Override
        public Integer getSize() {
            return ((this.size != null) && (this.size > 0) ? this.size : 0);
        }

        /**
         * @return {@link String}
         */
        @Override
        public String getQueryTemplate() {
            return this.queryTemplate;
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "{" +
                    " from = " + from +
                    ", size = " + size +
                    ", queryTemplate = '" + queryTemplate + '\'' +
                    '}';
        }
    }
}
