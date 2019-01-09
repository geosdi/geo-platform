/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2019 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.stax.reader;

import org.geosdi.geoplatform.stax.reader.demo.GoogleGeocodingStaxReader;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GoogleStaxReaderMultiThreadTest {

    private static final Logger logger = LoggerFactory.getLogger(GoogleStaxReaderMultiThreadTest.class);
    //
    private static File file;
    private static File file1;
    //
    private static final GoogleGeocodingStaxReader googleStaxReader = new GoogleGeocodingStaxReader();

    @BeforeClass
    public static void beforeClass() throws Exception {
        file = new File("src/test/resources/googleGeocodeExample.xml");
        file1 = new File("src/test/resources/googleGeocodeExample1.xml");
    }

    @Test
    public void googleStaxReaderMultiThreadTest() throws Exception {
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                new GoogleStaxReaderTask(i, "GoogleStaxReaderTask", file).start();
            } else {
                new GoogleStaxReaderTask(i, "GoogleStaxReaderTask", file1).start();
            }
        }
        Thread.sleep(500);
    }

    static class GoogleStaxReaderTask extends Thread {

        private final Integer count;
        private final String taskName;
        private final File file;

        public GoogleStaxReaderTask(Integer theCount, String theTaskName, File theFile) {
            this.count = theCount;
            this.taskName = theTaskName;
            this.file = theFile;
        }

        /**
         * If this thread was constructed using a separate
         * <code>Runnable</code> run object, then that
         * <code>Runnable</code> object's <code>run</code> method is called;
         * otherwise, this method does nothing and returns.
         * <p>
         * Subclasses of <code>Thread</code> should override this method.
         *
         * @see #start()
         * @see #stop()
         * @see {@link Thread#Thread(ThreadGroup, Runnable, String)}
         */
        @Override
        public void run() {
            try {
                logger.debug("###########################{} begin execution.\n", "#".concat(this.taskName + " - " + count));

                logger.debug("{} Result from File @@@@@@@@@@@@@@@@@@@@ \n{}\n",
                        "#".concat(this.taskName + " - " + count), googleStaxReader.read(this.file).toString());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
