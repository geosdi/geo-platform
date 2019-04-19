package org.geosdi.geoplatform.connector.wms.stax.multithread;

import org.geosdi.geoplatform.connector.reader.stax.GPWMSGetFeatureInfoStaxReader;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
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
public class WMSGetFeatureInfoMultiThreadStaxReaderTest {

    private static final Logger logger = LoggerFactory.getLogger(WMSGetFeatureInfoMultiThreadStaxReaderTest.class);
    //
    private static List<String> files;
    private static final JacksonSupport JACKSON_SUPPORT = new GPJacksonSupport(UNWRAP_ROOT_VALUE_DISABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
            WRAP_ROOT_VALUE_DISABLE,
            INDENT_OUTPUT_ENABLE, NON_NULL);
    private static final GPWMSGetFeatureInfoStaxReader wmsGetFeatureInfoStaxReader = new GPWMSGetFeatureInfoStaxReader();

    @BeforeClass
    public static void beforeClass() throws Exception {
        String basePath = of(new File(".").getCanonicalPath(), "src", "test", "resources", "stax")
                .collect(joining(separator, "", separator));
        files = of("geoserver-Vigneti-GetFeatureInfo.xml", "geoserver-GetFeatureInfo.xml", "geoserver-GetFeatureInfo1.xml",
                "geoserver-GetFeatureInfo-Point.xml", "geoserver-GetFeatureInfo-MultiLineString.xml",
                "spearfish-GetFeatureInfo.xml", "tasmaniaRoads-GetFeatureInfo.xml", "tasmaniaStates-GetFeatureInfo.xml",
                "tiger_ny-GetFeatureInfo.xml", "sfdem-GetFeatureInfo.xml", "nurcAPk50095-GetFeatureInfo.xml",
                "nurcArcSample-GetFeatureInfo.xml", "comuni-GetFeatureInfo.xml", "parchiNaturali-GetFeatureInfo.xml",
                "retiRiserve-GetFeatureInfo.xml", "linee-GetFeatureInfo.xml", "azioniPunto-GetFeatureInfo.xml",
                "comuniBasilicata-GetFeatureInfo.xml", "corine-GetFeatureInfo.xml", "airports.xml",
                "geologia.xml", "livelloEdifici.xml", "volumetria.xml", "livelloEdifici1.xml", "masw.xml")
                .map(v -> basePath.concat(v))
                .collect(toCollection(LinkedList::new));
    }

    @Test
    public void wmsGetFeatureInfoMultiThreadStaxReaderTest() throws Exception {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(files.size());
        AtomicInteger counter = new AtomicInteger(0);
        files.stream()
                .map(v -> new Thread(new WMSGetFeatureInfoStaxReaderTask(v, startSignal, doneSignal, counter)))
                .forEach(Thread::start);
        startSignal.countDown();
        doneSignal.await();
        assertTrue(counter.get() == 25);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@{} process {} files", this.getClass().getSimpleName(), counter.get());
    }

    protected static class WMSGetFeatureInfoStaxReaderTask implements Runnable {

        private static final Logger logger = LoggerFactory.getLogger(WMSGetFeatureInfoStaxReaderTask.class);
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
        WMSGetFeatureInfoStaxReaderTask(@Nonnull(when = NEVER) String theFileName, @Nonnull(when = NEVER) CountDownLatch theStartSignal,
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
                        .writeValueAsString(wmsGetFeatureInfoStaxReader.read(new File(fileName))), fileName);
                this.counter.incrementAndGet();
                doneSignal.countDown();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}