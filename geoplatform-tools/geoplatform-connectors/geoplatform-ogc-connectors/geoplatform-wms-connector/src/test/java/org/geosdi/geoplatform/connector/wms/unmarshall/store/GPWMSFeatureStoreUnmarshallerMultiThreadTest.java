package org.geosdi.geoplatform.connector.wms.unmarshall.store;

import org.geosdi.geoplatform.connector.reader.stax.GPWMSFeatureStore;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.io.File.separator;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature.NON_NULL;
import static org.junit.Assert.assertTrue;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWMSFeatureStoreUnmarshallerMultiThreadTest {

    private static final Logger logger = LoggerFactory.getLogger(GPWMSFeatureStoreUnmarshallerMultiThreadTest.class);
    //
    private static File[] files;

    @BeforeClass
    public static void beforeClass() throws Exception {
        String basePath = of(new File(".").getCanonicalPath(), "src", "test", "resources", "store")
                .collect(joining(separator, "", separator));
        File dirFiles = new File(basePath);
        checkArgument(dirFiles.exists(), "The Directory dirFiles doesn't exist.");
        checkArgument(dirFiles.isDirectory(), "The Parameter dirFiles must be a directory.");
        files = dirFiles.listFiles();
        checkNotNull(files != null, "The Parameter files must not be null.");
    }

    @Test
    public void wmsFeatureStoreReaderUnmarshallerTest() throws Exception {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(files.length);
        AtomicInteger counter = new AtomicInteger(0);
        stream(files)
                .map(v -> new Thread(new WMSFeatureStoreUnmarshallerTask(v, startSignal, doneSignal, counter)))
                .forEach(Thread::start);
        startSignal.countDown();
        doneSignal.await();
        assertTrue(counter.get() == 25);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@{} process {} files", this.getClass().getSimpleName(), counter.get());
    }

    static class WMSFeatureStoreUnmarshallerTask implements Runnable {

        private static final Logger logger = LoggerFactory.getLogger(WMSFeatureStoreUnmarshallerTask.class);
        //
        private static final JacksonSupport JACKSON_SUPPORT = new GPJacksonSupport(UNWRAP_ROOT_VALUE_DISABLE,
                FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
                ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
                WRAP_ROOT_VALUE_DISABLE,
                INDENT_OUTPUT_ENABLE, NON_NULL);
        //
        private final File file;
        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;
        private final AtomicInteger counter;

        /**
         * @param theFile
         * @param theStartSignal
         * @param theDoneSignal
         */
        WMSFeatureStoreUnmarshallerTask(@Nonnull(when = NEVER) File theFile, @Nonnull(when = NEVER) CountDownLatch theStartSignal,
                @Nonnull(when = NEVER) CountDownLatch theDoneSignal, @Nonnull(when = NEVER) AtomicInteger theCounter) {
            checkArgument(theFile != null, "The Parameter file must not be null.");
            checkArgument(theStartSignal != null, "The Parameter startSignal must not be null.");
            checkArgument(theDoneSignal != null, "The Parameter doneSignal must not be null.");
            checkArgument(theCounter != null, "The Parameter counter must not be null.");
            this.file = theFile;
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
                GPWMSFeatureStore wmsFeatureStore = JACKSON_SUPPORT.getDefaultMapper().readValue(file, GPWMSFeatureStore.class);
                logger.info("##########################GP_WMS_FEATURE_STORE_FROM_FILE : {} - Store : {}\n", file.getName(), wmsFeatureStore);
                this.counter.incrementAndGet();
                doneSignal.countDown();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}