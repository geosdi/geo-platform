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
package org.geosdi.geoplatform.experimental.el.rest.api.dao.async;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Cancellable;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.geosdi.geoplatform.experimental.el.api.function.GPElasticSearchCheck;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.api.response.IGPUpdateResponse;
import org.geosdi.geoplatform.experimental.el.rest.api.dao.find.ElasticSearchRestFindDAO;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.TRUE;
import static javax.annotation.meta.When.NEVER;
import static org.elasticsearch.action.DocWriteResponse.Result.CREATED;
import static org.elasticsearch.client.RequestOptions.DEFAULT;
import static org.elasticsearch.common.xcontent.XContentType.JSON;
import static org.elasticsearch.rest.RestStatus.OK;
import static org.geosdi.geoplatform.experimental.el.api.response.IGPUpdateResponse.of;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class ElasticSearchRestAsyncDAO<D extends Document> extends ElasticSearchRestFindDAO<D> implements GPElasticSearchRestAsyncDAO<D> {

    /**
     * @param theEntityClass
     * @param theJacksonSupport
     */
    protected ElasticSearchRestAsyncDAO(@Nonnull(when = NEVER) Class<D> theEntityClass, @Nullable GPJacksonSupport theJacksonSupport) {
        super(theEntityClass, theJacksonSupport);
    }

    /**
     * @param document
     * @throws Exception
     */
    @Override
    public Cancellable persistAsync(@Nonnull(when = NEVER) D document) throws Exception {
        checkArgument(document != null, "The Parameter document must not be null.");
        IndexRequest indexRequest = new IndexRequest(this.getIndexName());
        return this.elasticSearchRestHighLevelClient.indexAsync((document.isIdSetted() ?
                indexRequest.id(document.getId()).source(this.writeDocumentAsString(document), JSON) :
                indexRequest.source(this.writeDocumentAsString(document), JSON)), DEFAULT, new ActionListener<IndexResponse>() {

            @Override
            public void onResponse(IndexResponse indexResponse) {
                if (indexResponse.getResult() != CREATED) {
                    throw new IllegalStateException("Problem to persistAsync document, status : " + indexResponse.status());
                }
                document.setId(indexResponse.getId());
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
                logger.error("#######################persistAsync error : {}\n", e.getMessage());
                throw new IllegalStateException(e);
            }
        });
    }

    /**
     * @param document
     * @return {@link Cancellable}
     * @throws Exception
     */
    @Override
    public Cancellable updateAsync(@Nonnull(when = NEVER) D document) throws Exception {
        checkArgument(((document != null) && ((document.getId() != null) && !(document.getId().trim().isEmpty()))), "The {} to Update must  not be null or ID must not be null or Empty.", this.elasticSearchRestMapper.getDocumentClassName());
        UpdateRequest updateRequest = new UpdateRequest(this.getIndexName(), document.getId())
                .doc(this.writeDocumentAsString(document), JSON);
        logger.debug("##################################Try to Update : {}\n\n", document);
        return this.elasticSearchRestHighLevelClient.updateAsync(updateRequest, DEFAULT, new ActionListener<UpdateResponse>() {

            @Override
            public void onResponse(UpdateResponse updateResponse) {
                if (updateResponse.status() != OK) {
                    throw new IllegalStateException("Problem to update document, status : " + updateResponse.status());
                }
                try {
                    IGPUpdateResponse value = of(TRUE, updateResponse.getVersion());
                    logger.debug("#######################Document updated successfully, Response : {}\n", value);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    logger.error("#######################updateAsync error : {}\n", ex.getMessage());
                }
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
                logger.error("#######################updateAsync error : {}\n", e.getMessage());
                throw new IllegalStateException(e);
            }
        });
    }

    /**
     * @param document
     * @param theListener
     * @return {@link Cancellable}
     * @throws Exception
     */
    @Override
    public Cancellable updateAsync(@Nonnull(when = NEVER) D document, @Nonnull(when = NEVER) ActionListener<UpdateResponse> theListener) throws Exception {
        checkArgument(((document != null) && ((document.getId() != null) && !(document.getId().trim().isEmpty()))), "The {} to Update must  not be null or ID must not be null or Empty.", this.elasticSearchRestMapper.getDocumentClassName());
        checkArgument(theListener != null, "The Parameter ActionListener<UpdateResponse> must not be null.");
        UpdateRequest updateRequest = new UpdateRequest(this.getIndexName(), document.getId())
                .doc(this.writeDocumentAsString(document), JSON);
        logger.debug("##################################Try to Update : {}\n\n", document);
        return this.elasticSearchRestHighLevelClient.updateAsync(updateRequest, DEFAULT, theListener);
    }

    /**
     * @param theID
     * @param theProperties
     * @return {@link Cancellable}
     * @throws Exception
     */
    @Override
    public Cancellable updateAsync(@Nonnull(when = NEVER) String theID, @Nonnull(when = NEVER) Map<String, Object> theProperties) throws Exception {
        checkArgument((theID != null) && !(theID.trim().isEmpty()), "The Parameter id must not be null or an empty string.");
        checkArgument((theProperties != null) && (!theProperties.isEmpty()), "The Parameter properties must not be null or an empty map");
        UpdateRequest updateRequest = new UpdateRequest(this.getIndexName(), theID).doc(theProperties);
        logger.debug("###a###############################Try to Update Document with ID : {}\n\n", theID);
        return this.elasticSearchRestHighLevelClient.updateAsync(updateRequest, DEFAULT, new ActionListener<UpdateResponse>() {

            @Override
            public void onResponse(UpdateResponse updateResponse) {
                if (updateResponse.status() != OK) {
                    throw new IllegalStateException("Problem to update document, status : " + updateResponse.status());
                }
                try {
                    IGPUpdateResponse value = of(TRUE, updateResponse.getVersion());
                    logger.debug("#######################Document updated successfully, Response : {}\n", value);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    logger.error("#######################updateAsync error : {}\n", ex.getMessage());
                }
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
                logger.error("#######################updateAsync error : {}\n", e.getMessage());
            }
        });
    }

    /**
     * @param theID
     * @param theProperties
     * @param theListener
     * @return {@link Cancellable}
     * @throws Exception
     */
    @Override
    public Cancellable updateAsync(@Nonnull(when = NEVER) String theID, @Nonnull(when = NEVER) Map<String, Object> theProperties, @Nonnull(when = NEVER) ActionListener<UpdateResponse> theListener) throws Exception {
        checkArgument((theID != null) && !(theID.trim().isEmpty()), "The Parameter id must not be null or an empty string.");
        checkArgument((theProperties != null) && (!theProperties.isEmpty()), "The Parameter properties must not be null or an empty map");
        checkArgument(theListener != null, "The Parameter ActionListener<UpdateResponse> must not be null.");
        UpdateRequest updateRequest = new UpdateRequest(this.getIndexName(), theID).doc(theProperties);
        logger.debug("###a###############################Try to Update Document with ID : {}\n\n", theID);
        return this.elasticSearchRestHighLevelClient.updateAsync(updateRequest, DEFAULT, theListener);
    }

    /**
     * @param theID
     * @param theXcontetBuilder
     * @return {@link Cancellable}
     * @throws Exception
     */
    @Override
    public Cancellable updateAsync(@Nonnull(when = NEVER) String theID, @Nonnull(when = NEVER) XContentBuilder theXcontetBuilder) throws Exception {
        checkArgument((theID != null) && !(theID.trim().isEmpty()), "The Parameter id must not be null or an empty string.");
        checkArgument(theXcontetBuilder != null, "The Parameter xContenBuilder must not be null.");
        UpdateRequest updateRequest = new UpdateRequest(this.getIndexName(), theID).doc(theXcontetBuilder);
        logger.debug("##################################Try to Update Document with ID : {}\n\n", theID);
        return this.elasticSearchRestHighLevelClient.updateAsync(updateRequest, DEFAULT, new ActionListener<UpdateResponse>() {

            @Override
            public void onResponse(UpdateResponse updateResponse) {
                if (updateResponse.status() != OK) {
                    throw new IllegalStateException("Problem to update document, status : " + updateResponse.status());
                }
                try {
                    IGPUpdateResponse value = of(TRUE, updateResponse.getVersion());
                    logger.debug("#######################Document updated successfully, Response : {}\n", value);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    logger.error("#######################updateAsync error : {}\n", ex.getMessage());
                }
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
                logger.error("#######################updateAsync error : {}\n", e.getMessage());
            }
        });
    }

    /**
     * @param theID
     * @param theXcontetBuilder
     * @param theListener
     * @return {@link Cancellable}
     * @throws Exception
     */
    @Override
    public Cancellable updateAsync(@Nonnull(when = NEVER) String theID, @Nonnull(when = NEVER) XContentBuilder theXcontetBuilder, @Nonnull(when = NEVER) ActionListener<UpdateResponse> theListener) throws Exception {
        checkArgument((theID != null) && !(theID.trim().isEmpty()), "The Parameter id must not be null or an empty string.");
        checkArgument(theXcontetBuilder != null, "The Parameter xContenBuilder must not be null.");
        checkArgument(theListener != null, "The Parameter ActionListener<UpdateResponse> must not be null.");
        UpdateRequest updateRequest = new UpdateRequest(this.getIndexName(), theID).doc(theXcontetBuilder);
        return this.elasticSearchRestHighLevelClient.updateAsync(updateRequest, DEFAULT, theListener);
    }

    /**
     * @param theValue
     * @param theCheck
     * @return {@link Cancellable}
     * @throws Exception
     */
    @Override
    public <R extends UpdateByQueryRequest, V> Cancellable updateByQueryAsync(@Nonnull(when = NEVER) V theValue, @Nonnull(when = NEVER) GPElasticSearchCheck<R, V, Exception> theCheck) throws Exception {
        checkArgument(theValue != null, "The Parameter value must not be null.");
        checkArgument(theCheck != null, "The Parameter checkFunction must not be null.");
        return this.updateByQueryAsync(theValue, theCheck, new ActionListener<BulkByScrollResponse>() {

            @Override
            public void onResponse(BulkByScrollResponse bulkByScrollResponse) {
                logger.trace("############################BulkByScrollResponse : {}\n", bulkByScrollResponse);
            }

            @Override
            public void onFailure(Exception e) {
                logger.error("####################Failed {}#updateByQueryAsync , Reason : {}\n", ElasticSearchRestAsyncDAO.this.getClass().getSimpleName(), e.getMessage());
                e.printStackTrace();
            }
        });
    }

    /**
     * @param theValue
     * @param theCheck
     * @param theActionListener
     * @return {@link Cancellable}
     * @throws Exception
     */
    @Override
    public <R extends UpdateByQueryRequest, V> Cancellable updateByQueryAsync(@Nonnull(when = NEVER) V theValue, @Nonnull(when = NEVER) GPElasticSearchCheck<R, V, Exception> theCheck, @Nonnull(when = NEVER) ActionListener<BulkByScrollResponse> theActionListener) throws Exception {
        checkArgument(theValue != null, "The Parameter value must not be null.");
        checkArgument(theCheck != null, "The Parameter checkFunction must not be null.");
        checkArgument(theActionListener != null, "The Parameter actionListener must not be null.");
        return this.elasticSearchRestHighLevelClient.updateByQueryAsync(theCheck.apply(theValue), DEFAULT, theActionListener);
    }

    /**
     * @param theValue
     * @param theCheck
     * @return {@link Cancellable}
     * @throws Exception
     */
    @Override
    public <R extends DeleteByQueryRequest, V> Cancellable deleteByQueryAsync(@Nonnull(when = NEVER) V theValue, @Nonnull(when = NEVER) GPElasticSearchCheck<R, V, Exception> theCheck) throws Exception {
        checkArgument(theValue != null, "The Parameter value must not be null.");
        checkArgument(theCheck != null, "The Parameter checkFunction must not be null.");
        return this.deleteByQueryAsync(theValue, theCheck, new ActionListener<BulkByScrollResponse>() {

            @Override
            public void onResponse(BulkByScrollResponse bulkByScrollResponse) {
                logger.trace("############################BulkByScrollResponse : {}\n", bulkByScrollResponse);
            }

            @Override
            public void onFailure(Exception e) {
                logger.error("####################Failed {}#deleteByQueryAsync , Reason : {}\n", ElasticSearchRestAsyncDAO.this.getClass().getSimpleName(), e.getMessage());
                e.printStackTrace();
            }
        });
    }

    /**
     * @param theValue
     * @param theCheck
     * @param theActionListener
     * @return {@link Cancellable}
     * @throws Exception
     */
    @Override
    public <R extends DeleteByQueryRequest, V> Cancellable deleteByQueryAsync(@Nonnull(when = NEVER) V theValue, @Nonnull(when = NEVER) GPElasticSearchCheck<R, V, Exception> theCheck, @Nonnull(when = NEVER) ActionListener<BulkByScrollResponse> theActionListener) throws Exception {
        checkArgument(theValue != null, "The Parameter value must not be null.");
        checkArgument(theCheck != null, "The Parameter checkFunction must not be null.");
        checkArgument(theActionListener != null, "The Parameter actionListener must not be null.");
        return this.elasticSearchRestHighLevelClient.deleteByQueryAsync(theCheck.apply(theValue), DEFAULT, theActionListener);
    }

    /**
     * @param theListener
     * @return {@link Cancellable}
     * @throws Exception
     */
    @Override
    public Cancellable countAsync(@Nonnull(when = NEVER) ActionListener<CountResponse> theListener) throws Exception {
        checkArgument(theListener != null, "The Parameter listener must not be null.");
        return this.elasticSearchRestHighLevelClient.countAsync(new CountRequest(getIndexName()), DEFAULT, theListener);
    }

    /**
     * @param theDocument
     * @throws Exception
     */
    @Override
    protected final void updateDocumentIDAsync(@Nonnull(when = NEVER) D theDocument) throws Exception {
        checkArgument(theDocument != null, "The Parameter document must not be null.");
        checkArgument(theDocument.isIdSetted(), "The Document id must not be null or an empty string.");
        Map<String, Object> properties = new HashMap<>();
        properties.put(super.getJsonRootName().concat("id"), theDocument.getId());
        this.updateAsync(theDocument.getId(), properties);
    }
}