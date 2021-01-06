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
package org.geosdi.geoplatform.connector.wfs.services;

import org.geosdi.geoplatform.connector.server.request.v110.query.repository.QueryRestrictionStrategy;
import org.geosdi.geoplatform.gui.shared.wfs.OperatorType;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.geosdi.geoplatform.connector.server.request.v110.query.responsibility.ILogicOperatorHandler.QUERY_RESTRICTION_REPOSITORY;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class QueryRestrictionFactoryMassiveTest {

    private static final Logger logger = LoggerFactory.getLogger(QueryRestrictionFactoryMassiveTest.class);
    //
    private static final ThreadFactory QueryRestrictionThreadFactory = new ThreadFactory() {

        private final AtomicInteger threadID = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = Executors.privilegedThreadFactory().newThread(r);
            thread.setName("QueryRestrictionThread - " + threadID.getAndIncrement());
            thread.setDaemon(Boolean.TRUE);
            return thread;
        }

    };

    @Test
    public void queryRestrictionFactoryTest() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(150, QueryRestrictionThreadFactory);

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
            } else if ((i > 800) && (i < 900)) {
                tasks.add(new CallableEntity(OperatorType.NOT_EQUAL));
            } else if((i > 900) && (i < 1000)) {
                tasks.add(new CallableEntity(OperatorType.LIKE));
            }
        }

        List<Future<QueryRestrictionStrategy>> results = executor.invokeAll(tasks);
        executor.shutdown();

        boolean flag = executor.awaitTermination(1, TimeUnit.MINUTES);

        if (flag) {
            for (Future<QueryRestrictionStrategy> future : results) {
                logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@ {}\n", future.get());
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
            return QUERY_RESTRICTION_REPOSITORY.getQueryRestrictionStrategy(operator);
        }
    }
}