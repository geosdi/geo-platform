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
package org.geosdi.geoplatform.connector.wfs.transaction.stax;

import org.geosdi.geoplatform.connector.server.request.v110.WFSTransactionRequestV110;
import org.geosdi.geoplatform.connector.server.request.v110.transaction.stax.FeatureStreamWriter;
import org.geosdi.geoplatform.connector.wfs.response.GeometryAttributeDTO;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.xml.namespace.QName;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.String.valueOf;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.iterate;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.gui.shared.wfs.TransactionOperation.INSERT;
import static org.geosdi.geoplatform.gui.shared.wfs.TransactionOperation.UPDATE;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class FeatureStreamWriterMultiThreadTest {

    private static final Logger logger = LoggerFactory.getLogger(FeatureStreamWriterMultiThreadTest.class);
    //
    private static final Integer tasks = 100;
    private static final FeatureStreamWriter featureStreamWriter = new FeatureStreamWriter();

    @Test
    public void featureStreamWriterMultiThreadTest() throws Exception {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(tasks);
        AtomicInteger counter = new AtomicInteger(0);
        iterate(0, n -> n + 1)
                .limit(tasks)
                .boxed()
                .map(v -> new FeatureStreamWriterTask(startSignal, doneSignal, counter))
                .map(runnable -> new Thread(runnable))
                .forEach(Thread::start);
        //Do whatever you want
        startSignal.countDown();
        doneSignal.await();
        assertTrue(counter.get() == tasks);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@{} process {} files", this.getClass().getSimpleName(), counter.get());
    }

    static class FeatureStreamWriterTask implements Runnable {

        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;
        private final AtomicInteger counter;

        /**
         * @param theStartSignal
         * @param theDoneSignal
         * @param theCounter
         */
        FeatureStreamWriterTask(@Nonnull(when = NEVER) CountDownLatch theStartSignal, @Nonnull(when = NEVER) CountDownLatch theDoneSignal,
                @Nonnull(when = NEVER) AtomicInteger theCounter) {
            checkArgument(theStartSignal != null, "The Parameter startSignal must not be null.");
            checkArgument(theDoneSignal != null, "The Parameter doneSignal must not be null.");
            checkArgument(theCounter != null, "The Parameter counter must not be null.");
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
                startSignal.await();         //The thread keeps waiting till it is informed
                String value = valueOf(counter.getAndIncrement());
                QName qName = new QName("http://www.openplans.org/topp_".concat(value),
                        "topp:tasmania_roads_".concat(value), "topp_".concat(value));
                WFSTransactionRequestV110 request = mock(WFSTransactionRequestV110.class);
                when(request.getOperation()).thenReturn(counter.get() % 2 == 0 ? INSERT : UPDATE);
                when(request.getFID()).thenReturn("fid_test_".concat(value));
                when(request.getTypeName()).thenReturn(qName);
                List values = iterate(0, n -> n + 1)
                        .limit((counter.get() % 2 == 0) ? (5 + counter.get()) : (10 + counter.get()))
                        .boxed()
                        .map(FeatureStreamWriterTest::toAttributeDTO)
                        .collect(toList());
                GeometryAttributeDTO geometry = new GeometryAttributeDTO();
                geometry.setName("the_geom");
                geometry.setSrid(Integer.valueOf(4326));
                geometry.setValue(counter.get() % 2 == 0 ? "MULTIPOLYGON (((40 40, 20 45, 45 30, 40 40)),((20 35, 45 20, 30 5, 10 10, 10 30, 20 35)), ((0 0, 100 0, 100 100, 0 100, 0 0)))" : "MULTIPOINT ((10 40), (40 30), (20 20), (30 10))");
                values.add(geometry);
                when(request.getAttributes()).thenReturn(values);
                Writer writer = new StringWriter();
                featureStreamWriter.write(request, writer);
                logger.info("#######################\n{}\n, for COUNTER : {}\n", writer.toString(), value);
                doneSignal.countDown();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}