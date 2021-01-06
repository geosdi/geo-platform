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
package org.geosdi.geoplatform.experimental.el.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.ToString;
import net.jcip.annotations.Immutable;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.geosdi.geoplatform.experimental.el.api.function.GPElasticSearchCheck;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.condition.PredicateCondition;
import org.geosdi.geoplatform.experimental.el.dao.store.IPageMapStore;
import org.geosdi.geoplatform.experimental.el.dao.store.IPageStore;
import org.geosdi.geoplatform.experimental.el.dao.store.PageStore;
import org.geosdi.geoplatform.experimental.el.search.bool.IBooleanSearch;
import org.geosdi.geoplatform.experimental.el.search.date.IGPDateQuerySearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;
import static javax.annotation.meta.When.NEVER;
import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.geosdi.geoplatform.experimental.el.dao.store.IPageMapStore.of;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPPageableElasticSearchDAO<D extends Document> {

    /**
     * @param <P>
     * @param page
     * @return {@link IPageResult<D>}
     * @throws Exception
     */
    <P extends Page> IPageResult<D> find(@Nonnull(when = NEVER) P page) throws Exception;

    /**
     * @param page
     * @param thePredicate
     * @param <P>
     * @return {@link IPageResult<D>}
     * @throws Exception
     */
    <P extends Page> IPageResult<D> find(@Nonnull(when = NEVER) P page, @Nonnull(when = NEVER) PredicateCondition<D> thePredicate) throws Exception;

    /**
     * @param page
     * @param subType
     * @param <P>
     * @param <V>
     * @return {@link IPageResult<V>}
     * @throws Exception
     */
    <P extends Page, V extends D> IPageResult<V> find(@Nonnull(when = NEVER) P page, @Nonnull(when = NEVER) Class<V> subType) throws Exception;

    /**
     * @param page
     * @param classe
     * @param <P>
     * @param <V>
     * @return {@link IPageResult<V>}
     * @throws Exception
     */
    <P extends Page, V extends Document> IPageResult<V> findAndMappingWith(@Nonnull(when = NEVER) P page, @Nonnull(when = NEVER) Class<V> classe) throws Exception;

    /**
     * @param page
     * @param includeFields
     * @param excludeFields
     * @param <P>
     * @return {@link IPageResult <D>}
     * @throws Exception
     */
    <P extends Page> IPageResult<D> find(@Nonnull(when = NEVER) P page, String[] includeFields, String[] excludeFields) throws Exception;

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
    <P extends Page, V extends Document> IPageResult<V> find(@Nonnull(when = NEVER) P page, String[] includeFields, String[] excludeFields,
            @Nonnull(when = NEVER) Class<V> classe) throws Exception;

    /**
     * @param page
     * @param includeField
     * @param excludeField
     * @param <P>
     * @return {@link IPageResult <D>}
     * @throws Exception
     */
    <P extends Page> IPageResult<D> find(@Nonnull(when = NEVER) P page, String includeField, String excludeField) throws Exception;

    /**
     * @param page
     * @param aggregationBuilder
     * @param <P>
     * @return {@link SearchResponse}
     * @throws Exception
     */
    <P extends Page> SearchResponse find(@Nonnull(when = NEVER) P page, @Nonnull(when = NEVER) AbstractAggregationBuilder aggregationBuilder) throws Exception;

    /**
     * @param page
     * @param <P>
     * @return {@link Long}
     * @throws Exception
     */
    <P extends Page> Long count(@Nonnull(when = NEVER) P page) throws Exception;

    interface IPageResult<D>  extends Serializable {

        IPageResult EMPTY_PAGE_RESULT = new PageResult(0l, Lists.newArrayList());

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

        /**
         * @param theFunction
         * @param <T>
         * @return {@link IPageStore<T>}
         */
        @JsonIgnore
        default <T> IPageStore<T> toSubStore(@Nonnull(when = NEVER) Function<D, T> theFunction) {
            checkArgument(theFunction != null, "The Parameter function must not be null.");
            return new PageStore<T>(getTotal(), getResults().stream()
                    .filter(Objects::nonNull)
                    .map(theFunction)
                    .collect(toList()));
        }

        /**
         * @param theCheck
         * @param <K>
         * @return {@link IPageMapStore<K, D>}
         * @throws Exception
         */
        @JsonIgnore
        default <K> IPageMapStore<K, D> toMapStore(@Nonnull(when = NEVER) GPElasticSearchCheck<Map<K, List<D>>, IPageResult<D>, Exception> theCheck) throws Exception {
            checkArgument(theCheck != null, "The Parameter check must not be null.");
            return of(theCheck.apply(this));
        }
    }

    interface PageBuilder {

        /**
         * @param builder
         * @param <Builder>
         * @return {@link SearchRequestBuilder} Builder
         * @throws Exception
         */
        <Builder extends SearchRequestBuilder> Builder buildPage(@Nonnull(when = NEVER) Builder builder) throws Exception;

        /**
         *
         * @param builder
         * @param <Builder>
         * @return {@link Builder}
         * @throws Exception
         */
        <Builder extends SearchSourceBuilder> Builder buildPage(@Nonnull(when = NEVER) Builder builder) throws Exception;
    }

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
        default <Search extends IBooleanSearch> void buildQuery(@Nonnull(when = NEVER) Search search) {
            checkArgument(search != null, "The Parameter search must not be null.");
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

    @Getter
    @ToString
    @Immutable
    class Page implements PageBuilder {

        protected static final Logger logger = LoggerFactory.getLogger(Page.class);
        //
        private final int from;
        private final int size;

        /**
         * @param theFrom
         * @param theSize
         */
        public Page(int theFrom, int theSize) {
            this.from = Math.abs(theFrom);
            this.size = Math.abs(theSize);
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
        public <Builder extends SearchRequestBuilder> Builder buildPage(@Nonnull(when = NEVER) Builder builder) throws Exception {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            return (canBuildPage() ? this.internalBuildPage(builder) : builder);
        }

        /**
         * @param builder
         * @param <Builder>
         * @return {@link Builder}
         * @throws Exception
         */
        private <Builder extends SearchSourceBuilder> Builder internalBuildPage(Builder builder) throws Exception {
            logger.trace("####################Called {} #internalBuildPage with parameters from : {} - size : {}\n\n", getClass().getSimpleName(), from, size);
            return (Builder) ((this.from >= 0) ? builder.from(this.from).size(this.size) : builder.size(this.size));
        }

        /**
         * @param builder
         * @return {@link Builder}
         * @throws Exception
         */
        @Override
        public <Builder extends SearchSourceBuilder> Builder buildPage(@Nonnull(when = NEVER) Builder builder) throws Exception {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            return (canBuildPage() ? this.internalBuildPage(builder) : builder);
        }
    }

    @Getter
    @ToString(callSuper = true)
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

        /**
         * @param field
         * @param sortOrder
         * @param from
         * @param size
         */
        public SortablePage(String field, SortOrder sortOrder, int from, int size) {
            super(from, size);
            this.field = field;
            this.sortOrder = sortOrder;
        }

        /**
         * @return {@link Boolean}
         */
        private Boolean canBuildPage() {
            return (((this.field != null) && !(this.field.isEmpty())) && (this.sortOrder != null));
        }

        /**
         * @param builder
         * @param <Builder>
         * @return {@link Builder}
         * @throws Exception
         */
        private <Builder extends SearchRequestBuilder> Builder internalBuildPage(Builder builder) throws Exception {
            logger.trace("####################Called {} #internalBuildPage with parameters field : {} - sortOrder : {}\n\n", getClass().getSimpleName(), field, sortOrder);
            return (Builder) builder.addSort(this.field, this.sortOrder);
        }

        /**
         * @param builder
         * @param <Builder>
         * @return {@link Builder}
         * @throws Exception
         */
        @Override
        public <Builder extends SearchRequestBuilder> Builder buildPage(@Nonnull(when = NEVER) Builder builder) throws Exception {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            return (canBuildPage() ? internalBuildPage(super.buildPage(builder)) : super.buildPage(builder));
        }

        /**
         * @param builder
         * @param <Builder>
         * @return {@link Builder}
         * @throws Exception
         */
        private <Builder extends SearchSourceBuilder> Builder internalBuildPage(Builder builder) throws Exception {
            logger.trace("####################Called {} #internalBuildPage with parameters field : {} - sortOrder : {}\n\n", getClass().getSimpleName(), field, sortOrder);
            return (Builder) builder.sort(this.field, this.sortOrder);
        }

        /**
         * @param builder
         * @return {@link Builder}
         * @throws Exception
         */
        @Override
        public <Builder extends SearchSourceBuilder> Builder buildPage(@Nonnull(when = NEVER) Builder builder) throws Exception {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            return (canBuildPage() ? internalBuildPage(super.buildPage(builder)) : super.buildPage(builder));
        }
    }

    @Getter
    @ToString(callSuper = true)
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

        /**
         * @param field
         * @param sortOrder
         * @param from
         * @param size
         * @param query
         */
        public QueriableSortablePage(String field, SortOrder sortOrder, int from, int size, QueryBuilder query) {
            super(field, sortOrder, from, size);
            this.query = query;
        }

        /**
         * @return {@link Boolean}
         */
        private Boolean canBuildPage() {
            return (this.query != null);
        }

        /**
         * @param builder
         * @param <Builder>
         * @return {@link Builder}
         * @throws Exception
         */
        private <Builder extends SearchRequestBuilder> Builder internalBuildPage(Builder builder) throws Exception {
            logger.trace("####################Called {} #internalBuildPage with parameters query : {}\n\n", getClass().getSimpleName(), query);
            return (Builder) builder.setQuery(this.query);
        }

        /**
         * @param builder
         * @param <Builder>
         * @return {@link Builder}
         * @throws Exception
         */
        @Override
        public <Builder extends SearchRequestBuilder> Builder buildPage(@Nonnull(when = NEVER) Builder builder) throws Exception {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            return (canBuildPage() ? internalBuildPage(super.buildPage(builder)) : super.buildPage(builder));
        }

        /**
         * @param builder
         * @param <Builder>
         * @return {@link Builder}
         * @throws Exception
         */
        private <Builder extends SearchSourceBuilder> Builder internalBuildPage(Builder builder) throws Exception {
            logger.trace("####################Called {} #internalBuildPage with parameters query : {}\n\n", getClass().getSimpleName(), query);
            return (Builder) builder.query(this.query);
        }

        /**
         * @param builder
         * @return {@link Builder}
         * @throws Exception
         */
        @Override
        public <Builder extends SearchSourceBuilder> Builder buildPage(@Nonnull(when = NEVER) Builder builder) throws Exception {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            return (canBuildPage() ? internalBuildPage(super.buildPage(builder)) : super.buildPage(builder));
        }
    }

    @ToString(callSuper = true)
    @Immutable
    class DateRangeSortablePage extends SortablePage implements MultiFieldPageBuilder {

        @Getter
        private final IGPDateQuerySearch[] dateQuerySearch;
        private BoolQueryBuilder queryBuilder;

        /**
         * @param theDateQuerySearch
         */
        public DateRangeSortablePage(IGPDateQuerySearch... theDateQuerySearch) {
            this(null, null, theDateQuerySearch);
        }

        /**
         * @param from
         * @param size
         * @param theDateQuerySearch
         */
        public DateRangeSortablePage(int from, int size, IGPDateQuerySearch... theDateQuerySearch) {
            this(null, null, from, size, theDateQuerySearch);
        }

        /**
         * @param field
         * @param sortOrder
         * @param theDateQuerySearch
         */
        public DateRangeSortablePage(String field, SortOrder sortOrder, IGPDateQuerySearch... theDateQuerySearch) {
            this(field, sortOrder, 0, 0, theDateQuerySearch);
        }

        /**
         * @param field
         * @param sortOrder
         * @param from
         * @param size
         * @param theDateQuerySearch
         */
        public DateRangeSortablePage(String field, SortOrder sortOrder, int from, int size, IGPDateQuerySearch... theDateQuerySearch) {
            super(field, sortOrder, from, size);
            this.dateQuerySearch = theDateQuerySearch;
        }

        /**
         * @return {@link BoolQueryBuilder}
         */
        @Override
        public BoolQueryBuilder boolQueryBuilder() {
            return this.queryBuilder;
        }

        /**
         * @return {@link Boolean}
         */
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
            this.queryBuilder = boolQuery();
            logger.trace("####################Called {} #internalBuildPage with parameters dateQuerySearch : {} \n\n", getClass().getSimpleName(), this.dateQuerySearch);
            stream(this.dateQuerySearch)
                    .filter(Objects::nonNull)
                    .forEach(this::buildQuery);
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
        public <Builder extends SearchRequestBuilder> Builder buildPage(@Nonnull(when = NEVER) Builder builder) throws Exception {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            return (canBuildPage() ? internalBuildPage(super.buildPage(builder)) : super.buildPage(builder));
        }

        /**
         * @param builder
         * @param <Builder>
         * @return {@link Builder}
         * @throws Exception
         */
        private <Builder extends SearchSourceBuilder> Builder internalBuildPage(Builder builder) throws Exception {
            this.queryBuilder = boolQuery();
            logger.trace("####################Called {} #internalBuildPage with parameters dateQuerySearch : {} \n\n", getClass().getSimpleName(), this.dateQuerySearch);
            stream(this.dateQuerySearch)
                    .filter(Objects::nonNull)
                    .forEach(this::buildQuery);
            builder.query(queryBuilder);
            logger.trace("####################{} Query Created: \n{} \n\n", getClass().getSimpleName(), builder);
            return builder;
        }

        /**
         * @param builder
         * @return {@link Builder}
         * @throws Exception
         */
        @Override
        public <Builder extends SearchSourceBuilder> Builder buildPage(@Nonnull(when = NEVER) Builder builder) throws Exception {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            return (canBuildPage() ? internalBuildPage(super.buildPage(builder)) : super.buildPage(builder));
        }

        /**
         * @return {@link String}
         */
        @Override
        public String printQueryAsJson() {
            this.queryBuilder = boolQuery();
            stream(this.dateQuerySearch)
                    .filter(Objects::nonNull)
                    .forEach(this::buildQuery);
            return this.queryBuilder.toString();
        }
    }

    @ToString(callSuper = true)
    @Immutable
    class MultiFieldsSearch extends SortablePage implements MultiFieldPageBuilder {

        @Getter
        private final IBooleanSearch[] queryList;
        private BoolQueryBuilder queryBuilder;

        /**
         * @param theQueryList
         */
        public MultiFieldsSearch(IBooleanSearch... theQueryList) {
            this(null, null, 0, 0, theQueryList);
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
         * @param theQueryList
         */
        public MultiFieldsSearch(String field, SortOrder sortOrder, IBooleanSearch... theQueryList) {
            this(field, sortOrder, 0, 0, theQueryList);
        }

        /**
         * @param field
         * @param sortOrder
         * @param from
         * @param size
         * @param theQueryList
         */
        public MultiFieldsSearch(String field, SortOrder sortOrder, int from, int size, IBooleanSearch... theQueryList) {
            super(field, sortOrder, from, size);
            this.queryList = theQueryList;
        }

        /**
         * @return {@link Boolean}
         */
        private Boolean canBuildPage() {
            return ((this.queryList != null) && (this.queryList.length > 0));
        }

        /**
         * @param builder
         * @param <Builder>
         * @return {@link Builder}
         * @throws Exception
         */
        private <Builder extends SearchRequestBuilder> Builder internalBuildPage(Builder builder) throws Exception {
            this.queryBuilder = boolQuery();
            logger.trace("####################Called {} #internalBuildPage with parameters queryList : {} \n\n", getClass().getSimpleName(), this.queryList);
            of(this.queryList)
                    .filter(Objects::nonNull)
                    .forEach(this::buildQuery);
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
            checkArgument(builder != null, "The Parameter builder must not be null.");
            return (canBuildPage() ? internalBuildPage(super.buildPage(builder)) : super.buildPage(builder));
        }

        /**
         * @param builder
         * @param <Builder>
         * @return {@link Builder}
         * @throws Exception
         */
        private <Builder extends SearchSourceBuilder> Builder internalBuildPage(Builder builder) throws Exception {
            this.queryBuilder = boolQuery();
            logger.trace("####################Called {} #internalBuildPage with parameters queryList : {} \n\n", getClass().getSimpleName(), this.queryList);
            of(this.queryList)
                    .filter(Objects::nonNull)
                    .forEach(this::buildQuery);
            logger.trace("####################{} - Create Query: \n{} \n\n", getClass().getSimpleName(), this.queryBuilder.toString());
            return (Builder) builder.query(queryBuilder);
        }

        /**
         * @param builder
         * @return {@link Builder}
         * @throws Exception
         */
        @Override
        public <Builder extends SearchSourceBuilder> Builder buildPage(@Nonnull(when = NEVER) Builder builder) throws Exception {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            return (canBuildPage() ? internalBuildPage(super.buildPage(builder)) : super.buildPage(builder));
        }

        /**
         * @return {@link String}
         */
        @Override
        public String printQueryAsJson() {
            this.queryBuilder = boolQuery();
            of(this.queryList)
                    .filter(Objects::nonNull)
                    .forEach(this::buildQuery);
            return boolQueryBuilder().toString();
        }

        /**
         * @return {@link BoolQueryBuilder}
         */
        @Override
        public BoolQueryBuilder boolQueryBuilder() {
            return this.queryBuilder = ((this.queryBuilder != null) ? this.queryBuilder : boolQuery());
        }
    }
}