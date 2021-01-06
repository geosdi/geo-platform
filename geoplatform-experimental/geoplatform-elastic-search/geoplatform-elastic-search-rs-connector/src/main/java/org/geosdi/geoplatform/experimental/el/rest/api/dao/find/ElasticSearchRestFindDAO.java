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
package org.geosdi.geoplatform.experimental.el.rest.api.dao.find;

import com.google.common.collect.Iterables;
import org.elasticsearch.action.get.*;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.sort.SortOrder;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.condition.PredicateCondition;
import org.geosdi.geoplatform.experimental.el.dao.PageResult;
import org.geosdi.geoplatform.experimental.el.rest.api.dao.page.PageableElasticSearchRestDAO;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.TRUE;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;
import static javax.annotation.meta.When.NEVER;
import static org.elasticsearch.client.RequestOptions.DEFAULT;
import static org.elasticsearch.rest.RestStatus.OK;
import static org.elasticsearch.search.sort.SortOrder.DESC;
import static org.geosdi.geoplatform.experimental.el.condition.PredicateCondition.EMPTY_PREDICATE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class ElasticSearchRestFindDAO<D extends Document> extends PageableElasticSearchRestDAO<D> implements GPElasticSearchRestFindDAO<D> {

    /**
     * @param theEntityClass
     * @param theJacksonSupport
     */
    protected ElasticSearchRestFindDAO(@Nonnull(when = NEVER) Class<D> theEntityClass, @Nullable GPJacksonSupport theJacksonSupport) {
        super(theEntityClass, theJacksonSupport);
    }

    /**
     * @param theDocumentID
     * @return {@link D}
     * @throws Exception
     */
    @Override
    public D findByID(@Nonnull(when = NEVER) String theDocumentID) throws Exception {
        checkArgument((theDocumentID != null) && !(theDocumentID.trim().isEmpty()), "The Parameter documentID must not be null or an empty string.");
        GetResponse response = this.elasticSearchRestHighLevelClient.get(new GetRequest(this.getIndexName())
                .realtime(TRUE).refresh(TRUE).id(theDocumentID), DEFAULT);
        return (response.isExists()) ? this.readGetResponse(response) : null;
    }

    /**
     * @param theDocumentID
     * @param includeFields
     * @param excludeFields
     * @return {@link D}
     * @throws Exception
     */
    @Override
    public D findByID(@Nonnull(when = NEVER) String theDocumentID, @Nullable String[] includeFields, @Nullable String[] excludeFields) throws Exception {
        checkArgument((theDocumentID != null) && !(theDocumentID.trim().isEmpty()), "The Parameter documentID must not be null or an empty string.");
        GetResponse response = this.elasticSearchRestHighLevelClient.get(new GetRequest(this.getIndexName())
                .realtime(TRUE).refresh(TRUE).fetchSourceContext(new FetchSourceContext(TRUE, includeFields, excludeFields)).id(theDocumentID), DEFAULT);
        return (response.isExists()) ? this.readGetResponse(response) : null;
    }

    /**
     * @return {@link List<D>}
     * @throws Exception
     */
    @Override
    public List<D> findLasts() throws Exception {
        SearchRequest searchRequest = this.prepareSearchRequest();
        searchRequest.source(new Page(0, 10).buildPage(new SearchSourceBuilder()));
        SearchResponse searchResponse = this.elasticSearchRestHighLevelClient.search(searchRequest, DEFAULT);
        if (searchResponse.status() != OK) {
            throw new IllegalStateException("Problem in Search : " + searchResponse.status());
        }
        Long total = searchResponse.getHits().getTotalHits().value;
        logger.debug("###################TOTAL HITS FOUND : {} .\n\n", total);
        return of(searchResponse.getHits().getHits())
                .filter(Objects::nonNull)
                .map(this::readDocument)
                .filter(Objects::nonNull)
                .collect(toList());
    }

    /**
     * @param ids
     * @return {@link List<D>}
     * @throws Exception
     */
    @Override
    public List<D> findByIDS(@Nonnull(when = NEVER) Iterable<String> ids) throws Exception {
        return findByIDS(ids, EMPTY_PREDICATE);
    }

    /**
     * @param ids
     * @param condition
     * @return {@link List<D>}
     * @throws Exception
     */
    @Override
    public List<D> findByIDS(@Nonnull(when = NEVER) Iterable<String> ids, @Nullable PredicateCondition<D> condition) throws Exception {
        checkArgument((ids != null) && (Iterables.size(ids) > 0), "The Parameter ids must not be null and not empty.");
        List<String> result = StreamSupport.stream(ids.spliterator(), false)
                .filter(Objects::nonNull)
                .filter(id -> !(id.trim().isEmpty()))
                .collect(toList());
        checkArgument(!result.isEmpty(), "The Parameter ids must not contains null values or empty strings.");
        MultiGetRequest multiGetRequest = new MultiGetRequest().realtime(TRUE);
        result.stream().forEach(id -> multiGetRequest.add(this.getIndexName(), id));
        MultiGetResponse multiGetResponses = this.elasticSearchRestHighLevelClient.mget(multiGetRequest, DEFAULT);
        return stream(multiGetResponses.getResponses())
                .filter(itemResponse -> !(itemResponse.isFailed()))
                .map(MultiGetItemResponse::getResponse)
                .map(this::readGetResponse)
                .filter(d -> ((condition != null) ? ((d != null) && (condition.test(d))) : d != null))
                .collect(toList());
    }

    /**
     * @param ids
     * @param condition
     * @param includeFields
     * @param excludeFields
     * @return {@link List<D>}
     * @throws Exception
     */
    @Override
    public List<D> findByIDS(@Nonnull(when = NEVER) Iterable<String> ids, @Nullable PredicateCondition<D> condition, @Nullable String[] includeFields, @Nullable String[] excludeFields) throws Exception {
        checkArgument((ids != null) && (Iterables.size(ids) > 0), "The Parameter ids must not be null and not empty.");
        List<String> result = StreamSupport.stream(ids.spliterator(), false)
                .filter(Objects::nonNull)
                .filter(id -> !(id.trim().isEmpty()))
                .collect(toList());
        checkArgument(!result.isEmpty(), "The Parameter ids must not contains null values or empty strings.");
        MultiGetRequest multiGetRequest = new MultiGetRequest().realtime(TRUE);
        result.stream()
                .map(v -> new MultiGetRequest.Item(this.getIndexName(), v))
                .map(item -> item.fetchSourceContext(new FetchSourceContext(TRUE, includeFields, excludeFields)))
                .forEach(item -> multiGetRequest.add(item));
        MultiGetResponse multiGetResponses = this.elasticSearchRestHighLevelClient.mget(multiGetRequest, DEFAULT);
        return stream(multiGetResponses.getResponses())
                .filter(itemResponse -> !(itemResponse.isFailed()))
                .map(MultiGetItemResponse::getResponse)
                .map(this::readGetResponse)
                .filter(d -> ((condition != null) ? ((d != null) && (condition.test(d))) : d != null))
                .collect(toList());
    }

    /**
     * @param ids
     * @param includeFields
     * @param excludeFields
     * @return {@link List<D>}
     * @throws Exception
     */
    @Override
    public List<D> findByIDS(@Nonnull(when = NEVER) Iterable<String> ids, @Nullable String[] includeFields, @Nullable String[] excludeFields) throws Exception {
        return findByIDS(ids, EMPTY_PREDICATE, includeFields, excludeFields);
    }

    /**
     * @param from
     * @param size
     * @return {@link IPageResult<D>}
     * @throws Exception
     */
    @Override
    public IPageResult<D> find(Integer from, Integer size) throws Exception {
        return super.find(new MultiFieldsSearch(from, size));
    }

    /**
     * @param from
     * @param size
     * @param sortField
     * @param sortOrder
     * @return {@link IPageResult<D>}
     * @throws Exception
     */
    @Override
    public IPageResult<D> find(Integer from, Integer size, @Nonnull(when = NEVER) String sortField, @Nullable SortOrder sortOrder) throws Exception {
        checkArgument(((sortField != null) && !(sortField.trim().isEmpty())), "The Parameter sortField must not be null.");
        return super.find(new MultiFieldsSearch(super.getJsonRootName().concat(sortField), ((sortOrder != null) ? sortOrder : DESC), from, size));
    }

    /**
     * @param theSearchSourceBuilder
     * @return {@link IPageResult<D>}
     * @throws Exception
     */
    @Override
    public IPageResult<D> find(@Nonnull(when = NEVER) SearchSourceBuilder theSearchSourceBuilder) throws Exception {
        checkArgument(theSearchSourceBuilder != null, "The Parameter sourceBuilder must not be null.");
        SearchRequest searchRequest = this.prepareSearchRequest();
        searchRequest.source(theSearchSourceBuilder);
        logger.trace("@@@@@@@@@@@@@@@@@@@@@@@@@@@@SEARCH_SOURCE_BUILDER : {}\n\n", theSearchSourceBuilder.toString());
        SearchResponse searchResponse = this.elasticSearchRestHighLevelClient.search(searchRequest, DEFAULT);
        if (searchResponse.status() != OK) {
            throw new IllegalStateException("Problem in Search : " + searchResponse.status());
        }
        Long total = searchResponse.getHits().getTotalHits().value;
        logger.debug("###################TOTAL HITS FOUND : {} .\n\n", total);
        return new PageResult<D>(total, of(searchResponse.getHits().getHits())
                .filter(Objects::nonNull)
                .map(this::readDocument)
                .filter(Objects::nonNull)
                .collect(toList()));
    }

    /**
     * @param theQueryBuilder
     * @return {@link IPageResult<D>}
     * @throws Exception
     */
    @Override
    public IPageResult<D> find(@Nonnull(when = NEVER) QueryBuilder theQueryBuilder) throws Exception {
        checkArgument(theQueryBuilder != null, "The Parameter queryBuilder must not be null.");
        return this.find(new SearchSourceBuilder().query(theQueryBuilder));
    }
}