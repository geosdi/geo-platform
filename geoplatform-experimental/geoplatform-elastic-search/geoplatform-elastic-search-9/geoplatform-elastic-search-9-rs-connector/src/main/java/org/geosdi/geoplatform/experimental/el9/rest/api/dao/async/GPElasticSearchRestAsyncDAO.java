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
import org.geosdi.geoplatform.experimental.el.api.function.GPElasticSearchCheck;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.api.response.IGPUpdateResponse;
import org.geosdi.geoplatform.experimental.el9.rest.api.dao.find.GPElasticSearchRestFindDAO;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static javax.annotation.meta.When.NEVER;

/**
 * Scritture senza attesa, con il client asincrono: al posto degli {@code ActionListener} della 7 restituiscono un
 * {@link CompletableFuture}.
 *
 * @param <D> il tipo dei documenti
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPElasticSearchRestAsyncDAO<D extends Document> extends GPElasticSearchRestFindDAO<D> {

    /**
     * @param document il documento; se non ha l'id lo sceglie ElasticSearch
     * @return {@link CompletableFuture} lo stesso documento, con l'id
     * @throws Exception se la richiesta non parte
     */
    CompletableFuture<D> persistAsync(@Nonnull(when = NEVER) D document) throws Exception;

    /**
     * @param document il documento, con l'id
     * @return {@link CompletableFuture}
     * @throws Exception se la richiesta non parte
     */
    CompletableFuture<IGPUpdateResponse> updateAsync(@Nonnull(when = NEVER) D document) throws Exception;

    /**
     * @param theID         l'id
     * @param theProperties i campi da cambiare, con la struttura del JSON del documento: con il nome radice vanno dentro il suo oggetto, per esempio {@code {"place": {"population": 10}}}
     * @return {@link CompletableFuture}
     * @throws Exception se la richiesta non parte
     */
    CompletableFuture<IGPUpdateResponse> updateAsync(@Nonnull(when = NEVER) String theID, @Nonnull(when = NEVER) Map<String, Object> theProperties) throws Exception;

    /**
     * @param theValue il valore da cui costruire la richiesta
     * @param theCheck la funzione che costruisce la richiesta, con l'indice
     * @param <V>      il tipo del valore
     * @return {@link CompletableFuture}
     * @throws Exception se la richiesta non si costruisce
     */
    <V> CompletableFuture<UpdateByQueryResponse> updateByQueryAsync(@Nonnull(when = NEVER) V theValue,
            @Nonnull(when = NEVER) GPElasticSearchCheck<UpdateByQueryRequest, V, Exception> theCheck) throws Exception;

    /**
     * @param theValue il valore da cui costruire la richiesta
     * @param theCheck la funzione che costruisce la richiesta, con l'indice
     * @param <V>      il tipo del valore
     * @return {@link CompletableFuture}
     * @throws Exception se la richiesta non si costruisce
     */
    <V> CompletableFuture<DeleteByQueryResponse> deleteByQueryAsync(@Nonnull(when = NEVER) V theValue,
            @Nonnull(when = NEVER) GPElasticSearchCheck<DeleteByQueryRequest, V, Exception> theCheck) throws Exception;

    /**
     * @return {@link CompletableFuture} il numero dei documenti dell'indice
     * @throws Exception se la richiesta non parte
     */
    CompletableFuture<Long> countAsync() throws Exception;
}
