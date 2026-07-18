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
import org.geosdi.geoplatform.connector.server.request.WFSGetFeatureRequest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.xml.namespace.QName;
import java.math.BigInteger;
import java.net.URI;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import static com.google.common.base.Preconditions.checkArgument;
import static io.reactivex.rxjava3.core.Flowable.fromIterable;
import static java.util.Arrays.asList;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.WFSConnectorBuilder.newConnector;
import static org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfigBuilder.PooledConnectorConfigBuilder.pooledConnectorConfigBuilder;
import static org.geosdi.geoplatform.xml.wfs.v110.ResultTypeType.HITS;
import static org.geosdi.geoplatform.xml.wfs.v110.ResultTypeType.RESULTS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Verifies the thread-safety contract of the WFS {@code GetFeature} request : a <b>single, shared</b> request
 * instance can be configured with different parameters (typeName, featureIDs, resultType, maxFeatures, ...)
 * concurrently from many threads, and each thread always reads back its own configuration. This is exactly
 * what the {@code ThreadLocal} state inside {@code AbstractGetFeatureRequest} guarantees : if that state were
 * kept in plain instance fields, threads would clobber each other and the per-thread assertions below would
 * fail non-deterministically.
 * <p>
 * The test is deterministic and does <b>not</b> perform any network call : it relies on
 * {@link WFSGetFeatureRequest#showRequestAsString()}, which only marshals the request built from the current
 * per-thread state.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WFSGetFeatureRequestMultiThreadTest {

    private static final Logger logger = LoggerFactory.getLogger(WFSGetFeatureRequestMultiThreadTest.class);
    //
    private static final int THREADS = 16;
    private static final int ITERATIONS = 250;
    private static final String TOPP_NAMESPACE = "http://www.openplans.org/topp";
    //
    private static GPWFSConnectorStore serverConnector;
    /**
     * The one instance intentionally shared by every worker thread : its thread-safety is what we are testing.
     */
    private static WFSGetFeatureRequest<Object> sharedRequest;

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
        sharedRequest = serverConnector.createGetFeatureRequest();
    }

    @Test
    public void wfsGetFeatureRequestMultiThreadTest() throws Exception {
        List<WFSGetFeatureRequestTask> tasks = range(0, THREADS)
                .mapToObj(WFSGetFeatureRequestTask::new)
                .collect(toList());
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(tasks.size());
        AtomicInteger successfulIterations = new AtomicInteger(0);
        Queue<Throwable> errors = new ConcurrentLinkedQueue<>();
        fromIterable(tasks)
                .map(task -> task.prepare(startSignal, doneSignal, successfulIterations, errors))
                .subscribe(Thread::startVirtualThread, Throwable::printStackTrace);
        startSignal.countDown();
        assertTrue("Workers did not complete within the timeout : possible deadlock.", doneSignal.await(2, MINUTES));
        if (!errors.isEmpty())
            throw new AssertionError("Detected " + errors.size() + " cross-thread state leak(s) on the shared request. "
                    + "First failure : " + errors.peek().getMessage(), errors.peek());
        assertEquals(THREADS * ITERATIONS, successfulIterations.get());
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@{} verified {} iterations across {} threads on a shared request.\n",
                this.getClass().getSimpleName(), successfulIterations.get(), THREADS);
    }

    static final class WFSGetFeatureRequestTask implements Runnable {

        private final int index;
        private final QName typeName;
        private final String featureId;
        private final String resultType;
        private final BigInteger maxFeatures;
        //
        private CountDownLatch startSignal;
        private CountDownLatch doneSignal;
        private AtomicInteger successfulIterations;
        private Queue<Throwable> errors;

        WFSGetFeatureRequestTask(int theIndex) {
            this.index = theIndex;
            this.typeName = new QName(TOPP_NAMESPACE, "states_" + theIndex + "_end", "topp");
            this.featureId = "fid_" + theIndex + "_end";
            this.resultType = ((theIndex % 2) == 0) ? RESULTS.value() : HITS.value();
            this.maxFeatures = BigInteger.valueOf(theIndex + 1L);
        }

        WFSGetFeatureRequestTask prepare(@Nonnull(when = NEVER) CountDownLatch theStartSignal, @Nonnull(when = NEVER) CountDownLatch theDoneSignal,
                @Nonnull(when = NEVER) AtomicInteger theSuccessfulIterations, @Nonnull(when = NEVER) Queue<Throwable> theErrors) {
            checkArgument(theStartSignal != null, "The Parameter startSignal must not be null.");
            checkArgument(theDoneSignal != null, "The Parameter doneSignal must not be null.");
            checkArgument(theSuccessfulIterations != null, "The Parameter successfulIterations must not be null.");
            checkArgument(theErrors != null, "The Parameter errors must not be null.");
            this.startSignal = theStartSignal;
            this.doneSignal = theDoneSignal;
            this.successfulIterations = theSuccessfulIterations;
            this.errors = theErrors;
            return this;
        }

        @Override
        public void run() {
            try {
                this.startSignal.await();
                for (int i = 0; i < ITERATIONS; i++) {
                    String requestAsString = sharedRequest
                            .withTypeName(this.typeName)
                            .withResultType(this.resultType)
                            .withMaxFeatures(this.maxFeatures)
                            .withFeatureIDs(asList(this.featureId))
                            .showRequestAsString();
                    this.assertOwnState(requestAsString);
                    this.successfulIterations.incrementAndGet();
                }
            } catch (Throwable ex) {
                this.errors.add(ex);
            } finally {
                this.doneSignal.countDown();
            }
        }

        /**
         * Asserts that the request marshalled for this thread carries this thread's own typeName and featureId,
         * never another thread's.
         *
         * @param requestAsString the request produced by {@link WFSGetFeatureRequest#showRequestAsString()}
         */
        private void assertOwnState(@Nonnull(when = NEVER) String requestAsString) {
            checkArgument(requestAsString.contains(this.typeName.getLocalPart()),
                    "Cross-thread state leak on task %s : expected the request to contain typeName [%s] but was [%s].",
                    this.index, this.typeName.getLocalPart(), requestAsString);
            checkArgument(requestAsString.contains(this.featureId),
                    "Cross-thread state leak on task %s : expected the request to contain featureId [%s] but was [%s].",
                    this.index, this.featureId, requestAsString);
        }
    }
}
