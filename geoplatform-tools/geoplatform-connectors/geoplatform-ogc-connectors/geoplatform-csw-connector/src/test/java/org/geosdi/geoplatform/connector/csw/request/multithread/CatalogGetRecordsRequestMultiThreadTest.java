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
package org.geosdi.geoplatform.connector.csw.request.multithread;

import org.geosdi.geoplatform.connector.GPCatalogConnectorStore;
import org.geosdi.geoplatform.connector.server.request.CatalogGetRecordsRequest;
import org.geosdi.geoplatform.connector.server.request.CatalogGetRecordsRequestState;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.net.URI;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.stream.IntStream.range;
import static org.geosdi.geoplatform.connector.GPCSWConnectorBuilder.newConnector;
import static org.geosdi.geoplatform.xml.csw.ConstraintLanguage.CQL_TEXT;
import static org.geosdi.geoplatform.xml.csw.ConstraintLanguageVersion.V110;
import static org.geosdi.geoplatform.xml.csw.OutputSchema.CSW_V202;
import static org.geosdi.geoplatform.xml.csw.TypeName.RECORD_V202;
import static org.geosdi.geoplatform.xml.csw.v202.ElementSetType.BRIEF;
import static org.geosdi.geoplatform.xml.csw.v202.ElementSetType.FULL;
import static org.geosdi.geoplatform.xml.csw.v202.ResultType.HITS;
import static org.geosdi.geoplatform.xml.csw.v202.ResultType.RESULTS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Verifies that a <b>single, shared</b> {@code GetRecords} request can be configured concurrently by many
 * <b>virtual threads</b> without cross-thread clobbering : the fluent {@code withXxx(...)} mutators write into
 * {@link ThreadLocal} state, so each thread only ever observes its own configuration - both through the
 * {@link CatalogGetRecordsRequestState} read view and in the marshalled request. No network call is performed :
 * the assertions rely on {@code showRequestAsString()}, which only marshals the current per-thread state.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CatalogGetRecordsRequestMultiThreadTest {

    private static final Logger logger = LoggerFactory.getLogger(CatalogGetRecordsRequestMultiThreadTest.class);
    //
    private static final int THREADS = 16;
    private static final int ITERATIONS = 100;
    //
    private static GPCatalogConnectorStore serverConnector;
    /**
     * The one instance intentionally shared by every worker thread : the thread-safety of a shared request is
     * exactly what we are testing.
     */
    private static CatalogGetRecordsRequest<Object> sharedRequest;

    @BeforeClass
    public static void beforeClass() throws Exception {
        serverConnector = newConnector()
                .withServerUrl(new URI("http://localhost:8080/geonetwork/srv/eng/csw").toURL())
                .build();
        sharedRequest = serverConnector.createGetRecordsRequest();
    }

    @Test
    public void a_theSharedRequestKeepsItsConfigurationIsolatedPerThread() throws Exception {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(THREADS);
        AtomicInteger successfulIterations = new AtomicInteger(0);
        Queue<Throwable> errors = new ConcurrentLinkedQueue<>();
        range(0, THREADS).forEach(index -> Thread.startVirtualThread(() -> {
            try {
                String constraint = "AnyText LIKE '%thread_" + index + "_end%'";
                BigInteger maxRecords = BigInteger.valueOf(index + 1L);
                BigInteger startPosition = BigInteger.valueOf(index + 100L);
                String elementSetName = ((index % 2) == 0) ? FULL.value() : BRIEF.value();
                String resultType = ((index % 2) == 0) ? RESULTS.value() : HITS.value();
                startSignal.await();
                for (int i = 0; i < ITERATIONS; i++) {
                    sharedRequest.withTypeName(RECORD_V202)
                            .withOutputSchema(CSW_V202)
                            .withElementSetName(elementSetName)
                            .withResultType(resultType)
                            .withConstraintLanguage(CQL_TEXT)
                            .withConstraintLanguageVersion(V110)
                            .withConstraint(constraint)
                            .withMaxRecords(maxRecords)
                            .withStartPosition(startPosition);
                    String requestAsString = sharedRequest.showRequestAsString();
                    checkArgument(requestAsString.contains("thread_" + index + "_end"),
                            "Cross-thread state leak on task %s : expected constraint [%s] but was [%s].",
                            index, constraint, requestAsString);
                    checkArgument(requestAsString.contains("maxRecords=\"" + maxRecords + "\""),
                            "Cross-thread state leak on task %s : expected maxRecords [%s] but was [%s].",
                            index, maxRecords, requestAsString);
                    checkArgument(requestAsString.contains("startPosition=\"" + startPosition + "\""),
                            "Cross-thread state leak on task %s : expected startPosition [%s] but was [%s].",
                            index, startPosition, requestAsString);
                    checkArgument(requestAsString.contains(elementSetName),
                            "Cross-thread state leak on task %s : expected elementSetName [%s] but was [%s].",
                            index, elementSetName, requestAsString);
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
            throw new AssertionError("Detected " + errors.size() + " failure(s) on the shared GetRecords request. "
                    + "First failure : " + errors.peek().getMessage(), errors.peek());
        assertEquals(THREADS * ITERATIONS, successfulIterations.get());
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@{} verified {} iterations across {} virtual threads on a shared request.\n",
                this.getClass().getSimpleName(), successfulIterations.get(), THREADS);
    }

    @Test
    public void b_aConfigurationAppliedOnOneThreadIsInvisibleToTheOthers() throws Exception {
        CatalogGetRecordsRequestState state = (CatalogGetRecordsRequestState) sharedRequest;
        sharedRequest.withTypeName(RECORD_V202)
                .withMaxRecords(BigInteger.TEN)
                .withConstraint("AnyText LIKE '%main_thread%'");
        Queue<Throwable> errors = new ConcurrentLinkedQueue<>();
        Thread worker = Thread.ofVirtual().start(() -> {
            try {
                checkArgument(state.getTypeName() == null, "typeName must not leak to another thread.");
                checkArgument(state.getMaxRecords() == null, "maxRecords must not leak to another thread.");
                checkArgument(state.getConstraint() == null, "constraint must not leak to another thread.");
            } catch (Throwable ex) {
                errors.add(ex);
            }
        });
        worker.join();
        if (!errors.isEmpty())
            throw new AssertionError("The shared request leaked its state across threads : "
                    + errors.peek().getMessage(), errors.peek());
        assertEquals("the configuring thread must still see its own state.", BigInteger.TEN, state.getMaxRecords());

        sharedRequest.clearState();

        assertNull("clearState must release the state of the calling thread.", state.getMaxRecords());
    }
}