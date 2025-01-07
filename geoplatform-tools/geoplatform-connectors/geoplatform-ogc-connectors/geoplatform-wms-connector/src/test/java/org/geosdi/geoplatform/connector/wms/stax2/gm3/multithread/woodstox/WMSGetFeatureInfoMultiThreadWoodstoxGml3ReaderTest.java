/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.wms.stax2.gm3.multithread.woodstox;

import org.geosdi.geoplatform.connector.wms.stax.gml3.multithread.GPWMSGetFeatureMultiThreadGml3Test;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import static com.google.common.base.Preconditions.checkArgument;
import static io.reactivex.rxjava3.core.Flowable.fromIterable;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.wms.stax2.WMSGetFeatureInfoWoodstoxReaderTest.JACKSON_SUPPORT;
import static org.geosdi.geoplatform.connector.wms.stax2.gm3.WMSGetFeatureInfoWoodstoxGml3ReaderTest.wmsGetFeatureInfoWoodstoxGml3Reader;
import static org.junit.Assert.assertTrue;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WMSGetFeatureInfoMultiThreadWoodstoxGml3ReaderTest extends GPWMSGetFeatureMultiThreadGml3Test {

    private static final Logger logger = LoggerFactory.getLogger(WMSGetFeatureInfoMultiThreadWoodstoxGml3ReaderTest.class);

    @Test
    public void wmsGetFeatureInfoMultiThreadAaltoReaderTest() throws Exception {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(files.size());
        AtomicInteger counter = new AtomicInteger(0);
        fromIterable(files)
                .map(f -> new Thread(new WMSGetFeatureInfoWoodstoxGml3ReaderTask(f, startSignal, doneSignal, counter)))
                .subscribe(Thread::start);
        startSignal.countDown();
        doneSignal.await();
        assertTrue(counter.get() == files.size());
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@{} process {} files", this.getClass().getSimpleName(), counter.get());
    }

    static class WMSGetFeatureInfoWoodstoxGml3ReaderTask implements Runnable {

        private static final Logger logger = LoggerFactory.getLogger(WMSGetFeatureInfoWoodstoxGml3ReaderTask.class);
        //
        private final String fileName;
        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;
        private final AtomicInteger counter;

        /**
         * @param theFileName
         * @param theStartSignal
         * @param theDoneSignal
         */
        WMSGetFeatureInfoWoodstoxGml3ReaderTask(@Nonnull(when = NEVER) String theFileName, @Nonnull(when = NEVER) CountDownLatch theStartSignal,
                @Nonnull(when = NEVER) CountDownLatch theDoneSignal, @Nonnull(when = NEVER) AtomicInteger theCounter) {
            checkArgument((theFileName != null) && !(theFileName.trim().isEmpty()), "The Parameter fileName must not be null or an empty string.");
            checkArgument(theStartSignal != null, "The Parameter startSignal must not be null.");
            checkArgument(theDoneSignal != null, "The Parameter doneSignal must not be null.");
            checkArgument(theCounter != null, "The Parameter counter must not be null.");
            this.fileName = theFileName;
            this.startSignal = theStartSignal;
            this.doneSignal = theDoneSignal;
            this.counter = theCounter;
        }

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            try {
                startSignal.await();
                logger.info("#######################FEATURE_COLLECTION : \n{}\n for File : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                        .writeValueAsString(wmsGetFeatureInfoWoodstoxGml3Reader.read(new File(fileName))), fileName);
                this.counter.incrementAndGet();
                doneSignal.countDown();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}