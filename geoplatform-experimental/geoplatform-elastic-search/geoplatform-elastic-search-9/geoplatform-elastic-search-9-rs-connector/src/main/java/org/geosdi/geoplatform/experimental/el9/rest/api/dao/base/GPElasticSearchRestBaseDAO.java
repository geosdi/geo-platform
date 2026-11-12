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
package org.geosdi.geoplatform.experimental.el9.rest.api.dao.base;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.transport.rest5_client.Rest5ClientTransport;
import co.elastic.clients.transport.rest5_client.low_level.Rest5Client;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.rest.api.dao.support.GPElasticSearchRestSupportDAO;
import org.geosdi.geoplatform.experimental.el9.rest.api.info.GPElasticSearchRestInfo;

/**
 * DAO con i client di ElasticSearch 9.
 *
 * @param <D> il tipo dei documenti
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPElasticSearchRestBaseDAO<D extends Document> extends GPElasticSearchRestSupportDAO<D> {

    /**
     * @return {@link Boolean} true se il cluster risponde
     * @throws Exception se la richiesta non parte
     */
    Boolean isUpElasticSearchCluster() throws Exception;

    /**
     * @return {@link GPElasticSearchRestInfo} nodo, cluster e versione
     * @throws Exception se la richiesta fallisce
     */
    GPElasticSearchRestInfo info() throws Exception;

    /**
     * @return {@link ElasticsearchClient} il client sincrono, condiviso: non va chiuso
     */
    ElasticsearchClient client();

    /**
     * @return {@link ElasticsearchAsyncClient} il client asincrono, condiviso: non va chiuso
     */
    ElasticsearchAsyncClient asyncClient();

    /**
     * @return {@link Rest5Client} il client HTTP, per le richieste che il client non ha
     */
    default Rest5Client restClient() {
        return ((Rest5ClientTransport) this.client()._transport()).restClient();
    }
}
