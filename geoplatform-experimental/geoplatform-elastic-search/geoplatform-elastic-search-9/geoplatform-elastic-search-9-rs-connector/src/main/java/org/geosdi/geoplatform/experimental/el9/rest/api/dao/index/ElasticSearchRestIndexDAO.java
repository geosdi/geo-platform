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
package org.geosdi.geoplatform.experimental.el9.rest.api.dao.index;

import co.elastic.clients.elasticsearch._types.mapping.TypeMapping;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.json.JsonData;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el9.rest.api.dao.mapping.ElasticSearchRestMappingDAO;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import tools.jackson.databind.json.JsonMapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

import static co.elastic.clients.elasticsearch._types.OpType.Create;
import static co.elastic.clients.elasticsearch._types.OpType.Index;
import static co.elastic.clients.elasticsearch._types.Result.Created;
import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.util.concurrent.CompletableFuture.completedFuture;
import static javax.annotation.meta.When.NEVER;
import static org.springframework.core.env.Profiles.of;

/**
 * Implementazione di {@link GPElasticSearchRestIndexDAO}, con la scrittura dei documenti.
 * <p>Come nel connettore per ElasticSearch 7, con il profilo Spring {@code prod} attivo il DAO crea l'indice
 * all'avvio, o ne aggiorna il mapping se c'è già.</p>
 *
 * @param <D> il tipo dei documenti
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class ElasticSearchRestIndexDAO<D extends Document> extends ElasticSearchRestMappingDAO<D> implements GPElasticSearchRestIndexDAO<D> {

    /**
     * @param theEntityClass    la classe dei documenti
     * @param theJacksonSupport il mapper dei documenti; null vuol dire quello di default
     */
    protected ElasticSearchRestIndexDAO(@Nonnull(when = NEVER) Class<D> theEntityClass, @Nullable JacksonSupport<JsonMapper> theJacksonSupport) {
        super(theEntityClass, theJacksonSupport);
    }

    /**
     * @return {@link Boolean}
     * @throws Exception se la richiesta fallisce
     */
    @Override
    public Boolean createIndex() throws Exception {
        if (this.existIndex()) {
            logger.debug("#####################{} - l'indice {} c'è già.", getClass().getSimpleName(), this.getIndexName());
            return FALSE;
        }
        return this.elasticSearchClient.indices().create(this.createIndexRequest()).acknowledged();
    }

    /**
     * @return {@link CompletableFuture}
     * @throws Exception se la richiesta non parte
     */
    @Override
    public CompletableFuture<Boolean> createIndexAsync() throws Exception {
        if (this.existIndex()) {
            return completedFuture(FALSE);
        }
        return this.elasticSearchAsyncClient.indices().create(this.createIndexRequest()).thenApply(response -> response.acknowledged());
    }

    /**
     * @return {@link Boolean}
     * @throws Exception se la richiesta fallisce
     */
    @Override
    public Boolean deleteIndex() throws Exception {
        return this.elasticSearchClient.indices().delete(d -> d.index(this.getIndexName())).acknowledged();
    }

    /**
     * @return {@link CompletableFuture}
     * @throws Exception se la richiesta non parte
     */
    @Override
    public CompletableFuture<Boolean> deleteIndexAsync() throws Exception {
        return this.elasticSearchAsyncClient.indices().delete(d -> d.index(this.getIndexName())).thenApply(response -> response.acknowledged());
    }

    /**
     * @return {@link Boolean}
     * @throws Exception se la richiesta fallisce
     */
    @Override
    public Boolean existIndex() throws Exception {
        return this.elasticSearchClient.indices().exists(e -> e.index(this.getIndexName())).value();
    }

    /**
     * @throws Exception se la richiesta fallisce
     */
    @Override
    public void refreshIndex() throws Exception {
        this.elasticSearchClient.indices().refresh(r -> r.index(this.getIndexName()));
    }

    /**
     * @return {@link CompletableFuture}
     * @throws Exception se la richiesta non parte
     */
    @Override
    public CompletableFuture<Void> refreshIndexAsync() throws Exception {
        return this.elasticSearchAsyncClient.indices().refresh(r -> r.index(this.getIndexName())).thenAccept(response -> {
        });
    }

    /**
     * @return {@link CreateIndexRequest} con il mapping, se il documento lo chiede e il DAO lo dà
     * @throws Exception se il mapping non si costruisce
     */
    protected CreateIndexRequest createIndexRequest() throws Exception {
        TypeMapping mapping = (this.isCreateMapping() ? this.preparePutMapping() : null);
        return CreateIndexRequest.of(c -> {
            c.index(this.getIndexName());
            if (mapping != null) {
                c.mappings(mapping);
            }
            return c;
        });
    }

    /**
     * Con l'id la richiesta è {@code op_type=create}: se il documento c'è già ElasticSearch risponde 409 e non lo
     * tocca. Il connettore per ElasticSearch 7 usava {@code index}, che prima sovrascriveva il documento e poi
     * faceva fallire il persist.
     *
     * @param theDocument il documento; se non ha l'id lo sceglie ElasticSearch
     * @return {@link IndexRequest}
     * @throws Exception se il documento non si scrive
     */
    protected IndexRequest<JsonData> prepareIndexRequest(@Nonnull(when = NEVER) D theDocument) throws Exception {
        checkArgument(theDocument != null, "Il parametro document non deve essere null.");
        JsonData source = this.writeDocument(theDocument);
        return IndexRequest.of(i -> i.index(this.getIndexName()).id(theDocument.isIdSetted() ? theDocument.getId() : null)
                .document(source).opType(theDocument.isIdSetted() ? Create : Index));
    }

    /**
     * Nella bulk, come nella 7, un documento con l'id di uno che c'è già lo sostituisce.
     *
     * @param theDocument il documento; se non ha l'id lo sceglie ElasticSearch
     * @return {@link BulkOperation} l'operazione di indicizzazione per una bulk
     */
    protected BulkOperation prepareIndexOperation(@Nonnull(when = NEVER) D theDocument) {
        checkArgument(theDocument != null, "Il parametro document non deve essere null.");
        try {
            JsonData source = this.writeDocument(theDocument);
            return BulkOperation.of(b -> b.index(i -> i.index(this.getIndexName())
                    .id(theDocument.isIdSetted() ? theDocument.getId() : null).document(source)));
        } catch (Exception ex) {
            throw new IllegalStateException("Documento non scrivibile: " + theDocument, ex);
        }
    }

    /**
     * @param theDocument il documento, con l'id
     * @return {@link BulkOperation} l'operazione di aggiornamento per una bulk
     */
    protected BulkOperation prepareUpdateOperation(@Nonnull(when = NEVER) D theDocument) {
        checkArgument((theDocument != null) && (theDocument.isIdSetted()), "Il documento da aggiornare non deve essere null e deve avere l'id.");
        try {
            JsonData source = this.writeDocument(theDocument);
            return BulkOperation.of(b -> b.update(u -> u.index(this.getIndexName()).id(theDocument.getId()).action(a -> a.doc(source))));
        } catch (Exception ex) {
            throw new IllegalStateException("Documento non scrivibile: " + theDocument, ex);
        }
    }

    /**
     * Crea il documento. Se ha già l'id e quell'id c'è già nell'indice, fallisce senza toccare il documento
     * esistente; se non ha l'id, quello scelto da ElasticSearch va nel documento e poi nel suo JSON.
     *
     * @param theDocument il documento
     * @return {@link D} lo stesso documento, con l'id
     * @throws Exception se ElasticSearch non lo crea, per esempio per un id già usato
     */
    protected D index(@Nonnull(when = NEVER) D theDocument) throws Exception {
        checkArgument(theDocument != null, "Il parametro document non deve essere null.");
        boolean withID = theDocument.isIdSetted();
        IndexResponse response = this.elasticSearchClient.index(this.prepareIndexRequest(theDocument));
        if (response.result() != Created) {
            throw new IllegalStateException("Documento non creato, risultato: " + response.result());
        }
        if (!withID) {
            theDocument.setId(response.id());
            this.updateDocumentIDAsync(theDocument);
        }
        return theDocument;
    }

    /**
     * Con il profilo {@code prod} crea l'indice o ne aggiorna il mapping.
     *
     * @throws Exception se il cluster risponde con un errore
     */
    @Override
    protected void onStartUp() throws Exception {
        super.onStartUp();
        if (!this.env.acceptsProfiles(of("prod"))) {
            logger.warn("#######################Il profilo prod non è attivo: {} non crea l'indice {}.", getClass().getSimpleName(), this.getIndexName());
            return;
        }
        if (!this.isUpElasticSearchCluster()) {
            logger.warn("#######################ElasticSearch non risponde: {} non crea l'indice {}.", getClass().getSimpleName(), this.getIndexName());
            return;
        }
        if (!this.createIndex() && this.isCreateMapping()) {
            TypeMapping mapping = this.preparePutMapping();
            if (mapping != null) {
                logger.debug("#######################{} - aggiornamento del mapping dell'indice {}: {}", getClass().getSimpleName(), this.getIndexName(), this.putMapping(mapping));
            }
        }
    }

    /**
     * Scrive l'id scelto da ElasticSearch anche nel JSON del documento, senza aspettare.
     *
     * @param theDocument il documento, con l'id
     * @throws Exception se la richiesta non parte
     */
    protected abstract void updateDocumentIDAsync(@Nonnull(when = NEVER) D theDocument) throws Exception;
}
