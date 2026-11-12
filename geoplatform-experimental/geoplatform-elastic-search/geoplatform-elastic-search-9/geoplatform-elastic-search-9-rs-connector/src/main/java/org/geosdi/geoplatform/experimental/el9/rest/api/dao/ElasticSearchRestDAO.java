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
package org.geosdi.geoplatform.experimental.el9.rest.api.dao;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.DeleteByQueryRequest;
import co.elastic.clients.elasticsearch.core.DeleteByQueryResponse;
import co.elastic.clients.elasticsearch.core.DeleteResponse;
import co.elastic.clients.elasticsearch.core.UpdateByQueryRequest;
import co.elastic.clients.elasticsearch.core.UpdateByQueryResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.bulk.BulkResponseItem;
import co.elastic.clients.json.JsonData;
import org.geosdi.geoplatform.experimental.el.api.function.GPElasticSearchCheck;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.api.response.IGPUpdateResponse;
import org.geosdi.geoplatform.experimental.el9.rest.api.dao.async.ElasticSearchRestAsyncDAO;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import tools.jackson.databind.json.JsonMapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.StreamSupport;

import static co.elastic.clients.elasticsearch._types.Result.Deleted;
import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.stream.Collectors.joining;
import static javax.annotation.meta.When.NEVER;

/**
 * Implementazione di {@link GPElasticSearchRestDAO}: la classe da estendere per un DAO di ElasticSearch 9.
 * <p>Il DAO concreto passa al costruttore la classe dei documenti e il mapper di geoplatform-jackson-support, e
 * implementa {@link #preparePutMapping()}.</p>
 *
 * @param <D> il tipo dei documenti
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class ElasticSearchRestDAO<D extends Document> extends ElasticSearchRestAsyncDAO<D> implements GPElasticSearchRestDAO<D> {

    /**
     * @param theEntityClass    la classe dei documenti
     * @param theJacksonSupport il mapper dei documenti; null vuol dire quello di default
     */
    protected ElasticSearchRestDAO(@Nonnull(when = NEVER) Class<D> theEntityClass, @Nullable JacksonSupport<JsonMapper> theJacksonSupport) {
        super(theEntityClass, theJacksonSupport);
    }

    /**
     * @param document il documento
     * @return {@link D}
     * @throws Exception se ElasticSearch non lo crea
     */
    @Override
    public D persist(@Nonnull(when = NEVER) D document) throws Exception {
        checkArgument(document != null, "Il parametro document non deve essere null.");
        logger.debug("############################{} - inserisco {}\n", getClass().getSimpleName(), document);
        return this.index(document);
    }

    /**
     * @param documents i documenti
     * @return {@link BulkResponse}
     * @throws Exception se almeno un documento non è stato scritto
     */
    @Override
    public BulkResponse persist(@Nonnull(when = NEVER) Iterable<D> documents) throws Exception {
        return this.bulk(documents, this::prepareIndexOperation);
    }

    /**
     * @param document il documento, con l'id
     * @return {@link IGPUpdateResponse}
     * @throws Exception se l'aggiornamento fallisce
     */
    @Override
    public IGPUpdateResponse update(@Nonnull(when = NEVER) D document) throws Exception {
        checkArgument((document != null) && (document.isIdSetted()), "Il documento da aggiornare non deve essere null e deve avere l'id.");
        logger.debug("##################################{} - aggiorno {}\n", getClass().getSimpleName(), document);
        return this.toUpdateResponse(this.elasticSearchClient.update(this.createUpdateRequest(document.getId(), this.writeDocument(document)), JsonData.class));
    }

    /**
     * @param theID         l'id
     * @param theProperties i campi da cambiare
     * @return {@link IGPUpdateResponse}
     * @throws Exception se l'aggiornamento fallisce
     */
    @Override
    public IGPUpdateResponse update(@Nonnull(when = NEVER) String theID, @Nonnull(when = NEVER) Map<String, Object> theProperties) throws Exception {
        checkArgument((theID != null) && !(theID.trim().isEmpty()), "Il parametro id non deve essere null né vuoto.");
        checkArgument((theProperties != null) && !(theProperties.isEmpty()), "Il parametro properties non deve essere null né vuoto.");
        return this.toUpdateResponse(this.elasticSearchClient.update(this.createUpdateRequest(theID, JsonData.of(theProperties)), JsonData.class));
    }

    /**
     * @param documents i documenti, con l'id
     * @return {@link BulkResponse}
     * @throws Exception se almeno un documento non è stato aggiornato
     */
    @Override
    public BulkResponse update(@Nonnull(when = NEVER) Iterable<D> documents) throws Exception {
        return this.bulk(documents, this::prepareUpdateOperation);
    }

    /**
     * @param theValue il valore
     * @param theCheck la funzione che costruisce la richiesta
     * @param <V>      il tipo del valore
     * @return {@link UpdateByQueryResponse}
     * @throws Exception se la richiesta fallisce
     */
    @Override
    public <V> UpdateByQueryResponse updateByQuery(@Nonnull(when = NEVER) V theValue,
            @Nonnull(when = NEVER) GPElasticSearchCheck<UpdateByQueryRequest, V, Exception> theCheck) throws Exception {
        checkArgument(theValue != null, "Il parametro value non deve essere null.");
        checkArgument(theCheck != null, "Il parametro check non deve essere null.");
        return this.elasticSearchClient.updateByQuery(theCheck.apply(theValue));
    }

    /**
     * @param theID l'id
     * @return {@link Boolean}
     * @throws Exception se il documento non c'è o la richiesta fallisce
     */
    @Override
    public Boolean delete(@Nonnull(when = NEVER) String theID) throws Exception {
        checkArgument((theID != null) && !(theID.trim().isEmpty()), "Il parametro id non deve essere null né vuoto.");
        DeleteResponse response = this.elasticSearchClient.delete(d -> d.index(this.getIndexName()).id(theID));
        if (response.result() != Deleted) {
            throw new IllegalStateException("Documento " + theID + " non cancellato, risultato: " + response.result());
        }
        return TRUE;
    }

    /**
     * @param theValue il valore
     * @param theCheck la funzione che costruisce la richiesta
     * @param <V>      il tipo del valore
     * @return {@link DeleteByQueryResponse}
     * @throws Exception se la richiesta fallisce
     */
    @Override
    public <V> DeleteByQueryResponse deleteByQuery(@Nonnull(when = NEVER) V theValue,
            @Nonnull(when = NEVER) GPElasticSearchCheck<DeleteByQueryRequest, V, Exception> theCheck) throws Exception {
        checkArgument(theValue != null, "Il parametro value non deve essere null.");
        checkArgument(theCheck != null, "Il parametro check non deve essere null.");
        return this.elasticSearchClient.deleteByQuery(theCheck.apply(theValue));
    }

    /**
     * @param theQuery la query
     * @return {@link DeleteByQueryResponse}
     * @throws Exception se la richiesta fallisce
     */
    @Override
    public DeleteByQueryResponse deleteByQuery(@Nonnull(when = NEVER) Query theQuery) throws Exception {
        checkArgument(theQuery != null, "Il parametro query non deve essere null.");
        return this.elasticSearchClient.deleteByQuery(d -> d.index(this.getIndexName()).query(theQuery));
    }

    /**
     * @return {@link Long}
     * @throws Exception se il conteggio fallisce
     */
    @Override
    public Long count() throws Exception {
        return this.elasticSearchClient.count(c -> c.index(this.getIndexName())).count();
    }

    /**
     * @param theQuery la query
     * @return {@link Long}
     * @throws Exception se il conteggio fallisce
     */
    @Override
    public Long count(@Nonnull(when = NEVER) Query theQuery) throws Exception {
        checkArgument(theQuery != null, "Il parametro query non deve essere null.");
        return this.elasticSearchClient.count(c -> c.index(this.getIndexName()).query(theQuery)).count();
    }

    /**
     * @param theValue il valore
     * @param theCheck la funzione che costruisce la query
     * @param <V>      il tipo del valore
     * @return {@link Long}
     * @throws Exception se il conteggio fallisce
     */
    @Override
    public <V> Long count(@Nonnull(when = NEVER) V theValue, @Nonnull(when = NEVER) GPElasticSearchCheck<Query, V, Exception> theCheck) throws Exception {
        checkArgument(theValue != null, "Il parametro value non deve essere null.");
        checkArgument(theCheck != null, "Il parametro check non deve essere null.");
        return this.count(theCheck.apply(theValue));
    }

    /**
     * @param theDocuments  i documenti, almeno uno
     * @param theOperation  l'operazione per ogni documento
     * @return {@link BulkResponse}
     * @throws Exception se almeno un'operazione è fallita
     */
    protected BulkResponse bulk(@Nonnull(when = NEVER) Iterable<D> theDocuments, @Nonnull(when = NEVER) Function<D, BulkOperation> theOperation) throws Exception {
        checkArgument(theDocuments != null, "Il parametro documents non deve essere null.");
        List<BulkOperation> operations = StreamSupport.stream(theDocuments.spliterator(), FALSE)
                .filter(Objects::nonNull)
                .map(theOperation)
                .toList();
        checkArgument(!operations.isEmpty(), "Il parametro documents deve contenere almeno un documento.");
        BulkResponse response = this.elasticSearchClient.bulk(BulkRequest.of(b -> b.operations(operations)));
        if (response.errors()) {
            throw new IllegalStateException("Operazioni bulk fallite: " + response.items().stream()
                    .filter(item -> item.error() != null)
                    .map(ElasticSearchRestDAO::describeError)
                    .collect(joining("; ")));
        }
        return response;
    }

    /**
     * @param theItem l'operazione fallita
     * @return {@link String} id, tipo e motivo dell'errore
     */
    private static String describeError(@Nonnull(when = NEVER) BulkResponseItem theItem) {
        return "[" + theItem.id() + "] " + theItem.error().type() + ": " + theItem.error().reason();
    }
}
