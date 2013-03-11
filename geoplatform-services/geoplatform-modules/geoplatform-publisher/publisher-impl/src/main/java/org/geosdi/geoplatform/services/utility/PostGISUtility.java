/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2013 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.services.utility;

import it.geosolutions.geoserver.rest.GeoServerRESTReader;
import it.geosolutions.geoserver.rest.encoder.datastore.GSPostGISDatastoreEncoder;
import it.geosolutions.geoserver.rest.manager.GeoServerRESTDatastoreManager;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
@Service("postGISUtility")
public class PostGISUtility {

    @Autowired
    private GeoServerRESTReader reader;
    private @Value("configurator{geoserver_url}")
    String geoserverUrl;
    private @Value("configurator{geoserver_username}")
    String geoserverUser;
    private @Value("configurator{geoserver_password}")
    String geoserverPassword;
    private @Value("configurator{host_postgis_datastore_publisher}")
    String hostPostgisDatastore;
    private @Value("configurator{port_postgis_datastore_publisher}")
    int portPostgisDatastore;
    private @Value("configurator{db_name_postgis_datastore_publisher}")
    String dbNamePostgisDatastore;
    private @Value("configurator{username_db_postgis_datastore_publisher}")
    String userNameDBPostgisDatastore;
    private @Value("configurator{password_db_postgis_datastore_publisher}")
    String passwordDBPostgisDatastore;

    public GeoServerRESTDatastoreManager generateEncoder(String storeName, String workspace) {
//        RESTDataStore store = reader.getDatastore(workspace, storeName);
//        System.out.println("************ Store Retrieved: ");
//        System.out.println(store);
        GeoServerRESTDatastoreManager manager = null;
//        if (store == null) {
        try {
            manager = new GeoServerRESTDatastoreManager(
                    new URL(geoserverUrl), geoserverUser, geoserverPassword);
        } catch (MalformedURLException ex) {
            Logger.getLogger(PostGISUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
        GSPostGISDatastoreEncoder encoder = new GSPostGISDatastoreEncoder(storeName);
        encoder.setEnabled(true);
        encoder.setHost(hostPostgisDatastore);
        encoder.setPort(portPostgisDatastore);
        encoder.setDatabase(dbNamePostgisDatastore);
        encoder.setSchema("public");
        encoder.setUser(userNameDBPostgisDatastore);
        encoder.setPassword(passwordDBPostgisDatastore);
        encoder.setExposePrimaryKeys(Boolean.FALSE);
        encoder.setMaxConnections(20);
        encoder.setMinConnections(1);
        encoder.setFetchSize(1000);
        encoder.setConnectionTimeout(20);
        encoder.setValidateConnections(false);
        encoder.setLooseBBox(Boolean.TRUE);
        encoder.setPreparedStatements(false);
        encoder.setMaxOpenPreparedStatements(50);
        manager.create(workspace, encoder);
//        }
        return manager;
    }

//    public GSPostGISDatastoreEncoder generateEncoder(String storeName) {
//        GSPostGISDatastoreEncoder encoder = new GSPostGISDatastoreEncoder();
//        encoder.setName(storeName);
//        encoder.setEnabled(true);
//        encoder.setHost(hostPostgisDatastore);
//        encoder.setPort(portPostgisDatastore);
//        encoder.setDatabase(dbNamePostgisDatastore);
//        encoder.setSchema("public");
//        encoder.setUser(userNameDBPostgisDatastore);
//        encoder.setPassword(passwordDBPostgisDatastore);
//        encoder.setExposePrimaryKeys(Boolean.FALSE);
//        encoder.setMaxConnections(20);
//        encoder.setMinConnections(1);
//        encoder.setFetchSize(1000);
//        encoder.setConnectionTimeout(20);
//        encoder.setValidateConnections(false);
//        encoder.setLooseBBox(Boolean.TRUE);
//        encoder.setPreparedStatements(false);
//        encoder.setMaxOpenPreparedStatements(50);
//        return encoder;
//    }
    @Override
    public String toString() {
        return "PostGISUtility{ " + " hostPostgisDatastore = " + hostPostgisDatastore
                + ", portPostgisDatastore = " + portPostgisDatastore
                + ", dbNamePostgisDatastore = " + dbNamePostgisDatastore
                + ", userNameDBPostgisDatastore = " + userNameDBPostgisDatastore
                + ", passwordDBPostgisDatastore = " + passwordDBPostgisDatastore + "}";
    }
}
