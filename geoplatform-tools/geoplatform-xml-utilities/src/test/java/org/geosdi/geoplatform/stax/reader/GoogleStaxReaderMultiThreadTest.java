package org.geosdi.geoplatform.stax.reader;

import org.geosdi.geoplatform.stax.reader.demo.GoogleGeocodingStaxReader;
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
    private static File file = new File("src/test/resources/googleGeocodeExample.xml");
    private static File file1 = new File("src/test/resources/googleGeocodeExample1.xml");
    //
    private static final GoogleGeocodingStaxReader googleStaxReader = new GoogleGeocodingStaxReader();

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
