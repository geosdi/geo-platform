/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p/>
 * Copyright (C) 2008-2016 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p/>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p/>
 * ====================================================================
 * <p/>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p/>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.experimental.el.dao;

import net.jcip.annotations.Immutable;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.condition.PredicateCondition;
import org.geosdi.geoplatform.experimental.el.search.bool.IBooleanSearch;
import org.geosdi.geoplatform.experimental.el.search.date.IGPDateQuerySearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * param <D> Entity to Persist in ElasticSearch
 * </p>
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPElasticSearchDAO<D extends Document> extends ElasticSearchDAO<D> {

    /**
     * @param document
     * @return D
     * @throws Exception
     */
    D persist(D document) throws Exception;

    /**
     * @param documents
     * @return {@link BulkResponse}
     * @throws Exception
     */
    BulkResponse persist(Iterable<D> documents) throws Exception;

    /**
     * @param document
     * @throws Exception
     */
    void update(D document) throws Exception;

    /**
     * @throws Exception
     */
    void removeAll() throws Exception;

    /**
     * @return {@link List<D>}
     * @throws Exception
     */
    List<D> findLasts() throws Exception;

    /**
     * @param <P>
     * @param page
     * @return {@link List<D>}
     * @throws Exception
     */
    <P extends Page> IPageResult<D> find(P page) throws Exception;

    /**
     * @param ids
     * @return {@link List<D>}
     * @throws Exception
     */
    List<D> findByIDS(Iterable<String> ids, PredicateCondition<D> condition) throws Exception;

    /**
     * @param ids
     * @return {@link List<D>}
     * @throws Exception
     */
    List<D> findByIDS(Iterable<String> ids) throws Exception;

    /**
     * @param <D>
     */
    interface GPElasticSearchBaseDAO<D extends Document>
            extends GPElasticSearchDAO<D>, InitializingBean {

        /**
         * <p>
         * Delete Document by ElasticSearch ID</p>
         *
         * @param id
         * @throws java.lang.Exception
         */
        void delete(String id) throws Exception;

        /**
         * <p>
         * The ID must not be null otherwise an {@link IllegalArgumentException}
         * will be launched</p>
         *
         * @param id
         * @return D the Document Found
         * @throws Exception
         */
        D find(String id) throws Exception;

        /**
         * <p>
         * Return the number of Documents</p>
         *
         * @return {@link Long}
         * @throws java.lang.Exception
         */
        Long count() throws Exception;

        /**
         * @param queryBuilder
         * @return {@link Long}
         * @throws Exception
         */
        Long count(QueryBuilder queryBuilder) throws Exception;

    }

    /**
     *
     */
    interface IPageResult<D> {

        /**
         * <p>The Total Results Number</p>
         *
         * @return {@link Long}
         */
        Long getTotal();

        /**
         * @return {@link List<D>}
         */
        List<D> getResults();

    }

    interface PageBuilder {

        /**
         * @param builder
         * @param <Builder>
         * @return {@link SearchRequestBuilder} Builder
         * @throws Exception
         */
        <Builder extends SearchRequestBuilder> Builder buildPage(Builder builder)
                throws Exception;

    }

    /**
     *
     */
    @Immutable
    class Page implements PageBuilder {

        protected static final Logger logger = LoggerFactory.getLogger(Page.class);
        //
        private final int from;
        private final int size;

        public Page(int from, int size) {
            this.from = from;
            this.size = size;
        }

        /**
         * @return the from
         */
        public int getFrom() {
            return from;
        }

        /**
         * @return the size
         */
        public int getSize() {
            return size;
        }

        private Boolean canBuildPage() {
            return (this.size > 0);
        }

        private <Builder extends SearchRequestBuilder> Builder internalBuildPage(Builder builder)
                throws Exception {
            logger.trace("####################Called {} #internalBuildPage with parameters " +
                    "from : {} - size : {}\n\n", getClass().getSimpleName(), from, size);

            return (Builder) builder.setFrom(this.from).setSize(this.size);
        }

        @Override
        public <Builder extends SearchRequestBuilder> Builder buildPage(Builder builder)
                throws Exception {

            return (canBuildPage() ? this.internalBuildPage(builder) : builder);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + " {"
                    + " from = " + from
                    + ", size = " + size + '}';
        }
    }

    /**
     *
     */
    @Immutable
    class SortablePage extends Page {

        private final String field;
        private final SortOrder sortOrder;

        /**
         * <p>In this case no Pagination , and Elastic Search will return only
         * 10 results in case of Match
         * </p>
         *
         * @param field
         * @param sortOrder
         */
        public SortablePage(String field, SortOrder sortOrder) {
            this(field, sortOrder, 0, 0);
        }

        public SortablePage(String field, SortOrder sortOrder, int from,
                int size) {
            super(from, size);
            this.field = field;
            this.sortOrder = sortOrder;
        }

        /**
         * @return the field
         */
        public String getField() {
            return field;
        }

        /**
         * @return the sortOrder
         */
        public SortOrder getSortOrder() {
            return sortOrder;
        }

        private Boolean canBuildPage() {
            return (((this.field != null) && !(this.field.isEmpty()))
                    && (this.sortOrder != null));
        }

        private <Builder extends SearchRequestBuilder> Builder internalBuildPage(Builder builder)
                throws Exception {
            logger.trace("####################Called {} #internalBuildPage with parameters " +
                    "field : {} - sortOrder : {}\n\n", getClass().getSimpleName(), field, sortOrder);

            return (Builder) builder.addSort(this.field, this.sortOrder);
        }

        @Override
        public <Builder extends SearchRequestBuilder> Builder buildPage(Builder builder) throws Exception {
            return (canBuildPage() ? internalBuildPage(super.buildPage(builder)) : super.buildPage(builder));
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + " {"
                    + " from = " + super.getFrom()
                    + ", size = " + super.getSize()
                    + ", field = " + field
                    + ", sortOrder = " + sortOrder + '}';
        }

    }

    /**
     *
     */
    @Immutable
    class QueriableSortablePage extends SortablePage {

        private final QueryBuilder query;

        public QueriableSortablePage(QueryBuilder query) {
            this(0, 0, query);
        }

        public QueriableSortablePage(String field, SortOrder sortOrder, QueryBuilder query) {
            this(field, sortOrder, 0, 0, query);
        }

        public QueriableSortablePage(int from, int size, QueryBuilder query) {
            this(null, null, from, size, query);
        }

        public QueriableSortablePage(String field, SortOrder sortOrder, int from,
                int size, QueryBuilder query) {
            super(field, sortOrder, from, size);
            this.query = query;
        }

        /**
         * @return the query to perform
         */
        public QueryBuilder getQuery() {
            return query;
        }

        private Boolean canBuildPage() {
            return (this.query != null);
        }

        private <Builder extends SearchRequestBuilder> Builder internalBuildPage(Builder builder)
                throws Exception {
            logger.trace("####################Called {} #internalBuildPage with parameters " +
                    "query : {}\n\n", getClass().getSimpleName(), query);


            return (Builder) builder.setQuery(this.query);
        }

        @Override
        public <Builder extends SearchRequestBuilder> Builder buildPage(Builder builder) throws Exception {
            return (canBuildPage() ? internalBuildPage(super.buildPage(builder)) : super.buildPage(builder));
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + " {"
                    + " from = " + super.getFrom()
                    + ", size = " + super.getSize()
                    + ", field = " + super.getField()
                    + ", sortOrder = " + super.getSortOrder()
                    + ", query = " + query + '}';
        }
    }

    /**
     *
     */
    @Immutable
    class DateRangeSortablePage extends QueriableSortablePage {

        private final IGPDateQuerySearch[] dateQuerySearch;

        public DateRangeSortablePage(IGPDateQuerySearch... theDateQuerySearch) {
            this(null, null, null, theDateQuerySearch);
        }

        public DateRangeSortablePage(int from, int size, IGPDateQuerySearch... theDateQuerySearch) {
            this(null, null, null, from, size, theDateQuerySearch);
        }

        public DateRangeSortablePage(String field, SortOrder sortOrder, QueryBuilder query,
                IGPDateQuerySearch... theDateQuerySearch) {
            this(field, sortOrder, query, 0, 0, theDateQuerySearch);
        }

        public DateRangeSortablePage(String field, SortOrder sortOrder, QueryBuilder query, int from,
                int size, IGPDateQuerySearch... theDateQuerySearch) {
            super(field, sortOrder, from, size, query);
            this.dateQuerySearch = theDateQuerySearch;
        }

        /**
         * @return {@link IGPDateQuerySearch}
         */
        public IGPDateQuerySearch[] getDateQuerySearch() {
            return this.dateQuerySearch;
        }

        private Boolean canBuildPage() {
            return ((this.dateQuerySearch != null) && (this.dateQuerySearch.length > 0));
        }

        private <Builder extends SearchRequestBuilder> Builder internalBuildPage(Builder builder)
                throws Exception {
            logger.trace("####################Called {} #internalBuildPage with parameters " +
                    "dateQuerySearch : {} \n\n", getClass().getSimpleName(), this.dateQuerySearch);

            Arrays.stream(this.dateQuerySearch)
                    .filter(q -> q != null)
                    .forEach(q -> builder.setQuery(q.buildQuery()));
            logger.trace("####################Query Created: \n{} \n\n", builder.toString());
            return builder;
        }

        @Override
        public <Builder extends SearchRequestBuilder> Builder buildPage(Builder builder) throws Exception {
            return (canBuildPage() ? internalBuildPage(super.buildPage(builder)) : super.buildPage(builder));
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + " {" +
                    "  from = " + super.getFrom() +
                    ", size = " + super.getSize() +
                    ", field = " + super.getField() +
                    ", sortOrder = " + super.getSortOrder() +
                    ", query = " + super.getQuery() +
                    " ,dateQuerySearch = '" + dateQuerySearch + '\'' +
                    '}';
        }
    }

    @Immutable
    class MultiFieldsSearch extends SortablePage {

        private final IBooleanSearch[] queryList;
        private BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        public MultiFieldsSearch(IBooleanSearch... queryList) {
            this(null, null, 0, 0, queryList);
        }

        public MultiFieldsSearch(int from, int size, IBooleanSearch... queryList) {
            this(null, null, from, size, queryList);
        }

        public MultiFieldsSearch(String field, SortOrder sortOrder, int from,
                int size, IBooleanSearch... queryList) {
            super(field, sortOrder, from, size);
            this.queryList = queryList;
        }

        private Boolean canBuildPage() {
            return this.queryList != null && (this.queryList.length > 0);
        }

        private <Builder extends SearchRequestBuilder> Builder internalBuildPage(Builder builder)
                throws Exception {
            logger.trace("####################Called {} #internalBuildPage with parameters " +
                            "queryList : {} \n\n",
                    getClass().getSimpleName(), this.queryList);

            Arrays.stream(this.queryList).forEach(q -> buildQuery(q));
            logger.trace("####################Query Created: \n{} \n\n", this.queryBuilder.toString());
            return (Builder) builder.setQuery(queryBuilder);
        }

        @Override
        public <Builder extends SearchRequestBuilder> Builder buildPage(Builder builder) throws Exception {
            return (canBuildPage() ? internalBuildPage(super.buildPage(builder)) : super.buildPage(builder));
        }

        /**
         * @param booleanQuery
         */
        protected void buildQuery(IBooleanSearch booleanQuery) {
            switch (booleanQuery.getType()) {
                case SHOULD:
                    queryBuilder.should(booleanQuery.buildQuery());
                    break;
                case MUST:
                    queryBuilder.must(booleanQuery.buildQuery());
                    break;
                case MUST_NOT:
                    queryBuilder.mustNot(booleanQuery.buildQuery());
                    break;
            }
        }
    }
}
