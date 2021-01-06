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
package org.geosdi.geoplatform.experimental.el.rest.api.dao;

import com.google.common.collect.Iterables;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.geosdi.geoplatform.experimental.el.api.function.GPElasticSearchCheck;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.api.response.IGPUpdateResponse;
import org.geosdi.geoplatform.experimental.el.rest.api.dao.async.ElasticSearchRestAsyncDAO;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.stream.StreamSupport.stream;
import static javax.annotation.meta.When.NEVER;
import static org.elasticsearch.action.DocWriteResponse.Result.DELETED;
import static org.elasticsearch.client.RequestOptions.DEFAULT;
import static org.elasticsearch.common.xcontent.XContentType.JSON;
import static org.elasticsearch.rest.RestStatus.OK;
import static org.geosdi.geoplatform.experimental.el.api.response.IGPUpdateResponse.of;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class ElasticSearchRestDAO<D extends Document> extends ElasticSearchRestAsyncDAO<D> implements GPElasticSearchRestDAO<D> {

    /**
     * @param theEntityClass
     * @param theJacksonSupport
     */
    protected ElasticSearchRestDAO(@Nonnull(when = NEVER) Class<D> theEntityClass, @Nullable GPJacksonSupport theJacksonSupport) {
        super(theEntityClass, theJacksonSupport);
    }

    /**
     * @param document
     * @return D
     * @throws Exception
     */
    @Override
    public D persist(@Nonnull(when = NEVER) D document) throws Exception {
        checkArgument(document != null, "The Parameter document must not be null.");
        logger.debug("############################Try to insert {}\n", document);
        GPElasticSearchCheck<D, D, Exception> check = this::index;
        return check.apply(document);
    }

    /**
     * @param documents
     * @return {@link BulkResponse}
     * @throws Exception
     */
    @Override
    public BulkResponse persist(@Nonnull(when = NEVER) Iterable<D> documents) throws Exception {
        checkArgument((documents != null) && (Iterables.size(documents) > 0));
        BulkRequest bulkRequest = new BulkRequest();
        stream(documents.spliterator(), FALSE)
                .filter(Objects::nonNull)
                .map(this::prepareIndexRequest)
                .filter(Objects::nonNull)
                .forEach(bulkRequest::add);
        BulkResponse bulkResponse = this.elasticSearchRestHighLevelClient.bulk(bulkRequest, DEFAULT);
        if (bulkResponse.hasFailures()) {
            throw new IllegalStateException(bulkResponse.buildFailureMessage());
        }
        return bulkResponse;
    }

    /**
     * @param document
     * @return {@link IGPUpdateResponse}
     * @throws Exception
     */
    @Override
    public IGPUpdateResponse update(@Nonnull(when = NEVER) D document) throws Exception {
        checkArgument(((document != null) && ((document.getId() != null) && !(document.getId().trim().isEmpty()))), "The {} to Update must  not be null or ID must not be null or Empty.", this.elasticSearchRestMapper.getDocumentClassName());
        UpdateRequest updateRequest = new UpdateRequest(this.getIndexName(), document.getId())
                .doc(this.writeDocumentAsString(document), JSON);
        logger.debug("##################################Try to Update : {}\n\n", document);
        UpdateResponse updateResponse = this.elasticSearchRestHighLevelClient.update(updateRequest, DEFAULT);
        if (updateResponse.status() != OK) {
            throw new IllegalStateException("Problem to update document, status : " + updateResponse.status());
        }
        IGPUpdateResponse value = of(TRUE, updateResponse.getVersion());
        logger.debug("#######################Document updated successfully, Response : {}\n", value);
        return value;
    }

    /**
     * @param theID
     * @param theProperties
     * @return {@link IGPUpdateResponse}
     * @throws Exception
     */
    @Override
    public IGPUpdateResponse update(@Nonnull(when = NEVER) String theID, @Nonnull(when = NEVER) Map<String, Object> theProperties) throws Exception {
        checkArgument((theID != null) && !(theID.trim().isEmpty()), "The Parameter id must not be null or an empty string.");
        checkArgument((theProperties != null) && (!theProperties.isEmpty()), "The Parameter properties must not be null or an empty map");
        UpdateRequest updateRequest = new UpdateRequest(this.getIndexName(), theID).doc(theProperties);
        logger.debug("###a###############################Try to Update Document with ID : {}\n\n", theID);
        UpdateResponse updateResponse = this.elasticSearchRestHighLevelClient.update(updateRequest, DEFAULT);
        if (updateResponse.status() != OK) {
            throw new IllegalStateException("Problem to update document, status : " + updateResponse.status());
        }
        IGPUpdateResponse value = of(TRUE, updateResponse.getVersion());
        logger.debug("#######################Document updated successfully, Response : {}\n", value);
        return value;
    }

    /**
     * @param theID
     * @param theXcontetBuilder
     * @return {@link IGPUpdateResponse}
     * @throws Exception
     */
    @Override
    public IGPUpdateResponse update(@Nonnull(when = NEVER) String theID, @Nonnull(when = NEVER) XContentBuilder theXcontetBuilder) throws Exception {
        checkArgument((theID != null) && !(theID.trim().isEmpty()), "The Parameter id must not be null or an empty string.");
        checkArgument(theXcontetBuilder != null, "The Parameter xContenBuilder must not be null.");
        UpdateRequest updateRequest = new UpdateRequest(this.getIndexName(), theID).doc(theXcontetBuilder);
        logger.debug("##################################Try to Update Document with ID : {}\n\n", theID);
        UpdateResponse updateResponse = this.elasticSearchRestHighLevelClient.update(updateRequest, DEFAULT);
        if (updateResponse.status() != OK) {
            throw new IllegalStateException("Problem to update document, status : " + updateResponse.status());
        }
        IGPUpdateResponse value = of(TRUE, updateResponse.getVersion());
        logger.debug("#######################Document updated successfully, Response : {}\n", value);
        return value;
    }

    /**
     * @param documents
     * @return {@link BulkResponse}
     * @throws Exception
     */
    @Override
    public BulkResponse update(@Nonnull(when = NEVER) Iterable<D> documents) throws Exception {
        checkArgument((documents != null) && (Iterables.size(documents) > 0));
        BulkRequest bulkRequest = new BulkRequest();
        stream(documents.spliterator(), FALSE)
                .filter(Objects::nonNull)
                .map(this::prepareUpdateRequest)
                .filter(Objects::nonNull)
                .forEach(bulkRequest::add);
        BulkResponse bulkResponse = this.elasticSearchRestHighLevelClient.bulk(bulkRequest, DEFAULT);
        if (bulkResponse.hasFailures()) {
            throw new IllegalStateException(bulkResponse.buildFailureMessage());
        }
        return bulkResponse;
    }

    /**
     * @param theValue
     * @param theCheck
     * @return
     * @throws Exception
     */
    @Override
    public <R extends UpdateByQueryRequest, V> BulkByScrollResponse updateByQuery(@Nonnull(when = NEVER) V theValue, @Nonnull(when = NEVER) GPElasticSearchCheck<R, V, Exception> theCheck) throws Exception {
        checkArgument(theValue != null, "The Parameter value must not be null.");
        checkArgument(theCheck != null, "The Parameter checkFunction must not be null.");
        return this.elasticSearchRestHighLevelClient.updateByQuery(theCheck.apply(theValue), DEFAULT);
    }

    /**
     * <p>
     *      Delete Document by ElasticSearch ID
     * </p>
     *
     * @param theID
     * @return {@link Boolean}
     * @throws Exception
     */
    @Override
    public Boolean delete(@Nonnull(when = NEVER) String theID) throws Exception {
        checkArgument((theID != null) && !(theID.trim().isEmpty()), "The Parameter id must not be null or an empty string.");
        DeleteResponse deleteResponse = this.elasticSearchRestHighLevelClient.delete(new DeleteRequest(this.getIndexName(), theID), DEFAULT);
        if (deleteResponse.getResult() != DELETED) {
            logger.trace("#####################Error to delete document with id : {}, status : {}, result : {}", theID, deleteResponse.status(), deleteResponse.getResult());
            throw new IllegalStateException("Problem to delete document , status : " + deleteResponse.status());
        }
        return TRUE;
    }

    /**
     * @param theValue
     * @param theCheck
     * @return {@link BulkByScrollResponse}
     * @throws Exception
     */
    @Override
    public <R extends DeleteByQueryRequest, V> BulkByScrollResponse deleteByQuery(@Nonnull(when = NEVER) V theValue, @Nonnull(when = NEVER) GPElasticSearchCheck<R, V, Exception> theCheck) throws Exception {
        checkArgument(theValue != null, "The Parameter value must not be null.");
        checkArgument(theCheck != null, "The Parameter checkFunction must not be null.");
        return this.elasticSearchRestHighLevelClient.deleteByQuery(theCheck.apply(theValue), DEFAULT);
    }

    /**
     * <p>
     *      Return the number of Documents.
     * </p>
     *
     * @return {@link Long}
     * @throws Exception
     */
    @Override
    public Long count() throws Exception {
        CountResponse countResponse = this.elasticSearchRestHighLevelClient.count(new CountRequest(getIndexName()), DEFAULT);
        if (countResponse.status() != OK) {
            throw new IllegalStateException("Problem to count document, status : " + countResponse.status());
        }
        return countResponse.getCount();
    }

    /**
     * @param theQueryBuilder
     * @return {@link Long}
     * @throws Exception
     */
    @Override
    public Long count(@Nonnull(when = NEVER) QueryBuilder theQueryBuilder) throws Exception {
        checkArgument(theQueryBuilder != null, "The Parameter queryBuilder must not be null.");
        CountRequest countRequest = new CountRequest(this.getIndexName());
        countRequest.query(theQueryBuilder);
        logger.trace("#########################QueryBuilder : \n{}\n\n", theQueryBuilder.toString());
        CountResponse countResponse = this.elasticSearchRestHighLevelClient.count(countRequest, DEFAULT);
        if (countResponse.status() != OK) {
            throw new IllegalStateException("Problem in Search : " + countResponse.status());
        }
        return countResponse.getCount();
    }

    /**
     * @param theValue
     * @param theCheck
     * @return {@link Long}
     * @throws Exception
     */
    @Override
    public <R extends QueryBuilder, V> Long count(@Nonnull(when = NEVER) V theValue, @Nonnull(when = NEVER) GPElasticSearchCheck<R, V, Exception> theCheck) throws Exception {
        checkArgument(theValue != null, "The Parameter value must not be null.");
        checkArgument(theCheck != null, "The Parameter checkFunction must not be null.");
        return this.count(theCheck.apply(theValue));
    }
}