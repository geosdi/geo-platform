/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2015 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.experimental.el.dao;

import com.google.common.base.Preconditions;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.count.CountResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.geosdi.geoplatform.experimental.el.dao.GPElasticSearchDAO.GPElasticSearchBaseDAO;
import org.geosdi.geoplatform.experimental.el.index.GPIndexCreator;
import org.geosdi.geoplatform.experimental.el.mapper.GPBaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 * @param <D>
 */
public abstract class AbstractElasticSearchDAO<D extends Object>
        implements GPElasticSearchBaseDAO<D> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    protected GPIndexCreator indexCreator;
    protected GPBaseMapper<D> mapper;
    protected Client elastichSearchClient;

    @Override
    public void delete(String id) {
        Preconditions.checkArgument(((id != null) && !(id.isEmpty())),
                "The ID must not be null or an Empty String");
        DeleteResponse response = elastichSearchClient
                .prepareDelete(getIndexName(), getIndexType(), id)
                .execute()
                .actionGet();
        if (response.isFound()) {
            logger.debug("#################Document with ID : {}, "
                    + "was deleted.", id);
        } else {
            logger.debug("#################Document with ID : {}, "
                    + "was not found in ElasticSearch.");
        }
    }

    @Override
    public D find(String id) throws Exception {
        Preconditions.checkArgument((id != null) && !(id.isEmpty()),
                "The ElasticSearch ID must not be null or an Empty String");

        GetResponse existResponse = elastichSearchClient
                .prepareGet(getIndexName(), getIndexType(), id).get();

        return (existResponse.isExists())
                ? this.mapper.read(existResponse.getSourceAsString()) : null;
    }

    @Override
    public Long count() {
        CountResponse response = this.elastichSearchClient
                .prepareCount(getIndexName())
                .setTypes(getIndexType())
                .execute().actionGet();
        return response.getCount();
    }

    @Override
    public void removeAll() {
        this.elastichSearchClient
                .prepareDeleteByQuery(getIndexName())
                .setQuery(QueryBuilders.matchAllQuery())
                .setTypes(getIndexType())
                .execute().actionGet();
    }

    /**
     *
     * @return The Index Name
     */
    protected final String getIndexName() {
        return this.indexCreator.getIndexSettings().getIndexName();
    }

    /**
     *
     * @return The Index Type
     */
    protected final String getIndexType() {
        return this.indexCreator.getIndexSettings().getIndexType();
    }

    @Override
    public final void afterPropertiesSet() throws Exception {
        Preconditions.checkNotNull(this.mapper, "The Mapper must not be null.");
        Preconditions.checkNotNull(this.indexCreator, "The Index Creator must "
                + "not be null.");

        this.elastichSearchClient = this.indexCreator.client();

        Preconditions.checkNotNull(this.elastichSearchClient, "The ElasticSearch Client must "
                + "not be null.");
    }

}
