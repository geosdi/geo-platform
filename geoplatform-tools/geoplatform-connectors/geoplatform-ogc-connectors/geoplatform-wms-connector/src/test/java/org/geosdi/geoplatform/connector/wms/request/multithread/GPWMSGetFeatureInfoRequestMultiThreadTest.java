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
package org.geosdi.geoplatform.connector.wms.request.multithread;

import org.geosdi.geoplatform.connector.server.request.GPWMSBoundingBox;
import org.geosdi.geoplatform.connector.server.request.GPWMSGetMapBaseRequest;
import org.geosdi.geoplatform.connector.server.request.WMSBoundingBox;
import org.geosdi.geoplatform.connector.server.request.WMSFeatureInfoFormat;
import org.geosdi.geoplatform.connector.server.request.WMSGetMapBaseRequest;
import org.geosdi.geoplatform.connector.server.v111.GPWMSGetFeatureInfoV111Request;
import org.geosdi.geoplatform.connector.server.v111.IGPWMSConnectorStoreV111;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.net.URI;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import static com.google.common.base.Preconditions.checkArgument;
import static io.reactivex.rxjava3.core.Flowable.fromIterable;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static java.util.stream.Stream.of;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfigBuilder.PooledConnectorConfigBuilder.pooledConnectorConfigBuilder;
import static org.geosdi.geoplatform.connector.server.request.WMSFeatureInfoFormat.GML2;
import static org.geosdi.geoplatform.connector.server.request.WMSFeatureInfoFormat.GML3;
import static org.geosdi.geoplatform.connector.server.store.GPWMSConnectorBuilder.WMSConnectorBuilder.wmsConnectorBuilder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Verifies the thread-safety contract of {@link GPWMSGetFeatureInfoV111Request} : a <b>single, shared</b> request
 * instance can be configured with different parameters (query layers, x, y, featureCount, ...) concurrently from many
 * threads, and each thread always reads back its own configuration. This is exactly what the {@code ThreadLocal} state
 * inside {@code GPWMSBaseGetFeatureInfoRequest} guarantees : if that state were kept in plain instance fields, threads
 * would clobber each other and the per-thread assertions below would fail non-deterministically.
 * <p>
 * The test is deterministic and does <b>not</b> perform any network call : it relies on
 * {@link GPWMSGetFeatureInfoV111Request#showRequestAsString()}, which only builds the request URI from the current
 * per-thread state.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWMSGetFeatureInfoRequestMultiThreadTest {

    private static final Logger logger = LoggerFactory.getLogger(GPWMSGetFeatureInfoRequestMultiThreadTest.class);
    //
    private static final int THREADS = 16;
    private static final int ITERATIONS = 250;
    //
    private static IGPWMSConnectorStoreV111 wmsServerConnector;
    /**
     * The one instance intentionally shared by every worker thread : its thread-safety is what we are testing.
     */
    private static GPWMSGetFeatureInfoV111Request<Object> sharedRequest;

    @BeforeClass
    public static void beforeClass() throws Exception {
        wmsServerConnector = wmsConnectorBuilder()
                .wmsConnectorBuilderV111()
                .withServerUrl(new URI("http://localhost:8080/geoserver/wms").toURL())
                .withPooledConnectorConfig(pooledConnectorConfigBuilder()
                        .withMaxTotalConnections(20)
                        .withDefaultMaxPerRoute(8)
                        .withMaxRedirect(5)
                        .build())
                .build();
        sharedRequest = wmsServerConnector.createGetFeatureInfoRequest();
    }

    @Test
    public void wmsGetFeatureInfoRequestMultiThreadTest() throws Exception {
        List<GPWMSGetFeatureInfoRequestTask> tasks = range(0, THREADS)
                .mapToObj(GPWMSGetFeatureInfoRequestTask::new)
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

    static final class GPWMSGetFeatureInfoRequestTask implements Runnable {

        private final int index;
        private final String queryLayer;
        private final Integer x;
        private final Integer y;
        private final Integer featureCount;
        private final WMSFeatureInfoFormat infoFormat;
        private final GPWMSGetMapBaseRequest wmsGetMapBaseRequest;
        //
        private CountDownLatch startSignal;
        private CountDownLatch doneSignal;
        private AtomicInteger successfulIterations;
        private Queue<Throwable> errors;

        GPWMSGetFeatureInfoRequestTask(int theIndex) {
            this.index = theIndex;
            this.queryLayer = "topp:layer_" + theIndex;
            this.x = 100 + theIndex;
            this.y = 200 + theIndex;
            this.featureCount = 1 + theIndex;
            this.infoFormat = ((theIndex % 2) == 0) ? GML2 : GML3;
            GPWMSBoundingBox boundingBox = new WMSBoundingBox(-130d - theIndex, 24d, -66d, 50d + theIndex);
            this.wmsGetMapBaseRequest = new WMSGetMapBaseRequest(boundingBox, of(this.queryLayer).collect(toList()),
                    "EPSG:4326", String.valueOf(550 + theIndex), String.valueOf(250 + theIndex));
        }

        GPWMSGetFeatureInfoRequestTask prepare(@Nonnull(when = NEVER) CountDownLatch theStartSignal, @Nonnull(when = NEVER) CountDownLatch theDoneSignal,
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
                    String uriPath = sharedRequest
                            .withWMSGetMapRequest(this.wmsGetMapBaseRequest)
                            .withQueryLayers(this.queryLayer)
                            .withInfoFormat(this.infoFormat)
                            .withFeatureCount(this.featureCount)
                            .withX(this.x)
                            .withY(this.y)
                            .showRequestAsString();
                    this.assertOwnState(uriPath);
                    this.successfulIterations.incrementAndGet();
                }
            } catch (Throwable ex) {
                this.errors.add(ex);
            } finally {
                this.doneSignal.countDown();
            }
        }

        /**
         * Asserts that the URI built for this thread carries this thread's own parameters, never another thread's.
         *
         * @param uriPath the request URI produced by {@link GPWMSGetFeatureInfoV111Request#showRequestAsString()}
         */
        private void assertOwnState(@Nonnull(when = NEVER) String uriPath) {
            String getFeatureInfoParams = "&QUERY_LAYERS=" + this.queryLayer + "&INFO_FORMAT=" + this.infoFormat.getFormat()
                    + "&X=" + this.x + "&Y=" + this.y + "&FEATURE_COUNT=" + this.featureCount;
            checkArgument(uriPath.contains(getFeatureInfoParams),
                    "Cross-thread state leak on task %s : expected the URI to contain [%s] but was [%s].",
                    this.index, getFeatureInfoParams, uriPath);
        }
    }
}