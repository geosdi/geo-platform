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
package org.geosdi.geoplatform.experimental.el9.rest;

import org.geosdi.geoplatform.experimental.el9.rest.dao.GPPlaceDAO;
import org.geosdi.geoplatform.experimental.el9.rest.model.GPPlace;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.testcontainers.elasticsearch.ElasticsearchContainer;

import java.nio.file.Path;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;

/**
 * Il connettore per ElasticSearch 9 in HTTPS, con la configurazione predefinita di ElasticSearch 9 (TLS e
 * autenticazione attivi): il truststore con la CA generata da ElasticSearch sta sul disco.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@SpringJUnitConfig(classes = GPElasticSearchRestTestConfig.class)
@DirtiesContext(classMode = AFTER_CLASS)
class GPElasticSearchRestSslTest {

    private static final Logger logger = LoggerFactory.getLogger(GPElasticSearchRestSslTest.class);
    private static final String TRUST_STORE_PASSWORD = "gp-truststore-password";
    //
    private static ElasticsearchContainer container;
    //
    @Autowired
    private GPPlaceDAO placeDAO;

    /**
     * Avvia ElasticSearch con l'HTTPS e scrive truststore e proprietà prima che Spring crei il contesto.
     *
     * @throws Exception se il container, il truststore o le proprietà non si preparano
     */
    @BeforeAll
    static void beforeAll() throws Exception {
        container = GPElasticSearchContainerSupport.startContainer(true);
        Path trustStore = GPElasticSearchContainerSupport.writeTrustStore(container, TRUST_STORE_PASSWORD);
        GPElasticSearchContainerSupport.writeConnectorProperties("https://" + container.getHttpHostAddress(), trustStore, TRUST_STORE_PASSWORD);
    }

    /**
     * Ferma ElasticSearch.
     */
    @AfterAll
    static void afterAll() {
        if (container != null) {
            container.stop();
        }
    }

    /**
     * Il cluster risponde in HTTPS e il DAO scrive e legge un documento.
     *
     * @throws Exception se una richiesta fallisce
     */
    @Test
    void secureSocketLayerTest() throws Exception {
        assertTrue(this.placeDAO.isUpElasticSearchCluster());
        logger.info("#################ElasticSearch in HTTPS: {}", this.placeDAO.info());
        assertTrue(this.placeDAO.createIndex());
        this.placeDAO.persist(new GPPlace("potenza", "Potenza", "PZ", "Capoluogo della Basilicata", 66000L, Instant.parse("2000-01-01T00:00:00Z"),
                "40.6401,15.8056"));
        assertEquals("Potenza", this.placeDAO.findByID("potenza").getName());
        assertTrue(this.placeDAO.deleteIndex());
    }
}
