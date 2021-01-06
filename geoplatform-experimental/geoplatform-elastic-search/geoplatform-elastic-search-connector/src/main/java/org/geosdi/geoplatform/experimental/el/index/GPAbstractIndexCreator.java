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
package org.geosdi.geoplatform.experimental.el.index;

import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.Resource;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPAbstractIndexCreator implements GPIndexCreator, InitializingBean {

    @GeoPlatformLog
    protected Logger logger;
    //
    @Resource(name = "elastichSearchClient")
    private Client elastichSearchClient;

    @Override
    public synchronized void createIndex() throws Exception {
        if (!existIndex()) {
            logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@INDEX_NAME :" + " {} - INDEX_TYPE : {} doesn't exist, so i will create it.\n", getIndexName(), getIndexType());
            this.elastichSearchClient.admin().indices().prepareCreate(getIndexName()).get();
        } else {
            logger.debug("\n\n######################INDEX_NAME : {} " + "already exists.\n", getIndexName());
        }
        this.preparePutMapping();
    }

    @Override
    public synchronized void deleteIndex() throws Exception {
        if (existIndex()) {
            this.elastichSearchClient.admin().indices().prepareDelete(getIndexName()).get();
            logger.debug("#########################INDEX_NAME : {} - INDEX_TYPE : {} deleted.\n", getIndexName(), getIndexType());
        } else {
            logger.debug("@@@@@@@@@@@@@@@@@@@@INDEX_NAME : {} - INDEX_TYPE : {} doesn't exist.\n", getIndexName(), getIndexType());
        }
    }

    @Override
    public Client client() {
        return this.elastichSearchClient;
    }

    @Override
    public Boolean existIndex() throws Exception {
        return this.elastichSearchClient.admin().indices().prepareExists(getIndexName()).get().isExists();
    }

    /**
     * @return {@link Boolean}
     * @throws Exception
     */
    @Override
    public Boolean existsType() throws Exception {
        return this.elastichSearchClient.admin().indices().prepareTypesExists(getIndexName()).setTypes(getIndexType()).get().isExists();
    }

    /**
     * @param xContentBuilder
     * @return {@link AcknowledgedResponse}
     * @throws Exception
     */
    protected AcknowledgedResponse putMapping(XContentBuilder xContentBuilder) throws Exception {
        checkNotNull(xContentBuilder, "The XContentBuilder must not be null");
        return this.elastichSearchClient.admin().indices().preparePutMapping(getIndexName()).setType(getIndexType()).setSource(xContentBuilder).execute().actionGet();
    }

    /**
     * <p>Generate the Correct Mapping.</p>
     *
     * @throws Exception
     */
    protected void preparePutMapping() throws Exception {
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        checkNotNull(elastichSearchClient, "The ElasticSearch Client must not be null.");
        checkNotNull(logger, "The GeoPlatform Log must not be null.");
        checkArgument((this.getIndexSettings() != null), "The Index Settings must not be null.");
        checkArgument((this.getIndexName() != null) || !(this.getIndexName().isEmpty()), "The Index Name must not be null or an empty String.");
        checkArgument((this.getIndexType() != null) || !(this.getIndexType().isEmpty()), "The Index Type must not be null or an empty String.");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + " indexName = " + this.getIndexName() + ", indexType = " + this.getIndexType() + "}";
    }
}