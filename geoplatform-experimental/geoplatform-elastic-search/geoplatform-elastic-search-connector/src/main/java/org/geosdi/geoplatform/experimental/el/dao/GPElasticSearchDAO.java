/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2017 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.experimental.el.dao;

import com.google.common.base.Preconditions;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.condition.PredicateCondition;
import org.springframework.beans.factory.InitializingBean;

import java.nio.file.Path;
import java.util.List;

/**
 * @param <D> Entity to Persist in ElasticSearch
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPElasticSearchDAO<D extends Document> extends ElasticSearchDAO<D> {

    /**
     * @param document
     * @return D
     * @throws Exception
     */
    D persist(D document) throws Exception;

    /**
     * @param documents
     * @return {@link BulkResponse}
     * @throws Exception
     */
    BulkResponse persist(Iterable<D> documents) throws Exception;

    /**
     * @param documents
     * @return {@link BulkResponse}
     * @throws Exception
     */
    BulkResponse update(Iterable<D> documents) throws Exception;

    /**
     * <p>Persist all Files json in a Directory. This method in not recursive for the Directory.</p>
     *
     * @param direrctory
     * @return {@link}
     * @throws Exception
     */
    BulkResponse persist(Path direrctory) throws Exception;

    /**
     * @param document
     * @throws Exception
     */
    void update(D document) throws Exception;

    /**
     * @throws Exception
     */
    void removeAll() throws Exception;

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
    List<D> findByIDS(Iterable<String> ids, PredicateCondition<D> condition) throws Exception;

    /**
     * @param ids
     * @return {@link List<D>}
     * @throws Exception
     */
    List<D> findByIDS(Iterable<String> ids) throws Exception;

    /**
     * @param <D>
     */
    interface GPElasticSearchBaseDAO<D extends Document>
            extends GPElasticSearchDAO<D>, InitializingBean {

        /**
         * <p>
         * Delete Document by ElasticSearch ID</p>
         *
         * @param id
         * @throws java.lang.Exception
         */
        void delete(String id) throws Exception;

        /**
         * <p>
         * The ID must not be null otherwise an {@link IllegalArgumentException}
         * will be launched</p>
         *
         * @param id
         * @return D the Document Found
         * @throws Exception
         */
        D find(String id) throws Exception;

        /**
         * <p>
         * Return the number of Documents</p>
         *
         * @return {@link Long}
         * @throws java.lang.Exception
         */
        Long count() throws Exception;

        /**
         * @param queryBuilder
         * @return {@link Long}
         * @throws Exception
         */
        Long count(QueryBuilder queryBuilder) throws Exception;

        /**
         * @param builder
         * @param <Builder>
         * @return {@link Long}
         * @throws Exception
         */
        default <Builder extends SearchRequestBuilder> Long count(Builder builder) throws Exception {
            Preconditions.checkNotNull(builder, "The Parameter Builder must not be null");
            return builder.execute().get().getHits().getTotalHits();
        }
    }
}
