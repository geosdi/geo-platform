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

import com.google.common.collect.Iterables;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.geosdi.geoplatform.experimental.el.api.mapper.GPBaseMapper;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.condition.PredicateCondition;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.nio.file.Files.list;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static javax.annotation.meta.When.NEVER;
import static org.elasticsearch.action.DocWriteResponse.Result.DELETED;
import static org.elasticsearch.common.xcontent.XContentType.JSON;
import static org.geosdi.geoplatform.experimental.el.condition.PredicateCondition.EMPTY_PREDICATE;

/**
 * @param <D>
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class AbstractElasticSearchDAO<D extends Document> extends PageableAsyncElasticSearchDAO<D> {

    /**
     * @param document
     * @return {@link D}
     * @throws Exception
     */
    @Override
    public D persist(@Nonnull(when = NEVER) D document) throws Exception {
        checkArgument(document != null, "The Parameter document must not be null.");
        logger.debug("#################Try to insert {}\n\n", document);
        IndexResponse response;
        if (document.isIdSetted()) {
            response = this.elastichSearchClient.prepareIndex(getIndexName(), getIndexType(), document.getId())
                    .setSource(this.mapper.writeAsString(document), JSON)
                    .get();
        } else {
            response = this.elastichSearchClient.prepareIndex(getIndexName(), getIndexType())
                    .setSource(this.mapper.writeAsString(document), JSON)
                    .get();
            document.setId(response.getId());
            update(document);
        }
        logger.debug("##############{} Created : {}\n\n", this.mapper.getDocumentClassName(), response.getResult());
        return document;
    }

    /**
     * @param document
     * @throws Exception
     */
    @Override
    public void update(@Nonnull(when = NEVER) D document) throws Exception {
        checkArgument(((document != null) && ((document.getId() != null) && !(document.getId().trim().isEmpty()))),
                "The {} to Update must  not be null or ID must not be null or Empty.", this.mapper.getDocumentClassName());
        logger.debug("################Try to Update : {}\n\n", document);
        this.elastichSearchClient.prepareUpdate(getIndexName(), getIndexType(), document.getId())
                .setDoc(this.mapper.writeAsString(document), JSON)
                .get();
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
     * @return {@link List<D>}
     * @throws Exception
     */
    @Override
    public List<D> findByIDS(@Nonnull(when = NEVER) Iterable<String> ids, @Nullable PredicateCondition<D> condition) throws Exception {
        checkArgument((ids != null) && (Iterables.size(ids) > 0));
        MultiGetResponse multiGetResponses = this.elastichSearchClient.prepareMultiGet()
                .setRealtime(TRUE)
                .add(getIndexName(), getIndexType(), ids)
                .get();
        return stream(multiGetResponses.getResponses())
                .filter(response -> !response.isFailed())
                .map(r -> readGetResponse(r.getResponse()))
                .filter(d -> ((condition != null) ? ((d != null) && (condition.test(d))) : d != null))
                .collect(toList());
    }

    /**
     * @param documents
     * @return {@link BulkResponse}
     * @throws Exception
     */
    @Override
    public BulkResponse persist(@Nonnull(when = NEVER) Iterable<D> documents) throws Exception {
        checkArgument(((documents != null)), "The Documents to save, must not be null.");
        BulkRequestBuilder bulkRequest = this.elastichSearchClient.prepareBulk();
        StreamSupport.stream(documents.spliterator(), FALSE)
                .map(document -> this.prepareIndexRequestBuilder(document))
                .filter(builer -> builer != null)
                .forEach(builer -> bulkRequest.add(builer));
        BulkResponse bulkResponse = bulkRequest.get();
        if (bulkResponse.hasFailures()) {
            throw new IllegalStateException(bulkResponse.buildFailureMessage());
        }
        return bulkResponse;
    }

    /**
     * @param documents
     * @return {@link BulkResponse}
     * @throws Exception
     */
    @Override
    public BulkResponse update(@Nonnull(when = NEVER) Iterable<D> documents) throws Exception {
        checkArgument(((documents != null)), "The Documents to update, must not be null.");
        BulkRequestBuilder bulkRequest = this.elastichSearchClient.prepareBulk();
        StreamSupport.stream(documents.spliterator(), FALSE)
                .map(document -> this.prepareUpdateRequestBuilder(document))
                .filter(builer -> builer != null)
                .forEach(builder -> bulkRequest.add(builder));
        BulkResponse bulkResponse = bulkRequest.get();
        if (bulkResponse.hasFailures()) {
            throw new IllegalStateException(bulkResponse.buildFailureMessage());
        }
        return bulkResponse;
    }

    /**
     * <p>Persist all Files json in a Directory. This method in not recursive for the Directory.</p>
     *
     * @param direrctory
     * @return {@link}
     * @throws Exception
     */
    @Override
    public BulkResponse persist(@Nonnull(when = NEVER) Path direrctory) throws Exception {
        checkArgument((direrctory != null) && (direrctory.toFile().isDirectory()), "The Parameter Directory must not be null and must be a Directory.");
        return this.persist(list(direrctory)
                .filter(path -> path.toFile()
                        .getName()
                        .endsWith(".json"))
                .map(path -> super.readDocument(path))
                .filter(d -> d != null)
                .collect(toList()));
    }

    @Override
    public void delete(@Nonnull(when = NEVER) String id) {
        checkArgument(((id != null) && !(id.trim().isEmpty())), "The ID must not be null or an Empty String");
        DeleteResponse response = elastichSearchClient.prepareDelete(getIndexName(), getIndexType(), id)
                .execute()
                .actionGet();
        if (response.getResult() == DELETED) {
            logger.debug("#################Document with ID : {}, " + "was deleted.", id);
        } else {
            logger.debug("#################Document with ID : {}, " + "was not found in ElasticSearch.");
        }
    }

    @Override
    public D find(String id) throws Exception {
        checkArgument((id != null) && !(id.trim()
                .isEmpty()), "The ElasticSearch ID must not be null or an Empty String");
        GetResponse existResponse = elastichSearchClient.prepareGet(getIndexName(), getIndexType(), id)
                .get();
        return (existResponse.isExists()) ? readGetResponse(existResponse) : null;
    }

    @Override
    public Long count() {
        super.refreshIndex();
        return this.elastichSearchClient.prepareSearch(getIndexName())
                .setTypes(getIndexType())
                .get()
                .getHits()
                .getTotalHits().value;
    }

    /**
     * @param queryBuilder
     * @return {@link Long}
     * @throws Exception
     */
    @Override
    public Long count(@Nonnull(when = NEVER) QueryBuilder queryBuilder) throws Exception {
        checkArgument(queryBuilder != null, "The Parameter queryBuilder must not be null.");
        return this.elastichSearchClient.prepareSearch(getIndexName())
                .setQuery(queryBuilder)
                .setTypes(getIndexType())
                .get()
                .getHits()
                .getTotalHits().value;
    }

    @Override
    public void removeAll() throws Exception {
        SearchResponse searchResponse = this.elastichSearchClient.prepareSearch()
                .setIndices(getIndexName())
                .setTypes(getIndexType())
                .setScroll(new TimeValue(60000))
                .setSize(100)
                .execute()
                .actionGet();
        while (true) {
            Stream.of(searchResponse.getHits()
                    .getHits())
                    .forEach(document -> {
                        this.elastichSearchClient.delete(new DeleteRequest(getIndexName(), getIndexType(), document.getId()))
                                .actionGet();
                    });
            searchResponse = this.elastichSearchClient.prepareSearchScroll(searchResponse.getScrollId())
                    .setScroll(new TimeValue(60000))
                    .execute()
                    .actionGet();
            if (searchResponse.getHits()
                    .getHits().length == 0) {
                break;
            }
        }
    }

    /**
     * @return {@link GPBaseMapper<D>}
     */
    @Override
    public GPBaseMapper<D> mapper() {
        return this.mapper;
    }
}