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
package org.geosdi.geoplatform.connector.server.request;

import org.geosdi.geoplatform.connector.GPWFSConnectorStore;
import org.geosdi.geoplatform.gui.shared.wfs.TransactionOperation;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.namespace.QName;
import java.math.BigInteger;
import java.net.URI;

import static java.util.Arrays.asList;
import static org.geosdi.geoplatform.connector.WFSConnectorBuilder.newConnector;
import static org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfigBuilder.PooledConnectorConfigBuilder.pooledConnectorConfigBuilder;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 * Verifies the opt-in {@code clearState()} lifecycle method : after configuring a request through the fluent
 * {@code withXxx(...)} mutators, an explicit {@code clearState()} releases the per-thread {@code ThreadLocal}
 * configuration, so a shared request instance does not retain per-thread values on pooled threads.
 * {@code clearState()} is never invoked automatically (the request state must survive the whole
 * request/response lifecycle), so it is exercised here directly.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WFSRequestClearStateTest {

    private static GPWFSConnectorStore serverConnector;

    @BeforeClass
    public static void beforeClass() throws Exception {
        serverConnector = newConnector()
                .withServerUrl(new URI("http://localhost:8080/geoserver/wfs").toURL())
                .withPooledConnectorConfig(pooledConnectorConfigBuilder()
                        .withMaxTotalConnections(20)
                        .withDefaultMaxPerRoute(8)
                        .withMaxRedirect(5)
                        .build())
                .build();
    }

    @Test
    public void a_getFeatureStateIsClearedByClearState() throws Exception {
        AbstractGetFeatureRequest<?, ?> request = (AbstractGetFeatureRequest<?, ?>) serverConnector.createGetFeatureRequest();
        QName typeName = new QName("http://www.openplans.org/topp", "states", "topp");
        request.withTypeName(typeName)
                .withMaxFeatures(BigInteger.TEN)
                .withFeatureIDs(asList("states.1"));
        assertSame(typeName, request.getTypeName());
        assertSame(BigInteger.TEN, request.getMaxFeatures());

        request.clearState();

        assertNull("typeName must be released by clearState", request.getTypeName());
        assertNull("maxFeatures must be released by clearState", request.getMaxFeatures());
        assertNull("featureIDs must be released by clearState", request.getFeatureIDs());
    }

    @Test
    public void b_describeFeatureTypeStateIsClearedByClearState() throws Exception {
        AbstractDescribeFeatureTypeRequest<?, ?> request = (AbstractDescribeFeatureTypeRequest<?, ?>) serverConnector.createDescribeFeatureTypeRequest();
        QName typeName = new QName("http://www.openplans.org/topp", "states", "topp");
        request.withTypeName(asList(typeName)).withOutputFormat("text/xml; subtype=gml/3.1.1");

        request.clearState();

        assertNull("typeName must be released by clearState", request.typeName.get());
        assertNull("outputFormat must be released by clearState", request.outputFormat.get());
    }

    @Test
    public void c_transactionDeleteStateIsClearedByClearState() throws Exception {
        AbstractTransactionRequest<?, ?> request = (AbstractTransactionRequest<?, ?>) serverConnector.createTransactionRequest();
        QName typeName = new QName("http://www.openplans.org/topp", "states", "topp");
        request.withOperation(TransactionOperation.DELETE)
                .withTypeName(typeName)
                .withFID("states.1");
        assertSame(typeName, request.getTypeName());

        request.clearState();

        assertNull("typeName must be released by clearState", request.getTypeName());
        assertNull("fid must be released by clearState", request.getFID());
        assertNull("operation must be released by clearState", request.getOperation());
    }

    @Test
    public void d_transactionInsertStaxStateIsClearedByClearState() throws Exception {
        AbstractTransactionRequest<?, ?> request = (AbstractTransactionRequest<?, ?>) serverConnector.createTransactionRequest();
        QName typeName = new QName("http://www.openplans.org/topp", "states", "topp");
        request.withOperation(TransactionOperation.INSERT)
                .withTypeName(typeName)
                .withAttributes(asList());

        request.clearState();

        assertNull("typeName must be released by clearState", request.getTypeName());
        assertNull("operation must be released by clearState", request.getOperation());
        assertNull("attributes must be released by clearState", request.getAttributes());
    }
}
