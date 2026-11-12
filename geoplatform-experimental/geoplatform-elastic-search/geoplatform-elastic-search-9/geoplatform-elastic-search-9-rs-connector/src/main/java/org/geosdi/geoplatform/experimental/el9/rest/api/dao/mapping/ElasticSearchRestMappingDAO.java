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
package org.geosdi.geoplatform.experimental.el9.rest.api.dao.mapping;

import co.elastic.clients.elasticsearch._types.mapping.TypeMapping;
import co.elastic.clients.elasticsearch.indices.PutMappingRequest;
import co.elastic.clients.json.JsonData;
import co.elastic.clients.json.JsonpUtils;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el9.rest.api.dao.base.ElasticSearchRestBaseDAO;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import tools.jackson.databind.json.JsonMapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.StringReader;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * Implementazione di {@link GPElasticSearchRestMappingDAO}.
 *
 * @param <D> il tipo dei documenti
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class ElasticSearchRestMappingDAO<D extends Document> extends ElasticSearchRestBaseDAO<D> implements GPElasticSearchRestMappingDAO<D> {

    /**
     * @param theEntityClass    la classe dei documenti
     * @param theJacksonSupport il mapper dei documenti; null vuol dire quello di default
     */
    protected ElasticSearchRestMappingDAO(@Nonnull(when = NEVER) Class<D> theEntityClass, @Nullable JacksonSupport<JsonMapper> theJacksonSupport) {
        super(theEntityClass, theJacksonSupport);
    }

    /**
     * @param theMapping il mapping
     * @return {@link Boolean}
     * @throws Exception se la richiesta fallisce
     */
    @Override
    public Boolean putMapping(@Nonnull(when = NEVER) TypeMapping theMapping) throws Exception {
        checkArgument(theMapping != null, "Il parametro mapping non deve essere null.");
        return this.elasticSearchClient.indices().putMapping(this.createPutMappingRequest(theMapping)).acknowledged();
    }

    /**
     * @param theMapping il mapping
     * @return {@link CompletableFuture}
     * @throws Exception se la richiesta non parte
     */
    @Override
    public CompletableFuture<Boolean> putMappingAsync(@Nonnull(when = NEVER) TypeMapping theMapping) throws Exception {
        checkArgument(theMapping != null, "Il parametro mapping non deve essere null.");
        return this.elasticSearchAsyncClient.indices().putMapping(this.createPutMappingRequest(theMapping))
                .thenApply(response -> response.acknowledged());
    }

    /**
     * @return {@link TypeMapping}
     * @throws Exception se la richiesta fallisce
     */
    @Override
    public TypeMapping getMapping() throws Exception {
        return this.elasticSearchClient.indices().getMapping(g -> g.index(this.getIndexName())).get(this.getIndexName()).mappings();
    }

    /**
     * @return {@link CompletableFuture}
     * @throws Exception se la richiesta non parte
     */
    @Override
    public CompletableFuture<TypeMapping> getMappingAsync() throws Exception {
        return this.elasticSearchAsyncClient.indices().getMapping(g -> g.index(this.getIndexName()))
                .thenApply(response -> response.get(this.getIndexName()).mappings());
    }

    /**
     * @return {@link Map}
     * @throws Exception se la richiesta fallisce
     */
    @Override
    public Map<String, Object> loadMappingAsMap() throws Exception {
        return JsonData.fromJson(this.loadMappingAsString()).to(Map.class, this.jsonpMapper());
    }

    /**
     * @return {@link String}
     * @throws Exception se la richiesta fallisce
     */
    @Override
    public String loadMappingAsString() throws Exception {
        return JsonpUtils.toJsonString(this.getMapping(), this.jsonpMapper());
    }

    /**
     * Il mapping passa come JSON, così vale tutto quello che c'è nel {@link TypeMapping}.
     *
     * @param theMapping il mapping
     * @return {@link PutMappingRequest}
     */
    protected PutMappingRequest createPutMappingRequest(@Nonnull(when = NEVER) TypeMapping theMapping) {
        checkArgument(theMapping != null, "Il parametro mapping non deve essere null.");
        String json = JsonpUtils.toJsonString(theMapping, this.jsonpMapper());
        return PutMappingRequest.of(p -> p.index(this.getIndexName()).withJson(new StringReader(json)));
    }

    /**
     * Il mapping dei documenti, usato alla creazione dell'indice; null vuol dire nessun mapping (lo decide
     * ElasticSearch dai documenti).
     * <p>Si può costruire con i builder del client o da JSON:
     * {@code TypeMapping.of(m -> m.withJson(new StringReader(json)))}.</p>
     *
     * @return {@link TypeMapping}
     * @throws Exception se il mapping non si costruisce
     */
    @Nullable
    protected abstract TypeMapping preparePutMapping() throws Exception;
}
