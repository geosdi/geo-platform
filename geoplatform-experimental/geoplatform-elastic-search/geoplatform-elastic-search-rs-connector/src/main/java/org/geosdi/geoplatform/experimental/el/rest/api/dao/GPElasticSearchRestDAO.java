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
package org.geosdi.geoplatform.experimental.el.rest.api.dao;

import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.geosdi.geoplatform.experimental.el.api.function.GPElasticSearchCheck;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.api.response.IGPUpdateResponse;
import org.geosdi.geoplatform.experimental.el.rest.api.dao.async.GPElasticSearchRestAsyncDAO;

import javax.annotation.Nonnull;
import java.util.Map;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPElasticSearchRestDAO<D extends Document> extends GPElasticSearchRestAsyncDAO<D> {

    /**
     * @param document
     * @return D
     * @throws Exception
     */
    D persist(@Nonnull(when = NEVER) D document) throws Exception;

    /**
     * @param documents
     * @return {@link BulkResponse}
     * @throws Exception
     */
    BulkResponse persist(@Nonnull(when = NEVER) Iterable<D> documents) throws Exception;

    /**
     * @param document
     * @return {@link IGPUpdateResponse}
     * @throws Exception
     */
    IGPUpdateResponse update(@Nonnull(when = NEVER) D document) throws Exception;

    /**
     * @param theID
     * @param theProperties
     * @return {@link IGPUpdateResponse}
     * @throws Exception
     */
    IGPUpdateResponse update(@Nonnull(when = NEVER) String theID, @Nonnull(when = NEVER) Map<String, Object> theProperties) throws Exception;

    /**
     * @param theID
     * @param theXcontetBuilder
     * @return {@link IGPUpdateResponse}
     * @throws Exception
     */
    IGPUpdateResponse update(@Nonnull(when = NEVER) String theID, @Nonnull(when = NEVER) XContentBuilder theXcontetBuilder) throws Exception;

    /**
     * @param documents
     * @return {@link BulkResponse}
     * @throws Exception
     */
    BulkResponse update(@Nonnull(when = NEVER) Iterable<D> documents) throws Exception;

    /**
     * @param theValue
     * @param theCheck
     * @param <R>
     * @param <V>
     * @return
     * @throws Exception
     */
    <R extends UpdateByQueryRequest, V extends Object> BulkByScrollResponse updateByQuery(@Nonnull(when = NEVER) V theValue, @Nonnull(when = NEVER) GPElasticSearchCheck<R, V, Exception> theCheck) throws Exception;

    /**
     * <p>
     * Delete Document by ElasticSearch ID</p>
     *
     * @param theID
     * @return {@link Boolean}
     * @throws java.lang.Exception
     */
    Boolean delete(@Nonnull(when = NEVER) String theID) throws Exception;

    /**
     * @param theValue
     * @param theCheck
     * @param <R>
     * @param <V>
     * @return {@link BulkByScrollResponse}
     * @throws Exception
     */
    <R extends DeleteByQueryRequest, V extends Object> BulkByScrollResponse deleteByQuery(@Nonnull(when = NEVER) V theValue, @Nonnull(when = NEVER) GPElasticSearchCheck<R, V, Exception> theCheck) throws Exception;

    /**
     * <p>
     * Return the number of Documents</p>
     *
     * @return {@link Long}
     * @throws java.lang.Exception
     */
    Long count() throws Exception;

    /**
     * @param theQueryBuilder
     * @return {@link Long}
     * @throws Exception
     */
    Long count(@Nonnull(when = NEVER) QueryBuilder theQueryBuilder) throws Exception;

    /**
     * @param theValue
     * @param theCheck
     * @param <R>
     * @param <V>
     * @return {@link Long}
     * @throws Exception
     */
    <R extends QueryBuilder, V> Long count(@Nonnull(when = NEVER) V theValue ,@Nonnull(when = NEVER) GPElasticSearchCheck<R, V, Exception> theCheck) throws Exception;
}