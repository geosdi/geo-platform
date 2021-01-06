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
package org.geosdi.geoplatform.experimental.el.rest.api.dao.find;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.condition.PredicateCondition;
import org.geosdi.geoplatform.experimental.el.rest.api.dao.page.GPPageableElasticSearchRestDAO;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.meta.When;
import java.util.List;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPElasticSearchRestFindDAO<D extends Document> extends GPPageableElasticSearchRestDAO<D> {

    /**
     * @param theDocumentID
     * @return {@link D}
     * @throws Exception
     */
    D findByID(@Nonnull(when = When.NEVER) String theDocumentID) throws Exception;

    /**
     * @param theDocumentID
     * @param includeFields
     * @param excludeFields
     * @return {@link D}
     * @throws Exception
     */
    D findByID(@Nonnull(when = NEVER) String theDocumentID, @Nullable String[] includeFields, @Nullable String[] excludeFields) throws Exception;

    /**
     * @return {@link List<D>}
     * @throws Exception
     */
    List<D> findLasts() throws Exception;

    /**
     * @param ids
     * @return {@link List<D>}
     * @throws Exception
     */
    List<D> findByIDS(@Nonnull(when = NEVER) Iterable<String> ids, @Nullable PredicateCondition<D> condition) throws Exception;

    /**
     * @param ids
     * @return {@link List<D>}
     * @throws Exception
     */
    List<D> findByIDS(@Nonnull(when = NEVER) Iterable<String> ids) throws Exception;

    /**
     * @param ids
     * @param condition
     * @param includeFields
     * @param excludeFields
     * @return {@link List<D>}
     * @throws Exception
     */
    List<D> findByIDS(@Nonnull(when = NEVER) Iterable<String> ids, @Nullable PredicateCondition<D> condition, @Nullable String[] includeFields, @Nullable String[] excludeFields) throws Exception;

    /**
     * @param ids
     * @param includeFields
     * @param excludeFields
     * @return {@link List<D>}
     * @throws Exception
     */
    List<D> findByIDS(@Nonnull(when = NEVER) Iterable<String> ids, @Nullable String[] includeFields, @Nullable String[] excludeFields) throws Exception;

    /**
     * @param from
     * @param size
     * @return {@link org.geosdi.geoplatform.experimental.el.dao.GPPageableElasticSearchDAO.IPageResult<D>}
     * @throws Exception
     */
    IPageResult<D> find(Integer from, Integer size) throws Exception;

    /**
     * @param from
     * @param size
     * @param sortField
     * @param sortOrder
     * @return {@link org.geosdi.geoplatform.experimental.el.dao.GPPageableElasticSearchDAO.IPageResult<D>}
     * @throws Exception
     */
    IPageResult<D> find(Integer from, Integer size, @Nonnull(when = NEVER) String sortField, @Nullable SortOrder sortOrder) throws Exception;

    /**
     * @param theSearchSourceBuilder
     * @return {@link org.geosdi.geoplatform.experimental.el.dao.GPPageableElasticSearchDAO.IPageResult<D>}
     * @throws Exception
     */
    IPageResult<D> find(@Nonnull(when = NEVER) SearchSourceBuilder theSearchSourceBuilder) throws Exception;

    /**
     * @param theQueryBuilder
     * @return {@link org.geosdi.geoplatform.experimental.el.dao.GPPageableElasticSearchDAO.IPageResult<D>}
     * @throws Exception
     */
    IPageResult<D> find(@Nonnull(when = NEVER) QueryBuilder theQueryBuilder) throws Exception;
}