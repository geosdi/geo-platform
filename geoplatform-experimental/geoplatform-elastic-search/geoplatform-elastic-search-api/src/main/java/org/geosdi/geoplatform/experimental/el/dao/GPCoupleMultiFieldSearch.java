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

import lombok.ToString;
import net.jcip.annotations.Immutable;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.geosdi.geoplatform.experimental.el.dao.GPPageableElasticSearchDAO.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static io.reactivex.rxjava3.core.Observable.fromIterable;
import static java.util.stream.Collectors.toList;
import static javax.annotation.meta.When.NEVER;
import static org.elasticsearch.index.query.QueryBuilders.boolQuery;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPCoupleMultiFieldSearch extends GPPageableElasticSearchDAO.PageBuilder {

    /**
     * @return {@link BoolQueryBuilder}
     */
    BoolQueryBuilder boolQueryBuilder();

    /**
     * @return {@link String}
     */
    String printQueryAsJson();

    @ToString
    @Immutable
    class CoupleMultiFieldSearch extends Page implements GPCoupleMultiFieldSearch {

        private static final Logger logger = LoggerFactory.getLogger(CoupleMultiFieldSearch.class);
        //
        private final List<GPCoupleMultiFieldPageBuilder> multiFieldPageBuilders;
        private BoolQueryBuilder queryBuilder;

        /**
         * @param theMultiFieldPageBuilders
         */
        public CoupleMultiFieldSearch(@Nonnull(when = NEVER) List<GPCoupleMultiFieldPageBuilder> theMultiFieldPageBuilders) {
            this(0, 0, theMultiFieldPageBuilders);
        }

        /**
         * @param theFrom
         * @param theSize
         * @param theMultiFieldPageBuilders
         */
        public CoupleMultiFieldSearch(int theFrom, int theSize, @Nonnull(when = NEVER) List<GPCoupleMultiFieldPageBuilder> theMultiFieldPageBuilders) {
            super(theFrom, theSize);
            checkArgument((theMultiFieldPageBuilders != null) && !(theMultiFieldPageBuilders.isEmpty()), "The Parameter multiFieldPageBuilders must not be null or empty");
            this.multiFieldPageBuilders = theMultiFieldPageBuilders.stream()
                    .filter(Objects::nonNull)
                    .collect(toList());
            checkArgument(!this.multiFieldPageBuilders.isEmpty(), "The Parameter multiFieldPageBuilders must not contains null values");
        }

        /**
         * @param builder
         * @return {@link SearchRequestBuilder} Builder
         * @throws Exception
         */
        @Override
        public <Builder extends SearchRequestBuilder> Builder buildPage(@Nonnull(when = NEVER) Builder builder) throws Exception {
            checkArgument(builder != null, "The Parameter SearchRequestBuilder must not be null.");
            this.queryBuilder = boolQuery();
            logger.trace("####################Called {} #buildPage with parameters multiFieldPageBuilders : {} \n\n", getClass().getSimpleName(), this.multiFieldPageBuilders);
            fromIterable(this.multiFieldPageBuilders)
                    .filter(Objects::nonNull)
                    .doOnComplete(() ->  logger.trace("####################{} - Create Query: \n{} \n\n", getClass().getSimpleName(), this.queryBuilder.toString()))
                    .subscribe(value -> this.internalBuildPage(value, builder));
            return (Builder) super.buildPage(builder.setQuery(this.queryBuilder));
        }

        /**
         * @param builder
         * @return {@link Builder}
         * @throws Exception
         */
        @Override
        public <Builder extends SearchSourceBuilder> Builder buildPage(@Nonnull(when = NEVER) Builder builder) throws Exception {
            checkArgument(builder != null, "The Parameter SearchSourceBuilder must not be null.");
            this.queryBuilder = boolQuery();
            logger.trace("####################Called {} #buildPage with parameters multiFieldPageBuilders : {} \n\n", getClass().getSimpleName(), this.multiFieldPageBuilders);
            fromIterable(this.multiFieldPageBuilders)
                    .filter(Objects::nonNull)
                    .doOnComplete(() ->  logger.trace("####################{} - RX COMPLETE Create Query: \n{} \n\n", getClass().getSimpleName(), this.queryBuilder.toString()))
                    .subscribe(value -> this.internalBuildPage(value, builder));
            return (Builder) super.buildPage(builder.query(this.queryBuilder));
        }

        /**
         * @return {@link BoolQueryBuilder}
         */
        @Override
        public BoolQueryBuilder boolQueryBuilder() {
            return this.queryBuilder = ((this.queryBuilder != null) ? this.queryBuilder : boolQuery());
        }

        /**
         * @return {@link String}
         */
        @Override
        public String printQueryAsJson() {
            this.queryBuilder = boolQuery();
            logger.trace("####################Called {} #buildPage with parameters multiFieldPageBuilders : {} \n\n", getClass().getSimpleName(), this.multiFieldPageBuilders);
            fromIterable(this.multiFieldPageBuilders)
                    .filter(Objects::nonNull)
                    .doOnComplete(() ->  logger.trace("####################{} - Create Query: \n{} \n\n", getClass().getSimpleName(), this.queryBuilder.toString()))
                    .subscribe(value -> this.internalBuildPage(value, new SearchSourceBuilder()));
            return boolQueryBuilder().toString();
        }

        /**
         * @param search
         * @param theBuilder
         * @param <Search>
         * @param <Builder>
         * @throws Exception
         */
        <Search extends GPCoupleMultiFieldPageBuilder, Builder extends SearchSourceBuilder> void internalBuildPage(@Nonnull(when = NEVER) Search search, @Nonnull(when = NEVER) Builder theBuilder) throws Exception {
            checkArgument(search != null, "The Parameter search must not be null.");
            checkArgument(theBuilder != null, "The Parameter SearchSourceBuilder must not be null.");
            switch (search.getType()) {
                case SHOULD:
                    search.buildPage(theBuilder);
                    boolQueryBuilder().should(search.boolQueryBuilder());
                    break;
                case MUST:
                    search.buildPage(theBuilder);
                    boolQueryBuilder().must(search.boolQueryBuilder());
                    break;
                case MUST_NOT:
                    search.buildPage(theBuilder);
                    boolQueryBuilder().mustNot(search.boolQueryBuilder());
                    break;
                case FILTER:
                    search.buildPage(theBuilder);
                    boolQueryBuilder().filter(search.boolQueryBuilder());
                    break;
            }
        }

        /**
         * @param search
         * @param theBuilder
         * @param <Search>
         * @param <Builder>
         * @throws Exception
         */
        <Search extends GPCoupleMultiFieldPageBuilder, Builder extends SearchRequestBuilder> void internalBuildPage(@Nonnull(when = NEVER) Search search, @Nonnull(when = NEVER) Builder theBuilder) throws Exception {
            checkArgument(search != null, "The Parameter search must not be null.");
            checkArgument(theBuilder != null, "The Parameter SearchSourceBuilder must not be null.");
            switch (search.getType()) {
                case SHOULD:
                    search.buildPage(theBuilder);
                    boolQueryBuilder().should(search.boolQueryBuilder());
                    break;
                case MUST:
                    search.buildPage(theBuilder);
                    boolQueryBuilder().must(search.boolQueryBuilder());
                    break;
                case MUST_NOT:
                    search.buildPage(theBuilder);
                    boolQueryBuilder().mustNot(search.boolQueryBuilder());
                    break;
                case FILTER:
                    search.buildPage(theBuilder);
                    boolQueryBuilder().filter(search.boolQueryBuilder());
                    break;
            }
        }
    }
}