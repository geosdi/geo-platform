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
package org.geosdi.geoplatform.experimental.el.rest.api.dao.index;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.refresh.RefreshRequest;
import org.elasticsearch.action.admin.indices.refresh.RefreshResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Cancellable;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.geosdi.geoplatform.experimental.el.api.function.GPElasticSearchCheck;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.rest.api.dao.mapping.ElasticSeachRestMappingDAO;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static javax.annotation.meta.When.NEVER;
import static org.elasticsearch.action.DocWriteRequest.OpType.INDEX;
import static org.elasticsearch.action.DocWriteResponse.Result.CREATED;
import static org.elasticsearch.client.RequestOptions.DEFAULT;
import static org.elasticsearch.common.xcontent.XContentType.JSON;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class ElasticSearchRestIndexDAO<D extends Document> extends ElasticSeachRestMappingDAO<D> implements GPElasticSearchRestIndexDAO<D> {

    /**
     * @param theEntityClass
     * @param theJacksonSupport
     */
    protected ElasticSearchRestIndexDAO(@Nonnull(when = NEVER) Class<D> theEntityClass, @Nullable GPJacksonSupport theJacksonSupport) {
        super(theEntityClass, theJacksonSupport);
    }

    /**
     * @return {@link Boolean}
     * @throws Exception
     */
    @Override
    public Boolean createIndex() throws Exception {
        if (!existIndex()) {
            logger.debug("###############################Called {}#createIndex with Param : {}\n", this.getClass().getSimpleName(), getIndexName());
            CreateIndexRequest createIndexRequest = new CreateIndexRequest(this.getIndexName());
            XContentBuilder xContentBuilder = this.preparePutMapping();
            if ((this.isCreateMapping()) && (xContentBuilder != null)) {
                logger.debug("#####################TRYING TO PUT MAPPING with SOURCE : \n{}\n", Strings.toString(xContentBuilder));
                createIndexRequest.mapping(xContentBuilder);
            }
            return this.elasticSearchRestHighLevelClient.indices().create(createIndexRequest, DEFAULT).isAcknowledged();
        } else {
            logger.debug("##############################Index with name : {}, already exist.", this.getIndexName());
            return FALSE;
        }
    }

    /**
     * @param theActionListener
     * @throws Exception
     */
    @Override
    public void createIndexAsync(@Nonnull(when = NEVER) ActionListener<CreateIndexResponse> theActionListener) throws Exception {
        checkArgument(theActionListener != null, "The Parameter actionListener must not be null.");
        if (!existIndex()) {
            logger.debug("###############################Called {}#createIndexAsync with Param : {}\n",
                    this.getClass().getSimpleName(), getIndexName());
            CreateIndexRequest createIndexRequest = new CreateIndexRequest(this.getIndexName());
            XContentBuilder xContentBuilder = this.preparePutMapping();
            if ((this.isCreateMapping()) && (xContentBuilder != null)) {
                logger.debug("#####################TRYING TO PUT MAPPING with SOURCE : \n{}\n", Strings.toString(xContentBuilder));
                createIndexRequest.mapping(xContentBuilder);
            }
            this.elasticSearchRestHighLevelClient.indices().createAsync(createIndexRequest, DEFAULT, theActionListener);
        } else {
            logger.debug("##############################Index with name : {}, already exist.", this.getIndexName());
        }
    }

    /**
     * @return {@link Boolean}
     * @throws Exception
     */
    @Override
    public Boolean deleteIndex() throws Exception {
        logger.debug("###############################Called {}#deleteIndex with Param : {}\n", this.getClass().getSimpleName(), getIndexName());
        return this.elasticSearchRestHighLevelClient.indices().delete(new DeleteIndexRequest(this.getIndexName()), DEFAULT).isAcknowledged();
    }

    /**
     * @param theActionListener
     * @return {@link Cancellable}
     * @throws Exception
     */
    @Override
    public Cancellable deleteIndexAsync(@Nonnull(when = NEVER) ActionListener<AcknowledgedResponse> theActionListener) throws Exception {
        checkArgument(theActionListener != null, "The Parameter actionListener must not be null.");
        logger.debug("###############################Called {}#deleteIndexAsync with Param : {}\n", this.getClass().getSimpleName(), getIndexName());
        return this.elasticSearchRestHighLevelClient.indices().deleteAsync(new DeleteIndexRequest(this.getIndexName()), DEFAULT, theActionListener);
    }

    /**
     * @return {@link Boolean}
     * @throws Exception
     */
    @Override
    public Boolean existIndex() throws Exception {
        return this.elasticSearchRestHighLevelClient.indices().exists(new GetIndexRequest(this.getIndexName()).humanReadable(TRUE), DEFAULT);
    }

    /**
     * @throws Exception
     */
    @Override
    public void refreshIndex() throws Exception {
        this.elasticSearchRestHighLevelClient.indices().refresh(new RefreshRequest(this.getIndexName()), DEFAULT);
    }

    /**
     * @param theActionListener
     * @return
     * @throws Exception
     */
    @Override
    public Cancellable refreshIndexAsync(@Nonnull(when = NEVER) ActionListener<RefreshResponse> theActionListener) throws Exception {
        checkArgument(theActionListener != null, "The Parameter listener must not be null.");
        return this.elasticSearchRestHighLevelClient.indices().refreshAsync(new RefreshRequest(this.getIndexName()), DEFAULT, theActionListener);
    }

    /**
     * @param theDocument
     * @return {@link IndexRequest}
     */
    protected IndexRequest prepareIndexRequest(@Nonnull(when = NEVER) D theDocument) {
        checkArgument(theDocument != null, "The Parameter document must not be null.");
        try {
            return (theDocument.isIdSetted() ?
                    new IndexRequest(this.getIndexName()).id(theDocument.getId()).source(this.writeDocumentAsString(theDocument), JSON).opType(INDEX) :
                    new IndexRequest(this.getIndexName()).source(this.writeDocumentAsString(theDocument), JSON).opType(INDEX));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * @param theDocument
     * @return {@link UpdateRequest}
     */
    protected UpdateRequest prepareUpdateRequest(@Nonnull(when = NEVER) D theDocument) {
        checkArgument((theDocument != null) && (theDocument.isIdSetted()), "The Parameter document must not be null, its id must not be null or an empty string.");
        try {
            return new UpdateRequest(getIndexName(), theDocument.getId()).doc(super.writeDocumentAsString(theDocument), JSON);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * @param document
     * @return
     * @throws Exception
     */
    protected D index(@Nonnull(when = NEVER) D document) throws Exception {
        checkArgument(document != null, "The Document to index must not be null.");
        GPElasticSearchCheck<IndexResponse, D, Exception> responseCheck = (document.isIdSetted() ? this::indexDocumentWithID : this::indexDocument);
        IndexResponse response = responseCheck.apply(document);
        logger.debug("##############{} Created Document with ID : {}\n\n", this.elasticSearchRestMapper.getDocumentClassName(), response.getId());
        return document;
    }

    /**
     * @param theDocument
     * @return {@link IndexResponse}
     * @throws Exception
     */
    IndexResponse indexDocumentWithID(@Nonnull(when = NEVER) D theDocument) throws Exception {
        checkArgument((theDocument != null) && (theDocument.isIdSetted()), "The Parameter document must not be null and its id must not be null or an empty string.");
        IndexResponse response = this.elasticSearchRestHighLevelClient.index(new IndexRequest(this.getIndexName()).id(theDocument.getId())
                        .source(this.writeDocumentAsString(theDocument), JSON).opType(INDEX), DEFAULT);
        if (response.getResult() != CREATED) {
            throw new IllegalStateException("Problem to persist document, status : " + response.getResult());
        }
        return response;
    }

    /**
     * @param theDocument
     * @return {@link IndexResponse}
     * @throws Exception
     */
    IndexResponse indexDocument(@Nonnull(when = NEVER) D theDocument) throws Exception {
        checkArgument(theDocument != null, "The Parameter document must not be null.");
        IndexResponse response = this.elasticSearchRestHighLevelClient.index(new IndexRequest(this.getIndexName())
                .source(this.writeDocumentAsString(theDocument), JSON).opType(INDEX), DEFAULT);
        if (response.getResult() != CREATED) {
            throw new IllegalStateException("Problem to persist document, status : " + response.status());
        }
        try {
            theDocument.setId(response.getId());
            return response;
        } finally {
            this.updateDocumentIDAsync(theDocument);
        }
    }


    /**
     * @throws Exception
     */
    @Override
    protected void onStartUp() throws Exception {
        super.onStartUp();
        if(this.env.acceptsProfiles("prod")) {
            if (this.isUpElasticSearchCluster()) {
                if ((!createIndex()) && (this.isCreateMapping())) {
                    logger.debug("#######################Trying to create mapping for {}\n", this.elasticSearchRestMapper.getDocumentClassName());
                    XContentBuilder builder = this.preparePutMapping();
                    if (builder != null) {
                        AcknowledgedResponse putMappingResponse = this.putMapping(builder, this::createPutMappingRequest);
                        logger.debug("########################## {}\n", ((putMappingResponse.isAcknowledged()) ? "PUT_MAPPING_STATUS IS OK." : "PUT_MAPPING NOT CREATED."));
                        logger.debug("::::::::::::::::::::::GET_MAPPING_AS_STRING:::::::::::::: : {}\n", this.loadMappingAsString());
                    } else {
                        logger.debug("#########################There is no XContentBuilder defined so skip PutMapping.\n");
                    }
                } else {
                    logger.debug("@@@@@@@@@@@@@@@@@@@@@@@MAPPING_ALREADY_UP OR SKIPPING BECAUSE on {}, createMapping is set to {}.\n", this.elasticSearchRestMapper.getDocumentClassName(), this.isCreateMapping());
                    logger.debug("::::::::::::::::::::::GET_MAPPING_AS_STRING:::::::::::::: : {}\n", this.loadMappingAsString());
                }
            } else {
                logger.debug("####################Can't putMapping for : {}, because ElasticSearch is down.\n", this.elasticSearchRestMapper.getDocumentClassName());
            }
        } else {
            logger.warn("#######################Profile prod is not active so i will not perform Index Creation Request.");
        }
    }

    /**
     * @param theDocument
     * @throws Exception
     */
    protected abstract void updateDocumentIDAsync(@Nonnull(when = NEVER) D theDocument) throws Exception;
}
