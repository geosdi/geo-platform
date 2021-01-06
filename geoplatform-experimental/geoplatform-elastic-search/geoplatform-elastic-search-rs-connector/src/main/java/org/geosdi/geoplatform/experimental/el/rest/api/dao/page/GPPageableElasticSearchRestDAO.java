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
package org.geosdi.geoplatform.experimental.el.rest.api.dao.page;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.condition.PredicateCondition;
import org.geosdi.geoplatform.experimental.el.dao.GPPageableElasticSearchDAO;
import org.geosdi.geoplatform.experimental.el.rest.api.dao.index.GPElasticSearchRestIndexDAO;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPPageableElasticSearchRestDAO<D extends Document> extends GPPageableElasticSearchDAO<D>, GPElasticSearchRestIndexDAO<D> {

    /**
     * @param page
     * @return {@link IPageResult<D>}
     * @throws Exception
     */
    @Override
    <P extends Page> IPageResult<D> find(@Nonnull(when = NEVER) P page) throws Exception;

    /**
     * @param page
     * @param thePredicate
     * @return {@link IPageResult<D>}
     * @throws Exception
     */
    @Override
    <P extends Page> IPageResult<D> find(@Nonnull(when = NEVER) P page, @Nonnull(when = NEVER) PredicateCondition<D> thePredicate) throws Exception;

    /**
     * @param page
     * @param subType
     * @return {@link IPageResult<V>}
     * @throws Exception
     */
    @Override
    <P extends Page, V extends D> IPageResult<V> find(@Nonnull(when = NEVER) P page, @Nonnull(when = NEVER) Class<V> subType) throws Exception;

    /**
     * @param page
     * @param classe
     * @return {@link IPageResult<V>}
     * @throws Exception
     */
    @Override
    <P extends Page, V extends Document> IPageResult<V> findAndMappingWith(@Nonnull(when = NEVER) P page, @Nonnull(when = NEVER) Class<V> classe) throws Exception;

    /**
     * @param page
     * @param includeFields
     * @param excludeFields
     * @return {@link IPageResult <D>}
     * @throws Exception
     */
    @Override
    <P extends Page> IPageResult<D> find(@Nonnull(when = NEVER) P page, String[] includeFields, String[] excludeFields) throws Exception;

    /**
     * @param page
     * @param includeFields
     * @param excludeFields
     * @param classe
     * @return {@link IPageResult<V>}
     * @throws Exception
     */
    @Override
    <P extends Page, V extends Document> IPageResult<V> find(@Nonnull(when = NEVER) P page, String[] includeFields,
            String[] excludeFields, @Nonnull(when = NEVER) Class<V> classe) throws Exception;

    /**
     * @param page
     * @param includeField
     * @param excludeField
     * @return {@link IPageResult <D>}
     * @throws Exception
     */
    @Override
    <P extends Page> IPageResult<D> find(@Nonnull(when = NEVER) P page, String includeField, String excludeField) throws Exception;

    /**
     * @param page
     * @param aggregationBuilder
     * @return {@link SearchResponse}
     * @throws Exception
     */
    @Override
    <P extends Page> SearchResponse find(@Nonnull(when = NEVER) P page, @Nonnull(when = NEVER) AbstractAggregationBuilder aggregationBuilder) throws Exception;

    /**
     * @param page
     * @return {@link Long}
     * @throws Exception
     */
    @Override
    <P extends Page> Long count(@Nonnull(when = NEVER) P page) throws Exception;

    /**
     * @return {@link SearchRequest}
     * @throws Exception
     */
    default SearchRequest prepareSearchRequest() throws Exception {
        return new SearchRequest(this.getIndexName());
    }
}