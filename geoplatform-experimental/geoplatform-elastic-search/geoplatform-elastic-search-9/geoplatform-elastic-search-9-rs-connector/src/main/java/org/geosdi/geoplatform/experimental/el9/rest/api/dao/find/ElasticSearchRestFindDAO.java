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
package org.geosdi.geoplatform.experimental.el9.rest.api.dao.find;

import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.MgetRequest;
import co.elastic.clients.elasticsearch.core.MgetResponse;
import co.elastic.clients.elasticsearch.core.mget.MultiGetResponseItem;
import co.elastic.clients.json.JsonData;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.condition.PredicateCondition;
import org.geosdi.geoplatform.experimental.el9.rest.api.dao.page.scroll.PageableElasticSearchRestScrollDAO;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import tools.jackson.databind.json.JsonMapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

import static co.elastic.clients.elasticsearch._types.SortOrder.Desc;
import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Arrays.asList;
import static javax.annotation.meta.When.NEVER;

/**
 * Implementazione di {@link GPElasticSearchRestFindDAO}.
 *
 * @param <D> il tipo dei documenti
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class ElasticSearchRestFindDAO<D extends Document> extends PageableElasticSearchRestScrollDAO<D> implements GPElasticSearchRestFindDAO<D> {

    /**
     * @param theEntityClass    la classe dei documenti
     * @param theJacksonSupport il mapper dei documenti; null vuol dire quello di default
     */
    protected ElasticSearchRestFindDAO(@Nonnull(when = NEVER) Class<D> theEntityClass, @Nullable JacksonSupport<JsonMapper> theJacksonSupport) {
        super(theEntityClass, theJacksonSupport);
    }

    /**
     * @param theDocumentID l'id
     * @return {@link D}
     * @throws Exception se la richiesta fallisce
     */
    @Override
    public D findByID(@Nonnull(when = NEVER) String theDocumentID) throws Exception {
        return this.findByID(theDocumentID, null, null);
    }

    /**
     * @param theDocumentID l'id
     * @param includeFields i campi da restituire; null vuol dire tutti
     * @param excludeFields i campi da togliere; null vuol dire nessuno
     * @return {@link D}
     * @throws Exception se la richiesta fallisce
     */
    @Override
    public D findByID(@Nonnull(when = NEVER) String theDocumentID, @Nullable String[] includeFields, @Nullable String[] excludeFields) throws Exception {
        checkArgument((theDocumentID != null) && !(theDocumentID.trim().isEmpty()), "Il parametro documentID non deve essere null né vuoto.");
        GetResponse<JsonData> response = this.elasticSearchClient.get(g -> {
            g.index(this.getIndexName()).id(theDocumentID).realtime(TRUE).refresh(TRUE);
            if (includeFields != null) {
                g.sourceIncludes(asList(includeFields));
            }
            if (excludeFields != null) {
                g.sourceExcludes(asList(excludeFields));
            }
            return g;
        }, JsonData.class);
        return (response.found() ? this.readDocument(response.id(), response.source()) : null);
    }

    /**
     * @return {@link List}
     * @throws Exception se la ricerca fallisce
     */
    @Override
    public List<D> findLasts() throws Exception {
        return this.find(new Page(0, 10)).getResults();
    }

    /**
     * @param ids gli id
     * @return {@link List}
     * @throws Exception se la richiesta fallisce
     */
    @Override
    public List<D> findByIDS(@Nonnull(when = NEVER) Iterable<String> ids) throws Exception {
        return this.findByIDS(ids, null, null, null);
    }

    /**
     * @param ids       gli id
     * @param condition il filtro; null vuol dire nessun filtro
     * @return {@link List}
     * @throws Exception se la richiesta fallisce
     */
    @Override
    public List<D> findByIDS(@Nonnull(when = NEVER) Iterable<String> ids, @Nullable PredicateCondition<D> condition) throws Exception {
        return this.findByIDS(ids, condition, null, null);
    }

    /**
     * @param ids           gli id
     * @param includeFields i campi da restituire; null vuol dire tutti
     * @param excludeFields i campi da togliere; null vuol dire nessuno
     * @return {@link List}
     * @throws Exception se la richiesta fallisce
     */
    @Override
    public List<D> findByIDS(@Nonnull(when = NEVER) Iterable<String> ids, @Nullable String[] includeFields, @Nullable String[] excludeFields) throws Exception {
        return this.findByIDS(ids, null, includeFields, excludeFields);
    }

    /**
     * @param ids           gli id
     * @param condition     il filtro; null vuol dire nessun filtro
     * @param includeFields i campi da restituire; null vuol dire tutti
     * @param excludeFields i campi da togliere; null vuol dire nessuno
     * @return {@link List}
     * @throws Exception se la richiesta fallisce
     */
    @Override
    public List<D> findByIDS(@Nonnull(when = NEVER) Iterable<String> ids, @Nullable PredicateCondition<D> condition, @Nullable String[] includeFields,
            @Nullable String[] excludeFields) throws Exception {
        checkArgument(ids != null, "Il parametro ids non deve essere null.");
        List<String> values = StreamSupport.stream(ids.spliterator(), FALSE)
                .filter(Objects::nonNull)
                .filter(id -> !(id.trim().isEmpty()))
                .toList();
        // ElasticSearch rifiuta una mget senza id.
        if (values.isEmpty()) {
            return List.of();
        }
        MgetRequest request = MgetRequest.of(m -> {
            m.index(this.getIndexName()).ids(values).realtime(TRUE);
            if (includeFields != null) {
                m.sourceIncludes(asList(includeFields));
            }
            if (excludeFields != null) {
                m.sourceExcludes(asList(excludeFields));
            }
            return m;
        });
        MgetResponse<JsonData> response = this.elasticSearchClient.mget(request, JsonData.class);
        return response.docs().stream()
                .filter(MultiGetResponseItem::isResult)
                .map(MultiGetResponseItem::result)
                .filter(result -> result.found())
                .map(result -> this.readDocument(result.id(), result.source()))
                .filter(Objects::nonNull)
                .filter(document -> ((condition == null) || condition.test(document)))
                .toList();
    }

    /**
     * @param from il primo documento
     * @param size quanti documenti
     * @return {@link IPageResult}
     * @throws Exception se la ricerca fallisce
     */
    @Override
    public IPageResult<D> find(@Nonnull(when = NEVER) Integer from, @Nonnull(when = NEVER) Integer size) throws Exception {
        checkArgument((from != null) && (size != null), "I parametri from e size non devono essere null.");
        return this.find(new Page(from, size));
    }

    /**
     * @param from      il primo documento
     * @param size      quanti documenti
     * @param sortField il campo dell'ordinamento, senza il prefisso del nome radice
     * @param sortOrder il verso; null vuol dire decrescente
     * @return {@link IPageResult}
     * @throws Exception se la ricerca fallisce
     */
    @Override
    public IPageResult<D> find(@Nonnull(when = NEVER) Integer from, @Nonnull(when = NEVER) Integer size, @Nonnull(when = NEVER) String sortField,
            @Nullable SortOrder sortOrder) throws Exception {
        checkArgument((from != null) && (size != null), "I parametri from e size non devono essere null.");
        checkArgument((sortField != null) && !(sortField.trim().isEmpty()), "Il parametro sortField non deve essere null né vuoto.");
        return this.find(new SortablePage(this.getJsonRootName().concat(sortField), ((sortOrder != null) ? sortOrder : Desc), from, size));
    }

    /**
     * @param theQuery la query
     * @return {@link IPageResult}
     * @throws Exception se la ricerca fallisce
     */
    @Override
    public IPageResult<D> find(@Nonnull(when = NEVER) Query theQuery) throws Exception {
        checkArgument(theQuery != null, "Il parametro query non deve essere null.");
        return this.find(new QueriableSortablePage(theQuery));
    }
}
