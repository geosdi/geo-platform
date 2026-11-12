/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.experimental.el9.rest.api.dao.base;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.JsonpMapper;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el9.rest.api.dao.support.ElasticSearchRestSupportDAO;
import org.geosdi.geoplatform.experimental.el9.rest.api.info.GPElasticSearchRestInfo;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import tools.jackson.databind.json.JsonMapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * Base dei DAO con i client iniettati da Spring.
 *
 * @param <D> il tipo dei documenti
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class ElasticSearchRestBaseDAO<D extends Document> extends ElasticSearchRestSupportDAO<D> implements GPElasticSearchRestBaseDAO<D> {

    @Resource(name = "elasticSearchClient")
    protected ElasticsearchClient elasticSearchClient;
    @Resource(name = "elasticSearchAsyncClient")
    protected ElasticsearchAsyncClient elasticSearchAsyncClient;
    @Autowired
    protected Environment env;

    /**
     * @param theEntityClass    la classe dei documenti
     * @param theJacksonSupport il mapper dei documenti; null vuol dire quello di default
     */
    protected ElasticSearchRestBaseDAO(@Nonnull(when = NEVER) Class<D> theEntityClass, @Nullable JacksonSupport<JsonMapper> theJacksonSupport) {
        super(theEntityClass, theJacksonSupport);
    }

    /**
     * @return {@link Boolean}
     * @throws Exception se la richiesta non parte
     */
    @Override
    public Boolean isUpElasticSearchCluster() throws Exception {
        return this.elasticSearchClient.ping().value();
    }

    /**
     * @return {@link GPElasticSearchRestInfo}
     * @throws Exception se la richiesta fallisce
     */
    @Override
    public GPElasticSearchRestInfo info() throws Exception {
        return GPElasticSearchRestInfo.of(this.elasticSearchClient.info());
    }

    /**
     * @return {@link ElasticsearchClient}
     */
    @Override
    public ElasticsearchClient client() {
        return this.elasticSearchClient;
    }

    /**
     * @return {@link ElasticsearchAsyncClient}
     */
    @Override
    public ElasticsearchAsyncClient asyncClient() {
        return this.elasticSearchAsyncClient;
    }

    /**
     * @return {@link JsonpMapper}
     */
    @Override
    protected JsonpMapper jsonpMapper() {
        return this.elasticSearchClient._jsonpMapper();
    }

    /**
     * Controlla che Spring abbia iniettato i client.
     *
     * @throws Exception se manca un client
     */
    @PostConstruct
    protected void onStartUp() throws Exception {
        checkArgument(this.elasticSearchClient != null, "Manca il bean elasticSearchClient.");
        checkArgument(this.elasticSearchAsyncClient != null, "Manca il bean elasticSearchAsyncClient.");
    }
}
