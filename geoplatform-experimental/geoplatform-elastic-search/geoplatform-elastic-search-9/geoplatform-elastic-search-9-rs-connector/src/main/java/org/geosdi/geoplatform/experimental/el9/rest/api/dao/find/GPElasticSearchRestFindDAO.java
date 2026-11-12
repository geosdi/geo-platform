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
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.condition.PredicateCondition;
import org.geosdi.geoplatform.experimental.el9.rest.api.dao.page.scroll.GPPageableElasticSearchRestScrollDAO;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

import static javax.annotation.meta.When.NEVER;

/**
 * Letture per id e ricerche semplici.
 *
 * @param <D> il tipo dei documenti
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPElasticSearchRestFindDAO<D extends Document> extends GPPageableElasticSearchRestScrollDAO<D> {

    /**
     * @param theDocumentID l'id
     * @return {@link D} il documento, null se non c'è
     * @throws Exception se la richiesta fallisce
     */
    D findByID(@Nonnull(when = NEVER) String theDocumentID) throws Exception;

    /**
     * @param theDocumentID l'id
     * @param includeFields i campi da restituire; null vuol dire tutti
     * @param excludeFields i campi da togliere; null vuol dire nessuno
     * @return {@link D} il documento, null se non c'è
     * @throws Exception se la richiesta fallisce
     */
    D findByID(@Nonnull(when = NEVER) String theDocumentID, @Nullable String[] includeFields, @Nullable String[] excludeFields) throws Exception;

    /**
     * @return {@link List} i primi 10 documenti dell'indice, senza ordinamento, come nella 7
     * @throws Exception se la ricerca fallisce
     */
    List<D> findLasts() throws Exception;

    /**
     * @param ids gli id; i null e i vuoti si saltano
     * @return {@link List} i documenti trovati
     * @throws Exception se la richiesta fallisce
     */
    List<D> findByIDS(@Nonnull(when = NEVER) Iterable<String> ids) throws Exception;

    /**
     * @param ids       gli id; i null e i vuoti si saltano
     * @param condition il filtro sui documenti trovati; null vuol dire nessun filtro
     * @return {@link List} i documenti trovati che passano il filtro
     * @throws Exception se la richiesta fallisce
     */
    List<D> findByIDS(@Nonnull(when = NEVER) Iterable<String> ids, @Nullable PredicateCondition<D> condition) throws Exception;

    /**
     * @param ids           gli id; i null e i vuoti si saltano
     * @param condition     il filtro sui documenti trovati; null vuol dire nessun filtro
     * @param includeFields i campi da restituire; null vuol dire tutti
     * @param excludeFields i campi da togliere; null vuol dire nessuno
     * @return {@link List} i documenti trovati che passano il filtro
     * @throws Exception se la richiesta fallisce
     */
    List<D> findByIDS(@Nonnull(when = NEVER) Iterable<String> ids, @Nullable PredicateCondition<D> condition, @Nullable String[] includeFields,
            @Nullable String[] excludeFields) throws Exception;

    /**
     * @param ids           gli id; i null e i vuoti si saltano
     * @param includeFields i campi da restituire; null vuol dire tutti
     * @param excludeFields i campi da togliere; null vuol dire nessuno
     * @return {@link List} i documenti trovati
     * @throws Exception se la richiesta fallisce
     */
    List<D> findByIDS(@Nonnull(when = NEVER) Iterable<String> ids, @Nullable String[] includeFields, @Nullable String[] excludeFields) throws Exception;

    /**
     * @param from il primo documento, da zero
     * @param size quanti documenti; zero vuol dire nessuna paginazione
     * @return {@link IPageResult}
     * @throws Exception se la ricerca fallisce
     */
    IPageResult<D> find(@Nonnull(when = NEVER) Integer from, @Nonnull(when = NEVER) Integer size) throws Exception;

    /**
     * @param from      il primo documento, da zero
     * @param size      quanti documenti; zero vuol dire nessuna paginazione
     * @param sortField il campo dell'ordinamento, senza il prefisso del nome radice
     * @param sortOrder il verso; null vuol dire decrescente
     * @return {@link IPageResult}
     * @throws Exception se la ricerca fallisce
     */
    IPageResult<D> find(@Nonnull(when = NEVER) Integer from, @Nonnull(when = NEVER) Integer size, @Nonnull(when = NEVER) String sortField,
            @Nullable SortOrder sortOrder) throws Exception;

    /**
     * @param theQuery la query
     * @return {@link IPageResult} i primi 10 documenti e il totale
     * @throws Exception se la ricerca fallisce
     */
    IPageResult<D> find(@Nonnull(when = NEVER) Query theQuery) throws Exception;
}
