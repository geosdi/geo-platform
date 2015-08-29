package org.geosdi.geoplatform.wmc.jaxb.v110.comparison;

import org.geosdi.geoplatform.jaxb.GPBaseJAXBContext;
import org.geosdi.geoplatform.wmc.support.v110.jaxb.context.pool.WMCJAXBContextPoolV110;
import org.geosdi.geoplatform.xml.wmc.v110.ViewContextType;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBElement;
import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class AbstractWMCComparisonTest {

    private static final ThreadFactory WMCComparisonThreadFactory = new ThreadFactory() {

        private final AtomicInteger threadID = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = Executors.privilegedThreadFactory().newThread(r);
            thread.setName("WMCComparisonThread - " + threadID.getAndIncrement());
            thread.setDaemon(Boolean.TRUE);
            return thread;
        }
    };
    //
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private static File file;

    @BeforeClass
    public static void loadFile() throws Exception {
        String wmcFileString = new File(".").getCanonicalPath() + File.separator
                + "src/test/resources/WMC-v110.xml";
        file = new File(wmcFileString);
    }

    protected int defineNumThreads() {
        return 150;
    }

    protected long executeMultiThreadsTasks(GPBaseJAXBContext jaxbContext)
            throws Exception {
        long time = 0;

        int numThreads = defineNumThreads();

        ExecutorService executor = Executors.newFixedThreadPool(numThreads,
                WMCComparisonThreadFactory);

        List<Callable<Long>> tasks = new ArrayList<>(
                numThreads);
        for (int i = 0; i < numThreads; i++) {
            if (jaxbContext instanceof WMCJAXBContextPoolV110) {
                tasks.add(new CSWPooledTask(jaxbContext));
            } else {
                tasks.add(new CSWSimpleTask(jaxbContext));
            }
        }

        List<Future<Long>> results = executor.invokeAll(tasks);
        executor.shutdown();

        boolean flag = executor.awaitTermination(1, TimeUnit.MINUTES);

        if (flag) {
            for (Future<Long> future : results) {
                time += future.get();
            }
        } else {
            throw new InterruptedException("Some Threads are not executed.");
        }

        return time;
    }

    private class CSWSimpleTask implements Callable<Long> {

        final GPBaseJAXBContext jaxbContext;

        public CSWSimpleTask(GPBaseJAXBContext theJaxbContext) {
            this.jaxbContext = theJaxbContext;
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();

            ViewContextType viewContextType = ((JAXBElement<ViewContextType>) jaxbContext
                    .acquireUnmarshaller().unmarshal(file)).getValue();
            StringWriter writer = new StringWriter();

            jaxbContext.acquireMarshaller().marshal(viewContextType, writer);
            logger.debug("\n{}\n", writer);

            return System.currentTimeMillis() - start;
        }
    }

    private class CSWPooledTask extends CSWSimpleTask {

        public CSWPooledTask(GPBaseJAXBContext theJaxbContext) {
            super(theJaxbContext);
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();

            ViewContextType viewContextType = (ViewContextType) ((WMCJAXBContextPoolV110) jaxbContext).unmarshal(file);
            StringWriter writer = new StringWriter();
            ((WMCJAXBContextPoolV110) jaxbContext).marshal(viewContextType, writer);
            logger.debug("\n{}\n", writer);

            return System.currentTimeMillis() - start;
        }
    }
}
