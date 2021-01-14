/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import org.geosdi.geoplatform.connector.server.request.GPWMSGetMapBaseRequest;
import org.geosdi.geoplatform.connector.server.request.GPWMSGetMapBaseRequestBuilder;
import org.geosdi.geoplatform.connector.server.request.kvp.GPWMSKeyValuePairBuilder.GPWMSBaseKeyValuePairBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import static com.google.common.base.Preconditions.checkArgument;
import static io.reactivex.rxjava3.core.Flowable.fromIterable;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.server.request.GPWMSGetMapBaseRequestBuilder.builder;
import static org.junit.Assert.assertTrue;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWMSGetMapBaseRequestBuilderMultiThreadTest {

    private static final Logger logger = LoggerFactory.getLogger(GPWMSGetMapBaseRequestBuilderMultiThreadTest.class);
    //
    private static final GPWMSBaseKeyValuePairBuilder<GPWMSGetMapBaseRequest, GPWMSGetMapBaseRequestBuilder> builder = builder();

    @Test
    public void wmGetMapBaseRequestBuilderMultiThreadTest() throws Exception {
        List<String> urls = of("http://150.145.141.180/geoserver/topp/wms?service=WMS&version=1.1.0&request=GetMap&layers=topp%3Atasmania_roads&bbox=145.19754%2C-43.423512%2C148.27298000000002%2C-40.852802&width=551&height=545&srs=EPSG%3A4326&format=application/openlayers",
                "service=WMS&version=1.1.0&request=GetMap&layers=topp%3Atasmania_roads,topp:states,siti_protetti:zsc,retenatura:zsc&bbox=145.19754%2C-43.423512%2C148.27298000000002%2C-40.852802&width=768&height=641&srs=EPSG%3A4326&format=application/openlayers",
                "service=WMS&version=1.1.0&request=GetMap&layers=test,prova,topp:states,siti_protetti:zsc,retenatura:zsc,topp%3Atasmania_roads,topp:states,siti_protetti:zsc,retenatura:zsc&bbox=145.19754%2C-43.423512%2C148.27298000000002%2C-40.852802&width=345&height=800&srs=EPSG%3A4326&format=application/openlayers",
                "&version=1.1.0&  &layers=test,prova,topp:states,siti_protetti:zsc,retenatura:zsc,topp%3Atasmania_roads,topp:states,siti_protetti:zsc,retenatura:zsc&bbox=145.19754%2C-43.423512%2C148.27298000000002%2C-40.852802&width=256&height=765&srs=EPSG%3A4326&format=application/openlayers",
                "&layers=test,prova,admin:prova,admin:test,topp:states,siti_protetti:zsc,retenatura:zsc,topp%3Atasmania_roads,topp:states,siti_protetti:zsc,retenatura:zsc&bbox=145.19754%2C-43.423512%2C148.27298000000002%2C-40.852802&width=899&height=368&srs=EPSG%3A4326&format=application/openlayers")
                .collect(toList());
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(urls.size());
        AtomicInteger counter = new AtomicInteger(0);
        fromIterable(urls)
                .map(url -> new Thread(new GPWMSGetMapBaseRequestBuilderTask(url, startSignal, doneSignal, counter)))
                .subscribe(Thread::start);
        startSignal.countDown();
        doneSignal.await();
        assertTrue(counter.get() == 5);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@{} process {} files", this.getClass().getSimpleName(), counter.get());
    }

    static final class GPWMSGetMapBaseRequestBuilderTask implements Runnable {

        private static final Logger logger = LoggerFactory.getLogger(GPWMSGetMapBaseRequestBuilderTask.class);
        //
        private final String url;
        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;
        private final AtomicInteger counter;

        /**
         * @param theURL
         * @param theStartSignal
         * @param theDoneSignal
         * @param theCounter
         */
        GPWMSGetMapBaseRequestBuilderTask(@Nonnull(when = NEVER) String theURL, @Nonnull(when = NEVER) CountDownLatch theStartSignal,
                @Nonnull(when = NEVER) CountDownLatch theDoneSignal, @Nonnull(when = NEVER) AtomicInteger theCounter) {
            checkArgument((theURL != null) && !(theURL.trim().isEmpty()), "The Parameter url must not be null or an empty string.");
            checkArgument(theStartSignal != null, "The Parameter startSignal must not be null.");
            checkArgument(theDoneSignal != null, "The Parameter doneSignal must not be null.");
            checkArgument(theCounter != null, "The Parameter counter must not be null.");
            this.url = theURL;
            this.startSignal = theStartSignal;
            this.doneSignal = theDoneSignal;
            this.counter = theCounter;
        }

        /**
         * When an object implementing interface {@code Runnable} is used
         * to create a thread, starting the thread causes the object's
         * {@code run} method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method {@code run} is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            try {
                startSignal.await();
                logger.info("#######################{} Trying to build for URL : {}\n", this.getClass().getSimpleName(), this.url);
                logger.info("######################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder.withKeyValuePair(this.url).build());
                this.counter.incrementAndGet();
                doneSignal.countDown();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}