/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.store;

import org.geosdi.geoplatform.connector.geowebcache.model.seed.GPGeowebcacheSeedBody;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.GeowebcacheSeedBody;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.bounds.GPGeowebcacheBoundsBean;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.bounds.GeowebcacheBoundsBean;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.entry.coordinates.GPGeowebcacheCoordinatesEntry;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.entry.coordinates.GeowebcacheCoordinatesEntry;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.entry.entry.GPGeowebcacheEntryValue;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.entry.entry.GeowebcacheEntryValue;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.operation.GeowebcacheSeedOperationType;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.srs.GPGeowebcacheSrsBean;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.srs.GeowebcacheSrsBean;
import org.geosdi.geoplatform.connector.server.security.DigestPreemptiveSecurityConnector;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

import static org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfigBuilder.PooledConnectorConfigBuilder.pooledConnectorConfigBuilder;
import static org.geosdi.geoplatform.connector.store.GPGeowebcacheConnectorStoreBuilder.geowebcacheConnectorBuilder;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class GPBaseGeowebcacheConnectorStoreTest {

    protected static final Logger logger = LoggerFactory.getLogger(GPBaseGeowebcacheConnectorStoreTest.class);
    //
    private static final String geoserverURLV2_21_x = "http://150.145.141.92/geoserver/gwc/rest";
    protected static GPGeowebcacheConnectorStore geowebcacheConnectorStore;

    /**
     * @throws Exception
     */
    @BeforeClass
    public static void beforeClass() throws Exception {
        geowebcacheConnectorStore = geowebcacheConnectorBuilder()
                .withServerUrl(new URL(geoserverURLV2_21_x))
                .withPooledConnectorConfig(pooledConnectorConfigBuilder()
                        .withMaxTotalConnections(150)
                        .withDefaultMaxPerRoute(80)
                        .withMaxRedirect(20).build())
                .withClientSecurity(new DigestPreemptiveSecurityConnector("admin", "geoserver"))
                .build();
    }

    /**
     * @throws Exception
     */
    @AfterClass
    public static void afterClass() throws Exception {
        geowebcacheConnectorStore.dispose();
    }

    /**
     * @throws Exception
     */
    @Test
    public void isRunningTest() throws Exception {
        logger.info("###########GEOWEBCACHE RUNNING : {}\n", geowebcacheConnectorStore.isGeowebcacheRunning());
    }

    /**
     * @throws Exception
     */
    @Test
    public void reloadingTest() throws Exception {
        logger.info("###########GEOWEBCACHE RELOADING : {}\n", geowebcacheConnectorStore.createReloadingRequest()
                .getResponse());
    }

    /**
     * @throws Exception
     */
    @Test
    public void seedTest() throws Exception {
        logger.info("###########GEOWEBCACHE SEED : {}\n", this.geowebcacheConnectorStore.createSeedRequest()
                .getResponse());
    }

    /**
     * @throws Exception
     */
    @Test
    public void seedWithLayerNameTest() throws Exception {
        logger.info("###########GEOWEBCACHE SEED : {}\n", geowebcacheConnectorStore.createSeedWithLayerNameRequest()
                .withLayerName("topp:states")
                .getResponse());
    }

    /**
     * @throws Exception
     */
    @Test
    public void seedWithLayerNameBodyTest() throws Exception {
        GPGeowebcacheSeedBody geowebcacheSeedBody = new GeowebcacheSeedBody();
        geowebcacheSeedBody.setName("topp:states");
        geowebcacheSeedBody.setGridSetId("EPSG:2163");
        geowebcacheSeedBody.setZoomStart(0);
        geowebcacheSeedBody.setZoomStop(1);
        geowebcacheSeedBody.setType(GeowebcacheSeedOperationType.SEED);
        geowebcacheSeedBody.setThreadCount(1);
        geowebcacheSeedBody.setFormat("image/png");
        GPGeowebcacheCoordinatesEntry coordinatesEntry = new GeowebcacheCoordinatesEntry();
        coordinatesEntry.addNumbers(-124.0, 22.0, -66.0, 72.0);
        GPGeowebcacheBoundsBean coordinatesBean = new GeowebcacheBoundsBean();
        coordinatesBean.setCoordinates(coordinatesEntry);
        geowebcacheSeedBody.setBounds(coordinatesBean);
        GPGeowebcacheEntryValue geowebcacheEntryValue = new GeowebcacheEntryValue();
        geowebcacheEntryValue.addValue("STYLES");
        geowebcacheEntryValue.addValue("pophatch");
        GPGeowebcacheSrsBean gpGeowebcacheSrsBean = new GeowebcacheSrsBean();
        gpGeowebcacheSrsBean.setNumber(4326);
        geowebcacheSeedBody.setSrs(gpGeowebcacheSrsBean);
        geowebcacheSeedBody.addParameter(geowebcacheEntryValue);
        logger.info("###########GEOWEBCACHE SEED : {}\n", this.geowebcacheConnectorStore.createSeedWithLayerNameBodyRequest()
                .withLayerName("topp:states")
                .withBody(geowebcacheSeedBody)
                .getResponse());
    }
}