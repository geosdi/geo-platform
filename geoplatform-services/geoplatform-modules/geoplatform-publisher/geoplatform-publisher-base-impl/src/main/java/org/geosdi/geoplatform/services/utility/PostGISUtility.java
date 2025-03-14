/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.services.utility;

import com.google.common.collect.Maps;
import org.geosdi.geoplatform.connector.geoserver.model.datastores.IGPGeoserverCreateDatastoreResponse;
import org.geosdi.geoplatform.connector.store.GPGeoserverConnectorStore;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Map;

import static org.geosdi.geoplatform.connector.geoserver.model.datastores.body.builder.db.postgis.IGPPostgisDatastoreBodyBuilder.GPPostgisDatastoreBodyBuilder.postgisDatastoreBodyBuilder;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
@Component("postGISUtility")
public class PostGISUtility implements InitializingBean {

    private @Value("configurator{host_postgis_datastore_publisher}")
    String hostPostgisDatastore;
    private @Value("configurator{port_postgis_datastore_publisher}")
    int portPostgisDatastore;
    private @Value("configurator{min_connections_postgis_datastore_publisher}")
    int minConnectionsPostgisDatastore;
    private @Value("configurator{max_connections_postgis_datastore_publisher}")
    int maxConnectionsPostgisDatastore;
    private @Value("configurator{timeout_connections_postgis_datastore_publisher}")
    int timeoutConnectionsPostgisDatastore;
    private @Value("configurator{db_name_postgis_datastore_publisher}")
    String dbNamePostgisDatastore;
    private @Value("configurator{username_db_postgis_datastore_publisher}")
    String userNameDBPostgisDatastore;
    private @Value("configurator{password_db_postgis_datastore_publisher}")
    String passwordDBPostgisDatastore;
    @Resource(name = "geoserverConnectorStore")
    protected GPGeoserverConnectorStore geoserverConnectorStore;

    private Map<String, Serializable> outputDataStoreMap;

    public IGPGeoserverCreateDatastoreResponse generateEncoder(String storeName, String workspace) {
        try {
            return this.geoserverConnectorStore.createDatastoreRequest()
                    .withWorkspaceName(workspace).withDatastoreBody(
                    postgisDatastoreBodyBuilder()
                            .withName(storeName)
                            .withHost(hostPostgisDatastore)
                            .withPort(portPostgisDatastore)
                            .withDatabase(dbNamePostgisDatastore)
                            .withSchema("public")
                            .withUser(userNameDBPostgisDatastore)
                            .withPassword(passwordDBPostgisDatastore)
                            .withExposePrimaryKeys(Boolean.FALSE)
                            .withMaxConnections(maxConnectionsPostgisDatastore)
                            .withMinConnections(minConnectionsPostgisDatastore)
                            .withConnectionTimeout(timeoutConnectionsPostgisDatastore)
                            .withFetchSize(1000)
                            .withValidateConnections(Boolean.TRUE)
                            .withLooseBbox(Boolean.TRUE)
                            .withPreparedStatements(Boolean.FALSE)
                            .withMaxOpenPreparedStatements(50).build()).getResponse();
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, Serializable> getOutputDataStoreMap() {
        return outputDataStoreMap;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.outputDataStoreMap = Maps.<String, Serializable>newHashMap();
        outputDataStoreMap.put("dbtype", "postgis");
        outputDataStoreMap.put("host", this.hostPostgisDatastore);
        outputDataStoreMap.put("port", this.portPostgisDatastore);
        outputDataStoreMap.put("schema", "public");
        outputDataStoreMap.put("database", this.dbNamePostgisDatastore);
        outputDataStoreMap.put("user", this.userNameDBPostgisDatastore);
        outputDataStoreMap.put("passwd", this.passwordDBPostgisDatastore);
        outputDataStoreMap.put("charset", "UTF-8");
    }

    @Override
    public String toString() {
        return "PostGISUtility{ " + " hostPostgisDatastore = " + hostPostgisDatastore
                + ", portPostgisDatastore = " + portPostgisDatastore
                + ", dbNamePostgisDatastore = " + dbNamePostgisDatastore
                + ", userNameDBPostgisDatastore = " + userNameDBPostgisDatastore
                + ", passwordDBPostgisDatastore = " + passwordDBPostgisDatastore + "}";
    }
}
