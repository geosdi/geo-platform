package org.geosdi.geoplatform.connector.wfs.services;

import org.geosdi.geoplatform.connector.server.request.v110.query.factory.QueryRestrictionFactory;
import org.geosdi.geoplatform.connector.server.request.v110.query.factory.QueryRestrictionStrategy;
import org.geosdi.geoplatform.gui.shared.wfs.OperatorType;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class QueryRestrictionFactoryMassiveTest {

    private static final Logger logger = LoggerFactory.getLogger(QueryRestrictionFactoryMassiveTest.class);

    @Test
    public void queryRestrictionFactoryTest() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        List<Callable<QueryRestrictionStrategy>> tasks = new ArrayList<>(1000);

        for (int i = 0; i < 1000; i++) {
            if (i < 100) {
                tasks.add(new CallableEntity(OperatorType.CONTAINS));
            } else if ((i > 100) && (i < 200)) {
                tasks.add(new CallableEntity(OperatorType.ENDS_WITH));
            } else if ((i > 200) && (i < 300)) {
                tasks.add(new CallableEntity(OperatorType.EQUAL));
            } else if ((i > 300) && (i < 400)) {
                tasks.add(new CallableEntity(OperatorType.GREATER));
            } else if ((i > 400) && (i < 500)) {
                tasks.add(new CallableEntity(OperatorType.GREATER_OR_EQUAL));
            } else if ((i > 500) && (i < 600)) {
                tasks.add(new CallableEntity(OperatorType.LESS));
            } else if ((i > 600) && (i < 700)) {
                tasks.add(new CallableEntity(OperatorType.LESS_OR_EQUAL));
            } else if ((i > 700) && (i < 800)) {
                tasks.add(new CallableEntity(OperatorType.STARTS_WITH));
            } else if ((i > 800) && (i < 1000)) {
                tasks.add(new CallableEntity(OperatorType.NOT_EQUAL));
            }
        }

        List<Future<QueryRestrictionStrategy>> results = executor.invokeAll(tasks);
        executor.shutdown();

        boolean flag = executor.awaitTermination(1, TimeUnit.MINUTES);

        if (flag) {
            for (Future<QueryRestrictionStrategy> future : results) {
                logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@ {}\n", future.get());
            }
        } else {
            throw new InterruptedException("Some Threads are not executed.");
        }
    }

    class CallableEntity implements Callable<QueryRestrictionStrategy> {

        private final OperatorType operator;

        public CallableEntity(OperatorType theOperator) {
            this.operator = theOperator;
        }

        @Override
        public QueryRestrictionStrategy call() throws Exception {
            return QueryRestrictionFactory.getQueryRestrictionStrategy(operator);
        }
    }
}
