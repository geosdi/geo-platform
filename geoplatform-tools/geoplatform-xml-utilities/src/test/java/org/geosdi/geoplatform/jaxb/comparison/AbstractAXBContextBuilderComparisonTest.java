package org.geosdi.geoplatform.jaxb.comparison;

import org.geosdi.geoplatform.jaxb.comparison.task.function.GPJAXBContextBuilderTaskFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class AbstractAXBContextBuilderComparisonTest {

    public enum GPJAXBContextBuilderTaskType {

        SIMPLE,
        POOLED;
    }

    private static final ThreadFactory GPJAXBContextBuilderComparisonThreadFactory = new ThreadFactory() {

        private final AtomicInteger threadID = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = Executors.privilegedThreadFactory().newThread(r);
            thread.setName("GPJAXBContextBuilderComparisonThread - " + threadID.getAndIncrement());
            thread.setDaemon(Boolean.TRUE);
            return thread;
        }

    };
    //
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected int defineNumThreads() {
        return 150;
    }

    long executeMultiThreadsTasks(GPJAXBContextBuilderTaskType taskType) throws Exception {
        long time = 0;

        int numThreads = defineNumThreads();
        ExecutorService executor = Executors.newFixedThreadPool(numThreads,
                GPJAXBContextBuilderComparisonThreadFactory);

        List<Callable<Long>> tasks = IntStream.iterate(0, n -> n + 1)
                .limit(numThreads)
                .boxed()
                .map(new GPJAXBContextBuilderTaskFunction(taskType))
                .collect(Collectors.toList());

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
}
