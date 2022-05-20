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
                "&layers=test,prova,admin:prova,admin:test,topp:states,siti_protetti:zsc,retenatura:zsc,topp%3Atasmania_roads,topp:states,siti_protetti:zsc,retenatura:zsc&bbox=145.19754%2C-43.423512%2C148.27298000000002%2C-40.852802&width=899&height=368&srs=EPSG%3A4326&format=application/openlayers",
                "http://150.145.141.92/geoserver/topp/wms?SERVICE=WMS&VERSION=1.1.1&REQUEST=GetFeatureInfo&FORMAT=image%2Fpng&TRANSPARENT=true&QUERY_LAYERS=topp%3Atasmania_cities&STYLES&LAYERS=topp%3Atasmania_cities&exceptions=application%2Fvnd.ogc.se_inimage&INFO_FORMAT=text%2Fhtml&FEATURE_COUNT" +
                        "=50&X=50&Y=50&SRS=EPSG%3A4326&WIDTH=101&HEIGHT=101&BBOX=147.00470186769962%2C-43.117031436413534%2C147.55951143801212%2C-42.562221866101034",
                "https://sit2.regione.campania.it/geoserver/RegioneCampania.Cartografia.Tematica/wms?service=WMS&version=1.1.0&" +
                        "request=GetMap&layers=RegioneCampania.Cartografia.Tematica:sitdbo_curve_livello_25m&styles=&bbox=394273.34375,4426208.0,571791.3125,4601018.0&width=768&height=756&srs=EPSG:3045&format=application/openlayers",
                "https://sit2.regione.campania.it/geoserver/RegioneCampania.Catalogo/wms?service=WMS&version=1.1.0&request=GetMap" +
                        "&layers=RegioneCampania.Catalogo:sitdbo_corine_land_cover_90&styles=&bbox=395346.3125,4426030.5,569392.125,4596345.5" +
                        "&width=768&height=751&srs=EPSG:3045&format=application/openlayers",
                "https://wms.cfr.toscana.it/geoserver/tmp/wms?service=WMS&version=1.1.0&request=GetMap&layers=tmp%3Asitc_asl" +
                        "&bbox=1554750.625%2C4678325.5%2C1771722.875%2C4924792.0&width=676&height=768" +
                        "&srs=EPSG%3A3003&format=application/openlayers&p=fake")
                .collect(toList());
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(urls.size());
        AtomicInteger counter = new AtomicInteger(0);
        fromIterable(urls)
                .map(url -> new Thread(new GPWMSGetMapBaseRequestBuilderTask(url, startSignal, doneSignal, counter)))
                .subscribe(Thread::start, Throwable::printStackTrace);
        startSignal.countDown();
        doneSignal.await();
        assertTrue(counter.get() == urls.size());
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@{} process {} urls", this.getClass().getSimpleName(), counter.get());
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