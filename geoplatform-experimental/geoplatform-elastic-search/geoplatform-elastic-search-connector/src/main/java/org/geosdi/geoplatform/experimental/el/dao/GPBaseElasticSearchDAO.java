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

import org.elasticsearch.action.admin.indices.refresh.RefreshResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.reindex.UpdateByQueryRequestBuilder;
import org.geosdi.geoplatform.experimental.el.api.mapper.GPBaseMapper;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.configurator.GPIndexConfigurator;
import org.geosdi.geoplatform.experimental.el.index.GPBaseIndexCreator;
import org.geosdi.geoplatform.experimental.el.index.GPIndexCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Resource;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static javax.annotation.meta.When.NEVER;
import static org.elasticsearch.index.reindex.UpdateByQueryAction.INSTANCE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
abstract class GPBaseElasticSearchDAO<D extends Document> implements GPElasticSearchDAO.GPElasticSearchBaseDAO<D> {

    protected static final String EMPTY_JSON = "{}";
    //
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected GPBaseMapper<D> mapper;
    protected Client elastichSearchClient;
    @Resource(name = "elasticSearchExecutor")
    protected ExecutorService elasticSearchExecutor;
    private GPIndexCreator indexCreator;

    /**
     * @param document
     * @return {@link String}
     * @throws Exception
     */
    @Override
    public String writeDocumentAsString(@Nonnull(when = NEVER) D document) throws Exception {
        checkNotNull(document, "The Document must not be null.");
        return this.mapper.writeAsString(document);
    }

    /**
     * @param documentAsString
     * @return {@link D}
     * @throws Exception
     */
    @Override
    public D readDocument(@Nonnull(when = NEVER) String documentAsString) throws Exception {
        checkArgument(((documentAsString != null) && !(documentAsString.trim().isEmpty()) && !(documentAsString.equalsIgnoreCase(EMPTY_JSON))), "The String to Wrap must not be null or Empty");
        return mapper.read(documentAsString);
    }

    /**
     * @param documentAsString
     * @param classe
     * @return {@link V}
     * @throws Exception
     */
    @Override
    public <V extends Document> V readDocument(@Nonnull(when = NEVER) String documentAsString, @Nonnull(when = NEVER) Class<V> classe) throws Exception {
        checkArgument(((documentAsString != null) && !(documentAsString.trim().isEmpty()) && !(documentAsString.equalsIgnoreCase(EMPTY_JSON))), "The String to Wrap must not be null or Empty");
        checkArgument(classe != null, "The Parameter classe must not be null.");
        return mapper.read(documentAsString, classe);
    }

    /**
     * @param thePath
     * @return {}
     * @throws Exception
     */
    @Override
    public D readDocument(@Nonnull(when = NEVER) Path thePath) {
        checkArgument((thePath != null) && (thePath.toFile().exists()), "The Parameter thePath must not be null and the File must exist.");
        try {
            return this.mapper.read(thePath.toFile());
        } catch (Exception ex) {
            logger.error("@@@@@@@@@@@@@@@@@@@@@@@@@Error read path : {} - Exception : {}\n", thePath, ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * @return The Index Name
     */
    @Override
    public final String getIndexName() {
        return this.indexCreator.getIndexSettings().getIndexName();
    }

    /**
     * @return {@link org.geosdi.geoplatform.experimental.el.index.GPBaseIndexCreator.GPIndexSettings}
     */
    @Override
    public final <IS extends GPBaseIndexCreator.GPIndexSettings> IS getIndexSettings() {
        return this.indexCreator.getIndexSettings();
    }

    /**
     * @return The Index Type
     */
    @Override
    public final String getIndexType() {
        return this.indexCreator.getIndexSettings().getIndexType();
    }

    /**
     * <p>
     * Remember Index Creation is called by
     * {@link GPIndexConfigurator#configure()}
     * </p>
     *
     * @throws Exception
     */
    protected final void createIndex() throws Exception {
        this.indexCreator.createIndex();
    }

    /**
     * <p>
     * Dangerous. If called all Data will be dropped
     * </p>
     *
     * @throws Exception
     */
    protected final void deleteIndex() throws Exception {
        this.indexCreator.deleteIndex();
    }

    /**
     * @return {@link UpdateByQueryRequestBuilder}
     */
    protected final UpdateByQueryRequestBuilder updateByQueryRequestBuilder() throws Exception {
        checkArgument(this.elastichSearchClient != null, "The ElasticSearchClient must not be null");
        return new UpdateByQueryRequestBuilder(this.elastichSearchClient, INSTANCE);
    }

    /**
     * @return {@link Boolean}
     * @throws Exception
     */
    @Override
    public Boolean existIndex() throws Exception {
        return this.indexCreator.existIndex();
    }

    /**
     * @param theIndexCreator
     */
    @Override
    public <IC extends GPIndexCreator> void setIndexCreator(IC theIndexCreator) {
        this.indexCreator = theIndexCreator;
    }

    /**
     * @return {@link Client}
     * @throws Exception
     */
    @Override
    public final Client client() throws Exception {
        checkNotNull(this.elastichSearchClient, "The Client is null. Check your Configuration.");
        return this.elastichSearchClient;
    }

    /**
     * @return {@link E}
     * @throws Exception
     */
    @Override
    public final <E extends ExecutorService> E executor() throws Exception {
        checkNotNull(this.elasticSearchExecutor, "The Executor is null. Check your Configuration.");
        return (E) this.elasticSearchExecutor;
    }

    @Override
    public final void afterPropertiesSet() throws Exception {
        checkNotNull(this.mapper, "The Mapper must not be null.");
        checkNotNull(this.indexCreator, "The Index Creator must not be null.");
        this.elastichSearchClient = this.indexCreator.client();
        checkNotNull(this.elastichSearchClient, "The ElasticSearch Client must not be null.");
        checkNotNull(this.elasticSearchExecutor, "The ElasticSearchExecutor must not be null");
    }

    /**
     * @return {@link String}
     * @throws Exception
     */
    @Override
    public final String getJsonRootName() throws Exception {
        return this.mapper.getJsonRootName();
    }

    /**
     * @return {@link RefreshResponse}
     */
    protected RefreshResponse refreshIndex() {
        RefreshResponse refreshResponse = this.elastichSearchClient.admin().indices().prepareRefresh(getIndexName()).get();
        if (refreshResponse.getFailedShards() > 0)
            throw new IllegalStateException("Problem in Refresh : " + refreshResponse.getShardFailures());
        return refreshResponse;
    }
}