/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2019 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.jcip.annotations.Immutable;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.dao.store.IPageStore;
import org.geosdi.geoplatform.experimental.el.dao.store.PageStore;
import org.geosdi.geoplatform.experimental.el.search.bool.IBooleanSearch;
import org.geosdi.geoplatform.experimental.el.search.date.IGPDateQuerySearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Stream.of;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPPageableElasticSearchDAO<D extends Document> extends GPPageableAsyncElasticSearchDAO<D> {

    /**
     * @param <P>
     * @param page
     * @return {@link IPageResult<D>}
     * @throws Exception
     */
    <P extends Page> IPageResult<D> find(P page) throws Exception;

    /**
     * @param page
     * @param subType
     * @param <P>
     * @param <V>
     * @return {@link IPageResult<V>}
     * @throws Exception
     */
    <P extends Page, V extends D> IPageResult<V> find(P page, Class<V> subType) throws Exception;

    /**
     * @param page
     * @param classe
     * @param <P>
     * @param <V>
     * @return {@link IPageResult<V>}
     * @throws Exception
     */
    <P extends Page, V extends Document> IPageResult<V> findAndMappingWith(P page, Class<V> classe) throws Exception;

    /**
     * @param page
     * @param includeFields
     * @param excludeFields
     * @param <P>
     * @return {@link IPageResult <D>}
     * @throws Exception
     */
    <P extends Page> IPageResult<D> find(P page, String[] includeFields, String[] excludeFields)
            throws Exception;

    /**
     * @param page
     * @param includeFields
     * @param excludeFields
     * @param classe
     * @param <P>
     * @param <V>
     * @return {@link IPageResult<V>}
     * @throws Exception
     */
    <P extends Page, V extends Document> IPageResult<V> find(P page, String[] includeFields, String[] excludeFields,
            Class<V> classe) throws Exception;

    /**
     * @param page
     * @param includeField
     * @param excludeField
     * @param <P>
     * @return {@link IPageResult <D>}
     * @throws Exception
     */
    <P extends Page> IPageResult<D> find(P page, String includeField, String excludeField)
            throws Exception;

    /**
     * @param page
     * @param aggregationBuilder
     * @param <P>
     * @return {@link SearchResponse}
     * @throws Exception
     */
    <P extends Page> SearchResponse find(P page, AbstractAggregationBuilder aggregationBuilder)
            throws Exception;

    /**
     * @param page
     * @param <P>
     * @return {@link Long}
     * @throws Exception
     */
    <P extends Page> Long count(P page) throws Exception;

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
         * @return {@link List <D>}
         */
        List<D> getResults();

        /**
         * @return {@link IPageStore}
         */
        @JsonIgnore
        default IPageStore toStore() {
            return new PageStore(getTotal(), getResults());
        }
    }

    /**
     *
     */
    interface PageBuilder {

        /**
         * @param builder
         * @param <Builder>
         * @return {@link SearchRequestBuilder} Builder
         * @throws Exception
         */
        <Builder extends SearchRequestBuilder> Builder buildPage(Builder builder) throws Exception;
    }

    /**
     *
     */
    interface MultiFieldPageBuilder extends PageBuilder {

        /**
         * @return {@link BoolQueryBuilder}
         */
        BoolQueryBuilder boolQueryBuilder();

        /**
         * @return {@link String}
         */
        String printQueryAsJson();

        /**
         * @param search
         * @param <Search>
         */
        default <Search extends IBooleanSearch> void buildQuery(Search search) {
            switch (search.getType()) {
                case SHOULD:
                    boolQueryBuilder().should(search.buildQuery());
                    break;
                case MUST:
                    boolQueryBuilder().must(search.buildQuery());
                    break;
                case MUST_NOT:
                    boolQueryBuilder().mustNot(search.buildQuery());
                    break;
                case FILTER:
                    boolQueryBuilder().filter(search.buildQuery());
                    break;
            }
        }
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

        public Page(int theFrom, int theSize) {
            this.from = Math.abs(theFrom);
            this.size = Math.abs(theSize);
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

        /**
         * @param builder
         * @param <Builder>
         * @return {@link Builder}
         * @throws Exception
         */
        private <Builder extends SearchRequestBuilder> Builder internalBuildPage(Builder builder) throws Exception {
            logger.trace("####################Called {} #internalBuildPage with parameters from : {} - size : {}\n\n", getClass().getSimpleName(), from, size);
            return (Builder) ((this.from >= 0) ? builder.setFrom(this.from).setSize(this.size) : builder.setSize(this.size));
        }

        /**
         * @param builder
         * @param <Builder>
         * @return {@link Builder}
         * @throws Exception
         */
        @Override
        public <Builder extends SearchRequestBuilder> Builder buildPage(Builder builder) throws Exception {
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

        public SortablePage(String field, SortOrder sortOrder, int from, int size) {
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

        /**
         * @param query
         */
        public QueriableSortablePage(QueryBuilder query) {
            this(0, 0, query);
        }

        /**
         * @param field
         * @param sortOrder
         * @param query
         */
        public QueriableSortablePage(String field, SortOrder sortOrder, QueryBuilder query) {
            this(field, sortOrder, 0, 0, query);
        }

        /**
         * @param from
         * @param size
         * @param query
         */
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
    class DateRangeSortablePage extends SortablePage implements MultiFieldPageBuilder {

        private final IGPDateQuerySearch[] dateQuerySearch;
        private BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        public DateRangeSortablePage(IGPDateQuerySearch... theDateQuerySearch) {
            this(null, null, theDateQuerySearch);
        }

        public DateRangeSortablePage(int from, int size, IGPDateQuerySearch... theDateQuerySearch) {
            this(null, null, from, size, theDateQuerySearch);
        }

        public DateRangeSortablePage(String field, SortOrder sortOrder, IGPDateQuerySearch... theDateQuerySearch) {
            this(field, sortOrder, 0, 0, theDateQuerySearch);
        }

        public DateRangeSortablePage(String field, SortOrder sortOrder, int from, int size,
                IGPDateQuerySearch... theDateQuerySearch) {
            super(field, sortOrder, from, size);
            this.dateQuerySearch = theDateQuerySearch;
        }

        /**
         * @return {@link IGPDateQuerySearch}
         */
        public IGPDateQuerySearch[] getDateQuerySearch() {
            return this.dateQuerySearch;
        }

        /**
         * @return {@link BoolQueryBuilder}
         */
        @Override
        public BoolQueryBuilder boolQueryBuilder() {
            return this.queryBuilder;
        }

        private Boolean canBuildPage() {
            return ((this.dateQuerySearch != null) && (this.dateQuerySearch.length > 0));
        }

        /**
         * @param builder
         * @param <Builder>
         * @return {@link Builder}
         * @throws Exception
         */
        private <Builder extends SearchRequestBuilder> Builder internalBuildPage(Builder builder) throws Exception {
            logger.trace("####################Called {} #internalBuildPage with parameters dateQuerySearch : {} \n\n", getClass().getSimpleName(), this.dateQuerySearch);
            Arrays.stream(this.dateQuerySearch)
                    .filter(q -> q != null)
                    .forEach(q -> buildQuery(q));
            builder.setQuery(queryBuilder);
            logger.trace("####################{} Query Created: \n{} \n\n", getClass().getSimpleName(), builder);
            return builder;
        }

        /**
         * @param builder
         * @param <Builder>
         * @return {@link Builder}
         * @throws Exception
         */
        @Override
        public <Builder extends SearchRequestBuilder> Builder buildPage(Builder builder) throws Exception {
            return (canBuildPage() ? internalBuildPage(super.buildPage(builder)) : super.buildPage(builder));
        }

        /**
         * @return {@link String}
         */
        @Override
        public String printQueryAsJson() {
            return null;
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + " {" +
                    "  from = " + super.getFrom() +
                    ", size = " + super.getSize() +
                    ", field = " + super.getField() +
                    ", sortOrder = " + super.getSortOrder() +
                    " ,dateQuerySearch = '" + dateQuerySearch + '\'' +
                    '}';
        }
    }

    @Immutable
    class MultiFieldsSearch extends SortablePage implements MultiFieldPageBuilder {

        private final IBooleanSearch[] queryList;
        private BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        /**
         * @param queryList
         */
        public MultiFieldsSearch(IBooleanSearch... queryList) {
            this(null, null, 0, 0, queryList);
        }

        /**
         * @param from
         * @param size
         * @param queryList
         */
        public MultiFieldsSearch(int from, int size, IBooleanSearch... queryList) {
            this(null, null, from, size, queryList);
        }

        /**
         * @param field
         * @param sortOrder
         * @param queryList
         */
        public MultiFieldsSearch(String field, SortOrder sortOrder, IBooleanSearch... queryList) {
            this(field, sortOrder, 0, 0, queryList);
        }

        /**
         * @param field
         * @param sortOrder
         * @param from
         * @param size
         * @param queryList
         */
        public MultiFieldsSearch(String field, SortOrder sortOrder, int from, int size, IBooleanSearch... queryList) {
            super(field, sortOrder, from, size);
            this.queryList = queryList;
        }

        /**
         * @return {@link Boolean}
         */
        private Boolean canBuildPage() {
            return this.queryList != null && (this.queryList.length > 0);
        }

        /**
         * @param builder
         * @param <Builder>
         * @return {@link Builder}
         * @throws Exception
         */
        private <Builder extends SearchRequestBuilder> Builder internalBuildPage(Builder builder) throws Exception {
            logger.trace("####################Called {} #internalBuildPage with parameters queryList : {} \n\n", getClass().getSimpleName(), this.queryList);
            of(this.queryList).filter(q -> q != null).forEach(q -> buildQuery(q));
            logger.trace("####################{} - Create Query: \n{} \n\n", getClass().getSimpleName(), this.queryBuilder.toString());
            return (Builder) builder.setQuery(queryBuilder);
        }

        /**
         * @param builder
         * @param <Builder>
         * @return {@link Builder}
         * @throws Exception
         */
        @Override
        public <Builder extends SearchRequestBuilder> Builder buildPage(Builder builder) throws Exception {
            return (canBuildPage() ? internalBuildPage(super.buildPage(builder)) : super.buildPage(builder));
        }

        /**
         * @return {@link String}
         */
        @Override
        public String printQueryAsJson() {
            of(this.queryList).filter(q -> q != null).forEach(q -> buildQuery(q));
            return boolQueryBuilder().toString();
        }

        /**
         * @return {@link BoolQueryBuilder}
         */
        @Override
        public BoolQueryBuilder boolQueryBuilder() {
            return this.queryBuilder;
        }
    }
}