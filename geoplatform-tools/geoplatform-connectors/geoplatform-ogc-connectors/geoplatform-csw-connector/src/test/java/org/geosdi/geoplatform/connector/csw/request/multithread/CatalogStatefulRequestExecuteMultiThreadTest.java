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
import org.geosdi.geoplatform.connector.server.request.CatalogGetRecordByIdRequest;
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
import static org.geosdi.geoplatform.xml.csw.OutputSchema.GMD;
import static org.geosdi.geoplatform.xml.csw.TypeName.RECORD_V202;
import static org.geosdi.geoplatform.xml.csw.v202.ElementSetType.FULL;
import static org.geosdi.geoplatform.xml.csw.v202.ResultType.RESULTS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Verifies the {@code execute(...)} template of
 * {@link org.geosdi.geoplatform.connector.server.request.CatalogStatefulRequest} on <b>single, shared</b>
 * {@code GetRecords} / {@code GetRecordById} requests driven concurrently by many <b>virtual threads</b> - the
 * exact scenario that motivates the template : {@code execute} is meant to be used <b>only when the same
 * request instance is shared/reused across several threads</b>, because a long-lived shared instance would
 * otherwise retain each thread's last {@code ThreadLocal} configuration. (When a fresh request is created per
 * call, as the production services do, the state dies with the instance and {@code execute} is not needed.)
 * <p>
 * It asserts that : (a) under concurrency each thread always sees its own configuration inside the action, and
 * after every {@code execute} its per-thread state has been released; (b) the state is released even when the
 * action throws (the {@code finally clearState()} contract); (c) the single-argument overload releases the
 * state too; (d) the same holds for {@code GetRecordById}. No network call is performed : the actions rely on
 * {@code showRequestAsString()}, which only marshals the current per-thread state.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CatalogStatefulRequestExecuteMultiThreadTest {

    private static final Logger logger = LoggerFactory.getLogger(CatalogStatefulRequestExecuteMultiThreadTest.class);
    //
    private static final int THREADS = 16;
    private static final int ITERATIONS = 100;
    //
    private static GPCatalogConnectorStore serverConnector;
    /**
     * The instances intentionally shared by every worker thread : the {@code execute(...)} contract on a shared
     * instance is what we are testing.
     */
    private static CatalogGetRecordsRequest<Object> sharedGetRecords;
    private static CatalogGetRecordByIdRequest<Object> sharedGetRecordById;

    @BeforeClass
    public static void beforeClass() throws Exception {
        serverConnector = newConnector()
                .withServerUrl(new URI("http://localhost:8080/geonetwork/srv/eng/csw").toURL())
                .build();
        sharedGetRecords = serverConnector.createGetRecordsRequest();
        sharedGetRecordById = serverConnector.createGetRecordByIdRequest();
    }

    @Test
    public void a_executeIsThreadSafeAndAlwaysReleasesStateAcrossVirtualThreads() throws Exception {
        CatalogGetRecordsRequestState state = (CatalogGetRecordsRequestState) sharedGetRecords;
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(THREADS);
        AtomicInteger successfulIterations = new AtomicInteger(0);
        Queue<Throwable> errors = new ConcurrentLinkedQueue<>();
        range(0, THREADS).forEach(index -> Thread.startVirtualThread(() -> {
            try {
                String constraint = "AnyText LIKE '%execute_" + index + "_end%'";
                BigInteger maxRecords = BigInteger.valueOf(index + 1L);
                startSignal.await();
                for (int i = 0; i < ITERATIONS; i++) {
                    String requestAsString = sharedGetRecords.execute(
                            r -> r.withTypeName(RECORD_V202)
                                    .withOutputSchema(CSW_V202)
                                    .withElementSetName(FULL.value())
                                    .withResultType(RESULTS.value())
                                    .withConstraintLanguage(CQL_TEXT)
                                    .withConstraintLanguageVersion(V110)
                                    .withConstraint(constraint)
                                    .withMaxRecords(maxRecords),
                            CatalogGetRecordsRequest::showRequestAsString);
                    checkArgument(requestAsString.contains("execute_" + index + "_end"),
                            "Cross-thread state leak on task %s : expected constraint [%s] but was [%s].",
                            index, constraint, requestAsString);
                    checkArgument(requestAsString.contains("maxRecords=\"" + maxRecords + "\""),
                            "Cross-thread state leak on task %s : expected maxRecords [%s] but was [%s].",
                            index, maxRecords, requestAsString);
                    checkArgument(state.getTypeName() == null,
                            "execute did not release typeName after the action on task %s.", index);
                    checkArgument(state.getConstraint() == null,
                            "execute did not release constraint after the action on task %s.", index);
                    checkArgument(state.getMaxRecords() == null,
                            "execute did not release maxRecords after the action on task %s.", index);
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
            throw new AssertionError("Detected " + errors.size() + " failure(s) on the shared request under execute(). "
                    + "First failure : " + errors.peek().getMessage(), errors.peek());
        assertEquals(THREADS * ITERATIONS, successfulIterations.get());
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@{} verified {} execute() iterations across {} virtual threads on a shared request.\n",
                this.getClass().getSimpleName(), successfulIterations.get(), THREADS);
    }

    @Test
    public void b_executeReleasesStateWhenTheActionThrows() throws Exception {
        CatalogGetRecordsRequestState state = (CatalogGetRecordsRequestState) sharedGetRecords;
        IllegalStateException boom = new IllegalStateException("boom");
        try {
            sharedGetRecords.execute(
                    r -> r.withTypeName(RECORD_V202).withMaxRecords(BigInteger.TEN),
                    r -> {
                        throw boom;
                    });
            fail("execute must propagate the exception thrown by the action.");
        } catch (Exception ex) {
            assertSame("execute must propagate the very exception thrown by the action.", boom, ex);
        }
        assertNull("execute must release typeName even when the action throws.", state.getTypeName());
        assertNull("execute must release maxRecords even when the action throws.", state.getMaxRecords());
    }

    @Test
    public void c_singleArgumentExecuteReleasesStateAfterTheAction() throws Exception {
        CatalogGetRecordsRequestState state = (CatalogGetRecordsRequestState) sharedGetRecords;
        String requestAsString = sharedGetRecords.execute(r -> {
            r.withTypeName(RECORD_V202)
                    .withOutputSchema(CSW_V202)
                    .withResultType(RESULTS.value())
                    .withConstraintLanguage(CQL_TEXT)
                    .withConstraintLanguageVersion(V110)
                    .withConstraint("AnyText LIKE '%single_argument%'")
                    .withMaxRecords(BigInteger.ONE);
            return r.showRequestAsString();
        });
        assertTrue("the marshalled request must carry the configured constraint.",
                requestAsString.contains("single_argument"));
        assertNull("the single-argument execute must release the constraint after the action.", state.getConstraint());
        assertNull("the single-argument execute must release maxRecords after the action.", state.getMaxRecords());
    }

    @Test
    public void d_getRecordByIdExecuteIsThreadSafeAndAlwaysReleasesState() throws Exception {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(THREADS);
        AtomicInteger successfulIterations = new AtomicInteger(0);
        Queue<Throwable> errors = new ConcurrentLinkedQueue<>();
        range(0, THREADS).forEach(index -> Thread.startVirtualThread(() -> {
            try {
                String id = "record_" + index + "_end";
                startSignal.await();
                for (int i = 0; i < ITERATIONS; i++) {
                    String requestAsString = sharedGetRecordById.execute(
                            r -> r.withId(id)
                                    .withOutputSchema(GMD)
                                    .withElementSetType(FULL.value()),
                            CatalogGetRecordByIdRequest::showRequestAsString);
                    checkArgument(requestAsString.contains(id),
                            "Cross-thread state leak on task %s : expected id [%s] but was [%s].",
                            index, id, requestAsString);
                    // The state has been released : a terminal call now fails on the missing mandatory id.
                    try {
                        sharedGetRecordById.showRequestAsString();
                        throw new IllegalStateException("execute did not release the id on task " + index + ".");
                    } catch (IllegalArgumentException expected) {
                    }
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
            throw new AssertionError("Detected " + errors.size() + " failure(s) on the shared GetRecordById request "
                    + "under execute(). First failure : " + errors.peek().getMessage(), errors.peek());
        assertEquals(THREADS * ITERATIONS, successfulIterations.get());
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@{} verified {} GetRecordById execute() iterations across {} virtual threads.\n",
                this.getClass().getSimpleName(), successfulIterations.get(), THREADS);
    }
}