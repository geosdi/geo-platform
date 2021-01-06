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
package org.geosdi.geoplatform.experimental.el.rest.api.dao.base;

import org.elasticsearch.client.RestHighLevelClient;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.rest.api.dao.support.ElasticSearchRestSupportDAO;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.annotation.*;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static org.elasticsearch.client.RequestOptions.DEFAULT;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class ElasticSearchRestBaseDAO<D extends Document> extends ElasticSearchRestSupportDAO<D> implements GPElasticSearchRestBaseDAO<D> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    @Resource(name = "elasticSearchRestHighLevelClient")
    protected RestHighLevelClient elasticSearchRestHighLevelClient;
    @Autowired
    protected Environment env;

    /**
     * @param theEntityClass
     * @param theJacksonSupport
     */
    protected ElasticSearchRestBaseDAO(@Nonnull(when = NEVER) Class<D> theEntityClass, @Nullable GPJacksonSupport theJacksonSupport) {
       super(theEntityClass, theJacksonSupport);
    }

    /**
     * @return {@link Boolean}
     * @throws Exception
     */
    @Override
    public Boolean isUpElasticSearchCluster() throws Exception {
        return this.elasticSearchRestHighLevelClient.ping(DEFAULT);
    }

    /**
     * @return {@link RestHighLevelClient}
     */
    @Override
    public RestHighLevelClient highLevelClient() {
        return this.elasticSearchRestHighLevelClient;
    }

    /**
     * @throws Exception
     */
    @PreDestroy
    @Override
    public void destroy() throws Exception {
        logger.debug("#########################Called {}#destroy.", this.getClass().getSimpleName());
        if (this.elasticSearchRestHighLevelClient != null) {
            this.elasticSearchRestHighLevelClient.close();
        }
    }

    /**
     * @throws Exception
     */
    @PostConstruct
    protected void onStartUp() throws Exception {
        checkArgument(this.elasticSearchRestHighLevelClient != null, "The Parameter elasticSearchRestHighLevelClient must not be null.");
    }
}