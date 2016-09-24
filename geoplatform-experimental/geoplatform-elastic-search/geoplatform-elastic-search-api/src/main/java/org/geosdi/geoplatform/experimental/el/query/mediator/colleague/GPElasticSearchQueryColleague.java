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
