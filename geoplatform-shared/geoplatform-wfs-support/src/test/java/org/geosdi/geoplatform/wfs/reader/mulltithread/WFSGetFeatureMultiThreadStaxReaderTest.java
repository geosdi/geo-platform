package org.geosdi.geoplatform.wfs.reader.mulltithread;

import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.geosdi.geoplatform.support.wfs.feature.reader.geojson.GPWFSGetFeatureGeoJsonStaxReader;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import static com.google.common.base.Preconditions.checkArgument;
import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Stream.of;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature.NON_NULL;
import static org.junit.Assert.assertTrue;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WFSGetFeatureMultiThreadStaxReaderTest {

    private static List<String> files;
    private static String basePath;
    private static final GPWFSGetFeatureGeoJsonStaxReader geoJsonStaxReader = new GPWFSGetFeatureGeoJsonStaxReader();
    private static final JacksonSupport JACKSON_SUPPORT = new GPJacksonSupport(UNWRAP_ROOT_VALUE_DISABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
            WRAP_ROOT_VALUE_DISABLE,
            INDENT_OUTPUT_ENABLE, NON_NULL);

    @BeforeClass
    public static void beforeClass() throws Exception {
        basePath = of(new File(".").getCanonicalPath(), "src", "test", "resources", "reader")
                .collect(joining(separator, "", separator));
        files = of("GetFeaturePeUins.xml", "GetFeatureSFRestricted.xml", "GetFeatureSiteTR.xml",
                "GetFeatureToppStates.xml", "GetFeatureToppTasmaniaRoads.xml")
                .collect(toCollection(LinkedList::new));
    }

    @Test
    public void wfsGetFeatureMultiThreadStaxReaderTest() throws Exception {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(files.size());
        AtomicInteger counter = new AtomicInteger(0);
        files.stream()
                .map(v -> new Thread(new WFSGetFeatureIStaxReaderTask(v, startSignal, doneSignal, counter)))
                .forEach(Thread::start);
        startSignal.countDown();
        doneSignal.await();
        assertTrue(counter.get() == 5);
    }

    protected static class WFSGetFeatureIStaxReaderTask implements Runnable {

        private static final Logger logger = LoggerFactory.getLogger(WFSGetFeatureIStaxReaderTask.class);
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
        public WFSGetFeatureIStaxReaderTask(@Nonnull(when = NEVER) String theFileName, @Nonnull(when = NEVER) CountDownLatch theStartSignal,
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
                String basePathFile = of(new File(".").getCanonicalPath(), "target")
                        .collect(joining(separator, "", separator));
                JACKSON_SUPPORT.getDefaultMapper()
                        .writeValue(new File(basePathFile.concat(fileName.replace(".xml", ".json"))), geoJsonStaxReader.read(new File(basePath.concat(fileName))));
                logger.info("#######################WRITE_FEATURE_COLLECTION for File : {}\n", fileName);
                this.counter.incrementAndGet();
                doneSignal.countDown();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}