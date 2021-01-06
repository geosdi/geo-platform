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
package org.geosdi.geoplatform.experimental.el.query.dao;

import com.google.common.base.Preconditions;
import org.elasticsearch.search.sort.SortOrder;
import org.geosdi.geoplatform.experimental.el.dao.AbstractElasticSearchDAO;
import org.geosdi.geoplatform.experimental.el.query.model.GPElasticSearchQuery;
import org.geosdi.geoplatform.experimental.el.search.date.IGPDateQuerySearch.GPDateQuerySearch;
import org.geosdi.geoplatform.experimental.el.search.phrase.GPPhrasePrefixSearch;
import org.joda.time.DateTime;

import javax.annotation.Nullable;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.search.sort.SortOrder.DESC;
import static org.geosdi.geoplatform.experimental.el.search.bool.IBooleanSearch.BooleanQueryType.MUST;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPElasticSearchQueryDAO<Q extends GPElasticSearchQuery> extends AbstractElasticSearchDAO<Q> implements IGPElasticSearchQueryDAO<Q> {

    /**
     * @param queryName
     * @param from
     * @param size
     * @return {@link IPageResult<GPElasticSearchQuery>}
     * @throws Exception
     */
    @Override
    public IPageResult<Q> findByQueryName(String queryName, @Nullable Integer from, @Nullable Integer size) throws Exception {
        checkArgument(((queryName != null) && !(queryName.isEmpty())), "The Parameter " + "Query Name must not be null or an Empty String.");
        return super.find(new MultiFieldsSearch(from, size, new GPPhrasePrefixSearch(super.getJsonRootName().concat(".queryName"), queryName, MUST)));
    }

    /**
     * @param queryDescription
     * @param from
     * @param size
     * @return {@link IPageResult<GPElasticSearchQuery>}
     * @throws Exception
     */
    @Override
    public IPageResult<Q> findByQueryDescription(String queryDescription, @Nullable Integer from, @Nullable Integer size) throws Exception {
        checkArgument(((queryDescription != null) && !(queryDescription.isEmpty())), "The Parameter " + "Query Description must not be null or an Empty String.");
        return super.find(new MultiFieldsSearch(from, size, new GPPhrasePrefixSearch(super.getJsonRootName().concat(".description"), queryDescription, MUST)));
    }

    /**
     * @param fromDate
     * @param toDate
     * @param from
     * @param size
     * @return {@link IPageResult<GPElasticSearchQuery>}
     * @throws Exception
     */
    @Override
    public IPageResult<Q> findByQueryCreationDate(DateTime fromDate, DateTime toDate, @Nullable Integer from, @Nullable Integer size) throws Exception {
        checkArgument((fromDate != null), "The Parameter From Must not be null.");
        checkArgument((toDate != null), "The Parameter To Must not be null.");
        checkArgument(fromDate.isBefore(toDate), "The Parameter From must be Before of To");
        String field = super.getJsonRootName().concat(".creationDate");
        return super.find(new MultiFieldsSearch(field, SortOrder.DESC, from, size, new GPDateQuerySearch(field, MUST, fromDate, toDate)));
    }

    /**
     * @return {@link List<GPElasticSearchQuery>}
     * @throws Exception
     */
    @Override
    public List<Q> findLasts() throws Exception {
        logger.debug("###############Try to find Lasts 10 GPElasticSearchQuery, orderBy " + "creationDate #SortOrder.DESC.\n\n");
        return super.find(new QueriableSortablePage(super.getJsonRootName().concat(".creationDate"), DESC, matchAllQuery())).getResults();
    }

    /**
     * @param from
     * @param size
     * @return {@link IPageResult <Q>}
     * @throws Exception
     */
    @Override
    public IPageResult<Q> findQuery(@Nullable Integer from, @Nullable Integer size) throws Exception {
        return super.find(new QueriableSortablePage(super.getJsonRootName().concat(".creationDate"), DESC, from, size, matchAllQuery()));
    }
}