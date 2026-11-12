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
package org.geosdi.geoplatform.experimental.el9.rest.api.dao.async;

import co.elastic.clients.elasticsearch.core.DeleteByQueryRequest;
import co.elastic.clients.elasticsearch.core.DeleteByQueryResponse;
import co.elastic.clients.elasticsearch.core.UpdateByQueryRequest;
import co.elastic.clients.elasticsearch.core.UpdateByQueryResponse;
import co.elastic.clients.elasticsearch.core.UpdateRequest;
import co.elastic.clients.elasticsearch.core.UpdateResponse;
import co.elastic.clients.json.JsonData;
import org.geosdi.geoplatform.experimental.el.api.function.GPElasticSearchCheck;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.api.response.IGPUpdateResponse;
import org.geosdi.geoplatform.experimental.el9.rest.api.dao.find.ElasticSearchRestFindDAO;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import tools.jackson.databind.json.JsonMapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static co.elastic.clients.elasticsearch._types.Result.Created;
import static co.elastic.clients.elasticsearch._types.Result.NoOp;
import static co.elastic.clients.elasticsearch._types.Result.Updated;
import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.TRUE;
import static javax.annotation.meta.When.NEVER;

/**
 * Implementazione di {@link GPElasticSearchRestAsyncDAO}.
 *
 * @param <D> il tipo dei documenti
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class ElasticSearchRestAsyncDAO<D extends Document> extends ElasticSearchRestFindDAO<D> implements GPElasticSearchRestAsyncDAO<D> {

    /**
     * @param theEntityClass    la classe dei documenti
     * @param theJacksonSupport il mapper dei documenti; null vuol dire quello di default
     */
    protected ElasticSearchRestAsyncDAO(@Nonnull(when = NEVER) Class<D> theEntityClass, @Nullable JacksonSupport<JsonMapper> theJacksonSupport) {
        super(theEntityClass, theJacksonSupport);
    }

    /**
     * @param document il documento
     * @return {@link CompletableFuture}
     * @throws Exception se la richiesta non parte
     */
    @Override
    public CompletableFuture<D> persistAsync(@Nonnull(when = NEVER) D document) throws Exception {
        checkArgument(document != null, "Il parametro document non deve essere null.");
        return this.elasticSearchAsyncClient.index(this.prepareIndexRequest(document)).thenApply(response -> {
            if (response.result() != Created) {
                throw new IllegalStateException("Documento non creato, risultato: " + response.result());
            }
            document.setId(response.id());
            return document;
        });
    }

    /**
     * @param document il documento, con l'id
     * @return {@link CompletableFuture}
     * @throws Exception se la richiesta non parte
     */
    @Override
    public CompletableFuture<IGPUpdateResponse> updateAsync(@Nonnull(when = NEVER) D document) throws Exception {
        checkArgument((document != null) && (document.isIdSetted()), "Il documento da aggiornare non deve essere null e deve avere l'id.");
        return this.elasticSearchAsyncClient.update(this.createUpdateRequest(document.getId(), this.writeDocument(document)), JsonData.class)
                .thenApply(this::toUpdateResponse);
    }

    /**
     * @param theID         l'id
     * @param theProperties i campi da cambiare
     * @return {@link CompletableFuture}
     * @throws Exception se la richiesta non parte
     */
    @Override
    public CompletableFuture<IGPUpdateResponse> updateAsync(@Nonnull(when = NEVER) String theID, @Nonnull(when = NEVER) Map<String, Object> theProperties) throws Exception {
        checkArgument((theID != null) && !(theID.trim().isEmpty()), "Il parametro id non deve essere null né vuoto.");
        checkArgument((theProperties != null) && !(theProperties.isEmpty()), "Il parametro properties non deve essere null né vuoto.");
        return this.elasticSearchAsyncClient.update(this.createUpdateRequest(theID, JsonData.of(theProperties)), JsonData.class)
                .thenApply(this::toUpdateResponse);
    }

    /**
     * @param theValue il valore
     * @param theCheck la funzione che costruisce la richiesta
     * @param <V>      il tipo del valore
     * @return {@link CompletableFuture}
     * @throws Exception se la richiesta non si costruisce
     */
    @Override
    public <V> CompletableFuture<UpdateByQueryResponse> updateByQueryAsync(@Nonnull(when = NEVER) V theValue,
            @Nonnull(when = NEVER) GPElasticSearchCheck<UpdateByQueryRequest, V, Exception> theCheck) throws Exception {
        checkArgument(theValue != null, "Il parametro value non deve essere null.");
        checkArgument(theCheck != null, "Il parametro check non deve essere null.");
        return this.elasticSearchAsyncClient.updateByQuery(theCheck.apply(theValue));
    }

    /**
     * @param theValue il valore
     * @param theCheck la funzione che costruisce la richiesta
     * @param <V>      il tipo del valore
     * @return {@link CompletableFuture}
     * @throws Exception se la richiesta non si costruisce
     */
    @Override
    public <V> CompletableFuture<DeleteByQueryResponse> deleteByQueryAsync(@Nonnull(when = NEVER) V theValue,
            @Nonnull(when = NEVER) GPElasticSearchCheck<DeleteByQueryRequest, V, Exception> theCheck) throws Exception {
        checkArgument(theValue != null, "Il parametro value non deve essere null.");
        checkArgument(theCheck != null, "Il parametro check non deve essere null.");
        return this.elasticSearchAsyncClient.deleteByQuery(theCheck.apply(theValue));
    }

    /**
     * @return {@link CompletableFuture}
     * @throws Exception se la richiesta non parte
     */
    @Override
    public CompletableFuture<Long> countAsync() throws Exception {
        return this.elasticSearchAsyncClient.count(c -> c.index(this.getIndexName())).thenApply(response -> response.count());
    }

    /**
     * Con il nome radice l'id va dentro il suo oggetto ({@code {"place": {"id": ...}}}): una chiave
     * {@code "place.id"}, come faceva il connettore per ElasticSearch 7, finirebbe nel sorgente accanto a
     * {@code "place"} e il documento non si leggerebbe più.
     *
     * @param theDocument il documento, con l'id
     * @throws Exception se la richiesta non parte
     */
    @Override
    protected final void updateDocumentIDAsync(@Nonnull(when = NEVER) D theDocument) throws Exception {
        checkArgument(theDocument != null, "Il parametro document non deve essere null.");
        String rootName = this.getJsonRootName();
        Map<String, Object> properties = (rootName.isEmpty() ? Map.of("id", theDocument.getId())
                : Map.of(rootName.substring(0, rootName.length() - 1), Map.of("id", theDocument.getId())));
        this.updateAsync(theDocument.getId(), properties)
                .exceptionally(ex -> {
                    logger.error("@@@@@@@@@@@@@@@@@@@@@@{} - id non scritto nel documento {}: {}", getClass().getSimpleName(), theDocument.getId(), ex.toString());
                    return null;
                });
    }

    /**
     * @param theID  l'id
     * @param theDoc il documento parziale
     * @return {@link UpdateRequest}
     */
    protected UpdateRequest<JsonData, JsonData> createUpdateRequest(@Nonnull(when = NEVER) String theID, @Nonnull(when = NEVER) JsonData theDoc) {
        checkArgument(theID != null, "Il parametro id non deve essere null.");
        checkArgument(theDoc != null, "Il parametro doc non deve essere null.");
        return UpdateRequest.of(u -> u.index(this.getIndexName()).id(theID).doc(theDoc));
    }

    /**
     * @param theResponse la risposta di ElasticSearch
     * @return {@link IGPUpdateResponse} true e la versione nuova
     */
    protected IGPUpdateResponse toUpdateResponse(@Nonnull(when = NEVER) UpdateResponse<JsonData> theResponse) {
        checkArgument(theResponse != null, "Il parametro response non deve essere null.");
        if ((theResponse.result() != Updated) && (theResponse.result() != NoOp)) {
            throw new IllegalStateException("Documento non aggiornato, risultato: " + theResponse.result());
        }
        try {
            return IGPUpdateResponse.of(TRUE, theResponse.version());
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }
}
