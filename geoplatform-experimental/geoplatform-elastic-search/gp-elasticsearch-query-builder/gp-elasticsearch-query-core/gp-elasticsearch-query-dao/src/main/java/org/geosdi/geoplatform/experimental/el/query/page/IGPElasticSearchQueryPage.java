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
