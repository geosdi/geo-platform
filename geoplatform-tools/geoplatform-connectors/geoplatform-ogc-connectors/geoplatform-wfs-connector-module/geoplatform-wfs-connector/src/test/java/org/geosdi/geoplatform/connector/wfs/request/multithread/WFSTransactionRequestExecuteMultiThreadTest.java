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
package org.geosdi.geoplatform.connector.wfs.request.multithread;

import org.geosdi.geoplatform.connector.GPWFSConnectorStore;
import org.geosdi.geoplatform.connector.server.request.WFSTransactionRequest;
import org.geosdi.geoplatform.connector.server.request.WFSTransactionRequestState;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.net.URI;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.stream.IntStream.range;
import static org.geosdi.geoplatform.connector.WFSConnectorBuilder.newConnector;
import static org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfigBuilder.PooledConnectorConfigBuilder.pooledConnectorConfigBuilder;
import static org.geosdi.geoplatform.gui.shared.wfs.TransactionOperation.DELETE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Verifies the {@code execute(...)} template of {@link org.geosdi.geoplatform.connector.server.request.WFSStatefulRequest}
 * on a <b>single, shared</b> {@code Transaction} request driven concurrently by many <b>virtual threads</b>.
 * While the {@code execute} logic itself is shared across all WFS requests (see
 * {@code WFSStatefulRequestExecuteMultiThreadTest} for {@code GetFeature}), the {@link #clearState()} it invokes
 * is request-specific : this test proves the wiring releases the {@code Transaction} state (operation, typeName,
 * fid) and keeps a shared instance thread-safe. No network call is performed : the action relies on
 * {@link WFSTransactionRequest#showRequestAsString()}, which only marshals the current per-thread state.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WFSTransactionRequestExecuteMultiThreadTest {

    private static final Logger logger = LoggerFactory.getLogger(WFSTransactionRequestExecuteMultiThreadTest.class);
    //
    private static final int THREADS = 16;
    private static final int ITERATIONS = 200;
    private static final String TOPP_NAMESPACE = "http://www.openplans.org/topp";
    //
    private static GPWFSConnectorStore serverConnector;
    /**
     * The one instance intentionally shared by every worker thread : the {@code execute(...)} contract on a
     * shared instance is what we are testing.
     */
    private static WFSTransactionRequest<Object> sharedRequest;

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
        sharedRequest = serverConnector.createTransactionRequest();
    }

    @Test
    public void a_executeIsThreadSafeAndAlwaysReleasesStateAcrossVirtualThreads() throws Exception {
        WFSTransactionRequestState state = (WFSTransactionRequestState) sharedRequest;
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(THREADS);
        AtomicInteger successfulIterations = new AtomicInteger(0);
        Queue<Throwable> errors = new ConcurrentLinkedQueue<>();
        range(0, THREADS).forEach(index -> Thread.startVirtualThread(() -> {
            try {
                QName typeName = new QName(TOPP_NAMESPACE, "states_" + index + "_end", "topp");
                String featureId = "fid_" + index + "_end";
                startSignal.await();
                for (int i = 0; i < ITERATIONS; i++) {
                    String requestAsString = sharedRequest.execute(
                            r -> r.withOperation(DELETE)
                                    .withTypeName(typeName)
                                    .withFID(featureId),
                            WFSTransactionRequest::showRequestAsString);
                    checkArgument(requestAsString.contains(typeName.getLocalPart()),
                            "Cross-thread state leak on task %s : expected typeName [%s] but was [%s].",
                            index, typeName.getLocalPart(), requestAsString);
                    checkArgument(requestAsString.contains(featureId),
                            "Cross-thread state leak on task %s : expected fid [%s] but was [%s].",
                            index, featureId, requestAsString);
                    checkArgument(state.getOperation() == null,
                            "execute did not release operation after the action on task %s.", index);
                    checkArgument(state.getTypeName() == null,
                            "execute did not release typeName after the action on task %s.", index);
                    checkArgument(state.getFID() == null,
                            "execute did not release fid after the action on task %s.", index);
                    successfulIterations.incrementAndGet();
                }
            } catch (Throwable ex) {
                errors.add(ex);
            } finally {
                doneSignal.countDown();
            }
        }));
        startSignal.countDown();
        assertTrue("Workers did not complete within the timeout : possible deadlock.", doneSignal.await(2, MINUTES));
        if (!errors.isEmpty())
            throw new AssertionError("Detected " + errors.size() + " failure(s) on the shared transaction under execute(). "
                    + "First failure : " + errors.peek().getMessage(), errors.peek());
        assertEquals(THREADS * ITERATIONS, successfulIterations.get());
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@{} verified {} execute() iterations across {} virtual threads on a shared transaction.\n",
                this.getClass().getSimpleName(), successfulIterations.get(), THREADS);
    }

    @Test
    public void b_executeReleasesStateWhenTheActionThrows() throws Exception {
        WFSTransactionRequestState state = (WFSTransactionRequestState) sharedRequest;
        QName typeName = new QName(TOPP_NAMESPACE, "states_boom", "topp");
        IllegalStateException boom = new IllegalStateException("boom");
        try {
            sharedRequest.execute(
                    r -> r.withOperation(DELETE).withTypeName(typeName).withFID("fid_boom"),
                    r -> {
                        throw boom;
                    });
            fail("execute must propagate the exception thrown by the action.");
        } catch (Exception ex) {
            assertSame("execute must propagate the very exception thrown by the action.", boom, ex);
        }
        assertNull("execute must release operation even when the action throws.", state.getOperation());
        assertNull("execute must release typeName even when the action throws.", state.getTypeName());
        assertNull("execute must release fid even when the action throws.", state.getFID());
    }

    @Test
    public void c_singleArgumentExecuteReleasesStateAfterTheAction() throws Exception {
        WFSTransactionRequestState state = (WFSTransactionRequestState) sharedRequest;
        QName typeName = new QName(TOPP_NAMESPACE, "states_single", "topp");
        String requestAsString = sharedRequest.execute(r -> {
            r.withOperation(DELETE).withTypeName(typeName).withFID("fid_single");
            return r.showRequestAsString();
        });
        assertTrue("the marshalled transaction must carry the configured typeName.",
                requestAsString.contains("states_single"));
        assertTrue("the marshalled transaction must carry the configured fid.",
                requestAsString.contains("fid_single"));
        assertNull("the single-argument execute must release the operation after the action.", state.getOperation());
        assertNull("the single-argument execute must release the typeName after the action.", state.getTypeName());
    }
}